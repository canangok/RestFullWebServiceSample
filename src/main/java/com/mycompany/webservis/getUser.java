
package com.mycompany.webservis;

import static com.mycompany.webservis.Database.url;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/getUser")
public class getUser {
    
        
private Connection conn = null; 
static String url = "jdbc:mysql://localhost:3306/";
static String dbName = "webservice"; 
static String properties= "?useUnicode=true&characterEncoding=utf8"; 
static String driver = "com.mysql.jdbc.Driver";//MySQL-Java bağlantısını sağlayan JDBC sürücüsü
static String userName = "root"; 
static String password = ""; 
private ResultSet res; 
 private com.mysql.jdbc.Statement statement;
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON) 
    @Path("/getListValue")
    public List<User> getListAValue() throws Exception{
        
        Database db = new Database();
        db.baglantiAc();

        List<User> userList=new ArrayList<>();
      
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
            userList.add(user);
            user = null;  
        }   
        rs.close();  
    }catch(Exception e){
        System.out.println(e);
    }  
    return userList;
    
    }
}