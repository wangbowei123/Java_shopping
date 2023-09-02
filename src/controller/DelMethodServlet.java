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

public class DelMethodServlet extends HttpServlet {
     public void doPost(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
          String commodity = request.getParameter("commodity");
          try {
               Connection connection=DBUtils.getconnection();
               deleteCommodityData(connection,commodity);
          } catch (SQLException e) {
               throw new RuntimeException(e);
          }

          response.sendRedirect("del");
     }
     private void deleteCommodityData(Connection connection, String commodity) throws SQLException {
          try {
               String deleteSql = "DELETE FROM shopping WHERE commodity = ?";
               try (PreparedStatement statement = connection.prepareStatement(deleteSql)) {
                    statement.setString(1, commodity);
                    statement.executeUpdate();
               }
          } catch (SQLException e) {
               e.printStackTrace();
          }
     }

}
