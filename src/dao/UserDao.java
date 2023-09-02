package dao;

import entity.User;
import utils.DBUtils;

import java.sql.*;

public class UserDao {

     public User Login(String username) throws Exception{
          Connection connection=DBUtils.getconnection();

          String sql="SELECT * FROM user WHERE username = ?";
          PreparedStatement statement=connection.prepareStatement(sql);
          statement.setString(1,username);

          ResultSet resultSet=statement.executeQuery();

          User user=null;

          while(resultSet.next()){
               user=new User();
               user.setName(resultSet.getString("username"));
               user.setPassword(resultSet.getString("password"));
               System.out.println("user");
          }
          DBUtils.close(resultSet,statement,connection);

          return user;
     }
     public static int register(User user) throws SQLException, ClassNotFoundException {
          Connection connection= DBUtils.getconnection();
          String sql="INSERT INTO user(username,password) VALUES(?,?)";
          PreparedStatement statement=connection.prepareStatement(sql);

          statement.setString(1,user.getName());
          statement.setString(2,user.getPassword());

          int flag=statement.executeUpdate();

          DBUtils.close(null,statement,connection);
          return flag;
     }
     public static int unregister(User user) throws ClassNotFoundException, SQLException {
          int temp=0;
          Connection connection=DBUtils.getconnection();
          String sql="SELECT *FROM user WHERE username= ?";
          try(PreparedStatement statement=connection.prepareStatement(sql)){
               statement.setString(1, user.getName());
               try(ResultSet resultSet=statement.executeQuery()){
                    if(resultSet.next()){
                         temp=1;
                         return temp;
                    }
                    else{
                         return temp;
                    }
               }
          }
     }
}
