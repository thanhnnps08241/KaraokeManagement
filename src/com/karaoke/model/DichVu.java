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
public class DichVu {   
   
    private int maDV;
    private String maLoai;
    private String tenDV;
    private long giaBan;
    private int tonKho;
    private String hinh;
    private boolean trangThai;

    public DichVu() {
    }

    public DichVu(int maDV, String maLoai, String tenDV, long giaBan, int tonKho, String hinh, boolean trangThai) {
        this.maDV = maDV;
        this.maLoai = maLoai;
        this.tenDV = tenDV;
        this.giaBan = giaBan;
        this.tonKho = tonKho;
        this.hinh = hinh;
        this.trangThai = trangThai;
    }

    public int getMaDV() {
        return maDV;
    }

    public void setMaDV(int maDV) {
        this.maDV = maDV;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public long getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(long giaBan) {
        this.giaBan = giaBan;
    }

    public int getTonKho() {
        return tonKho;
    }

    public void setTonKho(int tonKho) {
        this.tonKho = tonKho;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
    
    
    
   
    
    
}
