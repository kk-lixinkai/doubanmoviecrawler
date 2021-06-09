package com.mybestcoding;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mybestcoding.domain.MovieInfo;
import com.mybestcoding.domain.MovieInfoRepository;
import com.mybestcoding.domain.MovieProfile;
import com.mybestcoding.domain.MovieProfileRepository;
import com.mybestcoding.jdbc.DataConnection;
import okhttp3.*;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {


    public static void main(String[] args) throws IOException, InterruptedException, SQLException {

        // 获取概要信息
        // catchMovieProfiles();

        // 获取数据库保存的概要信息
        List<String> urls = MovieProfileRepository.selectAll();
        List<MovieInfo> movieInfos = new ArrayList<>();
        // 遍历所有的链接
        for (String url : urls) {
            // 爬取电影详细信息
            MovieInfo movieInfo = catchMovieInfo(url);
            movieInfos.add(movieInfo);

        }
        // 将电影的详细信息保存到数据库
        saveMovieInfo(movieInfos);
    }

    private static void saveMovieInfo(List<MovieInfo> movieInfos) {
        try {
            MovieInfoRepository.insert(movieInfos);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static MovieInfo catchMovieInfo(String url) throws IOException, InterruptedException {
        MovieInfo movieInfo = new MovieInfo();
        Document doc = Jsoup.connect(url).get();
        // 获取电影标题
        String title = doc.select("h1 span").text();
        System.out.println("标题:" + title);
        movieInfo.setTitle(title);
        // 获取电影信息
        //导演和导演
        Elements elements = doc.select("div#info span span.attrs");
        if (elements.size() == 1) {
            System.out.println(elements.size());
            String direct = elements.get(0).text();
            System.out.println("导演:" + direct);
            movieInfo.setDirector(direct);
            movieInfo.setWriters("");
            movieInfo.setActor("");
        } else if (elements.size() == 2) {
            System.out.println(elements.size());
            String director = elements.get(0).text();
            String actor = elements.get(1).text();
            System.out.println("导演:" + director);
            System.out.println("主演:" + actor);
            movieInfo.setDirector(director);
            movieInfo.setActor(actor);
            movieInfo.setWriters("");
        } else if (elements.size() == 3) {
            System.out.println(elements.size());
            String director = elements.get(0).text();
            String writers = elements.get(1).text();
            String actor = elements.get(2).text();
            System.out.println("导演:" + director);
            System.out.println("编剧:" + writers);
            System.out.println("演员:" + actor);
            movieInfo.setDirector(director);
            movieInfo.setWriters(writers);
            movieInfo.setActor(actor);
        }

        // 电影类型
        Elements elements1 = doc.select("div#info span[property=v:genre]");
        StringBuilder sb = new StringBuilder();
        for (Element element : elements1) {
            sb.append(element.text()).append("/");
        }
        System.out.println("类型:" + sb.toString());
        movieInfo.setType(sb.toString());

        // 电影评分
        String score = doc.select("div#interest_sectl strong").first().text();
        System.out.println("分数:" + score);
        movieInfo.setScore(score);

        // 评分人数
        String number = doc.select("div.rating_sum a span").first().text();
        System.out.println("评分人数:" + number);
        movieInfo.setNumber(number);
        Thread.sleep(4000);

        return movieInfo;
    }

    /**
     * 抓取电影概要信息
     *
     * @throws IOException
     * @throws SQLException
     * @throws InterruptedException
     */
    private static void catchMovieProfiles() throws IOException, SQLException, InterruptedException {
        // 构建爬虫连接
        int page_start = 0;
        String baseUrl = "https://movie.douban.com/j/search_subjects?type=movie&tag=%E6%81%90%E6%80%96&sort=recommend&page_limit=20&page_start=";
        List<MovieProfile> movieProfiles = new ArrayList<>();
        // 响应信息
        String body = null;
        // 创建连接器
        OkHttpClient httpClient = new OkHttpClient();

        do {

            // 清空上一页数据
            body = null;

            String url = baseUrl + page_start;

            System.out.println(url);
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            // 请求数据
            Response response = httpClient.newCall(request).execute();
            body = response.body().string();

            // 解析JSON数组
            parseObject(movieProfiles, body);

            // 获取下一页数据
            page_start += 20;

            Thread.sleep(5000);
        } while (body.length() > 15);

        MovieProfileRepository.insert(movieProfiles);
        System.out.println(movieProfiles);
    }

    /**
     * 解析JSON数据
     *
     * @param movieProfiles
     * @param body
     */
    private static void parseObject(List<MovieProfile> movieProfiles, String body) throws SQLException {
        // 解析JSON数组
        JSONObject object = JSON.parseObject(body);
        JSONArray array = object.getJSONArray("subjects");

        for (int i = 0; i < array.size(); i++) {
            JSONObject o = array.getJSONObject(i);
            String episodes_info = o.getString("episodes_info");
            String rate = o.getString("rate");
            Integer cover_x = o.getInteger("cover_x");
            String title = o.getString("title");
            String urlM = o.getString("url");
            Boolean playable = o.getBoolean("playable");
            String cover = o.getString("cover");
            String id = o.getString("id");
            Integer cover_y = o.getInteger("cover_y");
            Boolean is_new = o.getBoolean("is_new");
            movieProfiles.add(new MovieProfile(episodes_info, rate, cover_x, title, urlM, playable, cover, id, cover_y, is_new));
        }

    }
}
