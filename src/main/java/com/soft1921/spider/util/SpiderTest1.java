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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Flobby
 * @version :1.0
 * @date :2020/9/24
 * @ClassName :整合测试  爬取数据放入txt
 */

public class SpiderTest1 {
    public static void main(String[] args) throws Exception{
        String content = null;
            BufferedWriter out = new BufferedWriter(new FileWriter("E:\\Case\\myDir"));
            CloseableHttpClient httpClient = HttpClients.createDefault();

            HttpGet httpGet = new HttpGet("https://movie.douban.com/chart");

            httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.102 Safari/537.36");

            CloseableHttpResponse response = httpClient.execute(httpGet);
            System.out.println(response.getStatusLine());
            System.out.println();

            if (response.getStatusLine().getStatusCode() == 200){
                HttpEntity httpEntity = response.getEntity();
                content = EntityUtils.toString(httpEntity,"UTF-8");
            }else {
                System.out.println("wrong");
            }

            String html = content;
            System.out.println(content);
            System.out.println();

            Document doc = Jsoup.parse(html);

            Elements elements = doc.select("div[class=pl2]");
            for( Element element : elements ){

                String title = element.select("a").text();
                String url = element.select("a").attr("href");
                String message = element.select("p[class=pl]").text();
                String mark = element.select("span[class=rating_nums]").text();

                System.out.println("title: "+title);
                System.out.println("url: "+url);
                System.out.println("message: "+message);
                System.out.println("mark: "+mark);

            out.write("title: "+title);
            out.write("\nurl: "+url);
            out.write("\nmessage: "+message);
            out.write("\nmark: "+mark);
            out.flush();
            out.newLine();
        }
    }
}
