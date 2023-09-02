package controller;

import utils.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class addcommodityServlet extends HttpServlet {
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          String commodity=request.getParameter("name");
          double price = Double.parseDouble(request.getParameter("price"));
          int number = Integer.parseInt(request.getParameter("number"));
          try{
               Connection connection= DBUtils.getconnection();
               String insertSql = "INSERT INTO shopping(commodity, price, number) VALUES (?, ?, ?)";
               try (PreparedStatement statement = connection.prepareStatement(insertSql)) {
                    statement.setString(1, commodity);
                    statement.setDouble(2, price);
                    statement.setInt(3, number);
                    statement.executeUpdate();
               }
          } catch (SQLException e) {
               e.printStackTrace();
          }
          response.sendRedirect("del");
     }

}
