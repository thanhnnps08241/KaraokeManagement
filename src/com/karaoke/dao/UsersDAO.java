/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karaoke.dao;

import com.karaoke.helper.JDBCHelper;
import com.karaoke.model.Users;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author Admin
 */
public class UsersDAO {

    public void insert(Users model) {
        String sql = "INSERT INTO dbo.USERS (username, hoTen, ngaySinh, gioiTinh, soDT, cmnd, role, matKhau) VALUES (?,?,?,?,?,?,?,?)";
        JDBCHelper.executeUpdate(sql, model.getUsername(), model.getHoTen(), model.getNgaySinh(), model.isGioiTinh(), model.getSoDT(), model.getCmnd(), model.isRole(), model.getMatKhau());
    }

    public void update(Users model, String username) {
        String sql = "UPDATE dbo.USERS SET username = ?, hoTen = ?, ngaySinh = ?, gioiTinh = ?, soDT = ?, cmnd = ?, role = ?, matKhau = ? WHERE username = ?";
        JDBCHelper.executeUpdate(sql, model.getUsername(), model.getHoTen(), model.getNgaySinh(), model.isGioiTinh(), model.getSoDT(), model.getCmnd(), model.isRole(), model.getMatKhau(), username);
    }
    public void update(Users model) {
        String sql = "UPDATE dbo.USERS SET hoTen = ?, ngaySinh = ?, gioiTinh = ?, soDT = ?, cmnd = ?, role = ?, matKhau = ? WHERE username = ?";
        JDBCHelper.executeUpdate(sql, model.getHoTen(), model.getNgaySinh(), model.isGioiTinh(), model.getSoDT(), model.getCmnd(), model.isRole(), model.getMatKhau(), model.getUsername());
    }

    public void delete(Users model) {
        String sql = "DELETE FROM dbo.USERS WHERE username = ?";
        JDBCHelper.executeUpdate(sql, model.getUsername());
    }

    public void delete(String username) {
        String sql = "DELETE FROM dbo.USERS WHERE username = ?";
        JDBCHelper.executeUpdate(sql, username);
    }

    public List<Users> select() {
        String sql = "SELECT username, hoTen, ngaySinh, gioiTinh, soDT, cmnd, role, matKhau FROM dbo.USERS";
        return select(sql);
    }

    public List<Users> select(String sql, Object... args) {
        List<Users> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    Users model = readFromResultSet(rs);
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private Users readFromResultSet(ResultSet rs) throws SQLException {
        Users model = new Users();
        model.setUsername(rs.getString("username")); 
        model.setHoTen(rs.getString("hoTen")); 
        model.setNgaySinh(rs.getString("ngaySinh"));
        model.setGioiTinh(rs.getBoolean("gioiTinh")); 
        model.setSoDT(rs.getString("soDT")); 
        model.setCmnd(rs.getString("cmnd")); 
        model.setRole(rs.getBoolean("role")); 
        model.setMatKhau(rs.getString("matKhau")); 

        return model;
    }
}
