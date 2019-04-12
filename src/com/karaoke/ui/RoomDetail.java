/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karaoke.ui;

import com.karaoke.dao.DichVuDAO;
import com.karaoke.dao.LoaiDichVuDAO;
import com.karaoke.helper.InHoaDon;
import com.karaoke.helper.JDBCHelper;
import com.karaoke.helper.JOptionPaneHelper;
import com.karaoke.helper.MarqueeLabel;
import com.karaoke.helper.SendSMS;
import com.karaoke.helper.XuLy;
import com.karaoke.model.DichVu;
import com.karaoke.model.LoaiDichVu;
import com.sun.java.swing.plaf.windows.WindowsScrollBarUI;
import java.awt.CardLayout;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ProtocolException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Admin
 */
public class RoomDetail extends javax.swing.JDialog {

    CardLayout cardLayout = null;
    boolean isTinhTien = true;
    List<JButton> listBtn = new ArrayList<>(); // Để lưu các JButton 
    List<JLabel> listtenDV = new ArrayList<>();
    List<JSpinner> listSL = new ArrayList<>(); //stored số lượng từng dv
    List<JScrollPane> listScr = new ArrayList<>();

    //Select Loại DV bỏ vào listLoai
    LoaiDichVuDAO ldvDao = new LoaiDichVuDAO();
    List<LoaiDichVu> listLoai = ldvDao.select();

    //Select DV bỏ vào listDV //450 550
    DichVuDAO dvdao = new DichVuDAO();
    List<DichVu> listDV = dvdao.select();

    long tienDv = 0, tienGio = 0, tienGiamGia = 0, tongTien = 0, thanhTien = 0;
    int slDV = 0; //Số lượng của TỪNG dịch vụ trong Hóa đơn
    int sldvhd = 0; //Số lượng các mặt hàng/dịch vụ trong Hóa đơn
    int tonKho = 0, soLuongbd = 0;//số luọng tồn Kho --> để check xem có còn ko để add
    String maHoaDon = "", maPhongDV = "", tienKhachDuaS = "", username;

