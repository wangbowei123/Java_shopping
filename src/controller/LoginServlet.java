package controller;

import entity.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
     private UserService userService=new UserService();
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
          String username=request.getParameter("id");
          String userpwd=request.getParameter("pwd");
          User user=userService.login(username,userpwd);
          System.out.println(user==null);
          if(user!=null){
               System.out.println("登陆成功");
               HttpSession session=request.getSession();
               session.setAttribute("user",user);
               request.getRequestDispatcher("LoginSuccess.jsp").forward(request,response);
          }
          else{
               System.out.println("登陆失败");
               request.getRequestDispatcher("index.jsp").forward(request,response);
          }
     }
}
