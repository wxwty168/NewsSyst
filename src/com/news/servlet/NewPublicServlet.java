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

@WebServlet("/NewPublicServlet")
public class NewPublicServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        //获取编码字符集
        request.setCharacterEncoding("utf-8");
        UserDaoInter userDao = new Userdao();
        //获取提交的页面信息
        String newstitle = request.getParameter("newstitle");
        int newstype = Integer.parseInt(request.getParameter("newstype"));
        String author;
        author = session.getAttribute("name").toString();
        String newscontent = request.getParameter("newscontent");
        boolean flag;
        flag = userDao.ifTheNewsExistsByTitle(newstitle);
        if(flag==true){
            request.setAttribute("msg","1");
            List<NewsData> topthree = userDao.getTopNews();
            request.setAttribute("topthree", topthree);
            request.getRequestDispatcher("NewPublic.jsp").forward(request,response);
        }else{
            NewsData newsData =new NewsData();
            newsData.setTitle(newstitle);
            newsData.setNewstype(newstype);
            newsData.setAuthor(author);
            newsData.setContent(newscontent);
            userDao.addNews(newsData);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.print("<script>alert('发布成功!');window.location.href='NewSetPageServlet?Lnewstype='+'1'</script>");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
