package com.news.servlet;

import com.news.dao.UserDaoInter;
import com.news.dao.Userdao;
import com.news.pojo.NewsData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/GetNewsToEditPageServlet")
public class GetNewsToEditPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //判断是否为管理员登录
        int is_admin;
        HttpSession session = request.getSession();
        if(session.getAttribute("name")!=null){
            is_admin = Integer.parseInt(session.getAttribute("id").toString());
            if(is_admin!=1){
                response.setContentType("text/html;charset=utf-8");
                PrintWriter out = response.getWriter();
                out.print("<script>alert('不是管理员不能访问!');window.location.href='MainPage.jsp'</script>");
                return;
            }
        }else{
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.print("<script>alert('请先登录!');window.location.href='Newlogin.jsp'</script>");
            return;
        }
        //获取新闻ID
        /*与前台？后的变量名对应*/
        String newsid_s = request.getParameter("Newsid");
        int news_id = Integer.parseInt(newsid_s);
        //获取新闻内容
        UserDaoInter userDao = new Userdao();
        NewsData newsData = userDao.getNewsById(news_id);
        List<NewsData> topthree = userDao.getTopNews();
        request.setAttribute("topthree", topthree);

        /*转发*/
        request.setAttribute("news",newsData);
        request.getRequestDispatcher("Newseditor.jsp").forward(request,response);
    }
}
