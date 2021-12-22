/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.KhachHang_DAL;
import DTO.KhachHang;
import Untils.Check;
import Untils.MessBox;
import Untils.Tool;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Duy
 */
public class KhachHang_BLL {
    KhachHang_DAL khDAL = new KhachHang_DAL();
    public void LoadTable(JTable tbl, ArrayList<KhachHang> lstKH){
        tbl.removeAll();
        Object[] obj = new Object[]{"STT","Mã khách hàng", "Tên khách hàng", "Giới tính", "Ngày Sinh", "CMND", "SDT", "Địa Chỉ", "Email", "Ghi chú"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(obj);
        Vector tbRowData;
        int i = 1;
        for (KhachHang kh : lstKH) {
            tbRowData = new Vector();
            tbRowData.add(i);
            tbRowData.add(kh.getMaKH());
            tbRowData.add(kh.getTenKH());
            tbRowData.add(kh.isGioiTinh()? "Nam" : "Nữ");
            tbRowData.add(kh.getNgaySinh());
            tbRowData.add(kh.getCMND());
            tbRowData.add(kh.getSoDT());
            tbRowData.add(kh.getDiaChi());
            tbRowData.add(kh.getEmail());
            tbRowData.add(kh.getGhiChu());
            model.addRow(tbRowData);
            i++;
        }
        tbl.setModel(model);
        tbl.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        Tool.setJTableColumnsWidth(tbl, 1240, 3, 9, 15, 5, 8, 10, 10, 10, 10, 8);
    }
    
    public void LoadTableBH(JTable tbl, ArrayList<KhachHang> lstKH){
        tbl.removeAll();
        Object[] obj = new Object[]{"Tên KH", "SĐT"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(obj);
        Vector tbRowData;
        for (KhachHang kh : lstKH) {
            tbRowData = new Vector();
            tbRowData.add(kh.getTenKH());
            tbRowData.add(kh.getSoDT());
            model.addRow(tbRowData);
        }
        tbl.setModel(model);
        tbl.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        Tool.setJTableColumnsWidth(tbl, 1240, 15, 8);
    }
    
    public ArrayList<KhachHang> getALL(){
        return khDAL.getALL();
    }
    
    public KhachHang getByID(String id){
        List<KhachHang> list = khDAL.getByID(id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }
    public ArrayList<KhachHang> getByName(String name){
        return (ArrayList<KhachHang>) khDAL.getByName("%"+name+"%");
    }
    
    public boolean them(KhachHang kh) {
        if (kh.getTenKH().equals("") || kh.getCMND().equals("") || kh.getSoDT().equals("") || 
                kh.getNgaySinh().equals("") || kh.getEmail().equals("") || kh.getDiaChi().equals("")) {
            MessBox.alert(null, "Bạn chưa nhập đầy đủ dữ liệu!");
            return false;
        } else if(!Check.KTPhoneNo(kh.getSoDT())){
            MessBox.alert(null, "Số điện thoại không đúng!");
            return false;
        } else if(!Check.KTIDNumber(kh.getCMND())){
            MessBox.alert(null, "Số chứng minh nhân dân không đúng!");
            return false;
        } else if(!Check.KTEmail(kh.getEmail())){
            MessBox.alert(null, "Địa chỉ Email không đúng!");
            return false;
        }
        return khDAL.them(kh);
    }
    public boolean sua(KhachHang kh) {
        if (kh.getTenKH().equals("") || kh.getCMND().equals("") || kh.getSoDT().equals("") || 
                kh.getNgaySinh().equals("") || kh.getEmail().equals("") || kh.getDiaChi().equals("")) {
            MessBox.alert(null, "Bạn chưa nhập đầy đủ dữ liệu!");
            return false;
        } else if(!Check.KTPhoneNo(kh.getSoDT())){
            MessBox.alert(null, "Số điện thoại không đúng!");
            return false;
        } else if(!Check.KTIDNumber(kh.getCMND())){
            MessBox.alert(null, "Số chứng minh nhân dân không đúng!");
            return false;
        } else if(!Check.KTEmail(kh.getEmail())){
            MessBox.alert(null, "Địa chỉ Email không đúng!");
            return false;
        }
        return khDAL.sua(kh);
    }

    public boolean xoa(String MaKH) {
        return khDAL.xoa(MaKH);
    }

    public String autoID() throws SQLException {
        return khDAL.Auto_ID();
    }
}
