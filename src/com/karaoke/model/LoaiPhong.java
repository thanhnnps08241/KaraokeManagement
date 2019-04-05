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
public class LoaiPhong {
    private String maLoai;
    private String tenLoai;
    private long giaPhong;
    public LoaiPhong(){
        
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public long getGiaPhong() {
        return giaPhong;
    }

    public void setGiaPhong(long giaPhong) {
        this.giaPhong = giaPhong;
    }
    
}
