package com.goods.dao;

import java.sql.*;

public class BaseDao {
    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    private  static final String URL = "jdbc:mysql://localhost:3306/goods?characterEncoding=utf8&useSSL=false&serverTimezone=CST";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    //获取数据库的连接
    public Connection getConnection() {
        try {
            //获取驱动名称
            Class.forName(DRIVER_CLASS);
            //获取连接
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);

        }
         catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
                e.printStackTrace();
            }
        return null;
    }

    //关闭数据库的连接
    public void closeAll(Connection conn, PreparedStatement prs, ResultSet rs) {
        try {
            if (conn != null) {
                conn.close();
            }
            if (prs != null) {
                prs.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int executeUpdate(String sql,Object[] paramObjs){
        Connection conn = null;
        PreparedStatement prs = null;

        try {
            // 1、获得连接对象
            conn = this.getConnection();

            // 2、获得预编译语句集
            prs = conn.prepareStatement(sql);

            // 3、设置占位符的值
            if(paramObjs!=null && paramObjs.length>0){
                for(int i=0;i<paramObjs.length;i++){
                    prs.setObject(i+1,paramObjs[i]);
                }
            }
            // 4、执行sql语句
            return prs.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeAll(conn,prs,null);
        }
        return 0;
    }


}
