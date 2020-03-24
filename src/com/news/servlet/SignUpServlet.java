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

@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        String name= request.getParameter("name");
        String pwd = request.getParameter("pwd");
        String mail = request.getParameter("email");
        int is_admin = Integer.parseInt(request.getParameter("isadmin"));
        String code = request.getParameter("verificationcode");
        //从session中获取用于检查的邮箱和验证码
        String chkcode = session.getAttribute("code_for_check").toString();
        String chkmail = session.getAttribute("mail_for_check").toString();
        //创建user对象
        MyUser myUser  = new MyUser(name,pwd,mail,is_admin);
        UserDaoInter userDao = new Userdao();
        //审核三个东西
        if(userDao.checkUserName(name)){
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.print("<script>alert('用户名已存在!');window.location.href='Newregister.jsp'</script>");
            return;
        }else if(name==""){
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.print("<script>alert('用户名未填写!');window.location.href='Newregister.jsp'</script>");
            return;
        } else if(userDao.checkEmail(mail)){
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.print("<script>alert('邮箱已被使用!');window.location.href='Newregister.jsp'</script>");
            return;
        }else if(mail==""){
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
        }
        //##########################这边我验证完不正确暂时用了重定向，''里面填跳转的注册页面，想办法把邮箱和密码填回去

        //注册
        userDao.getRegister(myUser);

        //将用户名存储到session对象中
        session.setAttribute("name",name);
        session.setAttribute("id",is_admin);
        session.setAttribute("topid",is_admin);
        request.setAttribute("Lnewstype","1");
        session.setAttribute("flag",1);
        //删除session中用于本次注册的邮箱和验证码
        session.removeAttribute("code_for_check");
        session.removeAttribute("mail_for_check");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("<script>alert('注册成功!'); window.location.href='NewSetPageServlet?Lnewstype='+'1'</script>");

        // getDispatcher(String path)其本意为将该用户请求直接转发给其它web页面；
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
