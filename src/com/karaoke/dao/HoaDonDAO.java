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
        String sql = "INSERT INTO dbo.HoaDonPhong (maPhong, nguoiLap, gioDatPhong, tenKH, soDT, tienDV, tienGio, tongTien, tienKhachDua, gioThanhToan) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        JDBCHelper.executeUpdate(sql, model.getMaPhong(), model.getNguoiLap(), model.getGioDatPhong(), model.getTenKH(), model.getSoDT(), model.getTienDV(), model.getTienGio(), model.getTongTien(), model.getTienKhachDua(), model.getGioThanhToan());
    }

    public void update(HoaDon model) {

    }

    public void delete(HoaDon model) {
    }

    public void delete(String maDV) {
    }

    public List<HoaDon> select() { //select cả tên loại (tích với bảng LoaiPhong)
        String sql = "SELECT * FROM dbo.HoaDonPhong";
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
        model.setNguoiLap(rs.getString("nguoiLap"));
        model.setGioDatPhong(rs.getString("gioDatPhong"));
        model.setTenKH(rs.getString("tenKH"));
        model.setSoDT(rs.getString("soDT"));
        model.setTienDV(rs.getLong("tienDV"));
        model.setTienGio(rs.getLong("tienGio"));
        model.setTongTien(rs.getLong("tongTien"));
        model.setGiamGia(rs.getLong("giamGia"));
        model.setThanhTien(rs.getLong("thanhTien"));
        model.setTienKhachDua(rs.getLong("tienKhachDua"));
        model.setGioThanhToan(rs.getString("gioThanhToan"));

        return model;
    }

}
