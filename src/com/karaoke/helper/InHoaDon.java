/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karaoke.helper;

import java.io.File;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JDialog;

/**
 *
 * @author Admin
 */
public class InHoaDon {

    public static String header() {
        return "<html>\n"
                + "   <head>\n"
                + "      <meta charset=\"utf-8\">\n"
                + "      <title>Hóa Đơn Thanh Toán</title>\n"
                + "      <style>\n"
                + "         body {\n"
                + "         font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;\n"
                + "         margin: auto;\n"
                + "         text-align: center;\n"
                + "         }\n"
                + "         .info {\n"
                + "         margin-bottom: 10px;\n"
                + "         }\n"
                + "         .chitiet,\n"
                + "         th,\n"
                + "         td {\n"
                + "         border-collapse: collapse;\n"
                + "         margin: 0 auto;\n"
                + "         }\n"
                + "         .chitiet td:first-child {\n"
                + "         text-align: center;\n"
                + "         }\n"
                + "         .chitiet td:nth-child(2) {\n"
                + "         text-align: left;\n"
                + "         }\n"
                + "         .chitiet td:nth-child(3) {\n"
                + "         text-align: center;\n"
                + "         }\n"
                + "         .chitiet td:nth-child(4),\n"
                + "         .chitiet td:nth-child(5) {\n"
                + "         text-align: right;\n"
                + "         }\n"
                + "         .kh {\n"
                + "         margin: 0 auto;\n"
                + "         margin-top: 10px;\n"
                + "         margin-bottom: 20px;\n"
                + "         text-align: left;\n"
                + "         }\n"
                + "      </style>\n"
                + "   </head>\n"
                + "   <body>";
    }

    public static String infokara(String tenkara, String diachi, String sdt) {
        return "<div class=\"info\">\n"
                + "         <b>" + tenkara + "</b><br>\n"
                + "         <i>" + diachi + "<br>" + sdt + "\n"
                + "         </i>\n"
                + "      </div>";
    }

