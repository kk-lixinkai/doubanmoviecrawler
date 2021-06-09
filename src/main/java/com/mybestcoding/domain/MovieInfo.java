package com.mybestcoding.domain;

/**
 * @author: lixinkai
 * @description: 电影信息
 * @date: 2021/6/7 17:37
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
public class MovieInfo {
    /**
     * 电影名
     */
    private String title;
    /**
     * 导员
     */
    private String director;
    /**
     * 编剧
     */
    private String writers;
    /**
     * 演员
     */
    private String actor;

    /**
     * 类型
     */
    private String type;
    /**
     * 评分
     */
    private String score;
    /**
     * 点评人数
     */
    private String number;

    public MovieInfo() {
    }

    public MovieInfo(String title, String director, String writers, String actor, String type, String score, String number) {
        this.title = title;
        this.director = director;
        this.writers = writers;
        this.actor = actor;
        this.type = type;
        this.score = score;
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriters() {
        return writers;
    }

    public void setWriters(String writers) {
        this.writers = writers;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
