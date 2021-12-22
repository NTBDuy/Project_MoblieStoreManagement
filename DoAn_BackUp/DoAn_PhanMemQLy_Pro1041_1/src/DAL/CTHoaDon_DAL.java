/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.CTHoaDon;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Duy
 */
public class CTHoaDon_DAL {
    Connection conn = new DBConnect().getConnection();
    String SQL_getALL = "SELECT * FROM CTHoaDon";
    String SQL_SelectByID = "SELECT * FROM CTHoaDon WHERE MaHD=?";
    String SQL_Insert = "INSERT INTO CTHoaDon(MaHD, MaSP, TenSP, SoLuong, DonGia, GiamGia, ThanhTien) VALUES(?, ?, ?, ?, ?, ?, ?)";
    String SQL_Update = "UPDATE CTHoaDon SET TenSP = ?, SoLuong = ?, DonGia = ?, GiamGia = ?, ThanhTien = ? WHERE MaHD = ? AND MaSP = ?";
    String SQL_Delete_SP = "DELETE FROM CTHoaDon WHERE MaSP = ? AND MaHD = ?";
    String SQL_Delete_ALL = "DELETE FROM CTHoaDon WHERE MaHD = ?";
    
    public ArrayList<CTHoaDon> getALL(){
        ArrayList<CTHoaDon> lstCTHD = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(SQL_getALL);
            while (rs.next()){
                CTHoaDon cthd = new CTHoaDon();
                cthd.setMaHD(rs.getString(1));
                cthd.setMaSP(rs.getString(2));
                cthd.setTenSP(rs.getString(3));
                cthd.setSoLuong(rs.getInt(4));
                cthd.setDonGia(rs.getDouble(5));
                cthd.setGiamGia(rs.getInt(6));
                cthd.setThanhTien(rs.getDouble(7));
                lstCTHD.add(cthd);
            }
        } catch (Exception e) {
            Logger.getLogger(CTHoaDon_DAL.class.getName()).log(Level.SEVERE, null, e);
        }
        return lstCTHD;
    }
    
    public ArrayList<CTHoaDon> getByID(String id){
        ArrayList<CTHoaDon> lstCTHD = new ArrayList<>();
        try {
            PreparedStatement pre = conn.prepareStatement(SQL_SelectByID);
            pre.setString(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()){
                CTHoaDon cthd = new CTHoaDon();
                cthd.setMaHD(rs.getString(1));
                cthd.setMaSP(rs.getString(2));
                cthd.setTenSP(rs.getString(3));
                cthd.setSoLuong(rs.getInt(4));
                cthd.setDonGia(rs.getDouble(5));
                cthd.setGiamGia(rs.getInt(6));
                cthd.setThanhTien(rs.getDouble(7));
                lstCTHD.add(cthd);
            }
        } catch (Exception e) {
            Logger.getLogger(CTHoaDon_DAL.class.getName()).log(Level.SEVERE, null, e);
        }
        return lstCTHD;
    }
    
    public boolean them(CTHoaDon ct){
        try{
            CallableStatement cs = conn.prepareCall(SQL_Insert);
            cs.setString(1, ct.getMaHD());
            cs.setString(2, ct.getMaSP());
            cs.setString(3, ct.getTenSP());
            cs.setInt(4, ct.getSoLuong());
            cs.setDouble(5, ct.getDonGia());
            cs.setDouble(6, ct.getGiamGia());
            cs.setDouble(7, ct.getThanhTien());
            cs.execute();
            return true; 
        } catch (SQLException e) {
            Logger.getLogger(CTHoaDon_DAL.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
    public boolean sua(CTHoaDon ct) {
        try {
            CallableStatement cs = conn.prepareCall(SQL_Update);
            cs.setString(1, ct.getTenSP());
            cs.setInt(2, ct.getSoLuong());
            cs.setDouble(3, ct.getDonGia());
            cs.setDouble(4, ct.getGiamGia());
            cs.setDouble(5, ct.getThanhTien());
            cs.setString(6, ct.getMaHD());
            cs.setString(7, ct.getMaSP());
            cs.execute();
            return true; 
        } catch (SQLException e) {
            return false;
        }
    }
    public boolean xoaALL(String MaHD) {
        try {
            CallableStatement cs = conn.prepareCall(SQL_Delete_ALL);
            cs.setString(1, MaHD);
            cs.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public boolean xoaSP(String MaSP, String MaHD) {
        try {
            CallableStatement cs = conn.prepareCall(SQL_Delete_SP);
            cs.setString(1, MaSP);
            cs.setString(2, MaHD);
            cs.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
