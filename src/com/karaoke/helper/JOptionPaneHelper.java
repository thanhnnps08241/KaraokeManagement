/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karaoke.helper;

import static com.karaoke.helper.XuLy.loi;
import static com.karaoke.helper.XuLy.msg;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author PS08241 - Nguyen Nhat Thanh
 */
public class JOptionPaneHelper {
    public static void popup(String type, JDialog j, String msg, String title) {
        if (type.equals("loi")) {
            ImageIcon icon = new ImageIcon(j.getClass().getResource("/com/karaoke/images/icon/JoptionPaneERROR.png"));
            JOptionPane.showMessageDialog(j, loi(msg), title, JOptionPane.ERROR_MESSAGE, icon);
        }
        if (type.equals("msg")) {
            ImageIcon icon = new ImageIcon(j.getClass().getResource("/com/karaoke/images/icon/JoptionPaneINFO.png"));
            JOptionPane.showMessageDialog(j, msg(msg), title, JOptionPane.INFORMATION_MESSAGE, icon);
        }
        if (type.equals("ok")) {
            ImageIcon icon = new ImageIcon(j.getClass().getResource("/com/karaoke/images/icon/JoptionPaneOK.png"));
            JOptionPane.showMessageDialog(j, msg(msg), title, JOptionPane.INFORMATION_MESSAGE, icon);
        }

    }
    public static void popup(String type, JPanel j, String msg, String title) {
        if (type.equals("loi")) {
            ImageIcon icon = new ImageIcon(j.getClass().getResource("/com/karaoke/images/icon/JoptionPaneERROR.png"));
            JOptionPane.showMessageDialog(j, loi(msg), title, JOptionPane.ERROR_MESSAGE, icon);
        }
        if (type.equals("msg")) {
            ImageIcon icon = new ImageIcon(j.getClass().getResource("/com/karaoke/images/icon/JoptionPaneINFO.png"));
            JOptionPane.showMessageDialog(j, msg(msg), title, JOptionPane.INFORMATION_MESSAGE, icon);
        }
        if (type.equals("ok")) {
            ImageIcon icon = new ImageIcon(j.getClass().getResource("/com/karaoke/images/icon/JoptionPaneOK.png"));
            JOptionPane.showMessageDialog(j, msg(msg), title, JOptionPane.INFORMATION_MESSAGE, icon);
        }

    }

    public static void popup(String type, JFrame j, String msg, String title) {
        if (type.equals("loi")) {
            ImageIcon icon = new ImageIcon(j.getClass().getResource("/com/karaoke/images/icon/JoptionPaneERROR.png"));
            JOptionPane.showMessageDialog(j, loi(msg), title, JOptionPane.ERROR_MESSAGE, icon);
        }
        if (type.equals("msg")) {
            ImageIcon icon = new ImageIcon(j.getClass().getResource("/com/karaoke/images/icon/JoptionPaneINFO.png"));
            JOptionPane.showMessageDialog(j, msg(msg), title, JOptionPane.INFORMATION_MESSAGE, icon);
        }
        if (type.equals("ok")) {
            ImageIcon icon = new ImageIcon(j.getClass().getResource("/com/karaoke/images/icon/JoptionPaneOK.png"));
            JOptionPane.showMessageDialog(j, msg(msg), title, JOptionPane.INFORMATION_MESSAGE, icon);
        }
        if (type.equals("deny")) {
            ImageIcon icon = new ImageIcon(j.getClass().getResource("/com/karaoke/images/icon/JoptionPaneDENY.png"));
            JOptionPane.showMessageDialog(j, loi(msg), title, JOptionPane.ERROR_MESSAGE, icon);
        }

    }
     public static int ask(JFrame j, String msg, String title) {
        ImageIcon icon = new ImageIcon(j.getClass().getResource("/com/karaoke/images/icon/JoptionPaneQUESTION.png"));
        String[] options = new String[2];
        options[0] = new String("Xác nhận");
        options[1] = new String("Hủy");
        int ask = JOptionPane.showOptionDialog(j, XuLy.msg(msg), title, 0, JOptionPane.QUESTION_MESSAGE, icon, options, options[0]);
        return ask;
    }

    public static int ask(JDialog j, String msg, String title) {
        ImageIcon icon = new ImageIcon(j.getClass().getResource("/com/karaoke/images/icon/JoptionPaneQUESTION.png"));
        String[] options = new String[2];
        options[0] = new String("Xác nhận");
        options[1] = new String("Hủy");
        int ask = JOptionPane.showOptionDialog(j, XuLy.msg(msg), title, 0, JOptionPane.QUESTION_MESSAGE, icon, options, options[0]);
        return ask;
    }
    public static int ask(JPanel j, String msg, String title) {
        ImageIcon icon = new ImageIcon(j.getClass().getResource("/com/karaoke/images/icon/JoptionPaneQUESTION.png"));
        String[] options = new String[2];
        options[0] = new String("Xác nhận");
        options[1] = new String("Hủy");
        int ask = JOptionPane.showOptionDialog(j, XuLy.msg(msg), title, 0, JOptionPane.QUESTION_MESSAGE, icon, options, options[0]);
        return ask;
    }

}
