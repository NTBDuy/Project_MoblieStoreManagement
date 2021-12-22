/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.NhanVien_DAL;
import DTO.NhanVien;
import DTO.SanPham;
import Untils.Check;
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
public class NhanVien_BLL {
    NhanVien_DAL nvDAL = new NhanVien_DAL();
    
    public void LoadTable(JTable tbl, ArrayList<NhanVien> lstNV){
        tbl.removeAll();
        Object[] obj = new Object[]{"STT","Mã nhân viên", "Tên nhân viên", "Giới tính", "Ngày Sinh", "Địa Chỉ", "Email", "SDT", "CMND", "Chức vụ", "Trạng thái", "Hình"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(obj);
        Vector tbRowData;
        int i = 1;
        for (NhanVien nv : lstNV) {
            tbRowData = new Vector();
            tbRowData.add(i);
            tbRowData.add(nv.getMaNV());
            tbRowData.add(nv.getTenNV());
            tbRowData.add(nv.isGioiTinh()? "Nam" : "Nữ");
            tbRowData.add(nv.getNgaySinh());
            tbRowData.add(nv.getDiaChi());
            tbRowData.add(nv.getEmail());
            tbRowData.add(nv.getSDT());
            tbRowData.add(nv.getCMND());
            tbRowData.add(nv.getChucVu());
            tbRowData.add(nv.getTrangThai());
            tbRowData.add(nv.getHinh());
            model.addRow(tbRowData);
            i++;
        }
        tbl.setModel(model);
        tbl.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        Tool.setJTableColumnsWidth(tbl, 1240, 3, 9, 15, 5, 8, 10, 10, 10, 10, 8, 8, 8);
    }
    
    public ArrayList<NhanVien> getALL(){
        return nvDAL.getALL();
    }
    
    public boolean them(NhanVien nv) {
        if (nv.getTenNV().equals("") || nv.getSDT().equals("") || nv.getCMND().equals("") || nv.getEmail().equals("") || nv.getDiaChi().equals("") || nv.getHinh().equals("") || nv.getNgaySinh().equals("")) {
            MessBox.alert(null, "Bạn chưa nhập đầy đủ dữ liệu!");
            return false;
        } else if(!Check.KTPhoneNo(nv.getSDT())){
            MessBox.alert(null, "Số điện thoại không đúng!");
            return false;
        } else if(!Check.KTIDNumber(nv.getCMND())){
            MessBox.alert(null, "Số chứng minh nhân dân không đúng!");
            return false;
        } else if(!Check.KTEmail(nv.getEmail())){
            MessBox.alert(null, "Địa chỉ Email không đúng!");
            return false;
        }
        return nvDAL.them(nv);
    }
    public boolean sua(NhanVien nv) {
        if (nv.getTenNV().equals("") || nv.getSDT().equals("") || nv.getCMND().equals("") || nv.getEmail().equals("") || nv.getDiaChi().equals("") || nv.getHinh().equals("") || nv.getNgaySinh().equals("")) {
            MessBox.alert(null, "Bạn chưa nhập đầy đủ dữ liệu!");
            return false;
        } else if(!Check.KTPhoneNo(nv.getSDT())){
            MessBox.alert(null, "Số điện thoại không đúng!");
            return false;
        } else if(!Check.KTIDNumber(nv.getCMND())){
            MessBox.alert(null, "Số chứng minh nhân dân không đúng!");
            return false;
        } else if(!Check.KTEmail(nv.getEmail())){
            MessBox.alert(null, "Địa chỉ Email không đúng!");
            return false;
        }
        return nvDAL.sua(nv);
    }

    public boolean xoa(String MaNV) {
        return nvDAL.xoa(MaNV);
    }

    public String autoID() throws SQLException {
        return nvDAL.Auto_ID();
    }
}
