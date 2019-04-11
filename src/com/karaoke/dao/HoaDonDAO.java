/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karaoke.dao;

import com.karaoke.helper.JDBCHelper;
import com.karaoke.model.HoaDon;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class HoaDonDAO {

    public void insert(HoaDon model) {
        model.setGhiChu("");
        String sql = "INSERT INTO dbo.HoaDon (maPhong, codevoucher, soDT, tenKH, tienDV, tienGio, "
                + "tienGiamGia, tongTien, thanhTien, tienKhachDua, gioDatPhong, gioThanhToan, ghiChu, maUser) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        JDBCHelper.executeUpdate(sql, model.getMaPhong(), model.getCodevoucher(), model.getSoDT(), 
                model.getTenKH(), model.getTienDV(), model.getTienGio(), model.getTienGiamGia(), 
                model.getTongTien(), model.getThanhTien(), model.getTienKhachDua(), model.getGioDatPhong(),  
                model.getGioThanhToan(), model.getGhiChu(), model.getMaUser());
    }

    public void update(HoaDon model) {

    }

    public void delete(HoaDon model) {
    }

    public void delete(String maDV) {
    }

    public List<HoaDon> select() { //select cả tên loại (tích với bảng LoaiPhong)
        String sql = "SELECT * FROM dbo.HoaDon";
        return select(sql);
    }

    private List<HoaDon> select(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    HoaDon model = readFromResultSet(rs);
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

    private HoaDon readFromResultSet(ResultSet rs) throws SQLException {
        HoaDon model = new HoaDon();
        model.setMaHD(rs.getInt("maHD"));
        model.setMaPhong(rs.getString("maPhong"));
        model.setCodevoucher(rs.getString("codevoucher"));
        model.setSoDT(rs.getString("soDT"));
        model.setTenKH(rs.getString("tenKH"));
        model.setTienDV(rs.getLong("tienDV"));
        model.setTienGio(rs.getLong("tienGio"));
        model.setTienGiamGia(rs.getLong("tienGiamGia"));
        model.setTongTien(rs.getLong("tongTien"));
        model.setThanhTien(rs.getLong("thanhTien"));
        model.setTienKhachDua(rs.getLong("tienKhachDua"));
        model.setGioDatPhong(rs.getString("gioDatPhong"));
        model.setGioThanhToan(rs.getString("gioThanhToan"));
        model.setGhiChu(rs.getString("ghiChu"));
        model.setMaUser(rs.getString("maUser"));        
        return model;
    }

}
