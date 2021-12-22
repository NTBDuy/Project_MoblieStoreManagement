/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.CTHoaDon_DAL;
import DTO.CTHoaDon;
import Untils.Tool;
import java.awt.Font;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Duy
 */
public class CTHoaDon_BLL {
    CTHoaDon_DAL ctDAL = new CTHoaDon_DAL();
    
    public Double LoadTable(JTable tbl, ArrayList<CTHoaDon> lst){
        tbl.removeAll();
        Object[] tieude = new Object[]{"Mã SP", "Tên SP",  "SL", 
                                        "Đơn giá", "Giảm (%)", "Thành tiền"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(tieude);
        double TongTien = 0.0;
        Vector tbRowData;
        for (CTHoaDon ct : lst){
            tbRowData = new Vector();
            tbRowData.add(ct.getMaSP());
            tbRowData.add(ct.getTenSP());
            tbRowData.add(ct.getSoLuong());
            tbRowData.add(new DecimalFormat("###,###.###").format(ct.getDonGia()));
            tbRowData.add(String.valueOf(ct.getGiamGia()) + "%");
            tbRowData.add(new DecimalFormat("###,###.###").format(ct.getThanhTien()));
            model.addRow(tbRowData);
            TongTien += ct.getThanhTien();
        }
        tbl.setModel(model);
        tbl.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        Tool.setJTableColumnsWidth(tbl, 450, 0, 30, 5, 20, 15, 30);
        tbl.getColumn("Mã SP").setMinWidth(0);
        tbl.getColumn("Mã SP").setMaxWidth(0);
        tbl.getColumn("Mã SP").setWidth(0);
        return TongTien;
    }
    
    public Double LoadTableCT(JTable tbl, ArrayList<CTHoaDon> lst){
        tbl.removeAll();
        Object[] tieude = new Object[]{"Mã SP", "Tên SP",  "SL", 
                                        "Đơn giá", "Giảm (%)", "Thành tiền"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(tieude);
        double TongTien = 0.0;
        Vector tbRowData;
        for (CTHoaDon ct : lst){
            tbRowData = new Vector();
            tbRowData.add(ct.getMaSP());
            tbRowData.add(ct.getTenSP());
            tbRowData.add(ct.getSoLuong());
            tbRowData.add(new DecimalFormat("###,###.###").format(ct.getDonGia()));
            tbRowData.add(String.valueOf(ct.getGiamGia()) + "%");
            tbRowData.add(new DecimalFormat("###,###.###").format(ct.getThanhTien()));
            model.addRow(tbRowData);
            TongTien += ct.getThanhTien();
        }
        tbl.setModel(model);
        tbl.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        Tool.setJTableColumnsWidth(tbl, 450, 0, 30, 5, 20, 15, 30);
        tbl.getColumn("Mã SP").setMinWidth(0);
        tbl.getColumn("Mã SP").setMaxWidth(0);
        tbl.getColumn("Mã SP").setWidth(0);
        return TongTien;
    }
    
    public ArrayList<CTHoaDon> getAll(){
        return ctDAL.getALL();
    }
    
    public boolean themCT(CTHoaDon ct){
        return ctDAL.them(ct);
    }
    
    public ArrayList<CTHoaDon> getByID(String id){
        return ctDAL.getByID(id);
    }
    
    public boolean suaCT(CTHoaDon ct){
        return ctDAL.sua(ct);
    }
    
    public boolean xoaALL(String MaHD){
        return ctDAL.xoaALL(MaHD);
    }
    
    public boolean xoaCT(String MaSP, String MaHD){
        return ctDAL.xoaSP(MaSP, MaHD);
    }
}
