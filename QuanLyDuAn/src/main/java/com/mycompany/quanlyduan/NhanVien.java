/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.quanlyduan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author HP
 */
public class NhanVien {

    private static double luongCoBan = 10000000;
    private int maNhanVien;
    private String tenNhanVien;
    private String email;
    private String gioiTinh;
    private double heSo;
    private List<DuAn> duAnCuaNV;
    private String phongBan;

    public String layLoai() {
        return "nhanvienthuong";
    }

    public double tinhLuong() {
        return this.getLuongCoBan() * this.getHeSo();
    }

    @Override
    public String toString() {
        return String.format("%d\t%s\t%s\t%.1f\t%s", this.maNhanVien, this.tenNhanVien, this.layLoai(), this.tinhLuong(),this.phongBan);
    }

    public NhanVien(int maNhanVien, String tenNhanVien, String email, String gioiTinh, double heSo, String phongBan) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.email = email;
        this.gioiTinh = gioiTinh;
        this.heSo = heSo;
        this.phongBan = phongBan;

    }

    public void mysql() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oop", "root", "12345678")) {
            System.out.println("connected");
            String sql = "INSERT INTO nhanvien VALUES(?,?,?,?,?,?,?)";
            try (PreparedStatement str = conn.prepareStatement(sql)) {
                str.setInt(1, this.maNhanVien);
                str.setString(2, this.tenNhanVien);
                str.setString(3, this.email);
                str.setString(4, this.gioiTinh);
                str.setString(5, this.layLoai());
                str.setDouble(6, this.tinhLuong());
                str.setString(7, this.phongBan);
                str.execute();
            }
        }
    }


    /**
     * @return the maNhanVien
     */
    public int getMaNhanVien() {
        return maNhanVien;
    }

    /**
     * @param maNhanVien the maNhanVien to set
     */
    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    /**
     * @return the tenNhanVien
     */
    public String getTenNhanVien() {
        return tenNhanVien;
    }

    /**
     * @param tenNhanVien the tenNhanVien to set
     */
    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the gioiTinh
     */
    public String getGioiTinh() {
        return gioiTinh;
    }

    /**
     * @param gioiTinh the gioiTinh to set
     */
    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    /**
     * @return the luongCoBan
     */
    public double getLuongCoBan() {
        return luongCoBan;
    }

    /**
     * @param luongCoBan the luongCoBan to set
     */
    public void setLuongCoBan(double luongCoBan) {
        this.luongCoBan = luongCoBan;
    }

    /**
     * @return the heSo
     */
    public double getHeSo() {
        return heSo;
    }

    /**
     * @param heSo the heSo to set
     */
    public void setHeSo(int heSo) {
        this.heSo = heSo;
    }

    /**
     * @return the duAnCuaNV
     */
    public List<DuAn> getDuAnCuaNV() {
        return duAnCuaNV;
    }

    /**
     * @param duAnCuaNV the duAnCuaNV to set
     */
    public void setDuAnCuaNV(List<DuAn> duAnCuaNV) {
        this.duAnCuaNV = duAnCuaNV;
    }

}
