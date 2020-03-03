package cn.edu.seu.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpClientDemo {
    private static Logger logger = LoggerFactory.getLogger(HttpClientDemo.class);
    private static final String[] USER_AGENTS = new String[]{
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36 Edge/18.18362", // Edge浏览器 版本44.18362.1.0
            "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko", // IE浏览器 版本11.175.18362.0
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:68.0) Gecko/20100101 Firefox/68.0", // 火狐浏览器 x64 版本68.0
            "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:68.0) Gecko/20100101 Firefox/68.0", // 火狐浏览器 x86 版本68.0
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36", // Google浏览器 x64 版本75.0.3770.100
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36", // Google浏览器 x86 版本72.0.3626.121
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36", // Google浏览器 x86 版本75.0.3770.100
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36", // 2345加速浏览器 版本9.9.0.19250
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3947.100 Safari/537.36", // 2345加速浏览器 版本10.0.0.19291
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3947.100 Safari/537.36 2345Explorer/10.0.0.19291", // 2345加速浏览器 版本10.0.0.19291
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3708.400 QQBrowser/10.4.3620.400", // QQ浏览器 版本10.4.2(3620)
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.26 Safari/537.36 Core/1.63.6823.400 QQBrowser/10.3.3117.400", // QQ浏览器 版本10.3.2(3117)
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3704.400 QQBrowser/10.4.3587.400", // QQ浏览器 版本10.4.2(3587)
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36", // 360浏览器 版本10.0.1920.0
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36 QIHU 360SE", // 360浏览器 版本10.0.1920.0 无痕
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36 QIHU 360EE", // 360极速浏览器 版本9.5.0.138
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36", // 360极速浏览器 版本11.0.2140.0
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 UBrowser/6.2.4098.3 Safari/537.36", // UC浏览器 版本6.2.4098.3
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36 SE 2.X MetaSr 1.0", // 搜狗高速浏览器 版本8.5.10.30358
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36", // 小智双核浏览器 版本2.0.1.12
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36 LBBROWSER", // 猎豹安全浏览器 版本6.5.115.19331.8001
            "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0)", // IE浏览器 x86 版本8.0.7601.17514
            "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Win64; x64; Trident/4.0; .NET CLR 2.0.50727; SLCC2; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0)", // IE浏览器 x64 版本8.0.7601.17514
            "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)", // IE浏览器 x86 版本9.0.8112.16421
            "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0)", // IE浏览器 x64 版本9.0.8112.16421
            "Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko", // IE浏览器 版本11.0.9600.17843
            "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:68.0) Gecko/20100101 Firefox/68.0", // 火狐浏览器 x64 版本68.0
            "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:68.0) Gecko/20100101 Firefox/68.0", // 火狐浏览器 x86 版本68.0
            "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36", // Google浏览器 x64 版本75.0.3770.100
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36", // Google浏览器 x86 版本72.0.3626.121
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36", // Google浏览器 x86 版本75.0.3770.100
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.90 Safari/537.36 2345Explorer/9.7.0.18838", // 2345加速浏览器 版本9.7.0.18838
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3947.100 Safari/537.36", // 2345加速浏览器 版本10.0.0.19291
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3708.400 QQBrowser/10.4.3620.400", // QQ浏览器 版本10.4.2(3620)
            "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36", // QQ浏览器 版本10.4.2(3620)
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36", // 360浏览器 版本10.0.1840.0
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 UBrowser/6.2.4098.3 Safari/537.36", // UC浏览器 版本6.2.4098.3
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36 SE 2.X MetaSr 1.0", // 搜狗高速浏览器 版本8.5.10.30358
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36", // 小智双核浏览器 版本2.0.1.12
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36 LBBROWSER", // 猎豹安全浏览器 版本6.5.115.19331.8001
            "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.80 Safari/537.36 OPR/62.0.3331.18", // Opera浏览器 版本62.0.3331.18
            "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36 OPR/62.0.3331.72", // Opera浏览器 版本62.0.3331.72
    };

    @Test
    public void collectData() {
        for (int i = 0; i <= 225; i += 25) {
            parseHtml(getHtml("https://movie.douban.com/top250?start=" + i + "&filter="));
        }


    }

    private String getHtml(String req_url) {
        // 1. 创建httpclient对象，相当于一个浏览器
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 2. 创建http请求对象, httpGet对象, 用来封装http请求数据
        HttpGet httpGet = new HttpGet(req_url);
        httpGet.setHeader("User-Agent", USER_AGENTS[new Random().nextInt(41)]);
        // 3. response 用于接收服务器响应，响应状态行/响应体
        CloseableHttpResponse response = null;
        // 4. 执行GET请求，返回response响应
        try {
            Thread.sleep(new Random().nextInt(3000) + 2000);
        } catch (Exception e) {
            System.exit(0);//退出程序
        }
        try {
            response = httpClient.execute(httpGet);
            // 5. 获取响应状态行信息
            StatusLine statusLine = response.getStatusLine();
            if (statusLine.getStatusCode() != 200) {
                return "";
            }
            // 6. 获取响应体中的数据
            HttpEntity httpEntity = response.getEntity();
            // 7. 转换格式
            return EntityUtils.toString(httpEntity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    private void parseHtml(String content) {
        // 1. 封装jsoup中的Document对象
        Document document = Jsoup.parse(content);
        // 2. 使用jsoup doucument api解析文档
        Elements elements = document.select("div.item");
        for (Element item : elements) {
            String movieName = item.select("div.hd > a > span").first().html();
            String score = item.select("div.star > span.rating_num").html();
            String number = item.select("div.star > span").last().html();

            Pattern pattern = Pattern.compile("\\d*");
            Matcher matcher = pattern.matcher(number);
            number = matcher.find() ? matcher.group() : "0";

            String someInfo = movieName +
                    "\t" + score +
                    "\t" + number;

            parseMovieHtml(someInfo, getHtml(item.select("div.hd > a").attr("href")));

        }
    }

    private void parseMovieHtml(String someInfo, String content) {
        // 1. 封装jsoup中的Document对象
        Document document = Jsoup.parse(content);
        // 2. 使用jsoup doucument api解析文档
        Element allInfo = document.select("div#info").first();

        Elements allSpan = allInfo.select("span");
        StringBuilder writers = new StringBuilder();
        for (Element item : allSpan) {
            if (item.text().contains("编剧: ")) {
                Elements allWriters = item.select("a");
                for (Element item2 : allWriters) {
                    writers.append(item2.text()).append(" ");
                }
                break;
            }
        }

        Elements allDate = allInfo.select("[property=v:initialReleaseDate]");
        StringBuilder releaseDate = new StringBuilder();
        for (Element item : allDate) {
            releaseDate.append(item.html()).append(" ");
        }

        Elements allRuntime = allInfo.select("[property=v:runtime]");
        StringBuilder runTime = new StringBuilder();
        for (Element item : allRuntime) {
            Pattern pattern = Pattern.compile("\\d*");
            Matcher matcher = pattern.matcher(item.html());
            runTime.append(matcher.find() ? matcher.group() : "0").append(" ");
        }

        Elements allTypes = allInfo.select("[property=v:genre]");
        StringBuilder types = new StringBuilder();
        for (Element item : allTypes) {
            types.append(item.html()).append(" ");
        }

        Elements allDirectors = allInfo.select("[rel=v:directedBy]");
        StringBuilder directors = new StringBuilder();
        for (Element item : allDirectors) {
            directors.append(item.html()).append(" ");
        }

        Elements allActors = allInfo.select("[rel=v:starring]");
        StringBuilder actors = new StringBuilder();
        for (Element item : allActors) {
            actors.append(item.html()).append(" ");
        }

        Matcher m1 = Pattern.compile("<span class=\"pl\">又名:</span> (.*)\n" + "<br>").matcher(allInfo.html());
        String alternateNames = m1.find() ? m1.group(1) : "无";

        Matcher m2 = Pattern.compile("<span class=\"pl\">语言:</span> (.*)\n" + "<br>").matcher(allInfo.html());
        String languages = m2.find() ? m2.group(1) : "无";

        Matcher m3 = Pattern.compile("<span class=\"pl\">制片国家/地区:</span> (.*)\n" + "<br>").matcher(allInfo.html());
        String countries = m3.find() ? m3.group(1) : "无";

        logger.info(someInfo + "\t" + directors + "\t" + writers + "\t" + actors + "\t" + types + "\t" + countries + "\t" + languages + "\t" + releaseDate + "\t" + runTime + "\t" + alternateNames);
    }


    public static void main(String[] args) {
        HttpClientDemo demo = new HttpClientDemo();
        demo.collectData();
    }
}
