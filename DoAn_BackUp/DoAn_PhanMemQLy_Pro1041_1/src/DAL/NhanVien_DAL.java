/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.NhanVien;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Duy
 */
public class NhanVien_DAL {
    Connection conn = new DBConnect().getConnection();
    String SQL_Select_ALL = "SELECT * FROM NHANVIEN";
    String SQL_Insert = "INSERT INTO NHANVIEN(MaNV, TenNV, GioiTinh, NgaySinh, DiaChi, Email, SoDT, CMND, ChucVu, TrangThai, Hinh) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String SQL_Update = "UPDATE NHANVIEN SET TenNV = ?, GioiTinh = ?, NgaySinh = ?, DiaChi = ?, Email = ?, SoDT = ?, CMND = ?, ChucVu = ?, TrangThai = ?, Hinh = ? WHERE MaNV = ?";
    String SQL_Delete = "DELETE FROM NHANVIEN WHERE MaNV = ?";
    String SQL_Auto_ID = "select dbo.AutoID_NV()";
    
    public ArrayList<NhanVien> getALL(){
        ArrayList<NhanVien> lstNV = new ArrayList<>();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(SQL_Select_ALL);
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString(1));
                nv.setTenNV(rs.getString(2));
                nv.setGioiTinh(rs.getBoolean(3));
                nv.setNgaySinh(rs.getDate(4));
                nv.setDiaChi(rs.getString(5));
                nv.setEmail(rs.getString(6));
                nv.setSDT(rs.getString(7));
                nv.setCMND(rs.getString(8));
                nv.setChucVu(rs.getString(9));
                nv.setTrangThai(rs.getString(10));
                nv.setHinh(rs.getString(11));
                lstNV.add(nv);
            }
        } catch (Exception e) {
        }
        return lstNV;
    }
    
    public boolean them(NhanVien sp){
        try {
            CallableStatement cs = conn.prepareCall(SQL_Insert);
            cs.setString(1, sp.getMaNV());
            cs.setString(2, sp.getTenNV());
            cs.setBoolean(3, sp.isGioiTinh());
            cs.setString(4, Untils.Date.toString(sp.getNgaySinh(), ("yyyy/MM/dd")));
            cs.setString(5, sp.getDiaChi());
            cs.setString(6, sp.getEmail());
            cs.setString(7, sp.getSDT());
            cs.setString(8, sp.getCMND());
            cs.setString(9, sp.getChucVu());
            cs.setString(10, sp.getTrangThai());
            cs.setString(11, sp.getHinh());
            cs.execute();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean sua(NhanVien sp) {
        try {
            CallableStatement cs = conn.prepareCall(SQL_Update);
            cs.setString(11, sp.getMaNV());
            cs.setString(1, sp.getTenNV());
            cs.setBoolean(2, sp.isGioiTinh());
            cs.setString(3, Untils.Date.toString(sp.getNgaySinh(), ("yyyy/MM/dd")));
            cs.setString(4, sp.getDiaChi());
            cs.setString(5, sp.getEmail());
            cs.setString(6, sp.getSDT());
            cs.setString(7, sp.getCMND());
            cs.setString(8, sp.getChucVu());
            cs.setString(9, sp.getTrangThai());
            cs.setString(10, sp.getHinh());
            cs.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean xoa(String MaNV) {
        try {
            CallableStatement cs = conn.prepareCall(SQL_Delete);
            cs.setString(1, MaNV);
            cs.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public String Auto_ID() throws SQLException {
        String MaNV = null;
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(SQL_Auto_ID);
        while (rs.next()){
            MaNV = rs.getString(1);
        }
        return MaNV;
    }
    
}
