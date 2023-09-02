package controller;

import entity.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RegisterServlet extends HttpServlet {
     private UserService userService=new UserService();
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

          String pwd1=request.getParameter("pwd1");
          String pwd2=request.getParameter("pwd2");

          User user=new User();
          user.setName(request.getParameter("id"));

          int flag=0;
          try {
               flag=userService.select_register(user);
          } catch (SQLException e) {
               throw new RuntimeException(e);
          } catch (ClassNotFoundException e) {
               throw new RuntimeException(e);
          }

          if(flag==1){
               System.out.println("用户名已被注册");
               request.getRequestDispatcher("Signupwrong.jsp").forward(request,response);
               return;
          }

          if(pwd1.equals(pwd2)){
               user.setPassword(pwd1);
               int temp=userService.Register(user);
               System.out.println("改变的行数 "+temp);
               if(temp>0){
                    System.out.println("注册成功");
                    request.getRequestDispatcher("SignupSuccess.jsp").forward(request,response);
               }
               else{
                    System.out.println("注册失败");
                    request.getRequestDispatcher("Signup.jsp").forward(request,response);
               }
          }
          else{
               System.out.println("两次输入的密码不同，注册失败");
               request.getRequestDispatcher("Signup.jsp").forward(request,response);
          }
     }
}
