package com.tool;

import com.sun.rowset.CachedRowSetImpl;

import javax.sql.RowSet;
import java.sql.*;
import java.util.Map;

/**
 * Created by yedaran on 2018/1/18.
 * 用于执行SQL操作
 */
public class SqlHelper {
    //执行更新语句
    public int updateSql(String sql){
        Connection connection = getConecttion_m();
        PreparedStatement psmt=null;
        int result=0;
        try {
            psmt = connection.prepareStatement(sql);
            result=psmt.executeUpdate();
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

    //执行查询语句
    public RowSet excuteQuery(String sql){
        Connection connection = getConecttion_m();
        PreparedStatement psmt=null;
        CachedRowSetImpl rs=null;
        try {
            rs = new CachedRowSetImpl();
            psmt = connection.prepareStatement(sql);
            rs.populate(psmt.executeQuery());
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
            return rs;
        }
    }




    //连接主库test_m
    private static Connection getConecttion_m(){
        Settings settings =new Settings();
        Map<String,String>setting= settings.getSettings("Database");

        return  getConnecttion(setting);
    }
    private static Connection getConnecttion(Map<String,String>setting){
        String IPAddress=setting.get("IPAdress");
        String Port=setting.get("port");
        String DbName=setting.get("DbName");
        String driver="com.mysql.jdbc.Driver";
        String url="jdbc:mysql://"+IPAddress+":"+Port+"/"+DbName+"?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        String user=setting.get("user");
        String password=setting.get("password");
        Connection connection=null;
        try{
            Class.forName(driver);
            connection= DriverManager.getConnection(url,user,password);

        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
}