    public static String infokh(String tenKH, String giovao, String giora, String phong, String maHD, String ghiChu) {
        return " <font size=6><b>HÓA ĐƠN TÍNH TIỀN</b></font><br>Số HĐ: <b>" + maHD + "</b> - In vào : <b>"
                + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()) + "</b>\n"
                + "      <table class=\"kh\">\n"
                + "         <tr>\n"
                + "            <th>Tên KH: </th>\n"
                + "            <td style=\"width:100px\">" + tenKH + "</td>\n"
                + "            <th>Giờ vào:</th>\n"
                + "            <td style=\"width:100px\">" + giovao + "</td>\n"
                + "         </tr>\n"
                + "         <tr>\n"
                + "            <th>Phòng: </th>\n"
                + "            <td>" + phong + "</td>\n"
                + "            <th>Giờ ra: </th>\n"
                + "            <td>" + giora + "</td>\n"
                + "         </tr>\n"
                + "      </table>\n"
                + "      <i>Ghi chú: " + ghiChu + "</i>\n"
                + "      <table class=\"chitiet\">\n"
                + "         <tr style=\"border-bottom: solid 1px gray\">\n"
                + "            <th style=\"width:30px\">STT</th>\n"
                + "            <th style=\"width:180px\">Tên Dịch vụ</th>\n"
                + "            <th style=\"width:80px\">Số lượng</th>\n"
                + "            <th style=\"width:80px\">Đơn giá</th>\n"
                + "            <th style=\"width:150px\">Thành tiền</th>\n"
                + "         </tr>";
    }

    public static String dichvu(int stt, String tenDV, String soluong, String dongia, String thanhtien) {
        return "\n<tr>\n"
                + "            <td>" + stt + "</td>\n"
                + "            <td>" + tenDV + "</td>\n"
                + "            <td>" + soluong + "</td>\n"
                + "            <td>" + dongia + "</td>\n"
                + "            <td>" + thanhtien + "</td>\n"
                + "         </tr>";
    }

    public static String footer(String tongtien, String voucher, String giamGia, String thanhTien, String tienkhach, String tienthua, String tenNV, String tangVoucher) {
        String noidung = "<tr style=\"border-top: solid 1px gray\">\n"
                + "            <td colspan=\"4\" style=\"text-align:right; font-size:14px\">Tổng cộng</td>\n"
                + "            <td style=\"text-align:right;font-size: 14px;\">" + tongtien + "</td>\n"
                + "         </tr>\n"
                + "         <tr>\n"
                + "            <td colspan=\"4\" style=\"text-align:right; font-size:14px\">Voucher giảm giá</td>\n"
                + "            <td style=\"text-align:right;font-size: 14px;\">" + voucher + " -" + giamGia + "</td>\n"
                + "         </tr>\n"
                + "         <tr>\n"
                + "            <td colspan=\"4\" style=\"text-align:right; font-size:14px\">Thành tiền</td>\n"
                + "            <td style=\"text-align:right;font-weight:bold;font-size: 18px;\">" + thanhTien + "</td>\n"
                + "         </tr>\n"
                + "         <tr>\n"
                + "            <td colspan=\"4\" style=\"text-align:right; font-size:14px\">Tiền khách đưa</td>\n"
                + "            <td style=\"text-align:right;font-size: 14px;\">" + tienkhach + "</td>\n"
                + "         </tr>\n"
                + "         <tr>\n"
                + "            <td colspan=\"4\" style=\"text-align:right; font-size:14px\">Tiền thừa</td>\n"
                + "            <td style=\"text-align:right;font-size: 14px;\">" + tienthua + "</td>\n"
                + "         </tr>\n"
                + "         <tr>\n"
                + "            <td colspan=\"4\" style=\"text-align:right; font-size:14px\">Nhân viên</td>\n"
                + "            <td style=\"text-align:right;font-size: 14px;\">" + tenNV + "</td>\n"
                + "         </tr>\n"
                + "      </table>\n";
        if (!tangVoucher.equals("")) {
            noidung += "      <br>Gửi tặng quý khách Voucher " + tangVoucher + "\n";
        }
        noidung +="      <br>Cảm ơn Quý khách! Hẹn gặp lại\n"
                + "   </body>\n"
                + "   <script type=\"text/javascript\">\n"
                + "        window.print();        \n"
                + "    </script>"
                + "</html>";
        return noidung;
    }

    public static void inHoaDon(String maHD) {
        ResultSet rs = null;
        long giaphong = 0;
        String voucher = "";
        File hd = new File(System.getProperty("user.home")+"/HoaDon.html");        
        String path = hd.getAbsolutePath();
        try (PrintWriter pw = new PrintWriter(hd, "UTF-8")) {

            pw.println(InHoaDon.header());
            rs = JDBCHelper.executeQuery("SELECT * FROM dbo.Information");
            if (rs.next()) {
                pw.println(infokara(rs.getString("tenKara"), rs.getString("diaChi"), rs.getString("dienThoai")));
            }
            rs = JDBCHelper.executeQuery("SELECT * FROM dbo.HoaDonPhong WHERE maHD = ?", maHD);
            if (rs.next()) {
                pw.println(infokh(rs.getString("tenKH"), XuLy.convertDate(rs.getString("gioDatPhong")), XuLy.convertDate(rs.getString("gioThanhToan")), rs.getString("maPhong"), maHD, rs.getString("ghiChu")));
                int i = 1;
                ResultSet rs1 = JDBCHelper.executeQuery("SELECT giaPhong FROM dbo.Phong JOIN dbo.LoaiPhong ON LoaiPhong.maLoai = Phong.maLoai JOIN dbo.HoaDonPhong ON HoaDonPhong.maPhong = Phong.maPhong WHERE maHD = ?", maHD);
                if (rs1.next()) {
                    giaphong = rs1.getLong("giaPhong");
                }
                pw.print(
                        dichvu(
                                i, "Tiền giờ", XuLy.tinhgio(XuLy.tinhgio(XuLy.convertDateTime(rs.getString("gioDatPhong")), XuLy.convertDateTime(rs.getString("gioDatPhong")))), XuLy.xulySo(giaphong + ""), XuLy.xulySo(rs.getString("tienGio"))
                        ));
                ResultSet rs2 = JDBCHelper.executeQuery("SELECT * FROM dbo.ChiTietHoaDon,dbo.DichVu WHERE DichVu.maDV = ChiTietHoaDon.maDV AND maHD = ?", maHD);
                while (rs2.next()) {
                    i++;
                    pw.print(dichvu(i, rs2.getString("tenDV"), rs2.getString("soLuong"), XuLy.xulySo(rs2.getLong("giaBan") + ""), XuLy.xulySo(rs2.getLong("giaBan") * rs2.getInt("soLuong") + "")));
                }
                ResultSet rs3 = JDBCHelper.executeQuery("SELECT * FROM dbo.Voucher WHERE maHD = ?", maHD);
                if (rs3.next()) {
                    voucher = "(" + rs3.getString("code") + " -" + rs3.getString("value") + "%)";
                }

            }
            pw.print(
                    footer(
                            XuLy.xulySo(rs.getString("tongTien")), voucher, XuLy.xulySo(rs.getString("giamGia")), XuLy.xulySo(rs.getString("thanhTien")), XuLy.xulySo(rs.getString("tienKhachDua")), XuLy.xulySo(rs.getLong("tienKhachDua") - rs.getLong("thanhTien") + ""), rs.getString("nguoiLap"), rs.getString("tangVoucher")));

            File file = new File(path);
            java.awt.Desktop.getDesktop().open(file);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
