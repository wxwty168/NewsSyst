package com.news.dao;

import com.news.pojo.MyUser;
import com.news.pojo.NewsData;

import java.util.List;

public interface UserDaoInter {
    /*------------------------------functions------------------------------*/
    //标题精确查找 = 输入新闻标题，存在为true，不存在为false
    boolean ifTheNewsExistsByTitle(String news_title);

    //从指定库中通过id查新闻内容########################
    NewsData functionGetNewsById(String database_name, int id);

    //修改verified标签信息
    boolean functionModifyVerification(String database_name,int[] id, int verification);

    //删除新闻
    boolean functionDeleteNews(String database_name, int[] id);

    //将文字转换为数字存入sql
    int userConvertForSql(String type);

    //将sql中存储的数字转换为文字
    String sqlConvertForUser(int int_typt);

    //搜索新闻
    List<NewsData> functionSearchNews(String database_name, int verified, String title);

    /*------------------------------functions------------------------------*/

    //发送登录邮件
    boolean sendEmail(String Address, String content);

    //用户名或者邮箱登录，是否管理员登录存到is_admin（必须有）
    boolean getLogin(MyUser myUser);

    //获得用户信息
    MyUser getUserInfo(MyUser myUser);

    //新增新闻需要newsdata中存有title,newstype,author,content,Imamgeurl,Imamgetitle
    boolean addNews(NewsData newsData);

    //获取浏览信息
    //获取用于放在首页上的信息，verification：1为通过验证的的，放在首页用户查看；0为等待验证的，管理员验证页面查看
    // 返回id,title,newstype,author,createdate,imageurl,imagetitle*/
    List<NewsData> getAllNewsByVerification(int verification);


    //点击页面传给我新闻id，通过id查找新闻内容
    NewsData getNewsById(int id);

    //新增是否审核通过,传进所有选择的id的int[]和verification信息，1为通过，-1为不通过（verification传入1或-1）
    boolean modifyVerificationStatusById(int[] id, int verification);

    //更新新闻，我会把更新的新闻存到update_news表中，等待管理员审核/*审核表中verification全为1*//*传给我的newsdata必须有id*/
    boolean addUpdateNews(NewsData newsData);////////////////////////////////////////////////////////////////

    //上面和下面两个里面不含有click值

    //获取所有待审核的更新新闻列表 用于管理员审核首页界面，用于浏览
    List<NewsData> getAllUpdateNewsTitleAndId();

    //根据id获取待更新的新闻具体内容
    NewsData getUpdateNewsById(int id);

    //更新的新闻如果通过，替代原有数据，否则verified标签置为-1（删除）（verification传入1或-1）
    boolean updateNewsById(int[] id,int verification);

    //删除新闻，仅管理员可以操作，
    boolean delNewsById(int[] id);

    //根据关键词查询新闻信息//搜索
    List<NewsData> getNewsByTitle(String title);

    //根据关键词查询新闻信息//搜索
    List<NewsData> getAddedNewsByTitleForVerify(String title);

    //根据关键词查询新闻信息//搜索
    List<NewsData> getUpdateNewsByTitleForVerify(String title);

    //注册
    boolean getRegister(MyUser myUser);/////////////////////////////////////

    //点击量
    boolean addClicks(int id);

    //根据newstype查新闻标题
    List<NewsData> getNewsTitleByType(int newstype);

    //检查用户名，存在为真
    boolean checkUserName(String userName);

    //检查邮箱，存在为真
    boolean checkEmail(String Email);

    //检查验证码，正确为真
    boolean checkVerificationCode(String code_s, String chkcode_s);

    //获取点击量最高
    List<NewsData> getTopNews();

    //重置密码，通过邮箱
    boolean resetPasswordByMail(String mail, String pwd);
}
