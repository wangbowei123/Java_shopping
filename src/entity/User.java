package entity;

public class User {
     private String name;
     private String password;
     private String sex;
     private int age;

     public String getName(){
          return name;
     }
     public void setName(String name){
          this.name=name;
     }

     public String getPassword(){
          return password;
     }
     public void setPassword(String password){
          this.password=password;
     }

     public String getSex(){
          return sex;
     }
     public void setSex(String sex){
          this.sex=sex;
     }
     public int getAge(){
          return age;
     }
     public void setAge(int age){
          this.age=age;
     }
}
