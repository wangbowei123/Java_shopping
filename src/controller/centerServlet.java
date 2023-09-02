package controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

public class centerServlet extends HttpServlet {
     public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
          response.setContentType("text/html;charset=utf-8");
          PrintWriter pw = response.getWriter();
          pw.println("<div style='text-align: center; margin-top: 50px;'>");
          String add = "add?action=add";
          pw.println("<form action='" + add + "' method='post'>");
          pw.println("<button type='submit'>增加商品</button>");
          pw.println("</form>");

//          pw.println("<form action='del' method='post'>");
//          pw.println("<input type='hidden' name='action' value='delete'>");  // 指定操作为删除商品
//          pw.println("<input type='submit' value='删除商品'>");
//          pw.println("</form>");
          String buyLink = "del";
          pw.println("<form action='" + buyLink + "' method='get'>");
          pw.println("<button type='submit'>删除商品</button>");
          pw.println("</form>");


          String buy = "index?action=index";
          pw.println("<form action='" + buy + "' method='post'>");
          pw.println("<button type='submit'>返回主页</button>");
          pw.println("</form>");
          pw.println("</div>");
     }
}
