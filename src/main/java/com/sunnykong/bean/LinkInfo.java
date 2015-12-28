package com.sunnykong.bean;

/**
 * Created by KXJ on 2015-12-21.
 */
public class LinkInfo {
    private int id;
    private String url;
    private String name;
    private String title;
    private String rule;
    private String info;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public LinkInfo(int id, String url, String name, String title, String rule, String info) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.title = title;
        this.rule = rule;
        this.info = info;
    }

    public LinkInfo() {
    }

    @Override
    public String toString() {
        return "LinkInfo{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", rule='" + rule + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
