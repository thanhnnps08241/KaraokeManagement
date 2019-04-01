/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karaoke.model;

/**
 *
 * @author Thien
 */
public class DatPhongOnline {
    private int id;
    private String tenKH;
    private String soDT;
    private String TGDatPhong;
    private String maLoai;
    private int songuoi;

    public DatPhongOnline() {
    }

    public DatPhongOnline(int id, String tenKH, String soDT, String TGDatPhong, String maLoai, int songuoi) {
        this.id = id;
        this.tenKH = tenKH;
        this.soDT = soDT;
        this.TGDatPhong = TGDatPhong;
        this.maLoai = maLoai;
        this.songuoi = songuoi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getTGDatPhong() {
        return TGDatPhong;
    }

    public void setTGDatPhong(String TGDatPhong) {
        this.TGDatPhong = TGDatPhong;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public int getSonguoi() {
        return songuoi;
    }

    public void setSonguoi(int songuoi) {
        this.songuoi = songuoi;
    }
    
    
}
