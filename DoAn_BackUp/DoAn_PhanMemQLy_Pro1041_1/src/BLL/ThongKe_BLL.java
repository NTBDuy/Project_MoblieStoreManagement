/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.ThongKe_DAL;
import java.awt.Font;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Duy
 */
public class ThongKe_BLL {
    ThongKe_DAL dsDAL = new ThongKe_DAL();
    
    public void LoadTableDoanhSo(JTable tbl, String ngayBD, String ngayKT, String maNV) throws SQLException, ParseException {
        tbl.removeAll();
        Object[] tieude = new Object[]{"Ngày", "Số lượng bán hàng", "Lợi nhuận", "Tổng tiền bán hàng"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(tieude);
        int i = 1;
        for (Vector hh : dsDAL.ThongKeDoanhThu(ngayBD, ngayKT, maNV)) {
            Vector tbRowData = new Vector();
            tbRowData.add(hh.get(0));
            tbRowData.add(hh.get(1));
            tbRowData.add(new DecimalFormat("###,###.###").format(hh.get(2)));
            tbRowData.add(new DecimalFormat("###,###.###").format(hh.get(3)));
            model.addRow(tbRowData);
        }
        tbl.setModel(model);
        tbl.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14)); 
    }
    
    public Double TongTienBanTrgNgay(String date) throws SQLException {
        return dsDAL.TongTienBanTrgNgay(date);
    }
    public Double TongTiendaytoday(String dateBD, String dateKT, String MaNV) throws SQLException {
        return dsDAL.TongTiendaytoday(dateBD, dateKT, MaNV);
    }
    public int TongSLSP(String date) throws SQLException {
        return dsDAL.TongSLSP(date);
    }
    public int TongSLSPdaytoday(String dateBD, String dateKT, String maNV) throws SQLException {
        return dsDAL.TongSLSPdaytoday(dateBD, dateKT, maNV);
    }
    public int TongKH(String date) throws SQLException {
        return dsDAL.TongKH(date);
    }
    public double TongLNDaytoDay(String dateBD, String dateKT, String maNV) throws SQLException {
        return dsDAL.TongLNDaytoDay(dateBD, dateKT, maNV);
    }
    public int TongHD(String date) throws SQLException {
        return dsDAL.TongHD(date);
    }
    public int TongHDdaytoday(String dateBD, String dateKT, String maNV) throws SQLException {
        return dsDAL.TongHDdaytoday(dateBD, dateKT, maNV);
    }
    public Double LoiNhuanNgay(String ngay) throws SQLException {
        return dsDAL.LoiNhuanNgay(ngay);
    }
}
