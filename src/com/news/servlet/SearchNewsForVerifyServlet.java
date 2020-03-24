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

@WebServlet( "/SearchNewsForVerifyServlet")
public class SearchNewsForVerifyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int is_admin;
        HttpSession session = request.getSession();
        if (session.getAttribute("name")!=null) {
            is_admin = Integer.parseInt(session.getAttribute("id").toString());
            if (is_admin != 1) {
                response.setContentType("text/html;charset=utf-8");
                PrintWriter out = response.getWriter();
                out.print("<script>alert('不是管理员不能访问!');window.location.href='MainPage.jsp'</script>");
                return;
            }
        } else {
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.print("<script>alert('请先登录!');window.location.href='Newlogin.jsp'</script>");
            return;
        }

        String newsname = request.getParameter("serchtitle");
        UserDaoInter userDao = new Userdao();
        List<NewsData> listver = userDao.getNewsByTitle(newsname);//转发
        session.setAttribute("listver",listver);
        int total_count = 0; ////////////返回信息！！！！
        if (listver != null)
            total_count = listver.size();   //信息的总量
        int page_total = total_count / 10 + (total_count % 10 != 0 ? 1 : 0);
        session.setAttribute("total_count",total_count);
        session.setAttribute("begin", 1);
        session.setAttribute("page_begin", 0);
        session.setAttribute("page_current", 1);
        session.setAttribute("page_end", 9);
        session.setAttribute("page_total", page_total);
 /*       page_current = Integer.parseInt(session.getAttribute("page_current").toString());  //获取当前页数                        }
        page_begin = (page_current - 1) * 10;
        page_end = page_begin + 9 > total_count ? total_count : page_begin + 9;
        request.getSession().setAttribute("page_begin", page_current);  //保存到session中
        request.getSession().setAttribute("page_end", page_end);*/
        List<NewsData> topthree = userDao.getTopNews();
        request.setAttribute("topthree", topthree);
        request.getRequestDispatcher("Newverify.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
