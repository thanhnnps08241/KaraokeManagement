/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karaoke.ui.quanly;

/**
 *
 * @author PS08241 - Nguyen Nhat Thanh
 */
public class sms extends javax.swing.JFrame {

    public sms() {
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlRoot = new javax.swing.JPanel();
        pnlNhaCungCap = new javax.swing.JPanel();
        lblMaNhaCC = new javax.swing.JLabel();
        txtMaNhaCC = new javax.swing.JTextField();
        txtSoDT = new javax.swing.JTextField();
        lblSoDT = new javax.swing.JLabel();
        lblTenNhaCC = new javax.swing.JLabel();
        txtTenNhaCC = new javax.swing.JTextField();
        lblDiaChi = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1050, 690));
        setMinimumSize(new java.awt.Dimension(1050, 690));
        setPreferredSize(new java.awt.Dimension(1050, 690));

        pnlRoot.setBackground(new java.awt.Color(0, 0, 0));
        pnlRoot.setMaximumSize(new java.awt.Dimension(1050, 690));
        pnlRoot.setMinimumSize(new java.awt.Dimension(1050, 690));
        pnlRoot.setOpaque(false);
        pnlRoot.setPreferredSize(new java.awt.Dimension(1050, 690));
        pnlRoot.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlNhaCungCap.setBackground(new java.awt.Color(204, 204, 255));
        pnlNhaCungCap.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin Nhà cung cấp", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 3, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlNhaCungCap.setForeground(new java.awt.Color(204, 255, 255));
        pnlNhaCungCap.setOpaque(false);
        pnlNhaCungCap.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblMaNhaCC.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblMaNhaCC.setForeground(new java.awt.Color(255, 255, 255));
        lblMaNhaCC.setText("Mã Nhà cung cấp");
        pnlNhaCungCap.add(lblMaNhaCC, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, -1, 30));

        txtMaNhaCC.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtMaNhaCC.setForeground(new java.awt.Color(102, 0, 0));
        pnlNhaCungCap.add(txtMaNhaCC, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 40, 120, 30));

        txtSoDT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtSoDT.setForeground(new java.awt.Color(102, 0, 0));
        pnlNhaCungCap.add(txtSoDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, 320, 30));

        lblSoDT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSoDT.setForeground(new java.awt.Color(255, 255, 255));
        lblSoDT.setText("Số điện thoại");
        pnlNhaCungCap.add(lblSoDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, -1, 30));

        lblTenNhaCC.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTenNhaCC.setForeground(new java.awt.Color(255, 255, 255));
        lblTenNhaCC.setText("Tên Nhà cung cấp");
        pnlNhaCungCap.add(lblTenNhaCC, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, -1, 30));

        txtTenNhaCC.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTenNhaCC.setForeground(new java.awt.Color(102, 0, 0));
        pnlNhaCungCap.add(txtTenNhaCC, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, 320, 30));

        lblDiaChi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblDiaChi.setForeground(new java.awt.Color(255, 255, 255));
        lblDiaChi.setText("Địa chỉ");
        pnlNhaCungCap.add(lblDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 70, 30));

        txtDiaChi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtDiaChi.setForeground(new java.awt.Color(102, 0, 0));
        pnlNhaCungCap.add(txtDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 120, 320, 30));

        pnlRoot.add(pnlNhaCungCap, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 680, 230));
        pnlRoot.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 340, 500));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1050, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnlRoot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 690, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnlRoot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(sms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(sms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(sms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(sms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new sms().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblMaNhaCC;
    private javax.swing.JLabel lblSoDT;
    private javax.swing.JLabel lblTenNhaCC;
    private javax.swing.JPanel pnlNhaCungCap;
    private javax.swing.JPanel pnlRoot;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtMaNhaCC;
    private javax.swing.JTextField txtSoDT;
    private javax.swing.JTextField txtTenNhaCC;
    // End of variables declaration//GEN-END:variables
}
