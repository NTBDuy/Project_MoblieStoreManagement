/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.SanPham;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Duy
 */
public class SanPham_DAL {
    Connection conn = new DBConnect().getConnection();
    String SQL_Select_ALL = "SELECT * FROM SanPham";
    String SQL_Insert = "INSERT INTO SANPHAM(MaSP, TenSP, NhaSX, DacDiem, SoLuong, GiaNhap, GiaBan, NgayNhap, HinhAnh) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String SQL_Update = "UPDATE SanPham SET TenSP = ?, NhaSX = ?, DacDiem = ?, SoLuong = ?, GiaNhap = ?, GiaBan = ?, NgayNhap = ?, HinhAnh = ? WHERE MaSP = ?";
    String SQL_Delete = "DELETE FROM SanPham WHERE MaSP = ?";
    String SQL_Auto_ID = "select dbo.AutoID_SP()";
    String SQL_Select_ByName = "SELECT * FROM SanPham WHERE TenSP like ?";
    String SQL_Select_ByIDBH = "SELECT * FROM SanPham WHERE MaSP like ?";
    String SQL_Select_ByID = "SELECT * FROM SanPham WHERE MaSP = ?";
    String SQL_Check = "SELECT COUNT(*) FROM SANPHAM where MaSP = ?";
    String SQL_TonKho = "select sum(SoLuong) from SANPHAM";
    String SQL_SPbanngay = "select  count(MAKH) from HoaDon where NgayLap = ?";
    
    public List<SanPham> getByID(String name){
        List<SanPham> list = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(SQL_Select_ByName);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getString(1));
                sp.setTenSP(rs.getString(2));
                sp.setNhaSX(rs.getString(3));
                sp.setDacDiem(rs.getString(4));
                sp.setSoLuong(rs.getInt(5));
                sp.setGiaNhap(rs.getDouble(6));
                sp.setGiaBan(rs.getDouble(7));
                sp.setNgayNhap(rs.getDate(8));
                sp.setHinhAnh(rs.getString(9));
                list.add(sp);
            }
        } catch (Exception e) {
        }
        return list;
    }
    public List<SanPham> getByidBh(String id){
        List<SanPham> list = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(SQL_Select_ByIDBH);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getString(1));
                sp.setTenSP(rs.getString(2));
                sp.setNhaSX(rs.getString(3));
                sp.setDacDiem(rs.getString(4));
                sp.setSoLuong(rs.getInt(5));
                sp.setGiaNhap(rs.getDouble(6));
                sp.setGiaBan(rs.getDouble(7));
                sp.setNgayNhap(rs.getDate(8));
                sp.setHinhAnh(rs.getString(9));
                list.add(sp);
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public SanPham getByMa(String name){
        SanPham sp = null;
        try {
            PreparedStatement stmt = conn.prepareStatement(SQL_Select_ByID);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                sp = new SanPham();
                sp.setMaSP(rs.getString(1));
                sp.setTenSP(rs.getString(2));
                sp.setNhaSX(rs.getString(3));
                sp.setDacDiem(rs.getString(4));
                sp.setSoLuong(rs.getInt(5));
                sp.setGiaNhap(rs.getDouble(6));
                sp.setGiaBan(rs.getDouble(7));
                sp.setNgayNhap(rs.getDate(8));
                sp.setHinhAnh(rs.getString(9));
            }
        } catch (Exception e) {
        }
        return sp;
    }
    public boolean check(String id){
        try {
            PreparedStatement pr = conn.prepareStatement(SQL_Check);
            pr.setString(1, id);
            ResultSet rs = pr.executeQuery();
            rs.next();
            if(rs.getBoolean(1)==false){
                return false;
            }
            
        } catch (Exception e) {
        }
        return true;
    }
    public ArrayList<SanPham> getALL(){
        ArrayList<SanPham> lstSP = new ArrayList<>();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(SQL_Select_ALL);
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getString(1));
                sp.setTenSP(rs.getString(2));
                sp.setNhaSX(rs.getString(3));
                sp.setDacDiem(rs.getString(4));
                sp.setSoLuong(rs.getInt(5));
                sp.setGiaNhap(rs.getDouble(6));
                sp.setGiaBan(rs.getDouble(7));
                sp.setNgayNhap(rs.getDate(8));
                sp.setHinhAnh(rs.getString(9));
                lstSP.add(sp);
            }
        } catch (Exception e) {
        }
        return lstSP;
    }
    
    public boolean themSP(SanPham sp){
        try {
            CallableStatement cs = conn.prepareCall(SQL_Insert);
            cs.setString(1, sp.getMaSP());
            cs.setString(2, sp.getTenSP());
            cs.setString(3, sp.getNhaSX());
            cs.setString(4, sp.getDacDiem());
            cs.setInt(5, sp.getSoLuong());
            cs.setDouble(6, sp.getGiaNhap());
            cs.setDouble(7, sp.getGiaBan());
            cs.setString(8, Untils.Date.toString(sp.getNgayNhap(), ("yyyy/MM/dd")));
            cs.setString(9, sp.getHinhAnh());
            cs.execute();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
      public boolean suaSP(SanPham sp) {
        try {
            CallableStatement cs = conn.prepareCall(SQL_Update);
            cs.setString(9, sp.getMaSP());
            cs.setString(1, sp.getTenSP());
            cs.setString(2, sp.getNhaSX());
            cs.setString(3, sp.getDacDiem());
            cs.setInt(4, sp.getSoLuong());
            cs.setDouble(5, sp.getGiaNhap());
            cs.setDouble(6, sp.getGiaBan());
            cs.setString(7, Untils.Date.toString(sp.getNgayNhap(), ("yyyy/MM/dd")));
            cs.setString(8, sp.getHinhAnh());
            cs.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean xoaSP(String MaSP) {
        try {
            CallableStatement cs = conn.prepareCall(SQL_Delete);
            cs.setString(1, MaSP);
            cs.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public String Auto_ID() throws SQLException {
        String MaSP = null;
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(SQL_Auto_ID);
        while (rs.next()){
            MaSP = rs.getString(1);
        }
        return MaSP;
    }
    
    public int SLTon() throws SQLException {
        int tong = 0;
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(SQL_TonKho);
        while (rs.next()){
            tong = rs.getInt(1);
        }
        return tong;
    }
    
    public int SLspBan() throws SQLException {
        int sl = 0;
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(SQL_SPbanngay);
        while (rs.next()){
            sl = rs.getInt(1);
        }
        return sl;
    }
}
