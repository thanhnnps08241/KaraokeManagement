package com.karaoke.ui;

import com.karaoke.dao.PhongDAO;
import com.karaoke.helper.JDBCHelper;
import com.karaoke.helper.OpenJPanel;
import com.karaoke.helper.RoundedPanel;
import com.karaoke.model.Phong;
import com.sun.java.swing.plaf.windows.WindowsScrollBarUI;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;

/**
 *
 * @author TVD
 */
public class PhongHatJPanel extends javax.swing.JPanel {

    Color roomColor = null;
    Icon roomIcon = null;
    final ScheduledExecutorService svc = Executors.newScheduledThreadPool(1);
    ResultSet rs = null;
    String clause = "WHERE ", username;

    public PhongHatJPanel(String username) {
        initComponents();
        loadRoom("");
        this.username = username;
        pnlRoom.repaint();
        pnlRoom.revalidate();
        scrRoom.setPreferredSize(new Dimension(1060, 160 * 4));
        scrRoom.getVerticalScrollBar().setUnitIncrement(80);
        scrRoom.setViewportView(pnlRoom);
        scrRoom.getVerticalScrollBar().setUI(new WindowsScrollBarUI());
        scrRoom.getViewport().setOpaque(false);
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
        scrRoom = new javax.swing.JScrollPane();
        pnlRoom = new javax.swing.JPanel();
        chkThuong = new javax.swing.JCheckBox();
        chkPhongTrong = new javax.swing.JCheckBox();
        chkVIP = new javax.swing.JCheckBox();
        chkPhongDangDung = new javax.swing.JCheckBox();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1050, 690));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(1050, 690));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlRoot.setBackground(new java.awt.Color(0, 0, 51));
        pnlRoot.setMaximumSize(new java.awt.Dimension(1050, 690));
        pnlRoot.setMinimumSize(new java.awt.Dimension(1050, 690));
        pnlRoot.setOpaque(false);
        pnlRoot.setPreferredSize(new java.awt.Dimension(1050, 690));
        pnlRoot.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scrRoom.setBackground(new java.awt.Color(255, 255, 255));
        scrRoom.setBorder(null);
        scrRoom.setMaximumSize(new java.awt.Dimension(1060, 650));
        scrRoom.setMinimumSize(new java.awt.Dimension(1060, 650));
        scrRoom.setOpaque(false);
        scrRoom.setPreferredSize(new java.awt.Dimension(1060, 650));

        pnlRoom.setBackground(new java.awt.Color(255, 255, 255));
        pnlRoom.setMinimumSize(new java.awt.Dimension(1030, 500));
        pnlRoom.setOpaque(false);
        pnlRoom.setPreferredSize(new java.awt.Dimension(1030, 500));
        pnlRoom.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 12, 10));
        scrRoom.setViewportView(pnlRoom);

        pnlRoot.add(scrRoom, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1050, -1));

        chkThuong.setBackground(new java.awt.Color(0, 0, 0));
        chkThuong.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        chkThuong.setForeground(new java.awt.Color(255, 255, 255));
        chkThuong.setText("Phòng thường");
        chkThuong.setBorder(null);
        chkThuong.setFocusPainted(false);
        chkThuong.setFocusable(false);
        chkThuong.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkThuong.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        chkThuong.setMaximumSize(new java.awt.Dimension(153, 30));
        chkThuong.setMinimumSize(new java.awt.Dimension(153, 30));
        chkThuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkThuongActionPerformed(evt);
            }
        });
        pnlRoot.add(chkThuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(893, 670, 100, -1));

        chkPhongTrong.setBackground(new java.awt.Color(0, 0, 0));
        chkPhongTrong.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        chkPhongTrong.setForeground(new java.awt.Color(0, 204, 0));
        chkPhongTrong.setText("Phòng trống");
        chkPhongTrong.setBorder(null);
        chkPhongTrong.setFocusPainted(false);
        chkPhongTrong.setFocusable(false);
        chkPhongTrong.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkPhongTrong.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        chkPhongTrong.setMaximumSize(new java.awt.Dimension(153, 30));
        chkPhongTrong.setMinimumSize(new java.awt.Dimension(153, 30));
        chkPhongTrong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPhongTrongActionPerformed(evt);
            }
        });
        pnlRoot.add(chkPhongTrong, new org.netbeans.lib.awtextra.AbsoluteConstraints(763, 650, 90, -1));

        chkVIP.setBackground(new java.awt.Color(0, 0, 0));
        chkVIP.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        chkVIP.setForeground(new java.awt.Color(255, 255, 0));
        chkVIP.setText("Phòng VIP");
        chkVIP.setBorder(null);
        chkVIP.setFocusPainted(false);
        chkVIP.setFocusable(false);
        chkVIP.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkVIP.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        chkVIP.setMaximumSize(new java.awt.Dimension(153, 30));
        chkVIP.setMinimumSize(new java.awt.Dimension(153, 30));
        chkVIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkVIPActionPerformed(evt);
            }
        });
        pnlRoot.add(chkVIP, new org.netbeans.lib.awtextra.AbsoluteConstraints(913, 650, 80, -1));

        chkPhongDangDung.setBackground(new java.awt.Color(0, 0, 0));
        chkPhongDangDung.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        chkPhongDangDung.setForeground(new java.awt.Color(255, 0, 0));
        chkPhongDangDung.setText("Phòng đang sử dụng");
        chkPhongDangDung.setBorder(null);
        chkPhongDangDung.setFocusPainted(false);
        chkPhongDangDung.setFocusable(false);
        chkPhongDangDung.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkPhongDangDung.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        chkPhongDangDung.setMaximumSize(new java.awt.Dimension(153, 30));
        chkPhongDangDung.setMinimumSize(new java.awt.Dimension(153, 30));
        chkPhongDangDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPhongDangDungActionPerformed(evt);
            }
        });
        pnlRoot.add(chkPhongDangDung, new org.netbeans.lib.awtextra.AbsoluteConstraints(723, 670, 130, -1));

        add(pnlRoot, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 690));
    }// </editor-fold>//GEN-END:initComponents

    private void chkThuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkThuongActionPerformed
        // TODO add your handling code here:
        loadRoom(locPhong());
    }//GEN-LAST:event_chkThuongActionPerformed

    private void chkPhongDangDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPhongDangDungActionPerformed
        // TODO add your handling code here:
        loadRoom(locPhong());

    }//GEN-LAST:event_chkPhongDangDungActionPerformed

    private void chkPhongTrongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPhongTrongActionPerformed
        // TODO add your handling code here:
        loadRoom(locPhong());

    }//GEN-LAST:event_chkPhongTrongActionPerformed

    private void chkVIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkVIPActionPerformed
        // TODO add your handling code here:
        loadRoom(locPhong());

    }//GEN-LAST:event_chkVIPActionPerformed

    private void loadRoom(String cls) {

        if (cls.equals("")) {
            clause = cls; //cập nhật clause
        }
        //XÓA TẤT CẢ MỖI KHI LOAD LẠI
        pnlRoom.removeAll();

        //SET SIZE BAN ĐẦU CHO CÁC COMPONENTS
        PhongDAO ph = new PhongDAO();
        List<Phong> listphong = ph.filter(cls);
        int slp = listphong.size();
        while (slp % 5 != 0) {
            slp++;
        }
        pnlRoom.setPreferredSize(new Dimension(1000, 155 * slp / 5));
        scrRoom.getVerticalScrollBar().setUnitIncrement(20);
        scrRoom.setViewportView(pnlRoom);
        scrRoom.getVerticalScrollBar().setUI(new WindowsScrollBarUI());

        //Tạo biến lưu mã HD
        for (Phong phong : listphong) {
            String noidung = "<html> <style>body{\n"
                    + "    text-align: center;\n"
                    + "    font-family: 'Tahoma';\n"
                    + "    color: white;\n"
                    + "}</style>";
            noidung += "<b><font size=5>" + (phong.getMaPhong()) + "</b></font>";

            RoundedPanel room = new RoundedPanel(); //tạo Phòng
            room.setPreferredSize(new Dimension(235, 150));
            
            JLabel roomInfo = new JLabel();
            roomInfo.setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            roomInfo.setPreferredSize(new Dimension(230, 150));
            if (phong.isTinhTrang()) {
                roomInfo.setToolTipText("Click vào để xem hóa đơn chi tiết phòng " + phong.getMaPhong());
                roomColor = new Color(155, 10, 10);
                noidung += "<br><b>Đang sử dụng</b><br><br>";
                if (phong.getMaLoai().equals("VIP")) {
                    roomInfo.setIcon(new ImageIcon(this.getClass().getResource("/com/karaoke/images/icon/SingingVIP.png")));
                } else {
                    roomInfo.setIcon(new ImageIcon(this.getClass().getResource("/com/karaoke/images/icon/Singing.png")));

                }
                ResultSet rs = JDBCHelper.executeQuery("SELECT * FROM dbo.HoaDon WHERE maPhong = ? AND gioThanhToan IS NULL", phong.getMaPhong());
                try {
                    while (rs.next()) {
                        String giovao = rs.getString("gioDatPhong").substring(11, 19);
                        String tenKH = rs.getString("tenKH");
                        noidung += "Giờ vào: <br><b><font size = 4 color='yellow'>" + giovao + "</font></b><br>";
                        noidung += "Tên KH: <br><b><font size = 4 color='yellow'>" + tenKH + "</font></b><br>";
                        roomInfo.setName(rs.getString("maHD"));

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(PhongHatJPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                roomInfo.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        try {
                            new RoomDetail(null, true, phong.getMaPhong(), roomInfo.getName(), username).setVisible(true);
                            loadRoom("");

                        } catch (SQLException ex) {
                            Logger.getLogger(PhongHatJPanel.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ParseException ex) {
                            Logger.getLogger(PhongHatJPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }

                });
            } else {
                roomInfo.setToolTipText("Click vào để đặt phòng " + phong.getMaPhong());
                roomColor = new Color(44, 153, 55);
                noidung += "<br><b><i>Sẵn sàng</i></b>";
                if (phong.getMaLoai().equals("VIP")) {
                    roomInfo.setIcon(new ImageIcon(this.getClass().getResource("/com/karaoke/images/icon/ReadyToSingVIP.png"))); // NOI18N

                } else {
                    roomInfo.setIcon(new ImageIcon(this.getClass().getResource("/com/karaoke/images/icon/ReadyToSing.png"))); // NOI18N

                }
                roomInfo.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        new DatPhongJDialog(null, true, phong.getMaPhong(), username).setVisible(true);
                        loadRoom("");
                    }

                });

            }
            roomInfo.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    roomColor = room.getBackground();
                    room.setBackground(Color.gray);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    room.setBackground(roomColor);

                }

            });
            roomInfo.setText(noidung);
            roomInfo.setHorizontalAlignment(SwingConstants.CENTER);
            room.add(roomInfo);
            room.setBackground(roomColor);
            pnlRoom.add(room);
            pnlRoom.revalidate();
            pnlRoom.repaint();

        }
        if (listphong.isEmpty()) {
            pnlRoom.setPreferredSize(new Dimension(1000, 50));
            for (int i = 0; i < 15; i++) {
                if (i == 7) {
                    JButton button = new JButton();
                    button.setText("<html><b><font color = #006699 size = 4>" + "Không có phòng nào<br>Click vào để quay lại" + "</html>");
                    button.setPreferredSize(new Dimension(235, 150));
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            loadRoom("");

                        }
                    });
                    button.setBackground(Color.YELLOW);
                    button.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    pnlRoom.add(button);

                } else {
//                    if (i % 2 == 0) {
//                        MarqueeLabel label = new MarqueeLabel(XuLy.msg("<center><font color = red>KaraTNQ</font><br> Phần mềm quản lý phòng hát Karaoke chuyên nghiệp"), MarqueeLabel.RIGHT_TO_LEFT, 20);
//                        label.setPreferredSize(new Dimension(235, 150));
//                        pnlRoom.add(label);
//                    } else {
//                        MarqueeLabel label = new MarqueeLabel(XuLy.msg("<center><font color = red>KaraTNQ</font><br> Phần mềm quản lý phòng hát Karaoke chuyên nghiệp"), MarqueeLabel.LEFT_TO_RIGHT, 20);
//                        label.setPreferredSize(new Dimension(235, 150));
//                        pnlRoom.add(label);
//                    }
                }

            }

        }
        requestFocusInWindow();

    }

    private String locPhong() {
        String[] cls = new String[4];
        if (chkVIP.isSelected()) {
            cls[0] = "VIP";
            if (chkThuong.isSelected()) {
                cls[1] = "NORMAL";
            } else {
                cls[1] = "VIP";
            }
        }
        if (chkThuong.isSelected()) {
            cls[0] = "NORMAL";
            if (chkVIP.isSelected()) {
                cls[1] = "VIP";
            } else {
                cls[1] = "NORMAL";
            }
        }
        if (!chkVIP.isSelected() && !chkThuong.isSelected()) {
            cls[0] = "";
            cls[1] = "";
        }

        if (chkPhongDangDung.isSelected()) {
            cls[2] = "1";
            if (chkPhongTrong.isSelected()) {
                cls[3] = "0";
            } else {
                cls[3] = "1";
            }
        }
        if (chkPhongTrong.isSelected()) {
            cls[2] = "0";
            if (chkPhongDangDung.isSelected()) {
                cls[3] = "1";
            } else {
                cls[3] = "0";
            }
        }
        if (!chkPhongDangDung.isSelected() && !chkPhongTrong.isSelected()) {
            cls[2] = "";
            cls[3] = "";
        }
        if ((chkVIP.isSelected() && chkThuong.isSelected() && chkPhongDangDung.isSelected() && chkPhongTrong.isSelected())) {
            cls[0] = "";
            cls[1] = "";
            cls[2] = "";
            cls[3] = "";
        }
        return " WHERE (maLoai LIKE '%" + cls[0] + "%' OR maLoai LIKE '%" + cls[1] + "%') AND (tinhTrang LIKE '%" + cls[2] + "%' OR tinhTrang LIKE '%" + cls[3] + "%')";

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chkPhongDangDung;
    private javax.swing.JCheckBox chkPhongTrong;
    private javax.swing.JCheckBox chkThuong;
    private javax.swing.JCheckBox chkVIP;
    private javax.swing.JPanel pnlRoom;
    private javax.swing.JPanel pnlRoot;
    private javax.swing.JScrollPane scrRoom;
    // End of variables declaration//GEN-END:variables
}
