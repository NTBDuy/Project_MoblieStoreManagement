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
public class TaiKhoan {

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
    private String TenTK;
    private String MatKhau;
    private String Quyen;
    private String MaNV;
    public TaiKhoan(String TenTK, String MatKhau, String Quyen, String MaNV){
        this.TenTK = TenTK;
        this.MatKhau = MatKhau;
        this.Quyen = Quyen;
        this.MaNV = MaNV;
    }
    
    public TaiKhoan(){
    }

    /**
     * @return the TenTK
     */
    public String getTenTK() {
        return TenTK;
    }

    /**
     * @param TenTK the TenTK to set
     */
    public void setTenTK(String TenTK) {
        this.TenTK = TenTK;
    }

    /**
     * @return the MatKhau
     */
    public String getMatKhau() {
        return MatKhau;
    }

    /**
     * @param MatKhau the MatKhau to set
     */
    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    /**
     * @return the Quyen
     */
    public String getQuyen() {
        return Quyen;
    }

    /**
     * @param Quyen the Quyen to set
     */
    public void setQuyen(String Quyen) {
        this.Quyen = Quyen;
    }
}
