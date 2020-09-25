package com.soft1921.spider.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author Flobby
 * @version :1.0
 * @date :2020/9/25
 * @ClassName :爬取豆瓣电影榜单
 */

public class SpiderDouBanTopTest {
    public static int add(DouBanMovieTopEntity douBanMovieTopEntity) {
        int code = 0;
        String sql = "insert into movietop(title,pictureUrl,description,message,movieUrl,mark) values (?,?,?,?,?,?)";
        code = DBUtils.update(sql, douBanMovieTopEntity.getTitle(),douBanMovieTopEntity.getPictureUrl(),douBanMovieTopEntity.getDescription(),  douBanMovieTopEntity.getMessage(), douBanMovieTopEntity.getMovieUrl(), douBanMovieTopEntity.getMark());
        return code;
    }

    public static void main(String[] args) throws Exception {
        DBUtils.getConnection();
        String content = null;

        int i = 0;
        while (i <= 10) {

            String WebUrl = "https://movie.douban.com/top250?start=" + (i * 25) + "&filter=";

            CloseableHttpClient httpClient = HttpClients.createDefault();

            HttpGet httpGet = new HttpGet(WebUrl);

            httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.102 Safari/537.36");

            CloseableHttpResponse response = httpClient.execute(httpGet);
            System.out.println(response.getStatusLine());
            System.out.println();

            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity httpEntity = response.getEntity();
                content = EntityUtils.toString(httpEntity, "UTF-8");
            } else {
                System.out.println("wrong");
            }

            String html = content;

            System.out.println();

            Document doc = Jsoup.parse(html);

            Elements elements = doc.select("div[class=item]");
            for (Element element : elements) {
                DouBanMovieTopEntity entity = new DouBanMovieTopEntity();

                String title = element.select("div[class=hd]").select("span[class=title]").first().text();
                String pictureUrl = element.select("div[class=pic]").select("img").attr("src");
                String description = element.select("div[class=bd]").select("p[class=quote]").text();
                String message = element.select("div[class=bd]").select("p").first().text();
                String movieUrl = element.select("div[class=hd]").select("a").attr("href");
                String mark = element.select("div[class=bd]").select("div[class=star]").select("span[class=rating_num]").text();

                entity.setTitle(title);
                entity.setPictureUrl(pictureUrl);
                entity.setDescription(description);
                entity.setMessage(message);
                entity.setMovieUrl(movieUrl);
                entity.setMark(mark);

                SpiderDouBanTopTest.add(entity);
            }
            i++;
        }
    }
}
