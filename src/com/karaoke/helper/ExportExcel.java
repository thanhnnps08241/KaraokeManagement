package com.karaoke.helper;

import java.awt.Desktop;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author oluwajayi
 */
public class ExportExcel {

    public static void Writer(JDialog JD, JTable jTable1) throws FileNotFoundException, IOException {
        JFileChooser save = new JFileChooser();
        save.setDialogTitle("Save as...");
        save.setSelectedFile(new File(jTable1.getName() + ".xls"));
        save.setFileFilter(new FileNameExtensionFilter("*.xls (Excel file)", "xls"));
        int choose = save.showSaveDialog(JD);
        if (choose == JFileChooser.APPROVE_OPTION) {
            HSSFWorkbook fWorkbook = new HSSFWorkbook();
            HSSFSheet fSheet = fWorkbook.createSheet(jTable1.getName());
            HSSFFont sheetTitleFont = fWorkbook.createFont();
            HSSFCellStyle cellStyle = fWorkbook.createCellStyle();
            cellStyle.setAlignment(HorizontalAlignment.LEFT);
            cellStyle.setBorderBottom(BorderStyle.THIN);
            cellStyle.setBorderTop(BorderStyle.THIN);

            sheetTitleFont.setFontName("Tahoma");
            fSheet.setFitToPage(true);

            //sheetTitleFont.setColor();
            TableModel model = jTable1.getModel();

            //Get Header
            TableColumnModel tcm = jTable1.getColumnModel();
            HSSFRow hRow = fSheet.createRow((short) 0);

            for (int j = 0; j < tcm.getColumnCount(); j++) {
                HSSFCell cell = hRow.createCell((short) j);
                cell.setCellValue(tcm.getColumn(j).getHeaderValue().toString());
                cell.setCellStyle(cellStyle);
                fSheet.autoSizeColumn(j); //resize lại colums

            }

            //Get Other details
            for (int i = 0; i < model.getRowCount(); i++) {
                HSSFRow fRow = fSheet.createRow((short) i + 1);
                for (int j = 0; j < model.getColumnCount(); j++) {
                    HSSFCell cell = fRow.createCell((short) j);
                    cell.setCellValue(model.getValueAt(i, j).toString());
                    cell.setCellStyle(cellStyle);
                    fSheet.autoSizeColumn(i); //resize lại colums

                }
            }
            FileOutputStream fileOutputStream;
            fileOutputStream = new FileOutputStream(save.getSelectedFile());
            try (BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream)) {
                fWorkbook.write(bos);

            }
            fileOutputStream.close();
            if (JDialogHelper.ask(JD, "Trích xuất file Excel thành công, mở file vừa lưu?", "Export to Excel") == 0) {
                Desktop.getDesktop().open(save.getSelectedFile());

                JD.requestFocusInWindow();
            }

        }
    }

    public static void Writer(JDialog JD, JTable jTable1, JTable jTable2) throws FileNotFoundException, IOException {
        JFileChooser save = new JFileChooser();
        save.setDialogTitle("Save as...");
        save.setSelectedFile(new File(jTable1.getName() + ".xls"));
        save.setFileFilter(new FileNameExtensionFilter("*.xls (Excel file)", "xls"));
        int choose = save.showSaveDialog(JD);
        if (choose == JFileChooser.APPROVE_OPTION) {
            HSSFWorkbook fWorkbook = new HSSFWorkbook();
            HSSFSheet fSheet = fWorkbook.createSheet(jTable1.getName());
            HSSFSheet fSheet2 = fWorkbook.createSheet(jTable2.getName());
            HSSFFont sheetTitleFont = fWorkbook.createFont();
            HSSFCellStyle cellStyle = fWorkbook.createCellStyle();
            cellStyle.setAlignment(HorizontalAlignment.LEFT);
            cellStyle.setBorderBottom(BorderStyle.THIN);
            cellStyle.setBorderTop(BorderStyle.THIN);

            sheetTitleFont.setFontName("Tahoma");
            fSheet.setFitToPage(true);
            fSheet2.setFitToPage(true);

            //sheetTitleFont.setColor();
            TableModel model = jTable1.getModel();

            //Get Header
            TableColumnModel tcm = jTable1.getColumnModel();
            HSSFRow hRow = fSheet.createRow((short) 0);

            for (int j = 0; j < tcm.getColumnCount(); j++) {
                HSSFCell cell = hRow.createCell((short) j);
                cell.setCellValue(tcm.getColumn(j).getHeaderValue().toString());
                cell.setCellStyle(cellStyle);
                fSheet.autoSizeColumn(j); //resize lại colums

            }

            //Get Other details
            for (int i = 0; i < model.getRowCount(); i++) {
                HSSFRow fRow = fSheet.createRow((short) i + 1);
                for (int j = 0; j < model.getColumnCount(); j++) {
                    HSSFCell cell = fRow.createCell((short) j);
                    cell.setCellValue(model.getValueAt(i, j).toString());
                    cell.setCellStyle(cellStyle);
                    fSheet.autoSizeColumn(i); //resize lại colums

                }
            }
            
            
            //sheetTitleFont.setColor();
            TableModel model2 = jTable2.getModel();

            //Get Header
            TableColumnModel tcm2 = jTable2.getColumnModel();
            HSSFRow hRow2 = fSheet2.createRow((short) 0);

            for (int j = 0; j < tcm2.getColumnCount(); j++) {
                HSSFCell cell = hRow2.createCell((short) j);
                cell.setCellValue(tcm2.getColumn(j).getHeaderValue().toString());
                cell.setCellStyle(cellStyle);
                fSheet2.autoSizeColumn(j); //resize lại colums

            }

            //Get Other details
            for (int i = 0; i < model2.getRowCount(); i++) {
                HSSFRow fRow2 = fSheet2.createRow((short) i + 1);
                for (int j = 0; j < model2.getColumnCount(); j++) {
                    HSSFCell cell = fRow2.createCell((short) j);
                    cell.setCellValue(model2.getValueAt(i, j).toString());
                    cell.setCellStyle(cellStyle);
                    fSheet2.autoSizeColumn(i); //resize lại colums

                }
            }
            FileOutputStream fileOutputStream;
            fileOutputStream = new FileOutputStream(save.getSelectedFile());
            try (BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream)) {
                fWorkbook.write(bos);

            }
            fileOutputStream.close();
            if (JDialogHelper.ask(JD, "Trích xuất file Excel thành công, mở file vừa lưu?", "Export to Excel") == 0) {
                Desktop.getDesktop().open(save.getSelectedFile());

                JD.requestFocusInWindow();
            }

        }
    }

    public static void Writer(JFrame JF, JTable jTable1) throws FileNotFoundException, IOException {
        JFileChooser save = new JFileChooser();
        save.setDialogTitle("Save as...");
        save.setSelectedFile(new File(jTable1.getName() + ".xls"));
        save.setFileFilter(new FileNameExtensionFilter("*.xls (Excel file)", "xls"));
        int choose = save.showSaveDialog(JF);
        if (choose == JFileChooser.APPROVE_OPTION) {
            HSSFWorkbook fWorkbook = new HSSFWorkbook();
            HSSFSheet fSheet = fWorkbook.createSheet(jTable1.getName());
            HSSFFont sheetTitleFont = fWorkbook.createFont();
            HSSFCellStyle cellStyle = fWorkbook.createCellStyle();
            cellStyle.setAlignment(HorizontalAlignment.LEFT);
            cellStyle.setBorderBottom(BorderStyle.THIN);
            cellStyle.setBorderTop(BorderStyle.THIN);

            sheetTitleFont.setFontName("Tahoma");
            fSheet.setFitToPage(true);

            //sheetTitleFont.setColor();
            TableModel model = jTable1.getModel();

            //Get Header
            TableColumnModel tcm = jTable1.getColumnModel();
            HSSFRow hRow = fSheet.createRow((short) 0);

            for (int j = 0; j < tcm.getColumnCount(); j++) {
                HSSFCell cell = hRow.createCell((short) j);
                cell.setCellValue(tcm.getColumn(j).getHeaderValue().toString());
                cell.setCellStyle(cellStyle);
                fSheet.autoSizeColumn(j); //resize lại colums

            }

            //Get Other details
            for (int i = 0; i < model.getRowCount(); i++) {
                HSSFRow fRow = fSheet.createRow((short) i + 1);
                for (int j = 0; j < model.getColumnCount(); j++) {
                    HSSFCell cell = fRow.createCell((short) j);
                    cell.setCellValue(model.getValueAt(i, j).toString());
                    cell.setCellStyle(cellStyle);
                    fSheet.autoSizeColumn(i); //resize lại colums

                }
            }
            FileOutputStream fileOutputStream;
            fileOutputStream = new FileOutputStream(save.getSelectedFile());
            try (BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream)) {
                fWorkbook.write(bos);

            }
            fileOutputStream.close();
            if (JDialogHelper.ask(JF, "Trích xuất file Excel thành công, mở file vừa lưu?", "Export to Excel") == 0) {
                Desktop.getDesktop().open(save.getSelectedFile());

                JF.requestFocusInWindow();
            }

        }
    }
}
