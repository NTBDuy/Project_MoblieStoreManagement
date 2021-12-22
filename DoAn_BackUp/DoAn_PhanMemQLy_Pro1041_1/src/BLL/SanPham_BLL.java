/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.SanPham_DAL;
import DTO.SanPham;
import Untils.MessBox;
import Untils.Tool;
import java.awt.Font;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Duy
 */
public class SanPham_BLL {
    SanPham_DAL spDAL = new SanPham_DAL();
    
    public void LoadTable(JTable tbl, ArrayList<SanPham> lstSP){
        tbl.removeAll();
        Object[] obj = new Object[]{"STT","Mã SP", "Tên sản phẩm", "NSX", "Đặc điểm",
            "SL", "Giá nhập", "Giá bán", "Ngày nhập", "Hình ảnh"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(obj);
        Vector tbRowData;
        int i = 1;
        for (SanPham sp : lstSP) {
            tbRowData = new Vector();
            tbRowData.add(i);
            tbRowData.add(sp.getMaSP());
            tbRowData.add(sp.getTenSP());
            tbRowData.add(sp.getNhaSX());
            tbRowData.add(sp.getDacDiem());
            tbRowData.add(sp.getSoLuong());
            tbRowData.add(new DecimalFormat("###,###.###").format(sp.getGiaNhap()));
            tbRowData.add(new DecimalFormat("###,###.###").format(sp.getGiaBan()));
            tbRowData.add(sp.getNgayNhap());
            tbRowData.add(sp.getHinhAnh());
            model.addRow(tbRowData);
            i++;
        }
        tbl.setModel(model);
        tbl.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        Tool.setJTableColumnsWidth(tbl, 1240, 3, 9, 20, 5, 8, 5, 10, 10, 10, 8);
    }
    
    public void LoadTableBH(JTable tbl, ArrayList<SanPham> lstSP){
        tbl.removeAll();
        Object[] obj = new Object[]{"Tên SP", "Đơn giá"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(obj);
        Vector tbRowData;
        for (SanPham sp : lstSP) {
            tbRowData = new Vector();
            tbRowData.add(sp.getTenSP());
            tbRowData.add(new DecimalFormat("###,###.###").format(sp.getGiaBan()));
            model.addRow(tbRowData);
        }
        tbl.setModel(model);
        tbl.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        Tool.setJTableColumnsWidth(tbl, 1240, 15, 8);
    }
    public ArrayList<SanPham> getALL(){
        return spDAL.getALL();
    }
    public boolean check(String id){
        return spDAL.check(id);
    }
    public ArrayList<SanPham> getByID(String id){
        return (ArrayList<SanPham>) spDAL.getByID("%"+id+"%");
    }
    public ArrayList<SanPham> getByidBh(String id){
        return (ArrayList<SanPham>) spDAL.getByidBh("%"+id+"%");
    }
    public SanPham getByMa(String id){
        return spDAL.getByMa(id);
    }
    public boolean themSP(SanPham sp) {
        if (sp.getTenSP().equals("") || sp.getNhaSX().equals("") || sp.getNgayNhap().equals("") || sp.getHinhAnh().equals("")) {
            MessBox.alert(null, "Bạn chưa nhập đầy đủ dữ liệu!");
            return false;
        }
        return spDAL.themSP(sp);
    }
    public boolean suaSP(SanPham sp) {
        if (sp.getTenSP().equals("") || sp.getNhaSX().equals("") || sp.getNgayNhap().equals("") || sp.getHinhAnh().equals("")) {
            MessBox.alert(null, "Bạn chưa nhập đầy đủ dữ liệu!");
            return false;
        }
        return spDAL.suaSP(sp);
    }

    public boolean xoaSP(String MaSP) {
        return spDAL.xoaSP(MaSP);
    }

    public String autoID() throws SQLException {
        return spDAL.Auto_ID();
    }
    public int SLTon() throws SQLException {
        return spDAL.SLTon();
    }
    public int SLspBan() throws SQLException {
        return spDAL.SLspBan();
    }
}
