package com.news.pojo;

public class NewsData {
    private int id;
    private String title;
    private int newstype;
    private String author;
    private String createdate;
    private String modifydate;
    private String content;
    private int verified;
    private String imamgeurl;
    private String imagetitle;
    private int clicks;

    public NewsData() {
    }

    public NewsData(int id, String title, int newstype, String author, String createdate, String modifydate, String content, int verified, String imamgeurl, String imagetitle, int clicks) {
        this.id = id;
        this.title = title;
        this.newstype = newstype;
        this.author = author;
        this.createdate = createdate;
        this.modifydate = modifydate;
        this.content = content;
        this.verified = verified;
        this.imamgeurl = imamgeurl;
        this.imagetitle = imagetitle;
        this.clicks = clicks;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getNewstype() {
        return newstype;
    }

    public String getAuthor() {
        return author;
    }

    public String getCreatedate() {
        return createdate;
    }

    public String getModifydate() {
        return modifydate;
    }

    public String getContent() {
        return content;
    }

    public int getVerified() {
        return verified;
    }

    public String getImamgeurl() {
        return imamgeurl;
    }

    public String getImagetitle() {
        return imagetitle;
    }

    public int getClicks() {
        return clicks;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNewstype(int newstype) {
        this.newstype = newstype;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public void setModifydate(String modifydate) {
        this.modifydate = modifydate;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setVerified(int verified) {
        this.verified = verified;
    }

    public void setImamgeurl(String imamgeurl) {
        this.imamgeurl = imamgeurl;
    }

    public void setImagetitle(String imagetitle) {
        this.imagetitle = imagetitle;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }
}
