/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karaoke.model;

/**
 *
 * @author Admin
 */
public class PhieuNhapHang {
    private int maPN;
    private String maNhaCC;
    private long tongTien;
    private String ngayNhap;
    public PhieuNhapHang(){
        
    }    

    public PhieuNhapHang(int maPN, String maNhaCC, long tongTien, String ngayNhap) {
        this.maPN = maPN;
        this.maNhaCC = maNhaCC;
        this.tongTien = tongTien;
        this.ngayNhap = ngayNhap;
    }

    public int getMaPN() {
        return maPN;
    }

    public void setMaPN(int maPN) {
        this.maPN = maPN;
    }

    public String getMaNhaCC() {
        return maNhaCC;
    }

    public void setMaNhaCC(String maNhaCC) {
        this.maNhaCC = maNhaCC;
    }

    public long getTongTien() {
        return tongTien;
    }

    public void setTongTien(long tongTien) {
        this.tongTien = tongTien;
    }

    public String getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }
    
    
}
