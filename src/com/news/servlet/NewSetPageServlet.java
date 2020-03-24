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

@WebServlet( "/NewSetPageServlet")
public class NewSetPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            // 在这sevelet中进行所有的安全验证，页面样式调整，安全跳转，参数为页面URL以及需要的参数进行try 获取{}
            String Lnewstype_s = request.getParameter("Lnewstype");
            int Lnewstype = Integer.parseInt(Lnewstype_s);
            UserDaoInter userDao = new Userdao();
            List<NewsData> list = userDao.getNewsTitleByType(Lnewstype);
            request.setAttribute("type"+Lnewstype_s,"list-group-item-warning");

        /*List<String> typelist = new ArrayList<String>();
        typelist.add("1");
        typelist.add("2");
        typelist.add("3");
        typelist.add("4");
        typelist.add("5");
        for(String s:typelist) {if(s!=Lnewstype_s){session.setAttribute("type"+s,"list-group-item");}}*/
            List<NewsData> topthree = userDao.getTopNews();
            request.setAttribute("topthree", topthree);
            request.setAttribute("list",list);
            request.getRequestDispatcher("RealMainPage.jsp").forward(request,response);
        }finally {
        }

    }
}
