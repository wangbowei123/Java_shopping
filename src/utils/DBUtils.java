package utils;

import java.sql.*;

public class DBUtils {
     static{
          try{
               Class.forName("com.mysql.cj.jdbc.Driver");
               System.out.println("数据库驱动加载成功");
          }catch (ClassNotFoundException e){
               e.printStackTrace();
          }
     }
     public static Connection getconnection() throws SQLException{
          String url="jdbc:mysql://localhost:3306/new_demo?useUnicode=true&characterEncoding=utf8&useSSL=true";
          String sqlname="root";
          String sqlpwd="Wbw200301";
          Connection connection= DriverManager.getConnection(url,sqlname,sqlpwd);
          return connection;
     }

     public static void close(ResultSet resultSet, Statement statement,Connection connection) throws SQLException{
          if(resultSet!=null){
               resultSet.close();
          }
          if(statement!=null){
               statement.close();
          }
          if(connection!=null){
               connection.close();
          }
     }
}
