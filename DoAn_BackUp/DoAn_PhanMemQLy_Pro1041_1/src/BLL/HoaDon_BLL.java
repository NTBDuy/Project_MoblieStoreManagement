/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.HoaDon_DAL;
import DTO.HoaDon;
import Untils.Tool;
import java.awt.Font;
import java.io.FileNotFoundException;
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
public class HoaDon_BLL {
    HoaDon_DAL hdDAL = new HoaDon_DAL();
    
    public void LoadTable(JTable tbl, ArrayList<HoaDon> lstHD){
        tbl.removeAll();
        Object[] obj = new Object[]{"STT","Mã hoá đơn", "Mã khách hàng", "Mã Nhân viên", "Ngày lập", "Tổng tiền", "Ghi chú"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(obj);
        Vector tbRowData;
        int i = 1;
        for (HoaDon hd : lstHD) {
            tbRowData = new Vector();
            tbRowData.add(i);
            tbRowData.add(hd.getMaHD());
            tbRowData.add(hd.getMaKH());
            tbRowData.add(hd.getMaNV());
            tbRowData.add(hd.getNgayLap());
            tbRowData.add(new DecimalFormat("###,###.###").format(hd.getTongTien()));
            tbRowData.add(hd.getGhiChu());
            model.addRow(tbRowData);
            i++;
        }
        tbl.setModel(model);
        tbl.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        Tool.setJTableColumnsWidth(tbl, 1240, 3, 9, 9, 9, 8, 10, 10);
    }
    public ArrayList<HoaDon> getAll(){
        return hdDAL.getAll();
    }
    public String autoID() throws SQLException {
        return hdDAL.Auto_ID();
    }
    public boolean them(HoaDon hd){
        if (hd.getMaHD().isEmpty() || hd.getMaNV().isEmpty() || hd.getMaKH().isEmpty()){
            return false;
        } else {
            return hdDAL.them(hd);
        }
    }
    public boolean sua(HoaDon hd){
        return hdDAL.sua(hd);
    }
    public boolean xoa(String MaHD){
        return hdDAL.xoa(MaHD);
    }
    public void XuatHD(String soHD) throws FileNotFoundException{
        hdDAL.InHoaDon(soHD);
    }
}
