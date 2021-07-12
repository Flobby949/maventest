package com.soft1921.maven.study;

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
 * @date :2020/9/24
 * @ClassName :连接数据库(JDBC)
 */

public class SpiderTestSql1 {
    public static int add(DouBanMovieEntity1 douBanMovieEntity1){
        int code = 0;
        String sql = "insert into movie(title,picture,description,url,message,mark) values (?,?,?,?,?,?)";
        code = DBUtils.update(sql, douBanMovieEntity1.getTitle(), douBanMovieEntity1.getPicture(), douBanMovieEntity1.getDescription(), douBanMovieEntity1.getUrl(), douBanMovieEntity1.getMessage(), douBanMovieEntity1.getMark());
        return code;
    }
    public static void main(String[] args) throws Exception {
        DBUtils.getConnection();
        String content = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet("https://movie.douban.com/chart");

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
        System.out.println(content);
        System.out.println();

        Document doc = Jsoup.parse(html);

        Elements elements = doc.select("tr[class=item]");
        for (Element element : elements) {
            DouBanMovieEntity1 entity = new DouBanMovieEntity1();


            String title = element.select("a").attr("title");
            String picture = element.select("img").attr("src");
            String description = element.select("div[class=pl2]").select("a").select("span").text();
            String url = element.select("div[class=pl2]").select("a").attr("href");
            String message = element.select("div[class=pl2]").select("p[class=pl]").text();
            String mark = element.select("div [class=star clearfix]").select("span[class=rating_nums]").text();


            entity.setTitle(title);
            entity.setPicture(picture);
            entity.setDescription(description);
            entity.setUrl(url);
            entity.setMessage(message);
            entity.setMark(mark);

            SpiderTestSql1.add(entity);

        }
    }

}
