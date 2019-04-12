/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karaoke.dao;

import com.karaoke.helper.JDBCHelper;
import com.karaoke.model.Users;
import com.karaoke.model.Voucher;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class VoucherDAO {

    public void insert(Voucher model) {
        String sql = "INSERT INTO dbo.Voucher (codevoucher, triGia, hanDung) VALUES (?,?,?)";
        JDBCHelper.executeUpdate(sql, model.getCodevoucher(), model.getTriGia(), model.getHanDung());
    }

    public void update(Voucher model) {
        String sql = "UPDATE dbo.Voucher SET  triGia = ?, hanDung = ? WHERE codevoucher = ?";
        JDBCHelper.executeUpdate(sql, model.getTriGia(), model.getHanDung(), model.getCodevoucher());
    }

    public void update(Voucher model, String codevoucher) {
        String sql = "UPDATE dbo.Voucher SET  codevoucher = ?, triGia = ?, hanDung = ? WHERE codevoucher = ?";
        JDBCHelper.executeUpdate(sql, model.getCodevoucher(), model.getTriGia(), model.getHanDung(), codevoucher);
    }

    public void delete(Voucher model) {
        String sql = "DELETE FROM dbo.Voucher WHERE codevoucher = ?";
        JDBCHelper.executeUpdate(sql, model.getCodevoucher());
    }

    public List<Voucher> select() {
        String sql = "SELECT * FROM dbo.Voucher";
        return select(sql);
    }

    public List<Voucher> select(String sql, Object... args) {
        List<Voucher> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    Voucher model = readFromResultSet(rs);
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

    private Voucher readFromResultSet(ResultSet rs) throws SQLException {
        Voucher model = new Voucher();
        model.setCodevoucher(rs.getString("codevoucher"));
        model.setTriGia(rs.getInt("triGia"));
        model.setHanDung(rs.getString("hanDung"));

        return model;
    }
}
