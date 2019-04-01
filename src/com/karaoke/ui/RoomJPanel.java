package com.karaoke.ui;

import com.karaoke.helper.OpenJPanel;
import com.karaoke.helper.RoundedPanel;
import com.sun.java.swing.plaf.windows.WindowsScrollBarUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


/**
 *
 * @author TVD
 */
public class RoomJPanel extends javax.swing.JPanel {

    Color roomColor = null;
    Icon roomIcon = null;
    final ScheduledExecutorService svc = Executors.newScheduledThreadPool(1);

    public RoomJPanel() {
        initComponents();
        int soluong = 22;
        for (int i = 0; i < soluong; i++) {
            roomColor = new Color(44, 153, 55);
            if (i%3==0) {
                roomColor = new Color(155, 10, 10);
            }
            RoundedPanel room = new RoundedPanel();
            room.setPreferredSize(new Dimension(235, 150));
            JLabel roomInfo = new JLabel();

            String noidung = "<html> <style>body{\n"
                    + "    text-align: center;\n"
                    + "    font-family: 'Tahoma';\n"
                    + "    color: white;\n"
                    + "}</style>";
            noidung += "<b><font size=5>Phòng " + i + "</font></b><br><i><b>(Chưa có dữ liệu)</b></i>";
            roomInfo.setText(noidung);
            roomInfo.setPreferredSize(new Dimension(230, 150));
            roomInfo.setBackground(roomColor);
            roomInfo.setIcon(new ImageIcon(getClass().getResource("/com/karaoke/images/icon/ReadyToSingVIP.png")));
            roomInfo.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    OpenJPanel.openJPanel(pnlRoot, new ChiTietPhongJPanel());
                }

               

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

            room.add(roomInfo);
            room.setBackground(roomColor);
            pnlRoom.add(room);

        }
        if (soluong % 4 == 0) {
            pnlRoom.setPreferredSize(new Dimension(1030, 160 * soluong / 4));

        } else {
            pnlRoom.setPreferredSize(new Dimension(1030, 160 * ((soluong / 4)+1)));

        }
        pnlRoom.repaint();
        pnlRoom.revalidate();
        scrRoom.setPreferredSize(new Dimension(1056, 160 * 4));
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
        lblBackground = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();

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
        scrRoom.setMaximumSize(new java.awt.Dimension(1056, 650));
        scrRoom.setMinimumSize(new java.awt.Dimension(1056, 650));
        scrRoom.setOpaque(false);
        scrRoom.setPreferredSize(new java.awt.Dimension(1056, 650));

        pnlRoom.setBackground(new java.awt.Color(255, 255, 255));
        pnlRoom.setMinimumSize(new java.awt.Dimension(1030, 500));
        pnlRoom.setOpaque(false);
        pnlRoom.setPreferredSize(new java.awt.Dimension(1030, 500));
        pnlRoom.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 12, 10));
        scrRoom.setViewportView(pnlRoom);

        pnlRoot.add(scrRoom, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1050, -1));

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/karaoke/images/icon/background_room.jpg"))); // NOI18N
        lblBackground.setText("jLabel1");
        lblBackground.setMaximumSize(new java.awt.Dimension(1056, 650));
        lblBackground.setMinimumSize(new java.awt.Dimension(1056, 650));
        lblBackground.setName(""); // NOI18N
        lblBackground.setPreferredSize(new java.awt.Dimension(1056, 650));
        pnlRoot.add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1050, 650));

        jCheckBox1.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setText("Phòng thường");
        jCheckBox1.setBorder(null);
        jCheckBox1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jCheckBox1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jCheckBox1.setMaximumSize(new java.awt.Dimension(153, 30));
        jCheckBox1.setMinimumSize(new java.awt.Dimension(153, 30));
        jCheckBox1.setOpaque(false);
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        pnlRoot.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(893, 670, 100, -1));

        jCheckBox2.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jCheckBox2.setForeground(new java.awt.Color(0, 204, 0));
        jCheckBox2.setText("Phòng trống");
        jCheckBox2.setBorder(null);
        jCheckBox2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jCheckBox2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jCheckBox2.setMaximumSize(new java.awt.Dimension(153, 30));
        jCheckBox2.setMinimumSize(new java.awt.Dimension(153, 30));
        jCheckBox2.setOpaque(false);
        pnlRoot.add(jCheckBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(763, 650, 90, -1));

        jCheckBox3.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jCheckBox3.setForeground(new java.awt.Color(255, 255, 0));
        jCheckBox3.setText("Phòng VIP");
        jCheckBox3.setBorder(null);
        jCheckBox3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jCheckBox3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jCheckBox3.setMaximumSize(new java.awt.Dimension(153, 30));
        jCheckBox3.setMinimumSize(new java.awt.Dimension(153, 30));
        jCheckBox3.setOpaque(false);
        pnlRoot.add(jCheckBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(913, 650, 80, -1));

        jCheckBox5.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jCheckBox5.setForeground(new java.awt.Color(255, 0, 0));
        jCheckBox5.setText("Phòng đang sử dụng");
        jCheckBox5.setBorder(null);
        jCheckBox5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jCheckBox5.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jCheckBox5.setMaximumSize(new java.awt.Dimension(153, 30));
        jCheckBox5.setMinimumSize(new java.awt.Dimension(153, 30));
        jCheckBox5.setOpaque(false);
        jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox5ActionPerformed(evt);
            }
        });
        pnlRoot.add(jCheckBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(723, 670, 130, -1));

        add(pnlRoot, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 690));
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JPanel pnlRoom;
    private javax.swing.JPanel pnlRoot;
    private javax.swing.JScrollPane scrRoom;
    // End of variables declaration//GEN-END:variables
}
