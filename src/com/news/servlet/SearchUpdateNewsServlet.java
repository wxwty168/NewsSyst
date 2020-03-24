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

@WebServlet("/SearchUpdateNewsServlet")
public class SearchUpdateNewsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        //获取用户请求信息
        request.setCharacterEncoding("utf-8");
        String newsname = request.getParameter("newsname");

        UserDaoInter userDao = new Userdao();
        List<NewsData> news_list = userDao.getUpdateNewsByTitleForVerify(newsname);

        //转发
        List<NewsData> topthree = userDao.getTopNews();
        request.setAttribute("topthree", topthree);
        request.setAttribute("list",news_list);
        request.getRequestDispatcher("Newverify.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
