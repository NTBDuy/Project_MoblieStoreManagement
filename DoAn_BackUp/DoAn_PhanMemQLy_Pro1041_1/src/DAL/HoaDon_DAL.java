/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.HoaDon;
import Untils.Tool;
import java.awt.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Duy
 */
public class HoaDon_DAL {
    Connection conn = new DBConnect().getConnection();
    String SQL_getALL = "SELECT * FROM HOADON";
    String SQL_Auto_ID = "SELECT dbo.AutoID_HD()";
    String SQL_Insert = "INSERT INTO HOADON(MaHD, MaKH, MaNV, NgayLap, TongTien, GhiChu) VALUES(?, ?, ?, ?, ?, ?)";
    String SQL_Update = "UPDATE HOADON SET MaKH = ?, MaNV = ?, NgayLap = ?, TongTien = ?, GhiChu = ? WHERE MaHD = ?";
    String SQL_Delete = "DELETE FROM HOADON WHERE MaHD = ?";
    
    public ArrayList<HoaDon> getAll(){
        ArrayList<HoaDon> lstHD = new ArrayList<>();
        try{
            Statement ss = conn.createStatement();
            ResultSet rs = ss.executeQuery(SQL_getALL);
            while (rs.next()){
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getString(1));
                hd.setMaKH(rs.getString(2));
                hd.setMaNV(rs.getString(3));
                hd.setNgayLap(rs.getDate(4));
                hd.setTongTien(rs.getDouble(5));
                hd.setGhiChu(rs.getString(6));
                lstHD.add(hd);
            }
        }
        catch(SQLException e){
        }
        return lstHD;
    }
    
    public String Auto_ID() throws SQLException {
        String MaHD = null;
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(SQL_Auto_ID);
        while (rs.next()){
            MaHD = rs.getString(1);
        }
        return MaHD;
    }
    
    public boolean them(HoaDon hd){
        try {
            CallableStatement cs = conn.prepareCall(SQL_Insert);
            cs.setString(1, hd.getMaHD());
            cs.setString(2, hd.getMaKH());
            cs.setString(3, hd.getMaNV());
            cs.setString(4, Untils.Date.toString(hd.getNgayLap(), ("yyyy/MM/dd")));
            cs.setDouble(5, hd.getTongTien());
            cs.setString(6, hd.getGhiChu());
            cs.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public boolean sua(HoaDon hd) {
        try {
            CallableStatement cs = conn.prepareCall(SQL_Update);
            cs.setString(1, hd.getMaKH());
            cs.setString(2, hd.getMaNV());
            cs.setString(3, Untils.Date.toString(hd.getNgayLap(), ("yyyy/MM/dd")));
            cs.setDouble(4, hd.getTongTien());
            cs.setString(5, hd.getGhiChu());
            cs.setString(6, hd.getMaHD());
            cs.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    public boolean xoa(String MaHD) {
        try {
            CallableStatement cs = conn.prepareCall(SQL_Delete);
            cs.setString(1, MaHD);
            cs.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public void InHoaDon(String soHD) throws FileNotFoundException{
        InputStream is = new FileInputStream("./src/Report/rptHoaDon.jrxml");
        try {
            // Tạo JasperReport
            JasperReport jr = JasperCompileManager.compileReport(is);
            // Tạo tham số cho report
            Map<String, Object> params = new HashMap<String, Object>(); 
            params.put("MaHD", soHD);  
            // Tạo JasperPrint
            JasperPrint jp = JasperFillManager.fillReport(jr,params, conn);
            // Tạo JasperViewer
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setTitle("Hóa đơn");
            jv.setZoomRatio(new Float(0.75));
            jv.setVisible(true);
            // Xuất ra pdf
            OutputStream os = new FileOutputStream("PX"+soHD+".pdf");  
            JasperExportManager.exportReportToPdfStream(jp, os);  
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}
