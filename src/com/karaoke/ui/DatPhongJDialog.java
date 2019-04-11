/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karaoke.ui;

import com.karaoke.dao.HoaDonDAO;
import com.karaoke.helper.JDBCHelper;
import com.karaoke.helper.JOptionPaneHelper;
import com.karaoke.helper.JOptionPaneHelper;
import com.karaoke.helper.XuLy;
import com.karaoke.model.HoaDon;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Admin
 */
public class DatPhongJDialog extends javax.swing.JDialog {

    String maPhong;
    String maUser;

    /**
     * Creates new form BookingRoom
     */
    public DatPhongJDialog(java.awt.Frame parent, boolean modal, String maPhong, String maUser) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        this.maPhong = maPhong;
        this.maUser = maUser;
        txtTenPhong.setText("THÔNG TIN ĐẶT PHÒNG " + maPhong);
        getRootPane().setDefaultButton(btnDatPhong);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBookingRoom = new javax.swing.JPanel();
        txtTenPhong = new javax.swing.JLabel();
        lblTenKH = new javax.swing.JLabel();
        lblSDT = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        btnDatPhong = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Thông Tin Đặt Phòng");
        setMinimumSize(new java.awt.Dimension(400, 230));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlBookingRoom.setBackground(new java.awt.Color(255, 255, 255));
        pnlBookingRoom.setMaximumSize(new java.awt.Dimension(400, 230));
        pnlBookingRoom.setMinimumSize(new java.awt.Dimension(400, 230));
        pnlBookingRoom.setPreferredSize(new java.awt.Dimension(400, 230));
        pnlBookingRoom.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTenPhong.setBackground(new java.awt.Color(51, 51, 51));
        txtTenPhong.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtTenPhong.setForeground(new java.awt.Color(255, 0, 0));
        txtTenPhong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTenPhong.setText("ĐẶT PHÒNG");
        pnlBookingRoom.add(txtTenPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 400, 30));

        lblTenKH.setBackground(new java.awt.Color(51, 51, 51));
        lblTenKH.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTenKH.setForeground(new java.awt.Color(0, 102, 153));
        lblTenKH.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTenKH.setText("Tên Khách hàng:");
        pnlBookingRoom.add(lblTenKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 120, 30));

        lblSDT.setBackground(new java.awt.Color(51, 51, 51));
        lblSDT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSDT.setForeground(new java.awt.Color(0, 102, 153));
        lblSDT.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblSDT.setText("Số điện thoại:");
        pnlBookingRoom.add(lblSDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 120, 30));

        txtTenKH.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtTenKH.setForeground(new java.awt.Color(51, 51, 51));
        pnlBookingRoom.add(txtTenKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 210, 30));

        txtSDT.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtSDT.setForeground(new java.awt.Color(51, 51, 51));
        pnlBookingRoom.add(txtSDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 210, 30));

        btnDatPhong.setBackground(new java.awt.Color(255, 255, 255));
        btnDatPhong.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnDatPhong.setForeground(new java.awt.Color(0, 102, 153));
        btnDatPhong.setText("Đặt Phòng");
        btnDatPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatPhongActionPerformed(evt);
            }
        });
        pnlBookingRoom.add(btnDatPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, 110, 40));

        getContentPane().add(pnlBookingRoom, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDatPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatPhongActionPerformed
        if (!txtTenKH.getText().matches("[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ\\s]{1,}")) {
            JOptionPaneHelper.popup("loi", this, "Tên Khách Hàng không hợp lệ", "LỖI");
            txtTenKH.requestFocus();
        } else if (!txtSDT.getText().matches("\\d{10,12}") && !txtSDT.getText().equals("")) {
            txtSDT.requestFocus();
            JOptionPaneHelper.popup("loi", this, "Điện thoại không hợp lệ", "LỖI");
        } else {
            if (txtSDT.getText().trim().equals("")) {
                txtSDT.setText(null);
            }
            try {
                HoaDon hd = new HoaDon();
                HoaDonDAO hdd = new HoaDonDAO();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                hd.setMaPhong(maPhong);
                hd.setGioDatPhong(format.format(new Date()));
                ResultSet rs = JDBCHelper.executeQuery("SELECT * FROM dbo.USERS WHERE maUser = ?", maUser);
                while (rs.next()) {
                    hd.setMaUser(rs.getString("maUser")); //lấy tên Nhân viên đang làm việc

                }
                hd.setTenKH(XuLy.makeupHoTen(txtTenKH.getText()));
                hd.setSoDT(txtSDT.getText());
                rs = JDBCHelper.executeQuery("SELECT giaPhong,phutMacDinh FROM dbo.LoaiPhong,dbo.Phong,dbo.Information WHERE LoaiPhong.maLoai = Phong.maLoai AND maPhong = ?", maPhong);
                if (rs.next()) {
                    hd.setTienGio((long) (rs.getLong("giaPhong") * (rs.getInt("phutMacDinh")) / 60.0));//set tiền mặc định

                }
                hdd.insert(hd);
                JDBCHelper.executeUpdate("UPDATE dbo.Phong SET tinhTrang = 1 WHERE maPhong = ?", maPhong);
            } catch (SQLException ex) {
                Logger.getLogger(DatPhongJDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
            dispose();
        }

    }//GEN-LAST:event_btnDatPhongActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DatPhongJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DatPhongJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DatPhongJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DatPhongJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DatPhongJDialog dialog = new DatPhongJDialog(new javax.swing.JFrame(), true, "RM101", null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDatPhong;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblTenKH;
    private javax.swing.JPanel pnlBookingRoom;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JLabel txtTenPhong;
    // End of variables declaration//GEN-END:variables
}
