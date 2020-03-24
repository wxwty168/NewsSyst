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

@WebServlet("/ChangeTypeValueServlet")
public class ChangeTypeValueServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取新闻ID
        /*与前台？后的变量名对应*/
        String Lnewstype_s = request.getParameter("Lnewstype");
        int Lnewstype = Integer.parseInt(Lnewstype_s);
        HttpSession session = request.getSession();
        session.setAttribute("Lnewstype",Lnewstype);
        session.setAttribute("flag" ,1);
        request.getRequestDispatcher("MainPage.jsp").forward(request,response);

    }
}
