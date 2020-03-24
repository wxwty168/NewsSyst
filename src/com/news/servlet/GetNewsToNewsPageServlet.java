package com.news.servlet;

import com.news.dao.UserDaoInter;
import com.news.dao.Userdao;
import com.news.pojo.NewsData;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/GetNewsToNewsPageServlet")
public class GetNewsToNewsPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //判断是否登录
//        HttpSession session = request.getSession();
//        if(session.getAttribute("name")==null){
//            response.setContentType("text/html;charset=utf-8");
//            PrintWriter out = response.getWriter();
//            out.print("<script>alert('请先登录!');window.location.href='Newlogin.jsp'</script>");
//            return;
//        }else{
        //获取新闻ID
        /*与前台？后的变量名对应*/
        String newsid_s = request.getParameter("Newsid");
        int news_id = Integer.parseInt(newsid_s);
        //获取新闻内容
        UserDaoInter userDao = new Userdao();
        //增加点击量
        userDao.addClicks(news_id);
        NewsData newsData = userDao.getNewsById(news_id);
        /*转发*/
        List<NewsData> topthree = userDao.getTopNews();
        request.setAttribute("topthree", topthree);
        request.setAttribute("news",newsData);
        HttpSession session = request.getSession();
        session.getAttribute("id");
        request.setAttribute("id",session.getAttribute("id"));
        request.getRequestDispatcher("NewsPage.jsp").forward(request,response);


    }

}
