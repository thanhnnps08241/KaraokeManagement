/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karaoke.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class JDBCHelper {

    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String dburl = "jdbc:sqlserver://localhost:1433;databasename=KaraokeManagement;username=sa;password=123";
    //private static String username = "sa";
    //private static String password = "123";

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Xây dựng PreparedStatement
     *
     * @param sql là câu lệnh SQL có thể chứa tham số, nó có thể là lời gọi thủ
     * tục
     * @param args là danh sách các giá trị được cung cấp cho các tham số trong
     * câu lệnh sql
     * @return PreparedStatement tạo được throws java.sql.SQLException lỗi sai
     * cú pháp
     *
     */
    public static PreparedStatement preparedStatement(String sql, Object... args) throws SQLException {
        Connection connection = DriverManager.getConnection(dburl);
        PreparedStatement pstmt = null;
        if (sql.trim().startsWith("{")) {
            pstmt = connection.prepareCall(sql);

        } else {
            pstmt = connection.prepareStatement(sql);
        }
        for (int i = 0; i < args.length; i++) {
            pstmt.setObject(i + 1, args[i]);

        }
        return pstmt;
    }

    /**
     * Thực hiện câu lệnh SQL thao tác (INSERT, UPDATE, DELETE) hoặc thủ tục lưu
     * thao tác dữ liệu
     *
     *      * @param sql là câu lệnh SQL có thể chứa tham số, nó có thể là lời gọi
     * thủ tục
     * @param args là danh sách các giá trị được cung cấp cho các tham số trong
     * câu lệnh sql
     */
    public static void executeUpdate(String sql, Object... args) {
        //Sinh code SQL 
        /*
        if (sql.startsWith("INSERT")) {
            System.out.println("");
            System.out.print(sql.substring(0, sql.lastIndexOf("(") + 1));
            if (args.length > 1) {
                for (int i = 0; i < args.length - 1; i++) {
                    System.out.print("N'" + args[i] + "', ");
                }
                System.out.print("N'" + args[args.length - 1] + "')");
            } else {
                System.out.print("N'" + args[args.length - 1] + "')");

            }
        } else {
            System.out.println("");
            System.out.println(sql.substring(0,sql.length()-2)+" '"+args[0]+"'");
            
        }        
        
         */
        try {
            PreparedStatement stmt = preparedStatement(sql, args);
            try {
                stmt.executeUpdate();
            } finally {
                stmt.getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet executeQuery(String sql, Object... args) {
        try {

            PreparedStatement stmt = preparedStatement(sql, args);
            return stmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }
}
