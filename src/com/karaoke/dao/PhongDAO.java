/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karaoke.dao;

import com.karaoke.helper.JDBCHelper;
import com.karaoke.model.Phong;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class PhongDAO {   
    
    public void insert(Phong model) {
        String sql = "INSERT INTO Phong (maPhong,  maLoai, tinhTrang) VALUES (?, ?, ?)";
        JDBCHelper.executeUpdate(sql, model.getMaPhong(),  model.getMaLoai(), model.isTinhTrang());
    }
    
    public void update(Phong model){
        String sql = "UPDATE dbo.Phong SET maLoai = ?, tinhTrang = ? WHERE maPhong = ?";
        JDBCHelper.executeUpdate(sql, model.getMaLoai(), model.isTinhTrang(), model.getMaPhong());
    }
    public void update(Phong model, String maPhong){
        String sql = "UPDATE dbo.Phong SET maPhong = ?, maLoai = ?, tinhTrang = ? WHERE maPhong = ?";
        JDBCHelper.executeUpdate(sql, model.getMaPhong(), model.getMaLoai(), model.isTinhTrang(), maPhong);
    }
    
    public void delete(Phong model){
        String sql = "DELETE FROM dbo.Phong WHERE maPhong = ?";
        JDBCHelper.executeUpdate(sql, model.getMaPhong());
    }  
    public void delete(String maPhong){
        String sql = "DELETE FROM dbo.Phong WHERE maPhong = ?";
        JDBCHelper.executeUpdate(sql, maPhong);
    }  
    
    
    public List<Phong> select( ){ //select cả tên loại (tích với bảng LoaiPhong)
        String sql = "SELECT * FROM dbo.Phong";
        return select(sql);
    }
     public List<Phong> fillter(String clause){ //
        String sql = "SELECT * FROM dbo.Phong "+clause;
        return select(sql);
    }

    private List<Phong> select(String sql, Object... args) {
        List<Phong> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    Phong model = readFromResultSet(rs);
                    list.add(model);
                }
            }finally{
                rs.getStatement().getConnection().close();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private Phong readFromResultSet(ResultSet rs) throws SQLException {
        Phong model = new Phong();
        model.setMaPhong(rs.getString("maPhong"));
        model.setMaLoai(rs.getString("maLoai"));
        model.setTinhTrang(rs.getBoolean("tinhTrang"));
        return model;
    }
}
