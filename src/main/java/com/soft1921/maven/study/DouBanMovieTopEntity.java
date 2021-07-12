package com.soft1921.maven.study;

/**
 * @author Flobby
 * @version :1.0
 * @date :2020/9/25
 * @ClassName :豆瓣电影top250实体类
 */

public class DouBanMovieTopEntity {
    private String title;
    private String pictureUrl;
    private String description;
    private String message;
    private String MovieUrl;
    private String mark;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMovieUrl() {
        return MovieUrl;
    }

    public void setMovieUrl(String movieUrl) {
        MovieUrl = movieUrl;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
