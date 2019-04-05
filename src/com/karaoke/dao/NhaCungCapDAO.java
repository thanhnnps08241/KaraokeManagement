/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karaoke.dao;


import com.karaoke.helper.JDBCHelper;
import com.karaoke.model.NhaCungCap;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Admin
 */
public class NhaCungCapDAO { 
          
     public void insert(NhaCungCap model) {
        String sql = "INSERT INTO dbo.NhaCungCap (maNhaCC, tenNhaCC, diaChi, soDT) VALUES(?,?,?,?)";
        JDBCHelper.executeUpdate(sql, model.getMaNhaCC(), model.getTenNhaCC(), model.getDiaChi(), model.getSoDT());
    }
    public void update(NhaCungCap model) {
        String sql = "UPDATE dbo.NhaCungCap SET  tenNhaCC = ?, diaChi = ?, soDT = ? WHERE maNhaCC = ?";
        JDBCHelper.executeUpdate(sql, model.getTenNhaCC(), model.getDiaChi(), model.getSoDT(), model.getMaNhaCC());
    }
    
    public void delete(NhaCungCap model){
        String sql = "DELETE FROM dbo.NhaCungCap WHERE maNhaCC = ?";
        JDBCHelper.executeUpdate(sql, model.getMaNhaCC());
    }
    public void delete(String maNhaCC){
        String sql = "DELETE FROM dbo.NhaCungCap WHERE maNhaCC = ?";
        JDBCHelper.executeUpdate(sql, maNhaCC);
    }
    
    public List<NhaCungCap> select(){ //select tất cả 
        String sql = "SELECT * FROM dbo.NhaCungCap ";
        return select(sql);
    }  
    private List<NhaCungCap> select(String sql, Object... args) {
        List<NhaCungCap> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    NhaCungCap model = readFromResultSet(rs);
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
    
    

    private NhaCungCap readFromResultSet(ResultSet rs) throws SQLException {
        NhaCungCap model = new NhaCungCap();
        model.setMaNhaCC(rs.getString("maNhaCC"));
        model.setTenNhaCC(rs.getString("tenNhaCC")); 
        model.setDiaChi(rs.getString("diaChi"));
        model.setSoDT(rs.getString("soDT"));     
        return model;
    }
        
}
