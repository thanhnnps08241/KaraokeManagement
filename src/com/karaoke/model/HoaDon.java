/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karaoke.model;


public class HoaDon {
    private int maHD;
    private String maPhong;
    private String codevoucher;
    private String soDT;
    private String tenKH;
    private long tienDV;
    private long tienGio;
    private long tienGiamGia;
    private long tongTien;
    private long thanhTien;
    private long tienKhachDua;
    private String gioDatPhong;
    private String gioThanhToan;
    private String ghiChu;
    private String maUser;
    
    public HoaDon(){
        
    }

    public HoaDon(int maHD, String maPhong, String codevoucher, String soDT, String tenKH, long tienDV, long tienGio, long tienGiamGia, long tongTien, long thanhTien, long tienKhachDua, String gioDatPhong, String gioThanhToan, String ghiChu, String maUser) {
        this.maHD = maHD;
        this.maPhong = maPhong;
        this.codevoucher = codevoucher;
        this.soDT = soDT;
        this.tenKH = tenKH;
        this.tienDV = tienDV;
        this.tienGio = tienGio;
        this.tienGiamGia = tienGiamGia;
        this.tongTien = tongTien;
        this.thanhTien = thanhTien;
        this.tienKhachDua = tienKhachDua;
        this.gioDatPhong = gioDatPhong;
        this.gioThanhToan = gioThanhToan;
        this.ghiChu = ghiChu;
        this.maUser = maUser;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getCodevoucher() {
        return codevoucher;
    }

    public void setCodevoucher(String codevoucher) {
        this.codevoucher = codevoucher;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public long getTienDV() {
        return tienDV;
    }

    public void setTienDV(long tienDV) {
        this.tienDV = tienDV;
    }

    public long getTienGio() {
        return tienGio;
    }

    public void setTienGio(long tienGio) {
        this.tienGio = tienGio;
    }

    public long getTienGiamGia() {
        return tienGiamGia;
    }

    public void setTienGiamGia(long tienGiamGia) {
        this.tienGiamGia = tienGiamGia;
    }

    public long getTongTien() {
        return tongTien;
    }

    public void setTongTien(long tongTien) {
        this.tongTien = tongTien;
    }

    public long getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(long thanhTien) {
        this.thanhTien = thanhTien;
    }

    public long getTienKhachDua() {
        return tienKhachDua;
    }

    public void setTienKhachDua(long tienKhachDua) {
        this.tienKhachDua = tienKhachDua;
    }

    public String getGioDatPhong() {
        return gioDatPhong;
    }

    public void setGioDatPhong(String gioDatPhong) {
        this.gioDatPhong = gioDatPhong;
    }

    public String getGioThanhToan() {
        return gioThanhToan;
    }

    public void setGioThanhToan(String gioThanhToan) {
        this.gioThanhToan = gioThanhToan;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getMaUser() {
        return maUser;
    }

    public void setMaUser(String maUser) {
        this.maUser = maUser;
    }
    
    

   

}
