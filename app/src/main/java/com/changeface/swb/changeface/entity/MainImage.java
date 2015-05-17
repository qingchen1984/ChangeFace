package com.changeface.swb.changeface.entity;

/**
 * Description:
 * Author: SheWenBiao
 * Date: 2015-05-17
 * Time: 15:23
 */
public class MainImage {
    private int id;
    private String url;
    private String hashKey;
    private String thumbUrl;
    private String bigUrl;
    private String smallUrl;
    private String largeUrl;

    public MainImage() {
    }

    public MainImage(int id, String url, String hashKey, String thumbUrl, String bigUrl, String smallUrl, String largeUrl) {
        this.id = id;
        this.url = url;
        this.hashKey = hashKey;
        this.thumbUrl = thumbUrl;
        this.bigUrl = bigUrl;
        this.smallUrl = smallUrl;
        this.largeUrl = largeUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHashKey() {
        return hashKey;
    }

    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getBigUrl() {
        return bigUrl;
    }

    public void setBigUrl(String bigUrl) {
        this.bigUrl = bigUrl;
    }

    public String getSmallUrl() {
        return smallUrl;
    }

    public void setSmallUrl(String smallUrl) {
        this.smallUrl = smallUrl;
    }

    public String getLargeUrl() {
        return largeUrl;
    }

    public void setLargeUrl(String largeUrl) {
        this.largeUrl = largeUrl;
    }

    @Override
    public String toString() {
        return "MainImage{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", hashKey='" + hashKey + '\'' +
                ", thumbUrl='" + thumbUrl + '\'' +
                ", bigUrl='" + bigUrl + '\'' +
                ", smallUrl='" + smallUrl + '\'' +
                ", largeUrl='" + largeUrl + '\'' +
                '}';
    }
}
