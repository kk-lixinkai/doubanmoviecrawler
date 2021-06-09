package com.mybestcoding.domain;

/**
 * @author: lixinkai
 * @description: 电影类
 * @date: 2021/6/6 15:43
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
public class MovieProfile {
    private String episodes_info;
    private String rate;
    private Integer cover_x;
    private String title;
    private String url;
    private boolean playable;
    private String cover;
    private String id;
    private Integer cover_y;
    private boolean is_new;

    public MovieProfile() {
    }

    public MovieProfile(String episodes_info, String rate, Integer cover_x, String title, String url, boolean playable, String cover, String id, Integer cover_y, boolean is_new) {
        this.episodes_info = episodes_info;
        this.rate = rate;
        this.cover_x = cover_x;
        this.title = title;
        this.url = url;
        this.playable = playable;
        this.cover = cover;
        this.id = id;
        this.cover_y = cover_y;
        this.is_new = is_new;
    }

    public String getEpisodes_info() {
        return episodes_info;
    }

    public void setEpisodes_info(String episodes_info) {
        this.episodes_info = episodes_info;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Integer getCover_x() {
        return cover_x;
    }

    public void setCover_x(Integer cover_x) {
        this.cover_x = cover_x;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isPlayable() {
        return playable;
    }

    public void setPlayable(boolean playable) {
        this.playable = playable;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCover_y() {
        return cover_y;
    }

    public void setCover_y(Integer cover_y) {
        this.cover_y = cover_y;
    }

    public boolean isIs_new() {
        return is_new;
    }

    public void setIs_new(boolean is_new) {
        this.is_new = is_new;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "episodes_info='" + episodes_info + '\'' +
                ", rate='" + rate + '\'' +
                ", cover_x=" + cover_x +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", playable=" + playable +
                ", cover='" + cover + '\'' +
                ", id='" + id + '\'' +
                ", cover_y=" + cover_y +
                ", is_new=" + is_new +
                "}\n\t";
    }
}
