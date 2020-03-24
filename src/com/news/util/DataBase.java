package com.news.util;

import java.sql.*;

/**
 * 打开和关闭数据库类
 * */

public class DataBase {
    //打开数据库
    public Connection openConn(){
        Connection conn = null;
        try {
            //加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //建立连接
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/news_center?useSSL=false&serverTimezone=UTC", "root", "root");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    //关闭数据库
    /*原则：先打开的后关闭*/
    public void closeConn(Connection conn, PreparedStatement preparedStatement, ResultSet resultSet){
        try{
            if(null!=resultSet){
                resultSet.close();
            }
            if(null!=preparedStatement){
                preparedStatement.close();
            }
            if(null!=conn){
                conn.close();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
