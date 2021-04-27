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
public class ThietKeVien extends NhanVien {

    private double bonus;

    public ThietKeVien(int maNhanVien, String tenNhanVien, String email, String gioiTinh ,int heSo, String phongBan, int bonus) {
        super(maNhanVien, tenNhanVien, email, gioiTinh,heSo , phongBan);
        this.bonus = bonus;
    }

    public String layLoai() {
        return "Thiet Ke Vien";
    }

    @Override
    public double tinhLuong() {
        return this.getLuongCoBan() + this.getBonus();
    }

    /**
     * @return the bonus
     */
    public double getBonus() {
        return bonus;
    }

    /**
     * @param bonus the bonus to set
     */
    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

}
