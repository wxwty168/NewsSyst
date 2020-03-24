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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/GetANewsTypeServlet")
public class GetANewsTypeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取新闻ID
        /*与前台？后的变量名对应*/
        String Lnewstype_s = request.getParameter("Lnewstype");
        int Lnewstype = Integer.parseInt(Lnewstype_s);
//        HttpSession session = request.getSession();
//        Lnewstype = Integer.parseInt(session.getAttribute("Lnewstype").toString());
        //获取新闻内容
        UserDaoInter userDao = new Userdao();
        List<NewsData> list = userDao.getNewsTitleByType(Lnewstype);
        HttpSession session = request.getSession();
        session.setAttribute("flag",0);
        request.setAttribute("type"+Lnewstype_s,"list-group-item-warning ");

        /*转发*/
        request.setAttribute("list",list);
        List<NewsData> topthree = userDao.getTopNews();
        request.setAttribute("topthree", topthree);
        request.getRequestDispatcher("MainPage.jsp").forward(request,response);
    }
}
