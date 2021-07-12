package com.soft1921.maven.study;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version :1.0
 * @date :2020/9/24
 * @ClassName :
 */

public class DBUtils {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/spiders?serverTimezone=UTC", "root", "kobe24");
        return connection;
    }


    public static void close(ResultSet rs, Statement st, Connection co){
        try {
            if (st!=null){
                st.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        try {
            if (co!=null){
                co.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        try {
            if (rs!=null){
                rs.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void close(Connection co,Statement st){
        try {
            if (st!=null){
                st.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        try {
            if (co!=null){
                co.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static int update(String sql,Object...objs){
        Connection connection=null;

        int code=0;
        PreparedStatement statement=null;
        try {
            connection= DBUtils.getConnection();
            statement = connection.prepareStatement(sql);
            for(int i=0;i<objs.length;i++){
                statement.setObject(i+1, objs[i]);
            }
            code=statement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally{
            close(connection, statement);
        }
        return code;
    }

    public static List<Map<String, Object>> query(String sql, Object...objs){
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet rs=null;
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();

        try {
            connection= DBUtils.getConnection();
            statement = connection.prepareStatement(sql);
            for(int i=0;i<objs.length;i++){
                statement.setObject(i+1, objs[i]);
            }
            rs=statement.executeQuery();

            ResultSetMetaData rsmd=rs.getMetaData();

            while(rs.next()){
                HashMap<String, Object> entry=new HashMap<>();
                for(int i=0;i<rsmd.getColumnCount();i++){
                    entry.put(rsmd.getColumnLabel(i+1), rs.getObject(i+1));
                }
                list.add(entry);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally{
            close(rs, statement,connection);
        }
        return list;
    }
}
