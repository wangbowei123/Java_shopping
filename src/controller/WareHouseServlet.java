package controller;

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

public class WareHouseServlet extends HttpServlet {
     public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
          Connection connection = null;
          ResultSet resultSet=null;
          try {
               connection = DBUtils.getconnection();
               String sql="select * from house";
               PreparedStatement preparedStatement=connection.prepareStatement(sql);
               resultSet=preparedStatement.executeQuery();
          } catch (SQLException e) {
               throw new RuntimeException(e);
          }
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

                    String commodity = resultSet.getString("commodity");
                    double price = Double.parseDouble(resultSet.getString("price"));
                    int number = Integer.parseInt(resultSet.getString("number"));
                    String buyLink = "del_user?commodity=" + commodity + "&price=" + price + "&number=" + number;
                    pw.println("<form action='" + buyLink + "' method='post'>");
                    pw.println("<button type='submit'>删除</button>");
                    pw.println("</form>");

                    // 在此处添加购买商品的按钮，可以是超链接或表单提交按钮，以下是示例超链接按钮：
                    pw.println("</td>");
                    pw.println("</tr>");
               }
          } catch (SQLException e) {
               e.printStackTrace();
          }
          String sc="main_page?action=main_page";
          pw.println("<form action='" + sc + "' method='post'>");
          pw.println("<button type='submit'>商城</button>");
          pw.println("</form>");
          pw.println("</table>");

          try{
               connection.close();
          }catch (SQLException e){
               e.printStackTrace();
          }
     }
}