    public RoomDetail(java.awt.Frame parent, boolean modal, String maPhong, String maHD, String username) throws SQLException, ParseException {
        super(parent, modal);
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/karaoke/images/icon/iconFrame.png")));
        this.setTitle("Thông tin chi tiết Phòng " + maPhong.toUpperCase());
        setLocationRelativeTo(null);
        this.username = username;
        maHoaDon = maHD;
        maPhongDV = maPhong;
        lblChitiet.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(0, 102, 153)));
        cardLayout = (CardLayout) pnlRight.getLayout();

        //SET SIZE BAN ĐẦU CHO CÁC COMPONENTS
        pnlHoaDon.setPreferredSize(new Dimension(420, 220)); //chiều cao là 50 + gap 5 = 55
        scrHoaDon.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0, 102, 153)));
        scrHoaDon.getVerticalScrollBar().setUnitIncrement(20);
        scrHoaDon.setViewportView(pnlHoaDon);
        scrHoaDon.getVerticalScrollBar().setUI(new WindowsScrollBarUI());
        txtQuickSearch.setText(" ");

        // <editor-fold defaultstate="collapsed" desc="SELECT THÔNG TIN CHI TIẾT PHÒNG">                          
        ResultSet rs = JDBCHelper.executeQuery("SELECT * FROM dbo.HoaDon,dbo.LoaiPhong,dbo.Phong WHERE HoaDon.maPhong = ? AND LoaiPhong.maLoai = Phong.maLoai AND HoaDon.maPhong = Phong.maPhong AND gioThanhToan IS NULL", maPhong);
        while (rs.next()) {
            if (!rs.getString("ghiChu").equals("")) {
                MarqueeLabel TNQ = new MarqueeLabel(rs.getString("ghiChu"), MarqueeLabel.RIGHT_TO_LEFT, 30);
                TNQ.setFont(new java.awt.Font("Segoe UI", 1, 12));
                TNQ.setForeground(Color.red);
                TNQ.setToolTipText(TNQ.getText());
                pnlTNQ.add(TNQ);
            }
            tienGio += rs.getLong("tienGio"); // get Tiền giờ trước, (trường hợp có đổi phòng)
            txtTenPhong.setText("<html><center>Mã HĐ: " + maHD + " - Phòng " + maPhong.toUpperCase() + "<br><font size = 4>(" + rs.getString("tenLoai") + ")</font>");
            txtGiaPhong.setText(XuLy.xulySo(rs.getLong("giaPhong") + ""));//Giá phòng đối chiếu với Loại phòng
            txtTenKH.setText(rs.getString("tenKH"));//Tên KH
            txtTenKH.setBorder(null);
            String sdt = rs.getString("soDT") != null ? rs.getString("soDT") : "";
            txtSDT.setText(sdt);
            txtSDT.setBorder(null);
            txtGioVao.setText(XuLy.convertDateTime(rs.getString("gioDatPhong")));//Giờ vào

            //Tính thời lượng sử dụng
            long[] x = new long[4];
            x = XuLy.tinhgio(txtGioVao.getText(), "");
            txtTienDV.setText(XuLy.xulySo("0"));
            txtThoiluong.setText(XuLy.tinhgio(x));

            //Thiết lập tiền mặc định khi vào quán
            int phutMacDinh = 0;
            ResultSet rs2 = JDBCHelper.executeQuery("SELECT phutMacDinh FROM Information");
            if (rs2.next()) {
                phutMacDinh = rs2.getInt(1);
            }
            double soGio = x[0] * 24 + x[1] + (x[2] / 60.0) - (phutMacDinh / 60.0);//phutMacDinh/60.0;//tính giờ trừ phútMD
            if (soGio > 0) {
                tienGio += (long) (rs.getLong("giaPhong") * soGio);

            }
            txtTienGio.setText(XuLy.xulySo(tienGio + ""));

            tongTien = tienGio;
            txtTongTamTinh.setText(XuLy.xulySo(tongTien + ""));
        }//</editor-fold>

        loadDV();
        loadHD(maHD);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlLeft = new javax.swing.JPanel();
        pnlChiTietHoaDon = new javax.swing.JPanel();
        txtGioVao = new javax.swing.JLabel();
        txtTenPhong = new javax.swing.JLabel();
        scrHoaDon = new javax.swing.JScrollPane();
        pnlHoaDon = new javax.swing.JPanel();
        lblKhachhangchuasudung = new javax.swing.JLabel();
        lblGiaPhong = new javax.swing.JLabel();
        txtGiaPhong = new javax.swing.JLabel();
        lblChitiet = new javax.swing.JLabel();
        lblTienDV = new javax.swing.JLabel();
        lblTienGio = new javax.swing.JLabel();
        txtTienGio = new javax.swing.JLabel();
        txtTongTamTinh = new javax.swing.JLabel();
        lblTongTamTinh = new javax.swing.JLabel();
        btnCapNhat = new javax.swing.JButton();
        btnTinhTien = new javax.swing.JButton();
        lblTenKH = new javax.swing.JLabel();
        lblThoiluong = new javax.swing.JLabel();
        txtThoiluong = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        lblGiovao = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        lblDT = new javax.swing.JLabel();
        txtTienDV = new javax.swing.JLabel();
        btnChuyenPhong = new javax.swing.JButton();
        pnlTNQ = new javax.swing.JPanel();
        btnHuy = new javax.swing.JButton();
        pnlRight = new javax.swing.JPanel();
        pnlDichVu = new javax.swing.JPanel();
        tabbedDV = new javax.swing.JTabbedPane();
        pnlQuickSearch = new javax.swing.JPanel();
        txtQuickSearch = new javax.swing.JTextField();
        lblQuickSearch = new javax.swing.JLabel();
        pnlThanhToan = new javax.swing.JPanel();
        txtTenPhong1 = new javax.swing.JLabel();
        lblTongTien1 = new javax.swing.JLabel();
        lblTienKhachDua = new javax.swing.JLabel();
        num4 = new javax.swing.JButton();
        num0 = new javax.swing.JButton();
        num1 = new javax.swing.JButton();
        num2 = new javax.swing.JButton();
        num3 = new javax.swing.JButton();
        num5 = new javax.swing.JButton();
        num6 = new javax.swing.JButton();
        num7 = new javax.swing.JButton();
        num8 = new javax.swing.JButton();
        num9 = new javax.swing.JButton();
        lblStatusVoucher = new javax.swing.JLabel();
        txtVoucher = new javax.swing.JTextField();
        lblVoucher = new javax.swing.JLabel();
        num10 = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        chkInHoaDon = new javax.swing.JCheckBox();
        chkSMS = new javax.swing.JCheckBox();
        lblTongThanhTien = new javax.swing.JLabel();
        txtTongThanhTien = new javax.swing.JLabel();
        txtTongTienHD = new javax.swing.JLabel();
        txtTienKhachDua = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("THÔNG TIN CHI TIẾT");
        setMinimumSize(new java.awt.Dimension(1200, 560));
        setResizable(false);
        setSize(new java.awt.Dimension(1200, 560));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlLeft.setMaximumSize(new java.awt.Dimension(600, 560));
        pnlLeft.setMinimumSize(new java.awt.Dimension(600, 560));
        pnlLeft.setPreferredSize(new java.awt.Dimension(600, 560));
        pnlLeft.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlChiTietHoaDon.setBackground(new java.awt.Color(255, 255, 255));
        pnlChiTietHoaDon.setForeground(new java.awt.Color(0, 102, 51));
        pnlChiTietHoaDon.setMaximumSize(new java.awt.Dimension(600, 560));
        pnlChiTietHoaDon.setMinimumSize(new java.awt.Dimension(600, 560));
        pnlChiTietHoaDon.setPreferredSize(new java.awt.Dimension(600, 560));
        pnlChiTietHoaDon.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtGioVao.setBackground(new java.awt.Color(51, 51, 51));
        txtGioVao.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtGioVao.setForeground(new java.awt.Color(51, 51, 51));
        txtGioVao.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtGioVao.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        pnlChiTietHoaDon.add(txtGioVao, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 330, 20));

        txtTenPhong.setBackground(new java.awt.Color(51, 51, 51));
        txtTenPhong.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        txtTenPhong.setForeground(new java.awt.Color(255, 0, 0));
        txtTenPhong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTenPhong.setText("Phòng RM0004");
        pnlChiTietHoaDon.add(txtTenPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 40));

        scrHoaDon.setBackground(new java.awt.Color(51, 51, 51));
        scrHoaDon.setBorder(null);
        scrHoaDon.setMaximumSize(new java.awt.Dimension(450, 250));
        scrHoaDon.setMinimumSize(new java.awt.Dimension(450, 250));
        scrHoaDon.setPreferredSize(new java.awt.Dimension(450, 250));

        pnlHoaDon.setBackground(new java.awt.Color(255, 255, 255));
        pnlHoaDon.setForeground(new java.awt.Color(153, 153, 255));

        lblKhachhangchuasudung.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        lblKhachhangchuasudung.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblKhachhangchuasudung.setText("(Khách hàng chưa sử dụng dịch vụ nào)");
        lblKhachhangchuasudung.setMaximumSize(new java.awt.Dimension(300, 20));
        lblKhachhangchuasudung.setMinimumSize(new java.awt.Dimension(300, 20));
        lblKhachhangchuasudung.setPreferredSize(new java.awt.Dimension(300, 20));
        pnlHoaDon.add(lblKhachhangchuasudung);

        scrHoaDon.setViewportView(pnlHoaDon);

        pnlChiTietHoaDon.add(scrHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 540, 220));

        lblGiaPhong.setBackground(new java.awt.Color(51, 51, 51));
        lblGiaPhong.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblGiaPhong.setForeground(new java.awt.Color(0, 102, 153));
        lblGiaPhong.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblGiaPhong.setText("Giá phòng (1h):");
        pnlChiTietHoaDon.add(lblGiaPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 130, 20));

        txtGiaPhong.setBackground(new java.awt.Color(51, 51, 51));
        txtGiaPhong.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtGiaPhong.setForeground(new java.awt.Color(51, 51, 51));
        txtGiaPhong.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtGiaPhong.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        pnlChiTietHoaDon.add(txtGiaPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 130, 290, 20));

        lblChitiet.setBackground(new java.awt.Color(51, 51, 51));
        lblChitiet.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblChitiet.setForeground(new java.awt.Color(0, 102, 153));
        lblChitiet.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblChitiet.setText("CHI TIẾT DỊCH VỤ SỬ DỤNG");
        pnlChiTietHoaDon.add(lblChitiet, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 540, 30));

        lblTienDV.setBackground(new java.awt.Color(51, 51, 51));
        lblTienDV.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTienDV.setForeground(new java.awt.Color(0, 102, 153));
        lblTienDV.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTienDV.setText("Tổng tiền dịch vụ:");
        pnlChiTietHoaDon.add(lblTienDV, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 410, 130, 30));

        lblTienGio.setBackground(new java.awt.Color(51, 51, 51));
        lblTienGio.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTienGio.setForeground(new java.awt.Color(0, 102, 153));
        lblTienGio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTienGio.setText("Tổng tiền giờ:");
        pnlChiTietHoaDon.add(lblTienGio, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 440, 130, 30));

        txtTienGio.setBackground(new java.awt.Color(51, 51, 51));
        txtTienGio.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtTienGio.setForeground(new java.awt.Color(255, 0, 0));
        txtTienGio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtTienGio.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        pnlChiTietHoaDon.add(txtTienGio, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 440, 230, 30));

        txtTongTamTinh.setBackground(new java.awt.Color(51, 51, 51));
        txtTongTamTinh.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtTongTamTinh.setForeground(new java.awt.Color(255, 0, 0));
        txtTongTamTinh.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtTongTamTinh.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        pnlChiTietHoaDon.add(txtTongTamTinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 470, 230, 30));

        lblTongTamTinh.setBackground(new java.awt.Color(51, 51, 51));
        lblTongTamTinh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTongTamTinh.setForeground(new java.awt.Color(0, 102, 153));
        lblTongTamTinh.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTongTamTinh.setText("Tổng tạm tính:");
        pnlChiTietHoaDon.add(lblTongTamTinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 470, 160, 30));

        btnCapNhat.setBackground(new java.awt.Color(255, 255, 255));
        btnCapNhat.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCapNhat.setForeground(new java.awt.Color(0, 102, 153));
        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/karaoke/images/icon/update.png"))); // NOI18N
        btnCapNhat.setText("CẬP NHẬT");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });
        pnlChiTietHoaDon.add(btnCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 500, 130, 40));

        btnTinhTien.setBackground(new java.awt.Color(255, 255, 255));
        btnTinhTien.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnTinhTien.setForeground(new java.awt.Color(0, 102, 153));
        btnTinhTien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/karaoke/images/icon/pay.png"))); // NOI18N
        btnTinhTien.setText("TÍNH TIỀN");
        btnTinhTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTinhTienActionPerformed(evt);
            }
        });
        pnlChiTietHoaDon.add(btnTinhTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 500, 130, 40));

        lblTenKH.setBackground(new java.awt.Color(51, 51, 51));
        lblTenKH.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTenKH.setForeground(new java.awt.Color(0, 102, 153));
        lblTenKH.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTenKH.setText("Khách hàng:");
        pnlChiTietHoaDon.add(lblTenKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(179, 40, 80, 20));

        lblThoiluong.setBackground(new java.awt.Color(51, 51, 51));
        lblThoiluong.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblThoiluong.setForeground(new java.awt.Color(0, 102, 153));
        lblThoiluong.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblThoiluong.setText("Thời lượng:");
        pnlChiTietHoaDon.add(lblThoiluong, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 100, 20));

        txtThoiluong.setBackground(new java.awt.Color(51, 51, 51));
        txtThoiluong.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtThoiluong.setForeground(new java.awt.Color(51, 51, 51));
        txtThoiluong.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtThoiluong.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        pnlChiTietHoaDon.add(txtThoiluong, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 100, 310, 20));

        txtTenKH.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtTenKH.setForeground(new java.awt.Color(51, 51, 51));
        txtTenKH.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtTenKH.setMaximumSize(new java.awt.Dimension(200, 30));
        txtTenKH.setMinimumSize(new java.awt.Dimension(200, 30));
        txtTenKH.setPreferredSize(new java.awt.Dimension(200, 30));
        txtTenKH.setRequestFocusEnabled(false);
        txtTenKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTenKHMouseClicked(evt);
            }
        });
        pnlChiTietHoaDon.add(txtTenKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, 140, 20));

        lblGiovao.setBackground(new java.awt.Color(51, 51, 51));
        lblGiovao.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblGiovao.setForeground(new java.awt.Color(0, 102, 153));
        lblGiovao.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblGiovao.setText("Giờ vào:");
        pnlChiTietHoaDon.add(lblGiovao, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 90, 20));

        txtSDT.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtSDT.setForeground(new java.awt.Color(51, 51, 51));
        txtSDT.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSDT.setMaximumSize(new java.awt.Dimension(200, 30));
        txtSDT.setMinimumSize(new java.awt.Dimension(200, 30));
        txtSDT.setPreferredSize(new java.awt.Dimension(200, 30));
        txtSDT.setRequestFocusEnabled(false);
        txtSDT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSDTMouseClicked(evt);
            }
        });
        pnlChiTietHoaDon.add(txtSDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 40, 120, 20));

        lblDT.setBackground(new java.awt.Color(51, 51, 51));
        lblDT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblDT.setForeground(new java.awt.Color(0, 102, 153));
        lblDT.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDT.setText("SĐT:");
        pnlChiTietHoaDon.add(lblDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 40, 30, 20));

        txtTienDV.setBackground(new java.awt.Color(51, 51, 51));
        txtTienDV.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtTienDV.setForeground(new java.awt.Color(255, 0, 0));
        txtTienDV.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtTienDV.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        pnlChiTietHoaDon.add(txtTienDV, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 410, 230, 30));

        btnChuyenPhong.setBackground(new java.awt.Color(255, 255, 255));
        btnChuyenPhong.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnChuyenPhong.setForeground(new java.awt.Color(0, 102, 153));
        btnChuyenPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/karaoke/images/icon/change.png"))); // NOI18N
        btnChuyenPhong.setText("CHUYỂN");
        btnChuyenPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChuyenPhongActionPerformed(evt);
            }
        });
        pnlChiTietHoaDon.add(btnChuyenPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 500, 130, 40));

        pnlTNQ.setBackground(new java.awt.Color(255, 255, 255));
        pnlTNQ.setLayout(new java.awt.GridLayout(1, 0));
        pnlChiTietHoaDon.add(pnlTNQ, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 540, 540, 20));

        btnHuy.setBackground(new java.awt.Color(255, 255, 255));
        btnHuy.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnHuy.setForeground(new java.awt.Color(0, 102, 153));
        btnHuy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/karaoke/images/icon/clear.png"))); // NOI18N
        btnHuy.setText("HỦY PHÒNG");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });
        pnlChiTietHoaDon.add(btnHuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 500, 130, 40));

        pnlLeft.add(pnlChiTietHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 560));

        getContentPane().add(pnlLeft, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pnlRight.setMaximumSize(new java.awt.Dimension(600, 560));
        pnlRight.setMinimumSize(new java.awt.Dimension(600, 560));
        pnlRight.setPreferredSize(new java.awt.Dimension(600, 560));
        pnlRight.setLayout(new java.awt.CardLayout());

        pnlDichVu.setBackground(new java.awt.Color(51, 51, 51));
        pnlDichVu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabbedDV.setBackground(new java.awt.Color(51, 51, 51));
        tabbedDV.setForeground(new java.awt.Color(255, 255, 255));
        tabbedDV.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        tabbedDV.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tabbedDV.setMaximumSize(new java.awt.Dimension(450, 580));
        tabbedDV.setMinimumSize(new java.awt.Dimension(450, 580));
        tabbedDV.setPreferredSize(new java.awt.Dimension(450, 580));
        pnlDichVu.add(tabbedDV, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 590, 530));

        pnlQuickSearch.setBackground(new java.awt.Color(51, 51, 51));
        pnlQuickSearch.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtQuickSearch.setBackground(new java.awt.Color(51, 51, 51));
        txtQuickSearch.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtQuickSearch.setForeground(new java.awt.Color(255, 255, 0));
        txtQuickSearch.setMaximumSize(new java.awt.Dimension(10, 20));
        txtQuickSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQuickSearchKeyReleased(evt);
            }
        });
        pnlQuickSearch.add(txtQuickSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 240, 30));

        lblQuickSearch.setBackground(new java.awt.Color(255, 255, 255));
        lblQuickSearch.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblQuickSearch.setForeground(new java.awt.Color(255, 255, 255));
        lblQuickSearch.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblQuickSearch.setText("Tìm kiếm nhanh:");
        lblQuickSearch.setMaximumSize(new java.awt.Dimension(100, 20));
        lblQuickSearch.setPreferredSize(new java.awt.Dimension(100, 20));
        pnlQuickSearch.add(lblQuickSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 130, 30));

        pnlDichVu.add(pnlQuickSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 30));

        pnlRight.add(pnlDichVu, "dichVu");

        pnlThanhToan.setBackground(new java.awt.Color(255, 255, 255));
        pnlThanhToan.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlThanhToan.setMaximumSize(new java.awt.Dimension(400, 230));
        pnlThanhToan.setMinimumSize(new java.awt.Dimension(400, 230));
        pnlThanhToan.setPreferredSize(new java.awt.Dimension(400, 230));
        pnlThanhToan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlThanhToanMouseClicked(evt);
            }
        });
        pnlThanhToan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pnlThanhToanKeyReleased(evt);
            }
        });
        pnlThanhToan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTenPhong1.setBackground(new java.awt.Color(51, 51, 51));
        txtTenPhong1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtTenPhong1.setForeground(new java.awt.Color(255, 0, 0));
        txtTenPhong1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTenPhong1.setText("THANH TOÁN HÓA ĐƠN");
        pnlThanhToan.add(txtTenPhong1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 510, 40));

        lblTongTien1.setBackground(new java.awt.Color(51, 51, 51));
        lblTongTien1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTongTien1.setForeground(new java.awt.Color(0, 102, 153));
        lblTongTien1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTongTien1.setText("Tổng tạm tính:");
        pnlThanhToan.add(lblTongTien1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 140, 40));

        lblTienKhachDua.setBackground(new java.awt.Color(51, 51, 51));
        lblTienKhachDua.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTienKhachDua.setForeground(new java.awt.Color(0, 102, 153));
        lblTienKhachDua.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTienKhachDua.setText("TIỀN KHÁCH ĐƯA:");
        pnlThanhToan.add(lblTienKhachDua, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 140, 40));

        num4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/karaoke/images/icon/4.png"))); // NOI18N
        num4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                num4ActionPerformed(evt);
            }
        });
        pnlThanhToan.add(num4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 260, 40, 40));

        num0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/karaoke/images/icon/0.png"))); // NOI18N
        num0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                num0ActionPerformed(evt);
            }
        });
        pnlThanhToan.add(num0, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 260, 40, 40));

        num1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/karaoke/images/icon/1.png"))); // NOI18N
        num1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                num1ActionPerformed(evt);
            }
        });
        pnlThanhToan.add(num1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 260, 40, 40));

        num2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/karaoke/images/icon/2.png"))); // NOI18N
        num2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                num2ActionPerformed(evt);
            }
        });
        pnlThanhToan.add(num2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 260, 40, 40));

        num3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/karaoke/images/icon/3.png"))); // NOI18N
        num3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                num3ActionPerformed(evt);
            }
        });
        pnlThanhToan.add(num3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 260, 40, 40));

        num5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/karaoke/images/icon/5.png"))); // NOI18N
        num5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                num5ActionPerformed(evt);
            }
        });
        pnlThanhToan.add(num5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, 40, 40));

        num6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/karaoke/images/icon/6.png"))); // NOI18N
        num6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                num6ActionPerformed(evt);
            }
        });
        pnlThanhToan.add(num6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 300, 40, 40));

        num7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/karaoke/images/icon/7.png"))); // NOI18N
        num7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                num7ActionPerformed(evt);
            }
        });
        pnlThanhToan.add(num7, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 300, 40, 40));

        num8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/karaoke/images/icon/8.png"))); // NOI18N
        num8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                num8ActionPerformed(evt);
            }
        });
        pnlThanhToan.add(num8, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 300, 40, 40));

        num9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/karaoke/images/icon/9.png"))); // NOI18N
        num9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                num9ActionPerformed(evt);
            }
        });
        pnlThanhToan.add(num9, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 300, 40, 40));

        lblStatusVoucher.setBackground(new java.awt.Color(51, 51, 51));
        lblStatusVoucher.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblStatusVoucher.setForeground(new java.awt.Color(0, 102, 153));
        lblStatusVoucher.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlThanhToan.add(lblStatusVoucher, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 320, 20));

        txtVoucher.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtVoucher.setForeground(new java.awt.Color(0, 204, 0));
        txtVoucher.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVoucher.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtVoucherKeyReleased(evt);
            }
        });
        pnlThanhToan.add(txtVoucher, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 320, 40));

        lblVoucher.setBackground(new java.awt.Color(51, 51, 51));
        lblVoucher.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblVoucher.setForeground(new java.awt.Color(0, 102, 153));
        lblVoucher.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblVoucher.setText("Voucher:");
        pnlThanhToan.add(lblVoucher, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 140, 40));

        num10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/karaoke/images/icon/clear.png"))); // NOI18N
        num10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                num10ActionPerformed(evt);
            }
        });
        pnlThanhToan.add(num10, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 260, 40, 80));

        btnThanhToan.setBackground(new java.awt.Color(255, 255, 255));
        btnThanhToan.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnThanhToan.setForeground(new java.awt.Color(0, 102, 153));
        btnThanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/karaoke/images/icon/pay.png"))); // NOI18N
        btnThanhToan.setText("THANH TOÁN");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });
        pnlThanhToan.add(btnThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 420, 150, 40));

        chkInHoaDon.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        chkInHoaDon.setForeground(new java.awt.Color(0, 102, 153));
        chkInHoaDon.setText("In Hóa Đơn");
        pnlThanhToan.add(chkInHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 370, -1, -1));

        chkSMS.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        chkSMS.setForeground(new java.awt.Color(0, 102, 153));
        chkSMS.setText("SMS Hóa Đơn");
        pnlThanhToan.add(chkSMS, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 370, -1, -1));

        lblTongThanhTien.setBackground(new java.awt.Color(51, 51, 51));
        lblTongThanhTien.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTongThanhTien.setForeground(new java.awt.Color(0, 102, 153));
        lblTongThanhTien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTongThanhTien.setText("TỔNG THÀNH TIỀN:");
        pnlThanhToan.add(lblTongThanhTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 150, 40));

        txtTongThanhTien.setBackground(new java.awt.Color(51, 51, 51));
        txtTongThanhTien.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtTongThanhTien.setForeground(new java.awt.Color(255, 0, 0));
        txtTongThanhTien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTongThanhTien.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        pnlThanhToan.add(txtTongThanhTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, 320, 40));

        txtTongTienHD.setBackground(new java.awt.Color(51, 51, 51));
        txtTongTienHD.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtTongTienHD.setForeground(new java.awt.Color(255, 0, 0));
        txtTongTienHD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTongTienHD.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        pnlThanhToan.add(txtTongTienHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 320, 40));

        txtTienKhachDua.setBackground(new java.awt.Color(51, 51, 51));
        txtTienKhachDua.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtTienKhachDua.setForeground(new java.awt.Color(0, 0, 204));
        txtTienKhachDua.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTienKhachDua.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        pnlThanhToan.add(txtTienKhachDua, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, 320, 40));

        pnlRight.add(pnlThanhToan, "thanhToan");

        getContentPane().add(pnlRight, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:

        if (JOptionPaneHelper.ask(this, "Xác nhận cập nhật Hóa đơn", "CẬP NHẬT HÓA ĐƠN") == 0) {
            if (updateHD()) {
                JOptionPaneHelper.popup("ok", this, "Cập nhật Hóa đơn thành công", "CẬP NHẬT HÓA ĐƠN");
                dispose();
            }

        }

    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnTinhTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTinhTienActionPerformed

        isTinhTien = !isTinhTien;
        if (!isTinhTien) {
            cardLayout.show(pnlRight, "thanhToan");
            txtTongThanhTien.setText(txtTongTamTinh.getText());
            txtTongTienHD.setText(txtTongTamTinh.getText());
            pnlThanhToan.requestFocusInWindow();
            thanhTien = tongTien;

        } else {
            cardLayout.show(pnlRight, "dichVu");

        }

    }//GEN-LAST:event_btnTinhTienActionPerformed

    private void txtQuickSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuickSearchKeyReleased
        // TODO add your handling code here:
        listBtn.forEach((btn) -> {
            if (XuLy.khongDau(btn.getText().substring(btn.getText().indexOf("<b>") + 3, btn.getText().indexOf("</b>"))).toLowerCase().contains(XuLy.khongDau(txtQuickSearch.getText().trim().toLowerCase())) && !txtQuickSearch.getText().equals("")) {
                btn.setBackground(Color.YELLOW);
                tabbedDV.setSelectedIndex(Integer.parseInt(btn.getParent().getName()));

                listScr.forEach((scr) -> {
                    scr.getVerticalScrollBar().setValue(btn.getY());
                });

            } else {
                btn.setBackground(Color.WHITE);

            }
        });
        if ("".equals(txtQuickSearch.getText().trim())) {
            listBtn.forEach((btn) -> {
                btn.setBackground(Color.WHITE);
                txtQuickSearch.setText(" ");

            });
        }
    }//GEN-LAST:event_txtQuickSearchKeyReleased

    private void txtTenKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTenKHMouseClicked
        // TODO add your handling code here:
        txtTenKH.requestFocus();
    }//GEN-LAST:event_txtTenKHMouseClicked

    private void txtSDTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSDTMouseClicked
        // TODO add your handling code here:
        txtSDT.requestFocus();
    }//GEN-LAST:event_txtSDTMouseClicked

    private void num4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_num4ActionPerformed
        dienso("4");
    }//GEN-LAST:event_num4ActionPerformed

    private void num0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_num0ActionPerformed
        dienso("0");
    }//GEN-LAST:event_num0ActionPerformed

    private void num1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_num1ActionPerformed
        // TODO add your handling code here:
        dienso("1");
    }//GEN-LAST:event_num1ActionPerformed

    private void num2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_num2ActionPerformed
        dienso("2");
    }//GEN-LAST:event_num2ActionPerformed

    private void num3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_num3ActionPerformed
        dienso("3");
    }//GEN-LAST:event_num3ActionPerformed

    private void num5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_num5ActionPerformed
        dienso("5");
    }//GEN-LAST:event_num5ActionPerformed

    private void num6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_num6ActionPerformed
        // TODO add your handling code here:
        dienso("6");
    }//GEN-LAST:event_num6ActionPerformed

    private void num7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_num7ActionPerformed
        // TODO add your handling code here:
        dienso("7");
    }//GEN-LAST:event_num7ActionPerformed

    private void num8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_num8ActionPerformed
        // TODO add your handling code here:
        dienso("8");
    }//GEN-LAST:event_num8ActionPerformed

    private void num9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_num9ActionPerformed
        // TODO add your handling code here:
        dienso("9");
    }//GEN-LAST:event_num9ActionPerformed

    private void num10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_num10ActionPerformed
        // TODO add your handling code here:
        if (tienKhachDuaS.length() > 1) {
            tienKhachDuaS = tienKhachDuaS.substring(0, tienKhachDuaS.length() - 1);
            txtTienKhachDua.setText(XuLy.xulySo(tienKhachDuaS));
        } else {
            tienKhachDuaS = "0";
            txtTienKhachDua.setText(XuLy.xulySo(tienKhachDuaS));
        }
    }//GEN-LAST:event_num10ActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        if (tienKhachDuaS.length() > 0) {
            long tienKH = Long.parseLong(tienKhachDuaS);
            if (tienKH < thanhTien) {
                JOptionPaneHelper.popup("loi", this, "Tiền khách đưa nhỏ hơn tiền cần thanh toán", "LỖI");
                pnlThanhToan.requestFocusInWindow();
            } else if (JOptionPaneHelper.ask(this, "<u>Xác nhận:</u><br>Khách hàng: " + txtTenKH.getText() + " đã thanh toán đủ <font color = red>" + txtTongThanhTien.getText()
                    + "</font><br> và đã nhận đủ <font color = red>" + XuLy.xulySo(tienKH - thanhTien + "") + "</font> tiền thừa", "THANH TOÁN") == 0) {
                updateHD();
                payHD(tienKH);
                String msg = "";
                ResultSet rs = JDBCHelper.executeQuery("SELECT tenKara, tenKH, HoaDon.soDT, gioDatPhong, gioThanhToan, thanhTien, urlSMS FROM dbo.HoaDon, dbo.Information WHERE maHD = ?", maHoaDon);
                try {
                    if (rs.next()) {
                        msg = rs.getString("tenKara")
                                + "\n"
                                + "- Số HĐ: "
                                + maHoaDon
                                + "\n"
                                + "- KH: "
                                + rs.getString("tenKH")
                                + "\n"
                                + "- Vào: "
                                + XuLy.convertDateTime(rs.getString("gioDatPhong"))
                                + "\n"
                                + "- Ra: "
                                + XuLy.convertDateTime(rs.getString("gioThanhToan"))
                                + "\n"
                                + "- T.Tiền: "
                                + XuLy.xulySo(rs.getString("thanhTien"));

                        msg += "\nCảm ơn Quý khách! Hẹn gặp lại!";
                        if (chkInHoaDon.isSelected()) {
                            inHD();
                        }
                        if (chkSMS.isSelected() && null != rs.getString("soDT") && rs.getString("soDT").trim().length() >= 10) {
                            SendSMS.send(rs.getString("urlapi"), rs.getString("soDT"), XuLy.khongDau(msg.replaceAll("%", "%25")));
                        }
                        JOptionPaneHelper.popup("msg", this, msg.replaceAll("\n", "<br>"), "Gửi nội dung đến KH");

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(RoomDetail.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ProtocolException ex) {
                    Logger.getLogger(RoomDetail.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(RoomDetail.class.getName()).log(Level.SEVERE, null, ex);
                }

                JOptionPaneHelper.popup("msg", this, "Thanh toán thành công", "THANH TOÁN");
                dispose();

            }
        } else {
            JOptionPaneHelper.popup("loi", this, "Chưa nhập tiền", "Lỗi");
            pnlThanhToan.requestFocusInWindow();

        }


    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void txtVoucherKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVoucherKeyReleased
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            tienGiamGia = 0;
            String code = txtVoucher.getText();
            ResultSet rs = JDBCHelper.executeQuery("SELECT * FROM dbo.Voucher WHERE codevoucher = ? AND hanDung >= GETDATE()", code);
            if (rs.next()) {
                tienGiamGia = tongTien * rs.getInt("triGia") / 100;
                String noidung = "<html><font color = green> Giảm giá -"
                        + rs.getString("triGia") + "% ( -"
                        + XuLy.xulySo(tienGiamGia + "")
                        + ")";
                lblStatusVoucher.setText(noidung);
                txtVoucher.setForeground(Color.green);
                JDBCHelper.executeUpdate("UPDATE dbo.HoaDon SET codevoucher = ? WHERE maHD = ?", code, maHoaDon);

            } else {
                txtVoucher.setForeground(Color.red);
                lblStatusVoucher.setText("<html><font color = red>Voucher không hiệu lực");
                JDBCHelper.executeUpdate("UPDATE dbo.HoaDon SET codevoucher = ? WHERE maHD = ?", null, maHoaDon);

            }
            thanhTien = tongTien - tienGiamGia;
            txtTongThanhTien.setText(XuLy.xulySo(thanhTien + ""));

        } catch (SQLException ex) {
        }
    }//GEN-LAST:event_txtVoucherKeyReleased

    private void pnlThanhToanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pnlThanhToanKeyReleased
        if (evt.getKeyCode() == 8) {
            if (tienKhachDuaS.length() > 1) {
                tienKhachDuaS = tienKhachDuaS.substring(0, tienKhachDuaS.length() - 1);
                txtTienKhachDua.setText(XuLy.xulySo(tienKhachDuaS));
            } else {
                tienKhachDuaS = "0";
                txtTienKhachDua.setText(XuLy.xulySo(tienKhachDuaS));
            }
        } else if ((evt.getKeyCode() >= 48 && evt.getKeyCode() <= 57) || (evt.getKeyCode() >= 96 && evt.getKeyCode() <= 105)) {
            dienso(evt.getKeyCode() % 48 + "");
        }

    }//GEN-LAST:event_pnlThanhToanKeyReleased

    private void pnlThanhToanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlThanhToanMouseClicked
        // TODO add your handling code here:
        pnlThanhToan.requestFocusInWindow();
    }//GEN-LAST:event_pnlThanhToanMouseClicked

    private void btnChuyenPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChuyenPhongActionPerformed
        // TODO add your handling code here:
        try {
            boolean check = false;
            JComboBox maPhong = new JComboBox();
            maPhong.setFont(new java.awt.Font("Segoe UI", 1, 16));
            ResultSet rs = JDBCHelper.executeQuery("SELECT maPhong FROM dbo.Phong WHERE tinhTrang = 0");
            while (rs.next()) {
                maPhong.addItem(rs.getString("maPhong"));
            }
            Object[] message = {
                XuLy.msg("Chọn phòng cần chuyển:"), maPhong,};
            int option = JOptionPane.showConfirmDialog(this, message, "CHUYỂN PHÒNG", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                JDBCHelper.executeUpdate("UPDATE dbo.Phong SET tinhTrang = 0 WHERE maPhong = ?", maPhongDV);
                JDBCHelper.executeUpdate("UPDATE dbo.HoaDon SET maPhong = ?, tienGio = ?, ghiChu += N'Chuyển từ phòng '+ maPhong + N' ('+CONVERT(varchar, gioDatPhong, 121)+'). ', gioDatPhong = GETDATE() WHERE maHD = ?", maPhong.getSelectedItem() + "", tienGio, maHoaDon);
                JDBCHelper.executeUpdate("UPDATE dbo.Phong SET  tinhTrang = 1 WHERE maPhong = ?", maPhong.getSelectedItem() + "");
                JOptionPaneHelper.popup("ok", this, "Chuyển phòng thành công", "CHUYỂN PHÒNG");
                dispose();

            }

        } catch (Exception e) {
            //e.printStackTrace();

        }


    }//GEN-LAST:event_btnChuyenPhongActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        ResultSet rs = JDBCHelper.executeQuery("SELECT phutHuyPhong FROM dbo.Information");
        int phutHuyPhong;
        try {
            if (rs.next()) {
                phutHuyPhong = rs.getInt("phutHuyPhong");
                if (phutHuyPhong > Integer.parseInt((txtThoiluong.getText().replaceAll(" ", "").substring(0, 2))) && 'p' == txtThoiluong.getText().trim().charAt(3) && pnlTNQ.getComponentCount() == 0) {
                    if (JOptionPaneHelper.ask(this, "Hủy phòng này?", "HỦY PHÒNG") == 0) {
                        JDBCHelper.executeUpdate("DELETE FROM dbo.HoaDon WHERE maHD = ?", maHoaDon);
                        JDBCHelper.executeUpdate("UPDATE dbo.Phong SET tinhTrang = 0 WHERE maPhong = ?", maPhongDV);
                        JOptionPaneHelper.popup("ok", this, "Đã hủy phòng", "HỦY PHÒNG");
                        dispose();
                    }
                } else {
                    JOptionPaneHelper.popup("loi", this, "Thời lượng vào phòng vượt quá thời gian cho phép hủy phòng!<br>Vui lòng tính tiền!", "TỪ CHỐI HỦY PHÒNG");
                    cardLayout.show(pnlRight, "thanhToan");
                    txtTongThanhTien.setText(txtTongTamTinh.getText());
                    txtTongTienHD.setText(txtTongTamTinh.getText());
                    pnlThanhToan.requestFocusInWindow();
                    thanhTien = tongTien;
                }
            } else {
                JOptionPaneHelper.popup("loi", this, "Chưa thiết lập, vui lòng liên hệ quản trị hệ thống.", "TỪ CHỐI HỦY PHÒNG");

            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDetail.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnHuyActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws SQLException, ParseException {
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
            java.util.logging.Logger.getLogger(RoomDetail.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RoomDetail.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RoomDetail.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RoomDetail.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RoomDetail dialog = new RoomDetail(new JFrame(), true, "PH107", "1", "thanh");
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    });
                    dialog.setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(RoomDetail.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(RoomDetail.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private void loadDV() {
        //gắn tab lên Jtabbed
        int soLuongLoai = 0;
        for (LoaiDichVu ldv : listLoai) {
            int soLuongDV = 0;
            int lines = 0;
            JScrollPane scrDV = new JScrollPane();
            //scrDV.setPreferredSize(new Dimension(380, 580));
            JPanel tabLoai = new JPanel();
            for (DichVu dv : listDV) {//select dịch vụ qua maloaidv                               
                if (dv.getMaLoaiDV().equals(ldv.getMaLoaiDV())) {
                    soLuongDV++;
                    JButton btn = new JButton(); //Tạo btn Dịch vụ
                    btn.setFocusable(false);
                    btn.setFocusPainted(false);
                    btn.setToolTipText(dv.getTenDV() + "- Đơn giá: " + XuLy.xulySo(dv.getGiaBan() + "") + "- SL tồn kho: " + dv.getTonKho());

                    btn.setName(dv.getMaDV() + "");//set name cho btn --> Mục đích để lưu Mã Dịch vụ 
                    btn.setHorizontalAlignment(SwingConstants.LEFT);
                    btn.setPreferredSize(new Dimension(180, 80)); //1 button dịch vụ có size là 180*80
                    btn.setText(xltenDV(dv.getTenDV(), dv.getGiaBan()));

                    if (dv.getTonKho() <= 0) {
                        btn.setText(xltenDV(dv.getTenDV(), XuLy.xulySo(dv.getGiaBan() + "") + "<br><font size =4>HẾT HÀNG")); //Hết hàng vì trong kho 0 còn
                        btn.setEnabled(false);
                    }

                    try {
                        ImageIcon icon = xlIcon(dv.getHinh());
                        btn.setIcon(icon);
                    } catch (Exception e) {
                        ImageIcon icon = xlIcon("default.png");
                        btn.setIcon(icon);
                    }
                    btn.addActionListener(new ActionListener() { //Thêm sự kiện cho nút bấm
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            addDV((ImageIcon) btn.getIcon(), dv.getTenDV(), btn.getName(), dv.getGiaBan(), 1, dv.getTonKho());
                            btn.setEnabled(false);

                        }
                    });
                    btn.setBackground(Color.WHITE);
                    tabLoai.add(btn);
                    listBtn.add(btn);
                }

            }
            lines = soLuongDV / 3; //một line có 3 dv --> tìm số lines = soLuongDV / 3
            if (soLuongDV % 3 > 0) { //Nếu có trường hợp lẻ (4,5, 7,8, ...) thì tăng lines thêm 1
                lines++;
            }
            tabLoai.setLayout(new FlowLayout(FlowLayout.LEFT));
            tabLoai.setPreferredSize(new Dimension(450, (lines * 85))); //lấy tổng cộng lines * 85 (1 btn có height là 80 + 5 gap)
            tabLoai.setBackground(new Color(51, 51, 51));
            tabLoai.setName(soLuongLoai + ""); // Used for QuickSearch Dịch vụ, lưu vị trí index của tab trong JTabbed
            tabLoai.revalidate();
            tabLoai.repaint();
            scrDV.add(tabLoai);
            scrDV.getVerticalScrollBar().setUnitIncrement(20);
            scrDV.setViewportView(tabLoai);
            scrDV.setBorder(new EmptyBorder(0, 0, 0, 0));
            scrDV.getViewport().setOpaque(false);
            scrDV.getVerticalScrollBar().setUI(new WindowsScrollBarUI());

            tabbedDV.addTab(ldv.getTenLoaiDV(), scrDV);
            
            scrDV.setName(ldv.getMaLoaiDV());
            listScr.add(scrDV); //add vào list scr để xử lý QuickSearch
            soLuongLoai++;

        }
    }

    private void loadHD(String maHD) {
        ResultSet rs = JDBCHelper.executeQuery("SELECT dbo.DichVu.maDV,tenDV,soLuong,giaBan,hinh,tonKho FROM dbo.ChiTietHoaDon,dbo.DichVu WHERE DichVu.maDV = ChiTietHoaDon.maDV AND maHD = ?", maHD);
        try {
            while (rs.next()) {
                pnlHoaDon.remove(lblKhachhangchuasudung);//Xóa dòng Khách hàng chưa sử dụng...
                addDV(xlIcon(rs.getString("hinh")), rs.getString("tenDV"), rs.getString("maDV"), rs.getLong("giaBan"), rs.getInt("soLuong"), rs.getInt("tonKho"));
                for (JButton btn : listBtn) {
                    if (btn.getName().equals(rs.getString("maDV"))) {
                        btn.setEnabled(false);

                    }
                }
            }
        } catch (SQLException ex) {
            //addDV(xlIcon("plus.png"), xltenDV(rs.getString("tenDV"), rs.getLong("gia")), rs.getString("maDV"),rs.getLong("gia"));
        }

    }

    private void addDV(ImageIcon icon, String tenDV, String maDV, long gia, int soLuong, int slTonKho) {
        //soLuongbd = soLuong == 1 ? 0 : soLuong;      
        ResultSet rs3 = JDBCHelper.executeQuery("SELECT soLuong FROM dbo.ChiTietHoaDon WHERE maHD = ? and maDV = ?", maHoaDon, maDV);
        try {
            if (rs3.next()) {
                soLuongbd = rs3.getInt(1);
            } else {
                soLuongbd = 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDetail.class.getName()).log(Level.SEVERE, null, ex);
        }

        sldvhd++;
        pnlHoaDon.remove(lblKhachhangchuasudung);
        tienDv += gia * soLuong;
        txtTienDV.setText(XuLy.xulySo(tienDv + ""));
        tongTien = tienGio + tienDv;
        txtTongTamTinh.setText(XuLy.xulySo(tongTien + ""));

        JLabel dv = new JLabel(icon);
        dv.setText(xltenDV(tenDV, gia));
        dv.setPreferredSize(new Dimension(350, 50));
        dv.setHorizontalAlignment(SwingConstants.LEFT);
        dv.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        dv.setName(maDV);

        JSpinner sp = new JSpinner(new SpinnerNumberModel(soLuong, 0, 10000, 1));
        sp.setBounds(280, 3, 70, 35);
        sp.setName(soLuongbd - soLuong + "");
        sp.setValue(soLuong);
        listtenDV.add(dv); //add vào listLabel
        listSL.add(sp);  //add vào list sp

        ///Xác nhận giá trị do người dùng nhập
        try {
            sp.commitEdit();
        } catch (ParseException ex) {
            Logger.getLogger(RoomDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        //get số lượng tồn kho và số lượng hiện tại trong CTHĐ để xét có thể thêm DV nữa hay ko
        //get số lượng tồn kho và số lượng hiện tại trong CTHĐ để xét có thể thêm DV nữa hay ko

        sp.addChangeListener(new ChangeListener() {

            //get số lượng trc khi có thay đổi
            int oldValue = (int) sp.getValue();

            @Override
            public void stateChanged(ChangeEvent e) {
                // lấy giá trị tồn kho để set maximum cho spinner
                ResultSet rs3 = JDBCHelper.executeQuery("SELECT tonKho FROM dbo.DichVu WHERE maDV = ?", maDV);
                try {
                    if (rs3.next()) {
                        tonKho = rs3.getInt(1);

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(RoomDetail.class.getName()).log(Level.SEVERE, null, ex);
                }

                // tìm ra sự thay đổi để +(-) vào tồn kho qua name của Spinner
                rs3 = JDBCHelper.executeQuery("SELECT soLuong FROM dbo.ChiTietHoaDon WHERE maHD = ? and maDV = ?", maHoaDon, maDV);
                try {
                    if (rs3.next()) {
                        soLuongbd = rs3.getInt(1);
                    } else {
                        soLuongbd = 0;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(RoomDetail.class.getName()).log(Level.SEVERE, null, ex);
                }

                sp.setName(soLuongbd - (int) sp.getValue() + "");
                System.out.println("TỒN KHO " + tonKho);
                System.out.println("NAME: " + sp.getName());
                System.out.println("TỒn + name + value = " + ((int) sp.getValue() + Integer.parseInt(sp.getName()) + tonKho));
                if ((int) sp.getValue() > (int) sp.getValue() + Integer.parseInt(sp.getName()) + tonKho) {
                    JOptionPaneHelper.popup("loi", new JDialog(), "Trong kho chỉ còn tối đa " + ((int) sp.getValue() + Integer.parseInt(sp.getName()) + tonKho), "LỖI");
                    sp.setValue((int) sp.getValue() + Integer.parseInt(sp.getName()) + tonKho);
                }
                if ((int) sp.getValue() == 0) {
                    System.out.println(sp.getName());
                    tienDv -= gia * oldValue;
                    oldValue = 0;
                    //sp.setValue(soLuong);
                    for (JButton btn : listBtn) {
                        if (btn.getName().equals(maDV)) {
                            if (btn.getText().contains("HẾT HÀNG")) {
                                btn.setText(btn.getText().substring(0, btn.getText().indexOf("<br><font size =4>HẾT HÀNG") - 1));
                            }
                            btn.setEnabled(true);
                            listtenDV.remove(dv);
                            listSL.remove(sp);
                            pnlHoaDon.remove(dv);
                            sldvhd--;
                            pnlHoaDon.setPreferredSize(new Dimension(420, sldvhd * 55));//chỉnh size pnl hóa 
                            pnlHoaDon.revalidate();
                            pnlHoaDon.repaint();

                        }
                    }
                    JSpinner tmpSpinner = sp;
                    listSL.add(tmpSpinner);// lưu tạm vị trí dv bị xóa khỏi Hóa đơn
                    JLabel tmpDv = new JLabel();//nt
                    tmpDv.setText("JLABELTAM");
                    tmpDv.setName(maDV);
                    listtenDV.add(tmpDv);

                } else {

                    //get sô lượng sau thay đổi
                    int newValue = (int) sp.getValue();
                    if (newValue > oldValue) {
                        tienDv += (gia * (newValue - oldValue));
                    } else {
                        tienDv -= (gia * (oldValue - newValue));
                    }
                    oldValue = newValue;
                }

                txtTienDV.setText(XuLy.xulySo(tienDv + ""));
                tongTien = tienDv + tienGio;
                txtTongTamTinh.setText(XuLy.xulySo(tongTien + ""));

            }

        });
        dv.add(sp);
        pnlHoaDon.add(dv);
        pnlHoaDon.revalidate();
        pnlHoaDon.repaint();
        pnlHoaDon.setPreferredSize(new Dimension(420, sldvhd * 55));//chỉnh size pnl hóa 
        scrHoaDon.getVerticalScrollBar().setValue(scrHoaDon.getHeight());

    }

    private String xltenDV(String tenDV, long gia) {
        return "<html><font color=#006699 size=4><b>" + tenDV + "</b></font><br>Đơn giá: <font color=red size=3><b>" + XuLy.xulySo(gia + "") + "</b></font>";

    }

    private String xltenDV(String tenDV, String gia) {
        return "<html><font color=#006699 size=4><b>" + tenDV + "</b></font><br>Đơn giá: <font color=red size=3><b>" + gia + "</b></font>";

    }

    private ImageIcon xlIcon(String path) {
        //JOptionPane.showMessageDialog(this, this.getClass().getResource("/com/karaoke/images/service/").toString() + path);
        return new ImageIcon("C:\\ProgramData\\KaraTNQ\\images\\service\\" + path); //set avt 

        //return new ImageIcon(this.getClass().getResource("/images/service/" + path));
    }

    private boolean updateHD() {
        if (checkHoTen(txtTenKH)) {
            if (checkSDT(txtSDT)) {
                JDBCHelper.executeUpdate("DELETE FROM dbo.ChiTietHoaDon WHERE maHD = ?", maHoaDon);
                JDBCHelper.executeUpdate("UPDATE dbo.HoaDon SET tenKH = ?, soDT = ? WHERE maHD = ?", XuLy.makeupHoTen(txtTenKH.getText()), txtSDT.getText(), maHoaDon);
                for (int i = 0; i < listSL.size(); i++) {
                    JDBCHelper.executeUpdate("UPDATE dbo.DichVu SET tonKho += ? WHERE maDV = ?", Integer.parseInt(listSL.get(i).getName()), listtenDV.get(i).getName());

                }
                for (int i = 0; i < listSL.size(); i++) {
                    if ((int) listSL.get(i).getValue() != 0) {
                        JDBCHelper.executeUpdate("INSERT INTO dbo.ChiTietHoaDon VALUES(?,?,?)", maHoaDon, listtenDV.get(i).getName(), (int) listSL.get(i).getValue());

                    }
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    private void payHD(long tienKhachdua) {
        JDBCHelper.executeUpdate("UPDATE dbo.HoaDon SET tenKH = ?, soDT = ?, tienGio = ?, tienDV = ?, tongTien = ?, tienGiamGia = ?, thanhTien = ?, tienKhachDua = ?, gioThanhToan = GETDATE() WHERE maHD = ?", txtTenKH.getText(), txtSDT.getText(), tienGio, tienDv, tongTien, tienGiamGia, thanhTien, tienKhachdua, maHoaDon);
        JDBCHelper.executeUpdate("UPDATE dbo.Phong SET tinhTrang = 0 WHERE maPhong = ?", maPhongDV);
//        JDBCHelper.executeUpdate("UPDATE dbo.Voucher SET status = 1, maHD = ? WHERE code = ?", maHoaDon, txtVoucher.getText());

    }

    private void inHD() {
        InHoaDon.inHoaDon(maHoaDon);

    }

    private void dienso(String so) {
        tienKhachDuaS += so;
        txtTienKhachDua.setText(XuLy.xulySo(tienKhachDuaS));

    }

    private boolean checkHoTen(JTextField hoTen) {
        if (!hoTen.getText().matches("[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ\\s]{1,}")) {
            JOptionPaneHelper.popup("loi", this, "Họ tên chứa kí tự không hợp lệ", "LỖI");
            hoTen.requestFocus();
            return false;
        }
        return true;

    }

    private boolean checkSDT(JTextField sdt) {
        if (!sdt.getText().matches("\\d{10,12}") && !sdt.getText().trim().equals("")) {
            JOptionPaneHelper.popup("loi", this, "Điện thoại không hợp lệ", "LỖI");
            sdt.requestFocus();
            return false;
        }
        return true;

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnChuyenPhong;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnTinhTien;
    private javax.swing.JCheckBox chkInHoaDon;
    private javax.swing.JCheckBox chkSMS;
    private javax.swing.JLabel lblChitiet;
    private javax.swing.JLabel lblDT;
    private javax.swing.JLabel lblGiaPhong;
    private javax.swing.JLabel lblGiovao;
    private javax.swing.JLabel lblKhachhangchuasudung;
    private javax.swing.JLabel lblQuickSearch;
    private javax.swing.JLabel lblStatusVoucher;
    private javax.swing.JLabel lblTenKH;
    private javax.swing.JLabel lblThoiluong;
    private javax.swing.JLabel lblTienDV;
    private javax.swing.JLabel lblTienGio;
    private javax.swing.JLabel lblTienKhachDua;
    private javax.swing.JLabel lblTongTamTinh;
    private javax.swing.JLabel lblTongThanhTien;
    private javax.swing.JLabel lblTongTien1;
    private javax.swing.JLabel lblVoucher;
    private javax.swing.JButton num0;
    private javax.swing.JButton num1;
    private javax.swing.JButton num10;
    private javax.swing.JButton num2;
    private javax.swing.JButton num3;
    private javax.swing.JButton num4;
    private javax.swing.JButton num5;
    private javax.swing.JButton num6;
    private javax.swing.JButton num7;
    private javax.swing.JButton num8;
    private javax.swing.JButton num9;
    private javax.swing.JPanel pnlChiTietHoaDon;
    private javax.swing.JPanel pnlDichVu;
    private javax.swing.JPanel pnlHoaDon;
    private javax.swing.JPanel pnlLeft;
    private javax.swing.JPanel pnlQuickSearch;
    private javax.swing.JPanel pnlRight;
    private javax.swing.JPanel pnlTNQ;
    private javax.swing.JPanel pnlThanhToan;
    private javax.swing.JScrollPane scrHoaDon;
    private javax.swing.JTabbedPane tabbedDV;
    private javax.swing.JLabel txtGiaPhong;
    private javax.swing.JLabel txtGioVao;
    private javax.swing.JTextField txtQuickSearch;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JLabel txtTenPhong;
    private javax.swing.JLabel txtTenPhong1;
    private javax.swing.JLabel txtThoiluong;
    private javax.swing.JLabel txtTienDV;
    private javax.swing.JLabel txtTienGio;
    private javax.swing.JLabel txtTienKhachDua;
    private javax.swing.JLabel txtTongTamTinh;
    private javax.swing.JLabel txtTongThanhTien;
    private javax.swing.JLabel txtTongTienHD;
    private javax.swing.JTextField txtVoucher;
    // End of variables declaration//GEN-END:variables

}
