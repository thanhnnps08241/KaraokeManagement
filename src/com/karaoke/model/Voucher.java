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
    private String tenvoucher;
    private int phantram;
    private String hanDung;

    public Voucher() {
    }

    public Voucher(String codevoucher, String tenvoucher, int phantram, String hanDung) {
        this.codevoucher = codevoucher;
        this.tenvoucher = tenvoucher;
        this.phantram = phantram;
        this.hanDung = hanDung;
    }

    public String getCodevoucher() {
        return codevoucher;
    }

    public void setCodevoucher(String codevoucher) {
        this.codevoucher = codevoucher;
    }

    public String getTenvoucher() {
        return tenvoucher;
    }

    public void setTenvoucher(String tenvoucher) {
        this.tenvoucher = tenvoucher;
    }

    public int getPhantram() {
        return phantram;
    }

    public void setPhantram(int phantram) {
        this.phantram = phantram;
    }

    public String getHanDung() {
        return hanDung;
    }

    public void setHanDung(String hanDung) {
        this.hanDung = hanDung;
    }
    
    
}
