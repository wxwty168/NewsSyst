package com.news.dao;

import com.news.pojo.NewsData;
import org.omg.CORBA.PUBLIC_MEMBER;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class testcode {

//    public static Map<String,Integer> map(){
//        Map<String,Integer> userConvertForSql = new HashMap<String,Integer>();       //数据采用的哈希表结构
//        userConvertForSql.put("科学研究", 1);
//        userConvertForSql.put("学生工作", 2);
//        userConvertForSql.put("党建工作", 3);
//        userConvertForSql.put("对外交流", 4);
//        userConvertForSql.put("校园生活", 5);
//        return userConvertForSql;
//    }


    public static void Send(String tittle,String value) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");// 连接协议
        properties.put("mail.smtp.host", "smtp.qq.com");// 主机名
        properties.put("mail.smtp.port", 465);// 端口号
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");//设置是否使用ssl安全连接 ---一般都使用
        properties.put("mail.debug", "true");//设置是否显示debug信息 true 会在控制台显示相关信息
        //得到回话对象
        Session session = Session.getInstance(properties);
        // 获取邮件对象
        Message message = new MimeMessage(session);
        //设置发件人邮箱地址
        message.setFrom(new InternetAddress("2580615241@qq.com"));
        //设置收件人地址
        message.setRecipients(MimeMessage.RecipientType.TO, new InternetAddress[]{new InternetAddress("1762277418@qq.com")});
        //设置邮件标题
        message.setSubject(tittle);
        //设置邮件内容
        message.setText(value);
        //得到邮差对象
        Transport transport = session.getTransport();
        //连接自己的邮箱账户
        transport.connect("2580615241@qq.com", "terqwuawhbbzdifc");//密码为刚才得到的授权码
        System.out.println(" IM OK");
        transport.sendMessage(message, message.getAllRecipients());
    }
    public  static void main(String[] args) throws MessagingException{
        String title = "test";

        Random rand = new Random(1);
        int ran_int = rand.nextInt(999999-101100+1)+101100;
        /*int randNumber =rand.nextInt(MAX - MIN + 1) + MIN; // randNumber 将被赋值为一个 MIN 和 MAX 范围内的随机数*/
        String content = "您的验证码为: ";
        try {
            Send(title, content);
        }catch (Exception e){
            //以下代码是为了获取错误信息以及错误位置
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw,true);
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        }
        System.out.println("OK");

    }
}

//    @Override
//        public NewsData getNewsById(int id) {//点击页面传给我新闻id，通过id查找新闻内容
//        String sql = "select id,title,newstype,author,createdate,modifydate,content,verified,imageurl,imagetitle from news_data where id=? ";
//        connection = dataBase.openConn();
//        NewsData newsData = new NewsData();
//        try {
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, id);
//            resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//                newsData.setId(resultSet.getInt("id"));
//                newsData.setTitle(resultSet.getString("title"));
//                newsData.setNewstype(resultSet.getString("newstype"));
//                newsData.setAuthor(resultSet.getString("author"));
//                newsData.setCreatedate(resultSet.getString("createdate"));
//                newsData.setModifydate(resultSet.getString("modifydate"));
//                newsData.setContent(resultSet.getString("content"));
//                newsData.setImamgeurl(resultSet.getString("imageurl"));
//                newsData.setImagetitle(resultSet.getString("imagetitle"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally{
//            dataBase.closeConn(connection,preparedStatement,resultSet);
//        }
//        return newsData;
//    }

//    @Override
//    public NewsData getUpdateNewsById(int id) {
//        String sql = "select id,title,newstype,author,createdate,modifydate,content,verified,imageurl,imagetitle from update_news where id=? ";
//        connection = dataBase.openConn();
//        NewsData newsData = new NewsData();
//        try {
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, id);
//            resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//                newsData.setId(resultSet.getInt("id"));
//                newsData.setTitle(resultSet.getString("title"));
//                newsData.setNewstype(resultSet.getString("newstype"));
//                newsData.setAuthor(resultSet.getString("author"));
//                newsData.setCreatedate(resultSet.getString("createdate"));
//                newsData.setModifydate(resultSet.getString("modifydate"));
//                newsData.setContent(resultSet.getString("content"));
//                newsData.setImamgeurl(resultSet.getString("imageurl"));
//                newsData.setImagetitle(resultSet.getString("imagetitle"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally{
//            dataBase.closeConn(connection,preparedStatement,resultSet);
//        }
//        return newsData;
//    }
//
//
//    @Override
//    public boolean modifyVerificationStatusById(int[] id, int verification) {
//        //是否审核通过,传进所有选择的id的int[]和verification信息，1为通过，-1为不通过
//        boolean isEdited = false;
//        int id_length = id.length;
//        String sql = "update news_data set verified = ? where id in (";
//        for(int i=0; i<id_length; i++){
//            if(i==id_length-1){
//                sql += "?)";
//            }else{
//                sql += "?,";
//            }
//        }
//        connection = dataBase.openConn();
//        try {
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1,verification);
//            for(int i=0; i<id_length; i++){
//                preparedStatement.setInt((i+2),id[i]);
//            }
//            int num = preparedStatement.executeUpdate();
//            if(num > 0){
//                isEdited = true;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
//            dataBase.closeConn(connection,preparedStatement,resultSet);
//        }
//        return isEdited;
//    }