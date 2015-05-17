package com.changeface.swb.changeface.entity;

/**
 * Description:
 * Author: SheWenBiao
 * Date: 2015-05-11
 * Time: 23:05
 */
public class ChangeFaceHome {
    private String _id;
    private String title;
    private String url;
    private String img;
    private String time;
    private String skip;
    private int _v;
    private String date;

    public ChangeFaceHome() {
    }

    public ChangeFaceHome(String _id, String title, String url, String img, String time, String skip, int _v, String date) {
        this._id = _id;
        this.title = title;
        this.url = url;
        this.img = img;
        this.time = time;
        this.skip = skip;
        this._v = _v;
        this.date = date;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSkip() {
        return skip;
    }

    public void setSkip(String skip) {
        this.skip = skip;
    }

    public int get_v() {
        return _v;
    }

    public void set_v(int _v) {
        this._v = _v;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "HomePage{" +
                "_id='" + _id + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", img='" + img + '\'' +
                ", time='" + time + '\'' +
                ", skip='" + skip + '\'' +
                ", _v=" + _v +
                ", date='" + date + '\'' +
                '}';
    }
}
