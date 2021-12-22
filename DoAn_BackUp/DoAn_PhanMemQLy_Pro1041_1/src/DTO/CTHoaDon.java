/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Duy
 */
public class CTHoaDon {

    /**
     * @return the ThanhTien
     */
    public Double getThanhTien() {
        return ThanhTien;
    }

    /**
     * @param ThanhTien the ThanhTien to set
     */
    public void setThanhTien(Double ThanhTien) {
        this.ThanhTien = ThanhTien;
    }

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
     * @return the MaSP
     */
    public String getMaSP() {
        return MaSP;
    }

    /**
     * @param MaSP the MaSP to set
     */
    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    /**
     * @return the TenSP
     */
    public String getTenSP() {
        return TenSP;
    }

    /**
     * @param TenSP the TenSP to set
     */
    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    /**
     * @return the SoLuong
     */
    public int getSoLuong() {
        return SoLuong;
    }

    /**
     * @param SoLuong the SoLuong to set
     */
    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    /**
     * @return the GiamGia
     */
    public int getGiamGia() {
        return GiamGia;
    }

    /**
     * @param GiamGia the GiamGia to set
     */
    public void setGiamGia(int GiamGia) {
        this.GiamGia = GiamGia;
    }

    /**
     * @return the DonGia
     */
    public Double getDonGia() {
        return DonGia;
    }

    /**
     * @param DonGia the DonGia to set
     */
    public void setDonGia(Double DonGia) {
        this.DonGia = DonGia;
    }
    private String MaHD;
    private String MaSP;
    private String TenSP;
    private int SoLuong;
    private int GiamGia;
    private Double DonGia;
    private Double ThanhTien;
}
