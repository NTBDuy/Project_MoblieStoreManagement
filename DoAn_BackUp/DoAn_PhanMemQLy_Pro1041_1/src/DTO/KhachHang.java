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
public class KhachHang {

    /**
     * @return the Email
     */
    public String getEmail() {
        return Email;
    }

    /**
     * @param Email the Email to set
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }

    /**
     * @return the GioiTinh
     */
    public boolean isGioiTinh() {
        return GioiTinh;
    }

    /**
     * @param GioiTinh the GioiTinh to set
     */
    public void setGioiTinh(boolean GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    /**
     * @return the CMND
     */
    public String getCMND() {
        return CMND;
    }

    /**
     * @param CMND the CMND to set
     */
    public void setCMND(String CMND) {
        this.CMND = CMND;
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
     * @return the TenKH
     */
    public String getTenKH() {
        return TenKH;
    }

    /**
     * @param TenKH the TenKH to set
     */
    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    /**
     * @return the NgaySinh
     */
    public Date getNgaySinh() {
        return NgaySinh;
    }

    /**
     * @param NgaySinh the NgaySinh to set
     */
    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    /**
     * @return the DiaChi
     */
    public String getDiaChi() {
        return DiaChi;
    }

    /**
     * @param DiaChi the DiaChi to set
     */
    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    /**
     * @return the SoDT
     */
    public String getSoDT() {
        return SoDT;
    }

    /**
     * @param SoDT the SoDT to set
     */
    public void setSoDT(String SoDT) {
        this.SoDT = SoDT;
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
    private String MaKH;
    private String TenKH;
    private boolean GioiTinh;
    private Date NgaySinh;
    private String CMND;
    private String SoDT;
    private String DiaChi;
    private String Email;
    private String GhiChu;
    
    public KhachHang(String MaKH, String TenKH, boolean GioiTinh, Date NgaySinh, String CMND, String DiaChi, String Email, String SoDT, String GhiChu){
        this.MaKH = MaKH;
        this.TenKH = TenKH;
        this.GioiTinh = GioiTinh;
        this.NgaySinh = NgaySinh;
        this.CMND = CMND;
        this.DiaChi = DiaChi;
        this.Email = Email;
        this.SoDT = SoDT;
        this.GhiChu = GhiChu;
    }
    
    public KhachHang(){
    }
}
