package com.news.dao;

import com.news.pojo.MyUser;
import com.news.pojo.NewsData;
import com.news.util.DataBase;
import com.news.util.SendMessage;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Userdao implements UserDaoInter {

    DataBase dataBase = new DataBase();
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    @Override
    public boolean ifTheNewsExistsByTitle(String news_title) {
        boolean flag = false;
        try {
            connection = dataBase.openConn();
            String sql = "select id from news_data where title=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,news_title);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dataBase.closeConn(connection, preparedStatement, resultSet);
        }
        return flag;
    }

    @Override
    public NewsData functionGetNewsById(String database_name, int id) {
        String sql = "select id,title,newstype,author,createdate,modifydate,content,verified,imageurl,imagetitle,clicks from " +
                database_name + " where id=? ";
        connection = dataBase.openConn();
        NewsData newsData = new NewsData();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                newsData.setId(resultSet.getInt("id"));
                newsData.setTitle(resultSet.getString("title"));
                newsData.setNewstype(resultSet.getInt("newstype"));
                newsData.setAuthor(resultSet.getString("author"));
                newsData.setCreatedate(resultSet.getString("createdate"));
                newsData.setModifydate(resultSet.getString("modifydate"));
                newsData.setContent(resultSet.getString("content"));
                newsData.setVerified(resultSet.getInt("verified"));
                newsData.setImamgeurl(resultSet.getString("imageurl"));
                newsData.setImagetitle(resultSet.getString("imagetitle"));
                newsData.setClicks(resultSet.getInt("clicks"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            dataBase.closeConn(connection,preparedStatement,resultSet);
        }
        return newsData;
    }

    @Override
    public boolean functionModifyVerification(String database_name, int[] id, int verification) {
        //是否审核通过,传进所有选择的id的int[]和verification信息，1为通过，-1为不通过
        boolean isEdited = false;
        int id_length = id.length;
        String sql = "update " + database_name + " set verified = ? where id in (";
        for(int i=0; i<id_length; i++){
            if(i==id_length-1){
                sql += "?)";
            }else{
                sql += "?,";
            }
        }
        connection = dataBase.openConn();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,verification);
            for(int i=0; i<id_length; i++){
                preparedStatement.setInt((i+2),id[i]);
            }
            int num = preparedStatement.executeUpdate();
            if(num > 0){
                isEdited = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dataBase.closeConn(connection,preparedStatement,resultSet);
        }
        return isEdited;
    }

    @Override
    public boolean functionDeleteNews(String database_name, int[] id) {
        boolean isDelete = false;
        int id_length = id.length;
        String sql = "delete from " + database_name + " where id in (";
        for(int i=0; i<id_length; i++){
            if(i==id_length-1){
                sql += "?)";
            }else{
                sql += "?,";
            }
        }
        connection = dataBase.openConn();
        try {
            preparedStatement = connection.prepareStatement(sql);
            for(int i=0; i<id_length; i++){
                preparedStatement.setInt((i+1), id[i]);
            }
            int num = preparedStatement.executeUpdate();
            if(num>0){
                isDelete = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dataBase.closeConn(connection,preparedStatement,resultSet);
        }
        return isDelete;
    }

    @Override
    public int userConvertForSql(String type) {
        Map<String,Integer> Convert = new HashMap<String,Integer>();       //数据采用的哈希表结构
        Convert.put("科学研究", 1);
        Convert.put("学生工作", 2);
        Convert.put("党建工作", 3);
        Convert.put("对外交流", 4);
        Convert.put("校园生活", 5);
        return Convert.get(type);
    }

    @Override
    public String sqlConvertForUser(int int_typt) {
        Map<Integer, String> Convert = new HashMap<Integer,String>();       //数据采用的哈希表结构
        Convert.put(1, "科学研究");
        Convert.put(2, "学生工作");
        Convert.put(3, "党建工作");
        Convert.put(4, "对外交流");
        Convert.put(5, "校园生活");
        return Convert.get(1);
    }

    @Override
    public List<NewsData> functionSearchNews(String database_name, int verified, String title) {
        List<NewsData> list = new ArrayList<NewsData>();
        String sql = "select id,title,newstype,author,createdate,imageurl,imagetitle from " + database_name + " where verified=? ";
        title = "%"+title+"%";
        /*字符串在if语句判断必须用equals()语句*/
        if(!("%%".equals(title))) {
            sql += "and title like ? ";
        }
        connection = dataBase.openConn();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, verified);
            if(!("%%".equals(title))) {
                preparedStatement.setString(2, title);
            }
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                NewsData newsData = new NewsData();
                newsData.setId(resultSet.getInt("id"));
                newsData.setTitle(resultSet.getString("title"));
                newsData.setNewstype(resultSet.getInt("newstype"));
                newsData.setAuthor(resultSet.getString("author"));
                newsData.setCreatedate(resultSet.getString("createdate"));
                newsData.setImamgeurl(resultSet.getString("imageurl"));
                newsData.setImagetitle(resultSet.getString("imagetitle"));
                list.add(newsData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            dataBase.closeConn(connection, preparedStatement, resultSet);
        }
        return list;
    }

    @Override
    public boolean sendEmail(String Address,String content) {
        try {
            SendMessage.Send(Address,content);
        } catch (Exception e) {
            //以下代码是为了获取错误信息以及错误位置
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw, true);
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        }
        System.out.println("OK");
        return true;
    }



        /*------------------------------functions------------------------------*/

    @Override
    public boolean getLogin(MyUser myUser) {
        boolean flag = false;
        try {
            connection = dataBase.openConn();
            String sql = "select * from myuser u where (u.user_name=? or u.mail=?) and u.pwd=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, myUser.getUser_name());
            preparedStatement.setString(2, myUser.getUser_name());
            preparedStatement.setString(3, myUser.getPwd());
            //preparedStatement.setInt(4,myUser.getIs_admin());
            resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet);
            while (resultSet.next()) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dataBase.closeConn(connection, preparedStatement, resultSet);
        }
        return flag;
    }

    @Override
    public MyUser getUserInfo(MyUser myUser) {
        boolean flag = false;
        MyUser myUser1 = new MyUser();
        try {
            connection = dataBase.openConn();
            String sql = "select * from myuser u where (u.user_name=? or u.mail=?) and u.pwd=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, myUser.getUser_name());
            preparedStatement.setString(2, myUser.getUser_name());
            preparedStatement.setString(3, myUser.getPwd());
            //preparedStatement.setInt(4,myUser.getIs_admin());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                myUser1.setUser_name(resultSet.getString("user_name"));
                myUser1.setIs_admin(resultSet.getInt("is_admin"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dataBase.closeConn(connection, preparedStatement, resultSet);
        }
        return myUser1;
    }

    @Override
    public boolean addNews(NewsData newsData) {
        boolean flag = false;
        connection = dataBase.openConn();
        String sql1 = "insert into news_data (title,newstype,author,createdate,modifydate,content,verified,imageurl,imagetitle) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        if(newsData.getAuthor()==null || newsData.getAuthor()==""){
            newsData.setAuthor("佚名");
        }
        if(newsData.getImamgeurl()==null || newsData.getImamgeurl()==""){
            newsData.setImamgeurl("/logo.jpg");
        }
        if(newsData.getImagetitle()==null){
            newsData.setImagetitle("empty");
        }
        //查询新增的最后一个元素
        //String sql2 = "select id from news_data order by id DESC limit 1";
        //点击量表增加id
        //String sql3 = "insert into click_num(id) values (?)";
        try{
            preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.setString(1, newsData.getTitle());
            preparedStatement.setInt(2, newsData.getNewstype());
            preparedStatement.setString(3, newsData.getAuthor());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String current_time = df.format(new Date());// new Date()为获取当前系统时间
            preparedStatement.setString(4, current_time);
            preparedStatement.setString(5, current_time);
            preparedStatement.setString(6, newsData.getContent());
            preparedStatement.setInt(7, 0);
            preparedStatement.setString(8,newsData.getImamgeurl());
            preparedStatement.setString(9,newsData.getImagetitle());
            int num = preparedStatement.executeUpdate();
            if(0!=num){
                flag = true;
            }
            ////查询新增的最后一个元素id
            //preparedStatement = connection.prepareStatement(sql2);
            //resultSet = preparedStatement.executeQuery();
            //int id_num = 0;
            //while (resultSet.next()) {
            //    id_num =resultSet.getInt("id");
            //}
            //点击量表增加id
            //preparedStatement = connection.prepareStatement(sql3);
            //if (id_num!=0){
            //    preparedStatement.setInt(1, id_num);
             //   preparedStatement.executeUpdate();
            //}
        }catch (SQLException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            dataBase.closeConn(connection, preparedStatement, resultSet);
        }
        return flag;
    }

    @Override
    public List<NewsData> getAllNewsByVerification(int verification) {//获取所有通过审核的新闻标题和id  用于放在首页上
        List<NewsData> list = new ArrayList<NewsData>();
        String sql = "select id,title,newstype,author,createdate,imageurl,imagetitle,clicks from news_data where verified=?";
        connection = dataBase.openConn();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,verification);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                NewsData newsData = new NewsData();
                newsData.setId(resultSet.getInt("id"));
                newsData.setTitle(resultSet.getString("title"));
                newsData.setNewstype(resultSet.getInt("newstype"));
                newsData.setAuthor(resultSet.getString("author"));
                newsData.setCreatedate(resultSet.getString("createdate"));
                newsData.setImamgeurl(resultSet.getString("imageurl"));
                newsData.setImagetitle(resultSet.getString("imagetitle"));
                newsData.setClicks(resultSet.getInt("clicks"));
                list.add(newsData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            dataBase.closeConn(connection, preparedStatement, resultSet);
        }
        return list;
    }

    @Override
    public NewsData getNewsById(int id) {//点击页面传给我新闻id，通过id查找新闻内容
        NewsData newsData;
        newsData = functionGetNewsById("news_data", id);
        return newsData;
    }

    @Override
    public boolean modifyVerificationStatusById(int[] id, int verification) {
        //是否审核通过,传进所有选择的id的int[]和verification信息，1为通过，-1为不通过
        boolean isEdited = false;
        isEdited = functionModifyVerification("news_data",id,verification);
        return isEdited;
    }

    @Override
    public boolean addUpdateNews(NewsData newsData) {//更新新闻，我会把更新的新闻存到update_news表中，等待管理员审核/*审核表中verification全为1*/
        boolean flag = false;
        connection = dataBase.openConn();
        String sql = "insert into update_news (id,title,newstype,author,createdate,modifydate,content,verified,imageurl,imagetitle) values(?,?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,newsData.getId());
            preparedStatement.setString(2, newsData.getTitle());
            preparedStatement.setInt(3, newsData.getNewstype());
            preparedStatement.setString(4, newsData.getAuthor());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String current_time = df.format(new Date());// new Date()为获取当前系统时间
            preparedStatement.setString(5, newsData.getCreatedate());
            preparedStatement.setString(6, current_time);
            preparedStatement.setString(7, newsData.getContent());
            preparedStatement.setInt(8, 1);
            preparedStatement.setString(9,newsData.getImamgeurl());
            preparedStatement.setString(10,newsData.getImagetitle());
            int num = preparedStatement.executeUpdate();
            if(0!=num){
                flag = true;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            dataBase.closeConn(connection, preparedStatement, resultSet);
        }
        return flag;
    }

    @Override
    public List<NewsData> getAllUpdateNewsTitleAndId() {//获取所有待审核的更新新闻列表 用于管理员审核界面
        List<NewsData> list = new ArrayList<NewsData>();
        String sql = "select id,title,newstype,author,createdate,modifydate,content,verified,imageurl,imagetitle from update_news";
        connection = dataBase.openConn();
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                NewsData newsData = new NewsData();
                newsData.setId(resultSet.getInt("id"));
                newsData.setTitle(resultSet.getString("title"));
                newsData.setNewstype(resultSet.getInt("newstype"));
                newsData.setAuthor(resultSet.getString("author"));
                newsData.setCreatedate(resultSet.getString("createdate"));
                newsData.setModifydate(resultSet.getString("modifydate"));
                newsData.setImamgeurl(resultSet.getString("imageurl"));
                newsData.setImagetitle(resultSet.getString("imagetitle"));
                list.add(newsData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            dataBase.closeConn(connection, preparedStatement, resultSet);
        }
        return list;
    }

    @Override
    public NewsData getUpdateNewsById(int id) {
        NewsData newsData;
        newsData = functionGetNewsById("update_news", id);
        return newsData;
    }

    @Override
    public boolean updateNewsById(int[] id, int verification) {//更新的新闻如果通过，替代原有数据，否则verified标签置为-1（删除）
        boolean isEdited = false;
        int id_length = id.length;
        if(verification==-1){
            isEdited = functionModifyVerification("update_news",id,verification);
        }else if(verification==1){
            //更新数据
            String sql = "update news_data INNER JOIN update_news on news_data.id=update_news.id " +
                    "set news_data.title=update_news.title, news_data.newstype=update_news.newstype, news_data.author=update_news.author" +
                    ", news_data.modifydate=update_news.modifydate, news_data.content=update_news.content, news_data.verified=update_news.verified, " +
                    "news_data.imageurl=update_news.imageurl, news_data.imagetitle=update_news.imagetitle where news_data.id=update_news.id";
            connection = dataBase.openConn();
            try{
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.executeUpdate();
                isEdited = true;
            }catch (SQLException e) {
                e.printStackTrace();
            }catch (Exception e) {
                e.printStackTrace();
            }finally {
                //关闭数据库连接
                dataBase.closeConn(connection, preparedStatement, resultSet);
            }

            //删除已经更新的数据
            functionDeleteNews("update_news",id);
        }
        return isEdited;
    }

    @Override
    public boolean delNewsById(int[] id) {
        boolean isEdit;
        isEdit = functionDeleteNews("news_data",id);
        return isEdit;
    }

    @Override
    public List<NewsData> getNewsByTitle(String title) {
        List<NewsData> list = functionSearchNews("news_data",1,title);
        return list;
    }

    @Override
    public List<NewsData> getAddedNewsByTitleForVerify(String title) {
        List<NewsData> list = functionSearchNews("news_data",0,title);
        return list;
    }

    @Override
    public List<NewsData> getUpdateNewsByTitleForVerify(String title) {
        List<NewsData> list = functionSearchNews("update_news",1,title);
        return list;
    }

    @Override
    public boolean getRegister(MyUser myUser) {
        boolean isSuccess = false;
        String sql = "insert into myuser (user_name,pwd,mail,is_admin) values (?,?,?,?)";
        connection = dataBase.openConn();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,myUser.getUser_name());
            preparedStatement.setString(2,myUser.getPwd());
            preparedStatement.setString(3,myUser.getMail());
            preparedStatement.setInt(4,myUser.getIs_admin());
            int num = preparedStatement.executeUpdate();
            if(num > 0){
                isSuccess = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dataBase.closeConn(connection,preparedStatement,null);
        }
        return isSuccess;
    }

    @Override
    public boolean addClicks(int id) {
        boolean flag = false;
        String sql = "update news_data set clicks=clicks+1 where id = ?";
        connection = dataBase.openConn();
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            flag = true;
        }catch (SQLException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            //关闭数据库连接
            dataBase.closeConn(connection, preparedStatement, resultSet);
        }
        return flag;
    }

    @Override
    public List<NewsData> getNewsTitleByType(int newstype) {
        List<NewsData> list = new ArrayList<NewsData>();
        String sql = "select id,title,newstype,author,createdate,imageurl,imagetitle from news_data where verified=1 and newstype=? ORDER BY modifydate DESC";

        connection = dataBase.openConn();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, newstype);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                NewsData newsData = new NewsData();
                newsData.setId(resultSet.getInt("id"));
                newsData.setTitle(resultSet.getString("title"));
                newsData.setNewstype(resultSet.getInt("newstype"));
                newsData.setAuthor(resultSet.getString("author"));
                newsData.setCreatedate(resultSet.getString("createdate"));
                newsData.setImamgeurl(resultSet.getString("imageurl"));
                newsData.setImagetitle(resultSet.getString("imagetitle"));
                list.add(newsData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            dataBase.closeConn(connection, preparedStatement, resultSet);
        }
        return list;
    }

    @Override
    public boolean checkUserName(String userName) {
        boolean flag = false;
        String sql = "select * from myuser where user_name=?";
        connection = dataBase.openConn();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            dataBase.closeConn(connection, preparedStatement, resultSet);
        }
        return flag;
    }

    @Override
    public boolean checkEmail(String Email) {
        boolean flag = false;
        String sql = "select * from myuser where mail=?";
        connection = dataBase.openConn();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Email);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            dataBase.closeConn(connection, preparedStatement, resultSet);
        }
        return flag;
    }

    @Override
    public boolean checkVerificationCode(String code_s, String chkcode_s) {
        int code = Integer.parseInt(code_s);
        int chkcode = Integer.parseInt(chkcode_s);
        if(code==chkcode){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<NewsData> getTopNews() {
        String sql = "SELECT id,title,newstype,author,createdate,imageurl,imagetitle,clicks FROM news_data where verified=1 order by clicks desc limit 4";
        List<NewsData> list = new ArrayList<NewsData>();
        connection = dataBase.openConn();
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                NewsData newsData = new NewsData();
                newsData.setId(resultSet.getInt("id"));
                newsData.setTitle(resultSet.getString("title"));
                newsData.setNewstype(resultSet.getInt("newstype"));
                newsData.setAuthor(resultSet.getString("author"));
                newsData.setCreatedate(resultSet.getString("createdate"));
                newsData.setImamgeurl(resultSet.getString("imageurl"));
                newsData.setImagetitle(resultSet.getString("imagetitle"));
                newsData.setClicks(resultSet.getInt("clicks"));
                list.add(newsData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            dataBase.closeConn(connection, preparedStatement, resultSet);
        }
        return list;
    }

    @Override
    public boolean resetPasswordByMail(String mail, String pwd) {
        boolean flag = false;
        String sql = "update myuser set verified = ? where mail = ?";
        connection = dataBase.openConn();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, mail);

            int num = preparedStatement.executeUpdate();
            if(num > 0){
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dataBase.closeConn(connection,preparedStatement,resultSet);
        }
        return flag;
    }


}

