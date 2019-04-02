/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karaoke.helper;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class OpenJPanel {
    public static void openJPanel(JPanel pnlRoot, JPanel pnlShow) {
        pnlRoot.removeAll();
        pnlRoot.setLayout(new BorderLayout());
        pnlRoot.add(pnlShow);
        pnlRoot.validate();
        pnlRoot.repaint();
    }
}
