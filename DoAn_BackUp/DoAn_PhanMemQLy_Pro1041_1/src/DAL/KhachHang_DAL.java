/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.KhachHang;
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
public class KhachHang_DAL {
    Connection conn = new DBConnect().getConnection();
    String SQL_Select_ALL = "SELECT * FROM KhachHang";
    String SQL_Insert = "INSERT INTO KhachHang(MaKH, TenKH, GioiTinh, NgaySinh, CMND, SoDT, DiaChi, Email, GhiChu) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String SQL_Update = "UPDATE KhachHang SET TenKH = ?, GioiTinh = ?, NgaySinh = ?, CMND = ?, SoDT = ?, DiaChi = ?, Email = ?, GhiChu = ? WHERE MaKH = ?";
    String SQL_Delete = "DELETE FROM KhachHang WHERE MaKH = ?";
    String SQL_Auto_ID = "select dbo.AutoID_KH()";
    String SQL_Select_ByID = "SELECT * FROM KhachHang WHERE MaKH = ?";
    String SQL_Select_ByName = "SELECT * FROM KhachHang WHERE TenKH like ?";
    public List<KhachHang> getByName(String name){
        List<KhachHang> list = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(SQL_Select_ByName);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKH(rs.getString(1));
                kh.setTenKH(rs.getString(2));
                kh.setGioiTinh(rs.getBoolean(3));
                kh.setNgaySinh(rs.getDate(4));
                kh.setCMND(rs.getString(5));
                kh.setSoDT(rs.getString(6));
                kh.setDiaChi(rs.getString(7));
                kh.setEmail(rs.getString(8));
                kh.setGhiChu(rs.getString(9));
                list.add(kh);
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public List<KhachHang> getByID(String id){
        List<KhachHang> list = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(SQL_Select_ByID);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKH(rs.getString(1));
                kh.setTenKH(rs.getString(2));
                kh.setGioiTinh(rs.getBoolean(3));
                kh.setNgaySinh(rs.getDate(4));
                kh.setCMND(rs.getString(5));
                kh.setSoDT(rs.getString(6));
                kh.setDiaChi(rs.getString(7));
                kh.setEmail(rs.getString(8));
                kh.setGhiChu(rs.getString(9));
                list.add(kh);
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public ArrayList<KhachHang> getALL(){
        ArrayList<KhachHang> lstKH = new ArrayList<>();
        
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(SQL_Select_ALL);
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKH(rs.getString(1));
                kh.setTenKH(rs.getString(2));
                kh.setGioiTinh(rs.getBoolean(3));
                kh.setNgaySinh(rs.getDate(4));
                kh.setCMND(rs.getString(5));
                kh.setSoDT(rs.getString(6));
                kh.setDiaChi(rs.getString(7));
                kh.setEmail(rs.getString(8));
                kh.setGhiChu(rs.getString(9));
                lstKH.add(kh);
            }
        } catch (Exception e) {
        }
        return lstKH;
        
    }
    
    public boolean them(KhachHang kh){
        try {
            CallableStatement cs = conn.prepareCall(SQL_Insert);
            cs.setString(1, kh.getMaKH());
            cs.setString(2, kh.getTenKH());
            cs.setBoolean(3, kh.isGioiTinh());
            cs.setString(4, Untils.Date.toString(kh.getNgaySinh(), ("yyyy/MM/dd")));
            cs.setString(5, kh.getCMND());
            cs.setString(6, kh.getSoDT());
            cs.setString(7, kh.getDiaChi());
            cs.setString(8, kh.getEmail());
            cs.setString(9, kh.getGhiChu());
            cs.execute();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
      public boolean sua(KhachHang kh) {
        try {
            CallableStatement cs = conn.prepareCall(SQL_Update);
            cs.setString(9, kh.getMaKH());
            cs.setString(1, kh.getTenKH());
            cs.setBoolean(2, kh.isGioiTinh());
            cs.setString(3, Untils.Date.toString(kh.getNgaySinh(), ("yyyy/MM/dd")));
            cs.setString(4, kh.getCMND());
            cs.setString(5, kh.getSoDT());
            cs.setString(6, kh.getDiaChi());
            cs.setString(7, kh.getEmail());
            cs.setString(8, kh.getGhiChu());
            cs.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean xoa(String MaKH) {
        try {
            CallableStatement cs = conn.prepareCall(SQL_Delete);
            cs.setString(1, MaKH);
            cs.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public String Auto_ID() throws SQLException {
        String MaKH = null;
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(SQL_Auto_ID);
        while (rs.next()){
            MaKH = rs.getString(1);
        }
        return MaKH;
    }
}
