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
public class SanPham {

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
     * @return the NhaSX
     */
    public String getNhaSX() {
        return NhaSX;
    }

    /**
     * @param NhaSX the NhaSX to set
     */
    public void setNhaSX(String NhaSX) {
        this.NhaSX = NhaSX;
    }

    /**
     * @return the DacDiem
     */
    public String getDacDiem() {
        return DacDiem;
    }

    /**
     * @param DacDiem the DacDiem to set
     */
    public void setDacDiem(String DacDiem) {
        this.DacDiem = DacDiem;
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
     * @return the GiaNhap
     */
    public double getGiaNhap() {
        return GiaNhap;
    }

    /**
     * @param GiaNhap the GiaNhap to set
     */
    public void setGiaNhap(double GiaNhap) {
        this.GiaNhap = GiaNhap;
    }

    /**
     * @return the GiaBan
     */
    public double getGiaBan() {
        return GiaBan;
    }

    /**
     * @param GiaBan the GiaBan to set
     */
    public void setGiaBan(double GiaBan) {
        this.GiaBan = GiaBan;
    }

    /**
     * @return the NgayNhap
     */
    public Date getNgayNhap() {
        return NgayNhap;
    }

    /**
     * @param NgayNhap the NgayNhap to set
     */
    public void setNgayNhap(Date NgayNhap) {
        this.NgayNhap = NgayNhap;
    }

    /**
     * @return the HinhAnh
     */
    public String getHinhAnh() {
        return HinhAnh;
    }

    /**
     * @param HinhAnh the HinhAnh to set
     */
    public void setHinhAnh(String HinhAnh) {
        this.HinhAnh = HinhAnh;
    }
    private String MaSP;
    private String TenSP;
    private String NhaSX;
    private String DacDiem;
    private int SoLuong;
    private double GiaNhap;
    private double GiaBan;
    private Date NgayNhap;
    private String HinhAnh;
    
    public SanPham(String MaSP, String TenSP, String NhaSX, String DacDiem, int SoLuong, double GiaNhap, double GiaBan, Date NgayNhap, String HinhAnh){
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.NhaSX = NhaSX;
        this.DacDiem = DacDiem;
        this.SoLuong = SoLuong;
        this.GiaNhap = GiaNhap;
        this.GiaBan = GiaBan;
        this.NgayNhap = NgayNhap;
        this.HinhAnh = HinhAnh;
    }
    
    public SanPham(){
    }

    
}
