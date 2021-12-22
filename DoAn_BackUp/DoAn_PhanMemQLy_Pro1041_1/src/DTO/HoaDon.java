/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.Date;

/**
 *
 * @author Duy
 */
public class HoaDon {

    /**
     * @return the MaHD
     */
    public String getMaHD() {
        return MaHD;
    }

    /**
     * @param MaHD the MaHD to set
     */
    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    /**
     * @return the MaKH
     */
    public String getMaKH() {
        return MaKH;
    }

    /**
     * @param MaKH the MaKH to set
     */
    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    /**
     * @return the MaNV
     */
    public String getMaNV() {
        return MaNV;
    }

    /**
     * @param MaNV the MaNV to set
     */
    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    /**
     * @return the NgayLap
     */
    public Date getNgayLap() {
        return NgayLap;
    }

    /**
     * @param NgayLap the NgayLap to set
     */
    public void setNgayLap(Date NgayLap) {
        this.NgayLap = NgayLap;
    }

    /**
     * @return the GhiChu
     */
    public String getGhiChu() {
        return GhiChu;
    }

    /**
     * @param GhiChu the GhiChu to set
     */
    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    /**
     * @return the TongTien
     */
    public double getTongTien() {
        return TongTien;
    }

    /**
     * @param TongTien the TongTien to set
     */
    public void setTongTien(double TongTien) {
        this.TongTien = TongTien;
    }
    private String MaHD;
    private String MaKH;
    private String MaNV;
    private Date NgayLap;
    private String GhiChu;
    private double TongTien;
}
