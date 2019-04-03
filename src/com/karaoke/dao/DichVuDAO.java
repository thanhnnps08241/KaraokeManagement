/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karaoke.dao;


import com.karaoke.helper.JDBCHelper;
import com.karaoke.model.DichVu;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class DichVuDAO { 
          
     public void insert(DichVu model) {
        String sql = "INSERT INTO dbo.DichVu (maLoaiDV, tenDV, giaBan, tonKho, hinh) VALUES(?,?,?,?,?)";
        JDBCHelper.executeUpdate(sql, model.getMaLoai(), model.getTenDV(), model.getGiaBan(), model.getTonKho(), model.getHinh());
    }
    public void update(DichVu model) {
        String sql = "UPDATE dbo.DichVu SET tenDV = ?, maLoaiDV = ?, giaBan = ?, hinh = ? WHERE maDV = ?";
        JDBCHelper.executeUpdate(sql, model.getTenDV(),  model.getMaLoai(), model.getGiaBan(), model.getHinh(), model.getMaDV());
    }
    
    public void delete(DichVu model){
        String sql = "DELETE FROM dbo.DichVu WHERE maDV = ?";
        JDBCHelper.executeUpdate(sql, model.getMaDV());
    }
    public void delete(String maDV){
        String sql = "DELETE FROM dbo.DichVu WHERE maDV = ?";
        JDBCHelper.executeUpdate(sql, maDV);
    }
    
    public List<DichVu> select(){ //select tất cả 
        String sql = "SELECT * FROM dbo.DichVu ORDER BY tenDV ASC ";
        return select(sql);
    }  
    private List<DichVu> select(String sql, Object... args) {
        List<DichVu> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    DichVu model = readFromResultSet(rs);
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
    
    private List<DichVu> selecttheotenloai(String sql, Object... args) {
        List<DichVu> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    DichVu model = readFromResultSet(rs);
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

    private DichVu readFromResultSet(ResultSet rs) throws SQLException {
        DichVu model = new DichVu();
        model.setMaDV(rs.getInt("maDV"));
        model.setMaLoai(rs.getString("maLoaiDV")); 
        model.setTenDV(rs.getString("tenDV"));
        model.setGiaBan(rs.getLong("giaBan"));
        model.setTonKho(rs.getInt("tonKho"));
        model.setHinh(rs.getString("hinh"));
        return model;
    }
        
}
