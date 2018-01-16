package com.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Redis.RedisServer;
import com.encrypt.Encrypt;

public class UserDao{

    //根据用户名查找用户密码
    public String getPW(String username){
        String password=null;
        String sql="select * from user where username=?";
        Connection connection = getConecttion_m();
        PreparedStatement psmt =null;
        ResultSet rs=null;
        try{
            password = RedisServer.findPW(username);                //先从缓存里找
            if(password!=null){
                return password;
            }
            psmt=connection.prepareStatement(sql);
            psmt.setString(1,username);
            rs=psmt.executeQuery();
            if(rs.next()){
                password = rs.getString("password");
                RedisServer.addUser(username,password);             //将用户添加到缓存
                return password;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            try{
                if(psmt!=null)psmt.close();
                if(connection!=null)connection.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return password;
    }

    //创建新用户,如果用户名已存在则返回false
    public boolean addUser(String username,String password){
        Connection connection=getConecttion_m();
        PreparedStatement psmt=null;
        String en_pw=Encrypt.MD5(password,username);                //对传入的密码进行加密
        boolean result=false;
        String sql_addUser="Insert INTO user(username,password) VALUES(?,?)";
        try{
            if(RedisServer.findPW(username)!=null && getPW(username)!=null)    //确认没有同名用户,先判断缓存里的
                return result;
            else {
                psmt=connection.prepareStatement(sql_addUser);
                psmt.setString(1,username);
                psmt.setString(2,en_pw);
                psmt.executeUpdate();
                result =true;
                RedisServer.addUser(username,en_pw);                         //将注册好的用户加入缓存
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            try{
                if(psmt!=null)psmt.close();
                if(connection!=null)connection.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
            return result;
        }
    }

    //检查用户密码是否正确
    public String checkPW(String username,String password){
        String result=null;
        String pw=getPW(username);
        if(pw!=null){
            if ( Encrypt.MD5(password,username).equals(pw) ){
                result="true";
            }
            else {
                result="false";
            }
        }
        return result;
    }

    //设置连接的主从库信息
    public static final String IPAddress_db = "192.168.56.1";
    public static final String Port_m="3307";
    //public static final String Port_s0="3308";
    public static final String DbName= "test_m";

    //连接主库test_m
    public static Connection getConecttion_m(){
        return  getConnecttion(IPAddress_db,Port_m,DbName);
    }
    //连接从库test_s0
  /*  public static Connection getConecttion_s0(){
        return  getConnecttion(IPAddress_db,Port_s0,DbName);
    }*/

    //建立连接
    public static Connection getConnecttion(String IpAddress, String Port , String database){
        String driver="com.mysql.jdbc.Driver";
        String url="jdbc:mysql://"+IpAddress+":"+Port+"/"+database+"?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        String user="root";
        String password="000000";
        Connection connection=null;
        try{
            Class.forName(driver);
            connection=DriverManager.getConnection(url,user,password);

        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
    public static void main(String[]args) {
/*//        		测试方法
        System.out.println(new UserDao().getPW("12345"));

        //new UserDao().addUser("134523", "134523");
        return;*/
    }
}