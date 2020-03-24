package com.news.util;

import java.sql.*;

public class JDBCTest {
    //java小程序,独立于tomcat可以独立运行
    public static void main(String[] args) {
        DataBase dataBase = new DataBase();
        Connection connection;
        PreparedStatement preparedStatement = null;

        ResultSet resultSet = null;
        String sql = "insert into myuser (user_name, pwd, mail, is_admin) values('admin', '123', '1762277418@qq.com', 1)";
        connection = dataBase.openConn();
        try {
            preparedStatement = connection.prepareStatement(sql);
            int num = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dataBase.closeConn(connection, preparedStatement, resultSet);
        }
    }
}
