/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karaoke.main;

import com.karaoke.ui.MainJFrame;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Admin
 */
public class Main {
    public static void main(String[] args) throws UnsupportedLookAndFeelException {
//        new MainJFrame().setVisible(true);
        MainJFrame dialog = new MainJFrame();
        dialog.setTitle("Đăng Nhập Hệ Thống");
        //dialog.setResizable(false);
       // dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
