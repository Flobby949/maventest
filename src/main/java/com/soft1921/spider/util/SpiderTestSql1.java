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
 * @date :2020/9/24
 * @ClassName :连接数据库(JDBC)
 */

public class SpiderTestSql1 {
    public static int add(Entity1 entity1){
        int code = 0;
        String sql = "insert into movie(title,url,message,mark) values (?,?,?,?)";
        code = DBUtils.update(sql,entity1.getTitle(),entity1.getUrl(),entity1.getMessage(),entity1.getMark());
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

        Elements elements = doc.select("div[class=pl2]");
        for (Element element : elements) {
            Entity1 entity = new Entity1();


            String title = element.select("a").text();
            String url = element.select("a").attr("href");
            String message = element.select("p[class=pl]").text();
            String mark = element.select("span[class=rating_nums]").text();

            entity.setTitle(title);
            entity.setUrl(url);
            entity.setMessage(message);
            entity.setMark(mark);

            SpiderTestSql1.add(entity);

        }
    }

}
