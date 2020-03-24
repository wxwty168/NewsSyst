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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/GetAddedNewsToVerifyServlet")
public class GetAddedNewsToVerifyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //判断是否为管理员登录
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
        //获取新闻内容
        String s_type = request.getParameter("verifytype");
        List<String> typelist = new ArrayList<String>();
        typelist.add("1");
        typelist.add("2");
        typelist.add("3");
        for(String s : typelist)
        {
            if(s.equals(s_type)){session.setAttribute("veritype"+s,"list-group-item list-group-item-warning"); }
            else {session.setAttribute("veritype"+s,"list-group-item");}
        }


        session.setAttribute("serchtype1","display:block");
        session.setAttribute("serchtype2","display:none");
        session.setAttribute("serchtype3","display:none");
        UserDaoInter userDao = new Userdao();
        List<NewsData> listver = userDao.getAllNewsByVerification(0);

        session.setAttribute("listver", listver);
        /*//此处是取出所存储的数据
        int page_current = 1;
        // 当前页数
        int page_begin = 0;
        //起始点,注意:下标从0开始
        int page_end = 9;   //终点,每页十条信息*/
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
}
