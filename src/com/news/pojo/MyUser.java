package com.news.pojo;

public class MyUser {
    private String user_name;
    private String pwd;
    private String mail;
    private int is_admin;

    public MyUser() {
    }

    public MyUser(String user_name, String pwd, String mail, int is_admin) {
        this.user_name = user_name;
        this.pwd = pwd;
        this.mail = mail;
        this.is_admin = is_admin;
    }


    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setIs_admin(int is_admin) {
        this.is_admin = is_admin;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getPwd() {
        return pwd;
    }

    public String getMail() {
        return mail;
    }

    public int getIs_admin() {
        return is_admin;
    }
}
