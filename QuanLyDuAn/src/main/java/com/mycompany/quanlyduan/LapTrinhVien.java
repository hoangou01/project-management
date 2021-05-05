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
public class LapTrinhVien extends NhanVien {

    private int soGioLamThem;
    
    public LapTrinhVien(int maNhanVien, String tenNhanVien, String email, String gioiTinh ,int heSo, String phongBan , int gioLamThem) {
        super(maNhanVien, tenNhanVien, email, gioiTinh ,heSo , phongBan);
        this.soGioLamThem =  gioLamThem;
    }

    @Override
    public String layLoai() {
        return "Lap Trinh Vien";
    }

    @Override
    public double tinhLuong() {
        return this.luongCoBan + this.soGioLamThem * this.luongCoBan;
    }

    /**
     * @return the soGioLamThem
     */
    public int getSoGioLamThem() {
        return soGioLamThem;
    }

    /**
     * @param soGioLamThem the soGioLamThem to set
     */
    public void setSoGioLamThem(int soGioLamThem) {
        this.soGioLamThem = soGioLamThem;
    }

}
