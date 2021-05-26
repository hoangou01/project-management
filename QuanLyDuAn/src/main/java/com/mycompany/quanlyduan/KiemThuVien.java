/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.quanlyduan;

/**
 *
 * @author HP
 */
public class KiemThuVien extends NhanVien {

    private static int tienError = 200000;
    private int soError;

    public KiemThuVien(int maNhanVien, String tenNhanVien, String email, String gioiTinh , int heSo ,PhongBan phongban, int soError) {
        super(maNhanVien, tenNhanVien, email, gioiTinh,heSo,phongban);
        this.soError = soError;
    }

    @Override
    public String layLoai() {
        return "Kiem Thu Vien";
    }

    @Override
    public double tinhLuong() {
        return this.luongCoBan + this.soError * this.tienError;
    }

    /**
     * @return the tienError
     */
    public static int getTienError() {
        return tienError;
    }

    /**
     * @param aTienError the tienError to set
     */
    public static void setTienError(int aTienError) {
        tienError = aTienError;
    }

    /**
     * @return the soError
     */
    public int getSoError() {
        return soError;
    }

    /**
     * @param soError the soError to set
     */
    public void setSoError(int soError) {
        this.soError = soError;
    }

}
