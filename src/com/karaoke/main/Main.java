/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karaoke.main;

import com.karaoke.ui.DangNhapJPanel;
import com.karaoke.ui.MainJFrame;
import com.karaoke.ui.WelcomeJDialog;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Admin
 */
public class Main {
    public static void main(String[] args) throws UnsupportedLookAndFeelException {
//        new MainJFrame().setVisible(true);
        WelcomeJDialog welcome = new WelcomeJDialog(null, true);
        welcome.setVisible(true);
    }
}
