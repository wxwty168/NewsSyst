package com.news.servlet;

import com.news.dao.UserDaoInter;
import com.news.dao.Userdao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/DelNewsServlet")
public class DelNewsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
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
                out.print("<script>alert('不是管理员不能访问!');window.location.href='NewSetPageServlet?Lnewstype='+'1'</script>");
                return;
            }
        } else {
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.print("<script>alert('请先登录!');window.location.href='Newlogin.jsp'</script>");
            return;
        }

        String ids_s = request.getParameter("news_id");
        //转换为字符串数组
        String[] arr_ids_s = ids_s.split(",");
        //将字符串数组中值转换为int整数
        int[] int_ids = new int[arr_ids_s.length];
        for(int i=0; i<int_ids.length; i++){
            int_ids[i] = Integer.parseInt(arr_ids_s[i]);
        }
        UserDaoInter userDao = new Userdao();
        boolean isDelete = userDao.delNewsById(int_ids);
        if(isDelete){
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            String type = request.getParameter("type");
            if("3".equals(type)){out.print("<script>alert('删除成功!');window.location.href='GetAllVerifiedNewsTitleServlet?verifytype='+'3'</script>");}
            if("1".equals(type)){out.print("<script>alert('删除成功!');window.location.href='GetAllVerifiedNewsTitleServlet?verifytype='+'1'</script>");}
            if("2".equals(type)){out.print("<script>alert('删除成功!');window.location.href='GetAllVerifiedNewsTitleServlet?verifytype='+'2'</script>");}

        }else{
            //如果更新失败，跳回更新页面
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            String type = request.getParameter("type");
            if("3".equals(type)){out.print("<script>alert('删除成功!');window.location.href='GetAllVerifiedNewsTitleServlet?verifytype='+'3'</script>");}
            if("1".equals(type)){out.print("<script>alert('删除成功!');window.location.href='GetAllVerifiedNewsTitleServlet?verifytype='+'1'</script>");}
            if("2".equals(type)){out.print("<script>alert('删除成功!');window.location.href='GetAllVerifiedNewsTitleServlet?verifytype='+'2'</script>");}
        }
    }
}
