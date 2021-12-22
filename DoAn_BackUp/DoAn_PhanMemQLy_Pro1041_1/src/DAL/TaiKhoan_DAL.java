/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.TaiKhoan;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Duy
 */
public class TaiKhoan_DAL {
    DBConnect connDB = new DBConnect();
    Connection conn = connDB.getConnection();
    String SQL_getALL = "SELECT * FROM TAIKHOAN";
    String SQL_getAC = "SELECT * FROM TAIKHOAN WHERE TenTK=? and MatKhau=?";
    String SQL_Insert = "INSERT INTO TaiKhoan(TenTK, MatKhau, Quyen, MaNV) VALUES(?, ?, ?, ?)";
    String SQL_Update = "UPDATE TaiKhoan SET MatKhau = ?, Quyen = ?, MaNV = ? WHERE TenTK = ?";
    String SQL_DoiMK = "UPDATE TaiKhoan SET MatKhau = ? WHERE TenTK = ?";
    String SQL_Delete = "DELETE FROM TaiKhoan WHERE TenTK = ?";
    
    public TaiKhoan dangnhap(String username, String password){
        TaiKhoan tk = null;
        try{
            PreparedStatement pre = conn.prepareStatement(SQL_getAC);
            pre.setString(1, username);
            pre.setString(2, password);
            ResultSet rs= pre.executeQuery();
            if(rs.next()){
                tk = new TaiKhoan();
                tk.setTenTK(rs.getString(1));
                tk.setMatKhau(rs.getString(2));
                tk.setQuyen(rs.getString(3));
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }            
        return tk;
    }
    public ArrayList<TaiKhoan> getAll(){
        ArrayList<TaiKhoan> lstTK = new ArrayList<>();
        try{
            Statement ss = conn.createStatement();
            ResultSet rs = ss.executeQuery(SQL_getALL);
            while (rs.next()){
                TaiKhoan tk = new TaiKhoan();
                tk.setTenTK(rs.getString(1).trim());
                tk.setMatKhau(rs.getString(2));
                tk.setQuyen(rs.getString(3));
                tk.setMaNV(rs.getString(4));
                lstTK.add(tk);
            }
        }
        catch(SQLException e){
        }
        return lstTK;     
    }
    public boolean them(TaiKhoan tk){
        try {
            CallableStatement cs = conn.prepareCall(SQL_Insert);
            cs.setString(1, tk.getTenTK());
            cs.setString(2, tk.getMatKhau());
            cs.setString(3, tk.getQuyen());
            cs.setString(4, tk.getMaNV());
            cs.execute();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean sua(TaiKhoan tk) {
        try {
            CallableStatement cs = conn.prepareCall(SQL_Update);
            cs.setString(4, tk.getTenTK());
            cs.setString(1, tk.getMatKhau());
            cs.setString(2, tk.getQuyen());
            cs.setString(3, tk.getMaNV());
            cs.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    public boolean doiMK(String tk, String mk) {
        try {
            CallableStatement cs = conn.prepareCall(SQL_DoiMK);
            cs.setString(2,tk);
            cs.setString(1,mk);
            cs.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    public boolean xoa(String TenTK) {
        try {
            CallableStatement cs = conn.prepareCall(SQL_Delete);
            cs.setString(1, TenTK);
            cs.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
