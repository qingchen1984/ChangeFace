package com.changeface.swb.changeface.entity;

import java.util.List;

/**
 * Description:
 * Author: SheWenBiao
 * Date: 2015-05-17
 * Time: 16:14
 */
public class HomePageList {
    private List<HomePage> items;

    public HomePageList() {
    }

    public HomePageList(List<HomePage> items) {
        this.items = items;
    }

    public List<HomePage> getItems() {
        return items;
    }

    public void setItems(List<HomePage> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "HomePageList{" +
                "items=" + items +
                '}';
    }
}
