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
public class Voucher {
    private String codevoucher;
    private int triGia;
    private String hanDung;

    public Voucher() {
    }

    public Voucher(String codevoucher, int triGia, String hanDung) {
        this.codevoucher = codevoucher;
        this.triGia = triGia;
        this.hanDung = hanDung;
    }

    public String getCodevoucher() {
        return codevoucher;
    }

    public void setCodevoucher(String codevoucher) {
        this.codevoucher = codevoucher;
    }

    public int getTriGia() {
        return triGia;
    }

    public void setTriGia(int triGia) {
        this.triGia = triGia;
    }

    public String getHanDung() {
        return hanDung;
    }

    public void setHanDung(String hanDung) {
        this.hanDung = hanDung;
    }

    
    
    
}
