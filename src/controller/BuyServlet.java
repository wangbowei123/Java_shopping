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

public class BuyServlet extends HttpServlet {
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
          // 获取购买链接中的商品数据参数
          String commodity = request.getParameter("commodity");
          double price = Double.parseDouble(request.getParameter("price"));
          int number = Integer.parseInt(request.getParameter("number"));

          // 在这里可以对商品数据进行处理，如存入数据库、跳转到购买页面等
          // ...
          try {
               int currentNumber = getCurrentNumber(commodity);
               int nextNumber = getNextNumber(commodity);
               System.out.println("商品数量"+nextNumber);
               insertOrUpdateCommodityData(commodity,price,nextNumber);
               updateCommodityData(commodity, price, currentNumber - 1);
          } catch (SQLException e) {
               throw new RuntimeException(e);
          }
          // 重定向回商品列表页面或其他页面
          response.sendRedirect("buy.jsp");
     }

     private void insertOrUpdateCommodityData(String commodity, double price, int nextNumber) throws SQLException {
          Connection connection = DBUtils.getconnection();
          try {
               if (existsCommodity(commodity)) {
                    // 如果已存在相同 commodity，执行更新操作
                    updateCommodityData(connection, commodity, price, nextNumber);
               } else {
                    // 否则，执行插入操作
                    insertCommodityData(connection, commodity, price, nextNumber);
               }
          } catch (SQLException e) {
               e.printStackTrace();
          } finally {
               connection.close();
          }
     }
     private void updateCommodityData(Connection connection, String commodity, double price, int nextNumber) throws SQLException {
          String updateSql = "UPDATE house SET price = ?, number = ? WHERE commodity = ?";
          try (PreparedStatement statement = connection.prepareStatement(updateSql)) {
               statement.setDouble(1, price);
               statement.setInt(2, nextNumber);
               statement.setString(3, commodity);
               statement.executeUpdate();
          }
     }
     private void insertCommodityData(Connection connection, String commodity,double price, int nextNumber) throws SQLException {
          try{
               String insertSql = "INSERT INTO house(commodity, price, number) VALUES (?, ?, ?)";
               try (PreparedStatement statement = connection.prepareStatement(insertSql)) {
                    statement.setString(1, commodity);
                    statement.setDouble(2, price);
                    statement.setInt(3, nextNumber);
                    statement.executeUpdate();
               }
          } catch (SQLException e) {
               e.printStackTrace();
          }
     }

     private boolean existsCommodity(String commodity) throws SQLException {
          Connection connection = DBUtils.getconnection();
          boolean exists = false;

          try {
               String selectSql = "SELECT COUNT(*) FROM house WHERE commodity = ?";
               try (PreparedStatement statement = connection.prepareStatement(selectSql)) {
                    statement.setString(1, commodity);
                    try (ResultSet resultSet = statement.executeQuery()) {
                         if (resultSet.next()) {
                              int count = resultSet.getInt(1);
                              exists = (count > 0);
                         }
                    }
               }
          } catch (SQLException e) {
               e.printStackTrace();
          } finally {
               connection.close();
          }

          return exists;
     }

     private int getNextNumber(String commodity) throws SQLException {
          Connection connection = DBUtils.getconnection();
          int nextNumber = 1;
          try {
               String selectSql = "SELECT number FROM house WHERE commodity = ?";
               try (PreparedStatement statement = connection.prepareStatement(selectSql)) {
                    statement.setString(1, commodity);  // 设置占位符的值为传入的商品名
                    try (ResultSet resultSet = statement.executeQuery()) {
                         if (resultSet.next()) {
                              nextNumber = resultSet.getInt("number") + 1;
                         }
                    }
               }
          } catch (SQLException e) {
               e.printStackTrace();
          }
          return nextNumber;
     }
     private void updateCommodityData(String commodity, double price, int number) throws SQLException {
          Connection connection = DBUtils.getconnection();
          try {
               String updateSql = "UPDATE shopping SET price = ?, number = ? WHERE commodity = ?";
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
     private int getCurrentNumber(String commodity) throws SQLException {
          Connection connection = DBUtils.getconnection();
          int currentNumber = 0;
          try {
               String selectSql = "SELECT number FROM shopping WHERE commodity = ?";
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
}
