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

@WebServlet( "/SetPageServlet")
public class SetPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int total_count=Integer.parseInt(session.getAttribute("total_count").toString());
        int page_total =Integer.parseInt(session.getAttribute("page_total").toString());
        int page_current = Integer.parseInt(session.getAttribute("page_current").toString())+1;  //获取当前页数
        if (page_current==page_total+1) {response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.print("<script>alert('当前已是尾页');window.location.href='Newverify.jsp'</script>");}else{
        int page_begin = Integer.parseInt(session.getAttribute("page_end").toString())+1;
        int page_end = page_begin + 10 > total_count ? total_count : page_begin + 9;
        request.getSession().setAttribute("page_current",page_current);
        request.getSession().setAttribute("page_begin", page_begin);
        request.getSession().setAttribute("page_end", page_end);
        UserDaoInter userDao= new Userdao();
        List<NewsData> topthree = userDao.getTopNews();
        request.setAttribute("topthree", topthree);
        request.getRequestDispatcher("Newverify.jsp").forward(request,response);}
    }
}
