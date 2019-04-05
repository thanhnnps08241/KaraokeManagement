/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karaoke.dao;
import com.karaoke.helper.JDBCHelper;
import com.karaoke.model.LoaiDichVu;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class LoaiDichVuDAO {

    public void insert(LoaiDichVu model) {
        String sql = "INSERT INTO LoaiDichVu VALUES (?, ?)";
        JDBCHelper.executeUpdate(sql, model.getMaLoaiDV(), model.getTenLoaiDV());
    }

    public void update(LoaiDichVu model) {
        String sql = "UPDATE LoaiDichVu SET tenLoaiDV = ? WHERE maLoaiDV = ?";
        JDBCHelper.executeUpdate(sql, model.getTenLoaiDV(), model.getMaLoaiDV());
    }

    public void update(LoaiDichVu model, String maLoaiDV) {
        String sql = "UPDATE LoaiDichVu SET maLoaiDV = ?, tenLoaiDV = ? WHERE maLoaiDV = ?";
        JDBCHelper.executeUpdate(sql, model.getMaLoaiDV(), model.getTenLoaiDV(), maLoaiDV);
    }

    public void delete(LoaiDichVu model) {
        String sql = "DELETE FROM dbo.LoaiDichVu WHERE maLoaiDV = ?";
        JDBCHelper.executeUpdate(sql, model.getMaLoaiDV());
    }

    public void delete(String maLoaiDV) {
        String sql = "DELETE FROM dbo.LoaiDichVu WHERE maLoaiDV = ?";
        JDBCHelper.executeUpdate(sql, maLoaiDV);
    }

    public List<LoaiDichVu> select() { //select cả 
        String sql = "SELECT * FROM dbo.LoaiDichVu ORDER BY tenLoaiDV ASC";
        return select(sql);
    }

    private List<LoaiDichVu> select(String sql, Object... args) {
        List<LoaiDichVu> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    LoaiDichVu model = readFromResultSet(rs);
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

    private LoaiDichVu readFromResultSet(ResultSet rs) throws SQLException {
        LoaiDichVu model = new LoaiDichVu();
        model.setMaLoaiDV(rs.getString("maLoaiDV"));
        model.setTenLoaiDV(rs.getString("tenLoaiDV"));
        return model;
    }

}
