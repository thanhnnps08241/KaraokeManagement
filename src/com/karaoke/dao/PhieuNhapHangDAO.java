/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karaoke.dao;


import com.karaoke.helper.JDBCHelper;
import com.karaoke.model.PhieuNhapHang;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Admin
 */
public class PhieuNhapHangDAO { 
          
     public void insert(PhieuNhapHang model) {
        String sql = "INSERT INTO dbo.PhieuNhapHang (maNhaCC, tongTien, ngayNhap, maUser) VALUES(?,?,?,?)";
        JDBCHelper.executeUpdate(sql, model.getMaNhaCC(), model.getTongTien(), model.getNgayNhap(), model.getMaUser());
    }
    public void update(PhieuNhapHang model) {
        String sql = "UPDATE dbo.PhieuNhapHang SET maNhaCC = ?, tongTien = ?, ngayNhap = ?, maUser = ? WHERE maPN = ?";
        JDBCHelper.executeUpdate(sql, model.getMaNhaCC(),  model.getTongTien(), model.getNgayNhap(), model.getMaUser(), model.getMaPN());
    }
    
    public void delete(PhieuNhapHang model){
        String sql = "DELETE FROM dbo.PhieuNhapHang WHERE maPN = ?";
        JDBCHelper.executeUpdate(sql, model.getMaPN());
    }
    public void delete(String maPN){
        String sql = "DELETE FROM dbo.PhieuNhapHang WHERE maPN = ?";
        JDBCHelper.executeUpdate(sql, maPN);
    }
    
    public List<PhieuNhapHang> select(){ //select tất cả 
        String sql = "SELECT * FROM dbo.PhieuNhapHang ";
        return select(sql);
    }  
    private List<PhieuNhapHang> select(String sql, Object... args) {
        List<PhieuNhapHang> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    PhieuNhapHang model = readFromResultSet(rs);
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
    
    

    private PhieuNhapHang readFromResultSet(ResultSet rs) throws SQLException {
        PhieuNhapHang model = new PhieuNhapHang();
        model.setMaPN(rs.getInt("maPN"));
        model.setMaNhaCC(rs.getString("maNhaCC")); 
        model.setNgayNhap(rs.getString("ngayNhap"));
        model.setTongTien(rs.getLong("tongTien"));   
        model.setMaUser(rs.getString("maUser"));
        return model;
    }
        
}
