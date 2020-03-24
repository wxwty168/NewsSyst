package com.news.servlet;

import com.news.dao.UserDaoInter;
import com.news.dao.Userdao;
import com.news.pojo.MyUser;
import com.news.util.SendMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取编码字符集
        request.setCharacterEncoding("utf-8");
        /*请求处理*/
        /*获取用户名及其密码*/
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        int is_admin;
        //创建user对象
        MyUser myUser = new MyUser();
        myUser.setUser_name(name);
        myUser.setPwd(pwd);

        UserDaoInter userDao = new Userdao();
        /*如果验证通过进行页面跳转,*/
        if(userDao.getLogin(myUser)){
            MyUser myUser1 = new MyUser();
            myUser1 = userDao.getUserInfo(myUser);
            name = myUser1.getUser_name();
            is_admin = myUser1.getIs_admin();
            HttpSession session = request.getSession();
            //将用户名存储到session对象中
            session.setAttribute("name",name);
            session.setAttribute("id",is_admin);
            session.setAttribute("topid",is_admin);
            request.setAttribute("Lnewstype","1");
            session.setAttribute("flag",1);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.print("<script>window.location.href='NewSetPageServlet?Lnewstype='+'1'</script>");

        }else {
            //跳转回登录页面
            request.setAttribute("msg1", "用户名或密码错误");
            request.getRequestDispatcher("Newlogin.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
