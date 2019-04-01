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
public class Phong {
    private String maPhong;
    private String maLoai;
    private boolean tinhTrang;
    private int succhua;
    public Phong(){
    }

    public Phong(String maPhong, String maLoai, boolean tinhTrang, int succhua) {
        this.maPhong = maPhong;
        this.maLoai = maLoai;
        this.tinhTrang = tinhTrang;
        this.succhua = succhua;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public boolean isTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(boolean tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public int getSucchua() {
        return succhua;
    }

    public void setSucchua(int succhua) {
        this.succhua = succhua;
    }
    
}
