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
import java.util.Random;

@WebServlet("/ForgetPwdSendEmailServlet")
public class ForgetPwdSendEmailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String mail = request.getParameter("email");
        UserDaoInter userDao = new Userdao();
        if(!userDao.checkEmail(mail)){
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.print("<script>alert('邮箱未注册!');window.location.href='Newregister.jsp'</script>");
            return;
        }

        //设置随机验证码
        Random rand = new Random();
        int ran_int = rand.nextInt(999999-101100+1)+101100;
        /*int randNumber =rand.nextInt(MAX - MIN + 1) + MIN; // randNumber 将被赋值为一个 MIN 和 MAX 范围内的随机数*/
        String content = "您的验证码为: "+ran_int;
        HttpSession session = request.getSession();
        session.setAttribute("code_for_check",ran_int);
        session.setAttribute("mail_for_check",mail);
        userDao.sendEmail(mail, content);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("<script>alert('邮件已发送!');window.location.href=''</script>");
        //#####################################这边我暂时用了重定向，''里面填跳转的忘记密码页面，想办法把邮箱和密码填回去
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
