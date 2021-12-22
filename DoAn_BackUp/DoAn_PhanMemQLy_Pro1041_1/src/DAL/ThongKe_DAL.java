/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author Duy
 */
public class ThongKe_DAL {
    DBConnect connDB = new DBConnect();
    Connection conn = connDB.getConnection();
    String SQL_DS = "";
    public ArrayList<Vector> ThongKeDoanhThu(String ngayBD, String ngayKT, String maNV) throws SQLException {
        ArrayList<Vector> list = new ArrayList<Vector>();
        if(maNV.equalsIgnoreCase("Tất cả")){
           SQL_DS = "DoanhSoBanHangg '" + ngayBD + "','" + ngayKT + "'";
        } else {
           SQL_DS = "DoanhSoBanHangg '" + ngayBD + "','" + ngayKT + "','" + maNV + "'";
        }
        CallableStatement cstm = conn.prepareCall(SQL_DS);
        try (ResultSet rs = cstm.executeQuery();) {
            while (rs.next()) {
                Vector dataRow = new Vector();
                dataRow.add(rs.getString(1));
                dataRow.add(rs.getInt(2));
                dataRow.add(rs.getDouble(3));
                dataRow.add(rs.getDouble(4));
                list.add(dataRow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    String SQL_TongTienBanTrongNgay = "select sum(TongTien) from HoaDon where NgayLap = ?";
    String SQL_TongTiendaytoday = "";
    String SQL_TongSLSP = "SELECT SUM(SoLuong) FROM dbo.HoaDon JOIN dbo.CTHoaDon ON CTHoaDon.MaHD = HoaDon.MaHD WHERE NgayLap = ?";
    String SQL_TongSLSPdaytoday = "";
    String SQL_TongKH = "SELECT COUNT(MaKH) FROM HoaDon WHERE NgayLap = ?";
    String SQL_TongLNDaytoDay = "";
    String SQL_TongHD = "SELECT COUNT(MaHD) FROM HoaDon WHERE NgayLap = ?";
    String SQL_TongHDdaytoday = "";
    
    public Double LoiNhuanBanTrgNgay(String date) throws SQLException {
        Double tong = 0.0;
        PreparedStatement stmt = conn.prepareStatement(SQL_TongTienBanTrongNgay);
        stmt.setString(1, date);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            tong = rs.getDouble(1);
        }
        return tong;
    }
    
    public Double TongTienBanTrgNgay(String date) throws SQLException {
        Double tong = 0.0;
        PreparedStatement stmt = conn.prepareStatement(SQL_TongTienBanTrongNgay);
        stmt.setString(1, date);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            tong = rs.getDouble(1);
        }
        return tong;
    }
    
    public Double TongTiendaytoday(String dateBD, String dateKT, String maNV) throws SQLException {
        Double tong = 0.0;
        if(maNV.equalsIgnoreCase("Tất cả")){
           SQL_TongTiendaytoday = "select sum(TongTien) from HoaDon where NgayLap BETWEEN '" + dateBD + "' AND '" + dateKT + "'";
        } else {
           SQL_TongTiendaytoday = "select sum(TongTien) from HoaDon where NgayLap BETWEEN '" + dateBD + "' AND '" + dateKT + "' AND MaNV =  N'" + maNV + "'";
            System.out.println(SQL_TongTiendaytoday);
        }
        PreparedStatement stmt = conn.prepareStatement(SQL_TongTiendaytoday);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            tong = rs.getDouble(1);
        }
        return tong;
    }
    public int TongSLSP(String date) throws SQLException {
        int tong = 0;
        PreparedStatement stmt = conn.prepareStatement(SQL_TongSLSP);
        stmt.setString(1, date);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            tong = rs.getInt(1);
        }
        return tong;
    }
    public int TongSLSPdaytoday(String dateBD, String dateKT, String maNV) throws SQLException {
        int tong = 0;
        if(maNV.equalsIgnoreCase("Tất cả")){
           SQL_TongSLSPdaytoday = "SELECT SUM(SoLuong) FROM dbo.HoaDon JOIN dbo.CTHoaDon ON CTHoaDon.MaHD = HoaDon.MaHD WHERE NgayLap BETWEEN '" + dateBD + "' AND '" + dateKT + "'";
        } else {
           SQL_TongSLSPdaytoday = "SELECT SUM(SoLuong) FROM dbo.HoaDon JOIN dbo.CTHoaDon ON CTHoaDon.MaHD = HoaDon.MaHD WHERE NgayLap BETWEEN '" + dateBD + "' AND '" + dateKT + "' AND MaNV =  N'" + maNV + "'";
            System.out.println(SQL_TongSLSPdaytoday);
        }
        PreparedStatement stmt = conn.prepareStatement(SQL_TongSLSPdaytoday);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            tong = rs.getInt(1);
        }
        return tong;
    }
    public int TongKH(String date) throws SQLException {
        int tong = 0;
        PreparedStatement stmt = conn.prepareStatement(SQL_TongKH);
        stmt.setString(1, date);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            tong = rs.getInt(1);
        }
        return tong;
    }
    
    public double TongLNDaytoDay(String dateBD, String dateKT, String maNV) throws SQLException {
        double tong = 0.0;
        if(maNV.equalsIgnoreCase("Tất cả")){
           SQL_TongLNDaytoDay = "SELECT dbo.TinhLoiNhuanDaytoday('" + dateBD + "','" + dateKT + "')";
        } else {
           SQL_TongLNDaytoDay = "SELECT dbo.TinhLoiNhuanDaytodayANDMaNV('" + dateBD + "','" + dateKT + "',N'" + maNV + "')";
            System.out.println(SQL_TongLNDaytoDay);
        }
        PreparedStatement stmt = conn.prepareStatement(SQL_TongLNDaytoDay);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            tong = rs.getDouble(1);
        }
        return tong;
    }
    
    public int TongHD(String date) throws SQLException {
        int tong = 0;
        PreparedStatement stmt = conn.prepareStatement(SQL_TongHD);
        stmt.setString(1, date);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            tong = rs.getInt(1);
        }
        return tong;
    }
    
    public int TongHDdaytoday(String dateBD, String dateKT, String maNV) throws SQLException {
        int tong = 0;
        if(maNV.equalsIgnoreCase("Tất cả")){
           SQL_TongHDdaytoday = "SELECT COUNT(MaHD) FROM HoaDon WHERE NgayLap BETWEEN '" + dateBD + "' AND '" + dateKT + "'";
        } else {
           SQL_TongHDdaytoday = "SELECT COUNT(MaHD) FROM HoaDon WHERE NgayLap BETWEEN '" + dateBD + "' AND '" + dateKT + "' AND MaNV =  N'" + maNV + "'";
            System.out.println(SQL_TongHDdaytoday);
        }
        PreparedStatement stmt = conn.prepareStatement(SQL_TongHDdaytoday);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            tong = rs.getInt(1);
        }
        return tong;
    }
    
    public Double LoiNhuanNgay(String Ngay) throws SQLException {
        Double LN = 0.0;
        Statement stm = conn.createStatement();
        String SQL_LoiNhuanBanTrongNgay = "SELECT dbo.TinhLoiNhuanNgay('"+Ngay+"',null)";
        ResultSet rs = stm.executeQuery(SQL_LoiNhuanBanTrongNgay);
        while (rs.next()){
            LN = rs.getDouble(1);
        }
        return LN;
    }
}
