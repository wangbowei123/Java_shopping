package controller;
import utils.DBUtils;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DelUser extends HttpServlet {
     public void doPost(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
          try {
               String commodity = request.getParameter("commodity");
               double price = Double.parseDouble(request.getParameter("price"));
               updateCommodityData(commodity,price);
          } catch (SQLException e) {
               throw new RuntimeException(e);
          }
          response.sendRedirect("house");
     }
     private void updateCommodityData(String commodity,double price) throws SQLException {
          Connection connection=DBUtils.getconnection();
          try {
               if (existsCommodity(commodity)) {
                    deleteCommodityData(connection,commodity);
               }
               else {
                    int currentNumber = getCurrentNumber(commodity);
                    changeCommodityData(commodity, price, currentNumber - 1);
               }
          } catch (SQLException e) {
               e.printStackTrace();
          } finally {
               connection.close();
          }

     }
     private void deleteCommodityData(Connection connection, String commodity) throws SQLException {
          try {
               String deleteSql = "DELETE FROM house WHERE commodity = ?";
               try (PreparedStatement statement = connection.prepareStatement(deleteSql)) {
                    statement.setString(1, commodity);
                    statement.executeUpdate();
               }
          } catch (SQLException e) {
               e.printStackTrace();
          }
     }
     private int getCurrentNumber(String commodity) throws SQLException {
          Connection connection = DBUtils.getconnection();
          int currentNumber = 0;
          try {
               String selectSql = "SELECT number FROM house WHERE commodity = ?";
               try (PreparedStatement statement = connection.prepareStatement(selectSql)) {
                    statement.setString(1, commodity);
                    try (ResultSet resultSet = statement.executeQuery()) {
                         if (resultSet.next()) {
                              currentNumber = resultSet.getInt("number");
                         }
                    }
               }
          } catch (SQLException e) {
               e.printStackTrace();
          }
          return currentNumber;
     }
     private boolean existsCommodity(String commodity) throws SQLException {
          Connection connection = DBUtils.getconnection();
          boolean exists = false;
          if(getCurrentNumber(commodity)==1) exists=true;
          return exists;
     }
     private void changeCommodityData(String commodity, double price, int number) throws SQLException {
          Connection connection = DBUtils.getconnection();
          try {
               String updateSql = "UPDATE house SET price = ?, number = ? WHERE commodity = ?";
               try (PreparedStatement statement = connection.prepareStatement(updateSql)) {
                    statement.setDouble(1, price);
                    statement.setInt(2, number);
                    statement.setString(3, commodity);
                    statement.executeUpdate();
               }
          } catch (SQLException e) {
               e.printStackTrace();
          }
     }
}
