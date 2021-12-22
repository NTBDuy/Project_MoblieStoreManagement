/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.TaiKhoan_DAL;
import DTO.TaiKhoan;
import Untils.MessBox;
import Untils.Tool;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Duy
 */
public class TaiKhoan_BLL {
    TaiKhoan_DAL tkDAL = new TaiKhoan_DAL();
    
    public ArrayList<TaiKhoan> getList(){
        return tkDAL.getAll();
    }
    
    public TaiKhoan dangnhap(String username,String password){
        return tkDAL.dangnhap(username, password);
    }
    
    public void LoadTable(JTable tbl, ArrayList<TaiKhoan> lstTK){
        tbl.removeAll();
        Object[] obj = new Object[]{"STT","Tên đăng nhập", "Mật khẩu", "Quyền", "Mã NV"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(obj);
        Vector tbRowData;
        int i = 1;
        for (TaiKhoan tk : lstTK) {
            tbRowData = new Vector();
            tbRowData.add(i);
            tbRowData.add(tk.getTenTK());
            tbRowData.add(tk.getMatKhau());
            tbRowData.add(tk.getQuyen());
            tbRowData.add(tk.getMaNV());
            model.addRow(tbRowData);
            i++;
        }
        tbl.setModel(model);
        tbl.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        Tool.setJTableColumnsWidth(tbl, 1240, 3, 9, 9, 9, 9);
    }
    public boolean them(TaiKhoan tk) {
        if (tk.getTenTK().equals("")) {
            MessBox.alert(null, "Tên đăng nhập trống!");
            return false;
        }
        return tkDAL.them(tk);
    }
    public boolean sua(TaiKhoan tk) {
        return tkDAL.sua(tk);
    }
    public boolean doiMK(String tk, String mk) {
        return tkDAL.doiMK(tk, mk);
    }
    public boolean xoa(String TenTK) {
        return tkDAL.xoa(TenTK);
    }
}
