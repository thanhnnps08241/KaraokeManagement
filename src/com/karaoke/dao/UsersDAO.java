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
        String sql = "INSERT INTO dbo.USERS (maUser, username, password, hoTen, ngaySinh, gioiTinh, soDT, cmnd, email, role, active) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        JDBCHelper.executeUpdate(sql, model.getMaUser(), model.getUsername(), model.getPassword(), model.getHoTen(), model.getNgaySinh(), model.isGioiTinh(), model.getSoDT(), model.getCmnd(), model.getEmail(), model.isRole(), model.isActive());
    }

    public void update(Users model) {
        String sql = "UPDATE dbo.USERS SET username = ?, password = ?, hoTen = ?, ngaySinh = ?, gioiTinh = ?, soDT = ?, cmnd = ?, email = ?, role = ?, active = ? WHERE maUser = ?";
        JDBCHelper.executeUpdate(sql, model.getUsername(), model.getPassword(), model.getHoTen(), model.getNgaySinh(), model.isGioiTinh(), model.getSoDT(), model.getCmnd(), model.getEmail(), model.isRole(), model.isActive(), model.getMaUser());
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
        String sql = "SELECT * FROM dbo.USERS ORDER BY role DESC";
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
        model.setMaUser(rs.getString("maUser"));
        model.setUsername(rs.getString("username")); 
        model.setPassword(rs.getString("password"));
        model.setHoTen(rs.getString("hoTen"));
        model.setNgaySinh(rs.getString("ngaySinh"));
        model.setGioiTinh(rs.getBoolean("gioiTinh"));
        model.setSoDT(rs.getString("soDT"));
        model.setCmnd(rs.getString("cmnd"));
        model.setEmail(rs.getString("email"));
        model.setRole(rs.getBoolean("role"));
        model.setActive(rs.getBoolean("active"));

        return model;
    }
}
