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
public class NhanVien {

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
     * @return the Hinh
     */
    public String getHinh() {
        return Hinh;
    }

    /**
     * @param Hinh the Hinh to set
     */
    public void setHinh(String Hinh) {
        this.Hinh = Hinh;
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
     * @return the TenNV
     */
    public String getTenNV() {
        return TenNV;
    }

    /**
     * @param TenNV the TenNV to set
     */
    public void setTenNV(String TenNV) {
        this.TenNV = TenNV;
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
     * @return the SDT
     */
    public String getSDT() {
        return SDT;
    }

    /**
     * @param SDT the SDT to set
     */
    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    /**
     * @return the ChucVu
     */
    public String getChucVu() {
        return ChucVu;
    }

    /**
     * @param ChucVu the ChucVu to set
     */
    public void setChucVu(String ChucVu) {
        this.ChucVu = ChucVu;
    }

    /**
     * @return the TrangThai
     */
    public String getTrangThai() {
        return TrangThai;
    }

    /**
     * @param TrangThai the TrangThai to set
     */
    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }
    private String MaNV;
    private String TenNV;
    private boolean GioiTinh;
    private Date NgaySinh;
    private String DiaChi;
    private String Email;
    private String SDT;
    private String CMND;
    private String ChucVu;
    private String TrangThai;
    private String Hinh;
    
    
    public NhanVien(String MaNV, String TenNV, boolean GioiTinh, Date NgaySinh, String DiaChi, String Email, String SDT, String CMND, String ChucVu, String TrangThai, String Hinh){
        this.MaNV = MaNV;
        this.TenNV = TenNV;
        this.GioiTinh = GioiTinh;
        this.NgaySinh = NgaySinh;
        this.DiaChi = DiaChi;
        this.Email = Email;
        this.SDT = SDT;
        this.CMND = CMND;
        this.ChucVu = ChucVu;
        this.TrangThai = TrangThai;
        this.Hinh = Hinh;
    }
    
    public NhanVien(){
    }
    
    @Override
    public String toString() {
        return this.MaNV;
    }
}
