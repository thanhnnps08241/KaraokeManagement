/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karaoke.helper;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class XuLy {

    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static SecureRandom rnd = new SecureRandom();

    public static String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }

    public static String MD5encryption(String str) {
        byte[] defaultBytes = str.getBytes();
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(defaultBytes);
            byte messageDigest[] = algorithm.digest();
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String hex = Integer.toHexString(0xFF & messageDigest[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            str = hexString + "";
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String loi(String msg) {
        return "<html><b><font color = red size = 4>" + msg + "</html>";
    }

    public static String msg(String msg) {
        return "<html><b><font color = #006699 size = 4>" + msg + "</html>";
    }

    public static void popup(String type, JDialog j, String msg, String title) {
        if (type.equals("loi")) {
            ImageIcon icon = new ImageIcon(j.getClass().getResource("/com/karaoke/images/icon/JoptionPaneERROR.png"));
            JOptionPane.showMessageDialog(j, loi(msg), title, JOptionPane.ERROR_MESSAGE, icon);
        }
        if (type.equals("msg")) {
            ImageIcon icon = new ImageIcon(j.getClass().getResource("/com/karaoke/images/icon/JoptionPaneINFO.png"));
            JOptionPane.showMessageDialog(j, msg(msg), title, JOptionPane.INFORMATION_MESSAGE, icon);
        }
        if (type.equals("ok")) {
            ImageIcon icon = new ImageIcon(j.getClass().getResource("/com/karaoke/images/icon/JoptionPaneOK.png"));
            JOptionPane.showMessageDialog(j, msg(msg), title, JOptionPane.INFORMATION_MESSAGE, icon);
        }

    }

    public static void popup(String type, JFrame j, String msg, String title) {
        if (type.equals("loi")) {
            ImageIcon icon = new ImageIcon(j.getClass().getResource("/com/karaoke/images/icon/JoptionPaneERROR.png"));
            JOptionPane.showMessageDialog(j, loi(msg), title, JOptionPane.ERROR_MESSAGE, icon);
        }
        if (type.equals("msg")) {
            ImageIcon icon = new ImageIcon(j.getClass().getResource("/com/karaoke/images/icon/JoptionPaneINFO.png"));
            JOptionPane.showMessageDialog(j, msg(msg), title, JOptionPane.INFORMATION_MESSAGE, icon);
        }
        if (type.equals("ok")) {
            ImageIcon icon = new ImageIcon(j.getClass().getResource("/com/karaoke/images/icon/JoptionPaneOK.png"));
            JOptionPane.showMessageDialog(j, msg(msg), title, JOptionPane.INFORMATION_MESSAGE, icon);
        }
        if (type.equals("deny")) {
            ImageIcon icon = new ImageIcon(j.getClass().getResource("/com/karaoke/images/icon/JoptionPaneDENY.png"));
            JOptionPane.showMessageDialog(j, loi(msg), title, JOptionPane.ERROR_MESSAGE, icon);
        }

    }

    public static int ask(JFrame j, String msg, String title) {
        ImageIcon icon = new ImageIcon(j.getClass().getResource("/com/karaoke/images/icon/JoptionPaneQUESTION.png"));
        String[] options = new String[2];
        options[0] = new String("Xác nhận");
        options[1] = new String("Hủy");
        int ask = JOptionPane.showOptionDialog(j, XuLy.msg(msg), title, 0, JOptionPane.QUESTION_MESSAGE, icon, options, options[0]);
        return ask;
    }

    public static int ask(JDialog j, String msg, String title) {
        ImageIcon icon = new ImageIcon(j.getClass().getResource("/com/karaoke/images/icon/JoptionPaneQUESTION.png"));
        String[] options = new String[2];
        options[0] = new String("Xác nhận");
        options[1] = new String("Hủy");
        int ask = JOptionPane.showOptionDialog(j, XuLy.msg(msg), title, 0, JOptionPane.QUESTION_MESSAGE, icon, options, options[0]);
        return ask;
    }

    public static String xulySo(String chuoi) {
        double amount = Double.parseDouble(chuoi);
        DecimalFormat formatter = new DecimalFormat("#,###đ");
        return formatter.format(amount);
    }

    public static String returnSo(String chuoi) {
        chuoi = chuoi.replaceAll("đ", "");
        chuoi = chuoi.replaceAll(",", "");
        return chuoi;

    }

    public static String makeupHoTen(String s) {
        // XAY DUNG CHUC NANG XU LY HO TEN
        String dxl = "";

        s = s.trim();
        s = s.toLowerCase();

        //xoa cac ki tu trang trong chuoi
        while (s.contains("  ")) {
            s = s.replaceAll("  ", " ");
        }

        // chuyen thanh mang
        String[] sa = s.split(" ");

        //xuat mang
        for (String item : sa) {
            //System.out.print(s[i].toUpperCase() + " ");
            for (int j = 0; j < item.length(); j++) {
                if (j == 0) {
                    String hoa = String.valueOf(item.charAt(0));
                    dxl += hoa.toUpperCase();
                } else {
                    dxl += String.valueOf(item.charAt(j));
                }
            }
            dxl += " ";
        }
        dxl = dxl.trim();
        return dxl;
    }

    public static boolean ktsdt(String s) {
        while (!s.matches("\\d{10,12}")) {
            return false;

        }
        return true;
    }

    public static long[] tinhgio(String dateStart, String dateStop) {
        long[] x = new long[4];
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        dateStop = format.format(new Date());

        Date d1 = null;
        Date d2 = null;

        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);

            //in milliseconds
            long diff = d2.getTime() - d1.getTime();

            x[3] = diff / 1000 % 60; //GIAY
            x[2] = diff / (60 * 1000) % 60; //PHUT
            x[1] = diff / (60 * 60 * 1000) % 24; //GIO
            x[0] = diff / (24 * 60 * 60 * 1000); //NGAY

        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;

    }

    public static String tinhgio(long[] x) {
        String thoiluong = "";
        if (x[0] != 0) {
            thoiluong += x[0] < 10 ? " 0" + x[0] + " ngày " : x[0] + " ngày ";
        }
        if (x[1] != 0) {
            thoiluong += x[1] < 10 ? " 0" + x[1] + " giờ " : x[1] + " giờ ";
        }
        if (x[2] != 0) {
            thoiluong += x[2] < 10 ? " 0" + x[2] + " phút " : x[2] + " phút ";

        }
        if (thoiluong.equals("")) {
            thoiluong = "00 phút";

        }
        return thoiluong;

    }

    public static String convertDateTime(String date) {
        try {
            SimpleDateFormat dateSQL = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat dateVN = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            return dateVN.format(dateSQL.parse(date));
        } catch (ParseException e) {
        }
        return "";

    }

    public static String convertDate(String date) {
        try {
            SimpleDateFormat dateSQL = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat dateVN = new SimpleDateFormat("dd/MM/yyyy");
            return dateVN.format(dateSQL.parse(date));
        } catch (ParseException e) {
        }
        return "";

    }

    public static String khongDau(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("").replace('đ', 'd').replace('Đ', 'D');
    }

    public static String[] addVoucher(String value) throws InterruptedException {
        String[] voucher = new String[2];
        voucher[0] = randomString(8);
        voucher[1] = "-" + value + "%";
        JDBCHelper.executeUpdate("INSERT INTO Voucher VALUES (?,?,?,?)", voucher[0], value, 0, null);

        return voucher;

    }

}
