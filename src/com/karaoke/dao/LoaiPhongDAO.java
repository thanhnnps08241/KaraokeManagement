/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karaoke.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import karatnq.helper.JDBCHelper;
import karatnq.model.LoaiPhong;

/**
 *
 * @author Admin
 */
public class LoaiPhongDAO {

    public void insert(LoaiPhong model) {
        String sql = "INSERT INTO LoaiPhong VALUES (?, ?, ?)";
        JDBCHelper.executeUpdate(sql, model.getMaLoai(), model.getTenloai(), model.getGia());
    }

    public void update(LoaiPhong model) {
        String sql = "UPDATE dbo.LoaiPhong SET tenLoai = ?, giaPhong = ? WHERE maLoai = ?";
        JDBCHelper.executeUpdate(sql, model.getTenloai(), model.getGia(), model.getMaLoai());
    }

    public void update(LoaiPhong model, String maLoai) {
        String sql = "UPDATE dbo.LoaiPhong SET maLoai = ?, tenLoai = ?, giaPhong = ? WHERE maLoai = ?";
        JDBCHelper.executeUpdate(sql, model.getMaLoai(), model.getTenloai(), model.getGia(), maLoai);
    }
    public void delete(LoaiPhong model){
        String sql = "DELETE FROM dbo.LoaiPhong WHERE maLoai = ?";
        JDBCHelper.executeUpdate(sql, model.getMaLoai());
    }  
    public void delete(String maLoai){
        String sql = "DELETE FROM dbo.LoaiPhong WHERE maLoai = ?";
        JDBCHelper.executeUpdate(sql, maLoai);
    }  

    public List<LoaiPhong> select() { //select cả 
        String sql = "SELECT * FROM dbo.LoaiPhong";
        return select(sql);
    }

    private List<LoaiPhong> select(String sql, Object... args) {
        List<LoaiPhong> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    LoaiPhong model = readFromResultSet(rs);
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

    private LoaiPhong readFromResultSet(ResultSet rs) throws SQLException {
        LoaiPhong model = new LoaiPhong();
        model.setMaLoai(rs.getString("maLoai")); 
        model.setTenloai(rs.getString("tenLoai"));
        model.setGia(rs.getLong("giaPhong")); 
        return model;
    }
}
