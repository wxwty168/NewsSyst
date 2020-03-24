package com.news.servlet;

import com.news.dao.UserDaoInter;
import com.news.dao.Userdao;
import com.news.pojo.MyUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ForgetPasswordServlet")
public class ForgetPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        String pwd = request.getParameter("pwd");
        String mail = request.getParameter("email");
        String code = request.getParameter("verificationcode");
        //从session中获取用于检查的邮箱和验证码
        String chkcode = session.getAttribute("code_for_check").toString();
        String chkmail = session.getAttribute("mail_for_check").toString();

        UserDaoInter userDao = new Userdao();
        //审核
        if(mail==""){
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.print("<script>alert('邮箱未填写!');window.location.href='Newregister.jsp'</script>");
        } else if (mail!=chkmail){
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.print("<script>alert('邮箱不一致!');window.location.href='Newregister.jsp'</script>");
        }
        else if(!userDao.checkVerificationCode(code, chkcode)){
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.print("<script>alert('验证码不正确!');window.location.href='Newregister.jsp'</script>");
            return;
        }else if(pwd==""){
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.print("<script>alert('密码未填写!');window.location.href='Newregister.jsp'</script>");
            return;
        }//##########################这边我验证完不正确暂时用了重定向，''里面填跳转的注册页面，想办法把邮箱和密码填回去

        //重置密码
        userDao.resetPasswordByMail(mail,pwd);

        //删除session中用于本次重置密码的邮箱和验证码
        session.removeAttribute("code_for_check");
        session.removeAttribute("mail_for_check");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("<script>alert('密码修改成功!'); window.location.href='Newlogin.jsp'</script>");
        //修改密码成功后跳转登录页面
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
