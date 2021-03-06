package com.soft1921.maven.study;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


/**
 * @author Flobby
 * @version :1.0
 * @date :2020/9/24
 * @ClassName : 爬取HTML代码
 */

public class SpiderTestHttpClient1 {
    public static void main(String[] args) throws Exception {
        //打开浏览器，创建一个httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //输入网址,发起get请求，创建httpGet对象
        HttpGet httpGet = new HttpGet("https://movie.douban.com/chart");

        //伪装成浏览器
        httpGet.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        httpGet.addHeader("Accept-Charset", "utf-8");
        httpGet.addHeader("Accept-Language", "zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7");
        httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.102 Safari/537.36");

        //发起请求，返回响应，使用HTTPClient对象发起请求
        CloseableHttpResponse response = httpClient.execute(httpGet);
        //输出请求状态
        System.out.println(response.getStatusLine());
        System.out.println();

        //解析响应，获取数据
          //判断状态码是否成功
        if (response.getStatusLine().getStatusCode() == 200){
            HttpEntity httpEntity = response.getEntity();
            String content = EntityUtils.toString(httpEntity,"UTF-8");

            //输出网页HTML代码
            System.out.println(content);
        }else {
            System.out.println("wrong");
        }
    }
}
