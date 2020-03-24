package com.news.servlet;

import com.news.dao.UserDaoInter;
import com.news.dao.Userdao;
import com.news.pojo.NewsData;

import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ResetRealPageServlet")
public class ResetRealPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*newpubic 1  要验证是否登录
        * newseditor 2 验证是否为登录
        * newspage 3不验证，跳转
        * newverify 4 验证管理员
        * */
        try{
            String pageNo_s = request.getParameter("pageName");
            int pageno = Integer.parseInt(pageNo_s);
            if(pageno==1){
                //判断是否登录
                HttpSession session = request.getSession();
                if(session.getAttribute("name")==null){
                    response.setContentType("text/html;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    out.print("<script>alert('请先登录!');window.location.href='Newlogin.jsp'</script>");
                    }else{
                    UserDaoInter userDao = new Userdao();
                    List<NewsData> topthree = userDao.getTopNews();
                    request.setAttribute("topthree", topthree);
                    request.getRequestDispatcher("NewPublic.jsp").forward(request, response);
                }
            }else if (pageno==2){
                //判断是否登录
                HttpSession session = request.getSession();
                if(session.getAttribute("name")==null){
                    response.setContentType("text/html;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    out.print("<script>alert('请先登录!');window.location.href='Newlogin.jsp'</script>");
                }else{
                    UserDaoInter userDao = new Userdao();
                    List<NewsData> topthree = userDao.getTopNews();
                    request.setAttribute("topthree", topthree);
                    request.getRequestDispatcher("GetNewsToEditPageServlet").forward(request, response);
                }
            }else if(pageno==3){
                //不验证是否登录 直接跳转
                String newsid_s = request.getParameter("Newsid");
                request.setAttribute("Newsid",newsid_s);
                UserDaoInter userDao = new Userdao();
                List<NewsData> topthree = userDao.getTopNews();
                request.setAttribute("topthree", topthree);
                request.getRequestDispatcher("GetNewsToNewsPageServlet").forward(request, response);
            }else if(pageno==4){
                //判断是否为管理员登录
                int is_admin;
                HttpSession session = request.getSession();
                if(session!=null){
                    is_admin = Integer.parseInt(session.getAttribute("id").toString());
                    if(is_admin!=1){
                        response.setContentType("text/html;charset=utf-8");
                        PrintWriter out = response.getWriter();
                        out.print("<script>alert('不是管理员不能访问!');window.location.href='MainPage.jsp'</script>");
                    }else{
                        UserDaoInter userDao = new Userdao();
                        List<NewsData> topthree = userDao.getTopNews();
                        request.setAttribute("topthree", topthree);
                        request.getRequestDispatcher("GetAddedNewsToVerifyServlet").forward(request, response);
                    }
                }else{
                    response.setContentType("text/html;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    out.print("<script>alert('请先登录!');window.location.href='Newlogin.jsp'</script>");
                }
            }
        }finally{
        }



    }
}
