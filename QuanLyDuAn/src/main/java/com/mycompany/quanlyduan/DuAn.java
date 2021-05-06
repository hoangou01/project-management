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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author HP
 */
public class DuAn {

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final Scanner in = new Scanner(System.in);
    private int maDuAn;
    private String tenDuAn;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private Double tongKinhPhi;
    private List<NhanVien> dsNhanVien = new ArrayList<>();
    private NhanVien truongDuAn;

    public DuAn(int ma, String ten, String DateStart, String DateFinish, double tongKinhPhi, NhanVien nv) throws ParseException {
        this.maDuAn = ma;
        this.tenDuAn = ten;
        this.ngayBatDau = FORMAT.parse(DateStart);
        this.ngayKetThuc = FORMAT.parse(DateFinish);
        this.tongKinhPhi = tongKinhPhi;
        this.truongDuAn = nv;
    }

    public void insertProject() throws ClassNotFoundException, SQLException {
        java.util.Date date_start = this.ngayBatDau;
        java.sql.Date sqlDate_start = new java.sql.Date(date_start.getTime());
        java.util.Date date_finish = this.ngayBatDau;
        java.sql.Date sqlDate_finish = new java.sql.Date(date_finish.getTime());
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oop", "root", "12345678");
        System.out.println("connected");
        String sql = "INSERT INTO duan VALUES (?,?,?,?,?,?)";
        PreparedStatement str = conn.prepareStatement(sql);
        str.setInt(1, this.maDuAn);
        str.setString(2, this.tenDuAn);
        str.setDate(3, (java.sql.Date) sqlDate_start);
        str.setDate(4, (java.sql.Date) sqlDate_finish);
        str.setDouble(5, this.tongKinhPhi);
        str.setInt(6, this.truongDuAn.getMaNhanVien());
        str.execute();
    }

    /**
     * @return the maDuAn
     */
    public int getMaDuAn() {
        return maDuAn;
    }

    /**
     * @param maDuAn the maDuAn to set
     */
    public void setMaDuAn(int maDuAn) {
        this.maDuAn = maDuAn;
    }

    /**
     * @return the tenDuAn
     */
    public String getTenDuAn() {
        return tenDuAn;
    }

    /**
     * @param tenDuAn the tenDuAn to set
     */
    public void setTenDuAn(String tenDuAn) {
        this.tenDuAn = tenDuAn;
    }

    /**
     * @return the ngayBatDau
     */
    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    /**
     * @param ngayBatDau the ngayBatDau to set
     */
    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    /**
     * @return the ngayKetThuc
     */
    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    /**
     * @param ngayKetThuc the ngayKetThuc to set
     */
    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    /**
     * @return the tongKinhPhi
     */
    public Double getTongKinhPhi() {
        return tongKinhPhi;
    }

    /**
     * @param tongKinhPhi the tongKinhPhi to set
     */
    public void setTongKinhPhi(Double tongKinhPhi) {
        this.tongKinhPhi = tongKinhPhi;
    }

    /**
     * @return the dsNhanVien
     */
    public List<NhanVien> getDsNhanVien() {
        return dsNhanVien;
    }

    /**
     * @param dsNhanVien the dsNhanVien to set
     */
    public void setDsNhanVien(List<NhanVien> dsNhanVien) {
        this.dsNhanVien = dsNhanVien;
    }

    /**
     * @return the truongDuAn
     */
    public NhanVien getTruongDuAn() {
        return truongDuAn;
    }

    /**
     * @param truongDuAn the truongDuAn to set
     */
    public void setTruongDuAn(NhanVien truongDuAn) {
        this.truongDuAn = truongDuAn;
    }
}
