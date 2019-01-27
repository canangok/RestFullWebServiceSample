package com.mycompany.webservis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.application.Application;

public class Database {
    
private Connection conn = null; 
static String url = "jdbc:mysql://localhost:3306/";
static String dbName = "webservice"; 
static String properties= "?useUnicode=true&characterEncoding=utf8"; 
static String driver = "com.mysql.jdbc.Driver";
static String userName = "root"; 
static String password = ""; 
private ResultSet res;
 private com.mysql.jdbc.Statement statement;

public Connection baglantiAc() throws Exception {
       if(conn==null){
               
               try {
                   Class.forName(driver);
                   this.conn = (Connection)DriverManager.getConnection(url+dbName,userName,password);
                   System.out.println("basarılı");
               }
               catch(ClassNotFoundException | SQLException sqle){
                   System.out.println("fail oldu");
               }
           }
           return conn;
 
}

public void baglantiKapat() throws Exception {
    conn.close();
}

public static void ekle(int id,String isim,String meslek,int yas){
      String url = "jdbc:mysql://localhost/";
      String dbname ="webservice";
      String driver = "com.mysql.jdbc.Driver";
      String username = "root";
      String password = "";
    
      try{
          try(Connection connect = (Connection)DriverManager.getConnection(url+dbName,userName,password)){
              PreparedStatement ps = (PreparedStatement) connect.prepareStatement("INSERT INTO users(user_id,ad_soyad,meslek,yas) VALUES(?,?,?)");
              ps.setInt(0, id);
              ps.setString(1, isim);
              ps.setString(2, meslek);
              ps.setInt(3, yas);
              ps.executeUpdate();
              
              System.out.println("çalıştı ekleme");
          }
      }catch(SQLException e){
          System.out.println(e);
      }
      
}


public static void getAllUser(ArrayList<User> users) throws SQLException{
    users = new ArrayList<User>();
    
    Connection connect = (Connection)DriverManager.getConnection(url+dbName,userName,password);
    try{
        Statement st = (Statement) connect.createStatement();
        String sorgu = "SELECT * FROM users";
        
        ResultSet rs = st.executeQuery(sorgu);
     
        while(rs.next()){
            User user = new User();
            user.setId(rs.getInt("user_id"));
            user.setIsim(rs.getString("ad_soyad"));
            user.setMeslek(rs.getString("meslek"));
            user.setYas(rs.getInt("yas"));
            
            users.add(user);
            user = null;  
        }
        
        rs.close();
        
        
    }catch(Exception e){
        System.out.println(e);
    }
    
      
}


}

