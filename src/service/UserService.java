package service;

import dao.UserDao;
import entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
     private UserDao userDao=new UserDao();

     public User login(String name,String pwd){
          User user=null;
          try{
              user=userDao.Login(name);
              if(!user.getPassword().equals(pwd)){
                   System.out.println("密码错误");
                   user=null;
              }
          }catch (Exception e){
               e.printStackTrace();
          }
          return user;
     }
     public int Register(User user){
          int flag=0;
          try{
               flag=UserDao.register(user);
          }catch (SQLException e){
               e.printStackTrace();
          } catch (ClassNotFoundException e) {
               throw new RuntimeException(e);
          }
          return flag;
     }
     public int select_register(User user) throws SQLException, ClassNotFoundException {
          int temp=userDao.unregister(user);
          return temp;
     }
}
