package com.changeface.swb.changeface.entity;

import java.util.Arrays;

/**
 * Description:
 * Author: SheWenBiao
 * Date: 2015-05-17
 * Time: 15:16
 */
public class HomePage {
    private long id;
    private String url;
    private String hashKey;
    private String title;
    private double price;
    private String category;
    private long cid;
    private int[] images;
    private int shopRank;
    private int salesData;
    private String seller;
    private String location;
    private String _id;
    private MainImage mainImage;
    private String pageUrl;
    private String shelveTime;

    public HomePage() {
    }

    public HomePage(long id, String url, String hashKey, String title, Double price, String category, long cid, int[] images, int shopRank, int salesData, String seller, String location, String _id, MainImage mainImage, String pageUrl, String shelveTime) {
        this.id = id;
        this.url = url;
        this.hashKey = hashKey;
        this.title = title;
        this.price = price;
        this.category = category;
        this.cid = cid;
        this.images = images;
        this.shopRank = shopRank;
        this.salesData = salesData;
        this.seller = seller;
        this.location = location;
        this._id = _id;
        this.mainImage = mainImage;
        this.pageUrl = pageUrl;
        this.shelveTime = shelveTime;
    }

    public long getId() {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int[] getImages() {
        return images;
    }

    public void setImages(int[] images) {
        this.images = images;
    }

    public int getShopRank() {
        return shopRank;
    }

    public void setShopRank(int shopRank) {
        this.shopRank = shopRank;
    }

    public int getSalesData() {
        return salesData;
    }

    public void setSalesData(int salesData) {
        this.salesData = salesData;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public MainImage getMainImage() {
        return mainImage;
    }

    public void setMainImage(MainImage mainImage) {
        this.mainImage = mainImage;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getShelveTime() {
        return shelveTime;
    }

    public void setShelveTime(String shelveTime) {
        this.shelveTime = shelveTime;
    }

    @Override
    public String toString() {
        return "HomePage{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", hashKey='" + hashKey + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", cid=" + cid +
                ", images=" + Arrays.toString(images) +
                ", shopRank=" + shopRank +
                ", salesData=" + salesData +
                ", seller='" + seller + '\'' +
                ", location='" + location + '\'' +
                ", _id='" + _id + '\'' +
                ", mainImage=" + mainImage +
                ", pageUrl='" + pageUrl + '\'' +
                ", shelveTime='" + shelveTime + '\'' +
                '}';
    }
}
