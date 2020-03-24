package com.news.servlet;

import com.news.dao.UserDaoInter;
import com.news.dao.Userdao;
import com.news.pojo.NewsData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/SearchNewsServlet")
public class SearchNewsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户请求信息
        request.setCharacterEncoding("utf-8");
        String newsname = request.getParameter("serchtitle");

        UserDaoInter userDao = new Userdao();
        List<NewsData> news_list = userDao.getNewsByTitle(newsname);

        //转发
        List<NewsData> topthree = userDao.getTopNews();
        request.setAttribute("topthree", topthree);
        request.setAttribute("list",news_list);
        request.getRequestDispatcher("SearchResult.jsp").forward(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
