package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserLinkServlet extends HttpServlet {
//     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//          request.getRequestDispatcher("Signup.jsp").forward(request,response);
//     }
     protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
          String action = request.getParameter("action");
          if("SignupUser".equals(action)){
               request.getRequestDispatcher("Signup.jsp").forward(request,response);
          }
          if("control".equals(action)){
               request.getRequestDispatcher("control.jsp").forward(request,response);
          }
          if("index".equals(action)){
               request.getRequestDispatcher("index.jsp").forward(request,response);
          }
          if("conntroll".equals(action)){
               response.sendRedirect("lll");
          }
          if("add".equals(action)){
               request.getRequestDispatcher("addCommodity.jsp").forward(request,response);
          }
          if("main_page".equals(action)){
               response.sendRedirect("list");
          }
          if("bu".equals(action)){
               response.sendRedirect("house");
          }
          if("comeback".equals(action)){
               request.getRequestDispatcher("index.jsp").forward(request,response);
          }
          if("cangku".equals(action)){
               response.sendRedirect("house");
          }
     }
}
