package controller;

import service.UserService;
import utils.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserListServlet extends HttpServlet {
     public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
          Connection connection = null;
          ResultSet resultSet=null;
          try {
               connection = DBUtils.getconnection();
               String sql="select * from shopping";
               PreparedStatement preparedStatement=connection.prepareStatement(sql);
               resultSet=preparedStatement.executeQuery();
          } catch (SQLException e) {
               throw new RuntimeException(e);
          }
//          response.setContentType("text/html;charset=utf-8");
//          PrintWriter pw=response.getWriter();
//          pw.println("<table border='1' cellspacing='0' width='400' height='80' align='center'>");
//          pw.println("<caption> 商品信息表</caption>");
//          pw.println("<tr align='center'>");
//          pw.println("<td>商品名</td><td>价格</td><td>库存</td>");
//          pw.println("</tr>");
//          try{
//               while(resultSet.next()){
//                    pw.println("<tr align='center'><td>"+resultSet.getString("commodity")+"</td><td>"+
//                            resultSet.getString("price")+"</td><td>"+resultSet.getString("number")+"</td><td>");
//               }
//          }catch(SQLException e){
//               e.printStackTrace();
//          }
//          pw.println("</table>");
          doPost(request,response,resultSet);
          try{
               connection.close();
          }catch (SQLException e){
               e.printStackTrace();
          }
     }
     public void doPost(HttpServletRequest request, HttpServletResponse response,ResultSet resultSet) throws IOException {
          response.setContentType("text/html;charset=utf-8");
          PrintWriter pw = response.getWriter();
          pw.println("<table border='1' cellspacing='0' width='400' height='80' align='center'>");
          pw.println("<caption> 商品信息表</caption>");
          pw.println("<tr align='center'>");
          pw.println("<td>商品名</td><td>价格</td><td>库存</td><td>操作</td>"); // 添加一个额外的列用于放置按钮
          pw.println("</tr>");
          try {
               while (resultSet.next()) {
                    pw.println("<tr align='center'>");
                    pw.println("<td>" + resultSet.getString("commodity") + "</td>");
                    pw.println("<td>" + resultSet.getString("price") + "</td>");
                    pw.println("<td>" + resultSet.getString("number") + "</td>");
                    pw.println("<td>");
                    // 在此处添加购买商品的按钮，可以是超链接或表单提交按钮，以下是示例超链接按钮：
                    String commodity = resultSet.getString("commodity");
                    double price = Double.parseDouble(resultSet.getString("price"));
                    int number = Integer.parseInt(resultSet.getString("number"));
                    String buyLink = "buy?commodity=" + commodity + "&price=" + price + "&number=" + number;
                    pw.println("<form action='" + buyLink + "' method='post'>");
                    pw.println("<button type='submit'>购买</button>");
                    pw.println("</form>");
                    pw.println("</td>");
                    pw.println("</tr>");
               }
          } catch (SQLException e) {
               e.printStackTrace();
          }
          String cangku="cangku?action=cangku";
          pw.println("<form action='" + cangku + "' method='post'>");
          pw.println("<button type='submit'>仓库</button>");
          pw.println("</form>");
          pw.println("</table>");
     }
}