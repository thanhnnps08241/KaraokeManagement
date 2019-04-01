/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karaoke.model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class HoaDon {
    private int maHD;
    private String maPhong;
    private String codevoucher;
    private String soDT;
    private String tenKH;
    private long tienDV;
    private long tienGio;
    private long tiengGiamGia;
    private long thanhTien;
    private long tienKhachDua;
    private String gioDatPhong;
    private String gioThanhToan;
    private String ghiChu;
    private String username;
    public HoaDon(){
        
    }

    public HoaDon(int maHD, String maPhong, String codevoucher, String soDT, String tenKH, long tienDV, long tienGio, long tiengGiamGia, long thanhTien, long tienKhachDua, String gioDatPhong, String gioThanhToan, String ghiChu, String username) {
        this.maHD = maHD;
        this.maPhong = maPhong;
        this.codevoucher = codevoucher;
        this.soDT = soDT;
        this.tenKH = tenKH;
        this.tienDV = tienDV;
        this.tienGio = tienGio;
        this.tiengGiamGia = tiengGiamGia;
        this.thanhTien = thanhTien;
        this.tienKhachDua = tienKhachDua;
        this.gioDatPhong = gioDatPhong;
        this.gioThanhToan = gioThanhToan;
        this.ghiChu = ghiChu;
        this.username = username;
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

    public long getTiengGiamGia() {
        return tiengGiamGia;
    }

    public void setTiengGiamGia(long tiengGiamGia) {
        this.tiengGiamGia = tiengGiamGia;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    

}
