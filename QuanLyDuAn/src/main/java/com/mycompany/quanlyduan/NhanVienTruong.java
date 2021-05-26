/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.quanlyduan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.util.Date.parse;

/**
 *
 * @author HP
 */
public class NhanVienTruong extends NhanVien {

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private Date ngayNhamChuc;

    public NhanVienTruong(int maNhanVien, String tenNhanVien, String email, String gioiTinh, String Ngay, double heSo, PhongBan phongBan) throws ParseException, SQLException, ClassNotFoundException {
        super(maNhanVien, tenNhanVien, email, gioiTinh, heSo, phongBan);
        this.ngayNhamChuc = FORMAT.parse(Ngay);
    }

    @Override
    public void insertStaff() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try ( Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oop", "root", "12345678")) {
            System.out.println("connected");
            String sql = "INSERT INTO nhanvien VALUES(?,?,?,?,?,?,?)";
            try ( PreparedStatement str = conn.prepareStatement(sql)) {
                str.setInt(1, this.getMaNhanVien());
                str.setString(2, this.getTenNhanVien());
                str.setString(3, this.getEmail());
                str.setString(4, this.getGioiTinh());
                str.setString(5, this.layLoai());
                str.setDouble(6, this.tinhLuong());
                str.setString(7, this.getPhongBan().getTenPhongBan());
                str.execute();
            }
        }
    }
//    public void insertManager() throws ClassNotFoundException, SQLException{
//         java.util.Date date_NhamChuc = this.ngayNhamChuc;
//         java.sql.Date sqlDate_NhamChuc = new java.sql.Date (date_NhamChuc.getTime());
//
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oop", "root", "12345678")) {
//            System.out.println("connected");
//            String sql = "INSERT INTO nvtruong VALUES(?,?,?,?,?,?,?)";
//             try (PreparedStatement str = conn.prepareStatement(sql)) {
//                 str.setInt(1, this.getMaNhanVien());
//                 str.setString(2, this.getTenNhanVien());
//                 str.setString(3, this.getEmail());
//                 str.setString(4, this.getGioiTinh());
//                 str.setDouble(5, this.tinhLuong());
//                 str.setDate(6, (java.sql.Date) sqlDate_NhamChuc);
//                 str.setString(7, this.layLoai());
//                 str.execute();
//             }
//        }
//    }

    @Override
    public String layLoai() {
        return "Nhan Vien Truong";
    }

    @Override
    public double tinhLuong() {
        return super.tinhLuong();
    }

    /**
     * @return the FORMAT
     */
    public static SimpleDateFormat getFORMAT() {
        return FORMAT;
    }

    /**
     * @param FORMAT
     */
    public static void setFORMAT(SimpleDateFormat FORMAT) {
        FORMAT = FORMAT;
    }

    /**
     * @return the ngayNhamChuc
     */
    public Date getNgayNhamChuc() {
        return ngayNhamChuc;
    }

    /**
     * @param ngayNhamChuc the ngayNhamChuc to set
     */
    public void setNgayNhamChuc(Date ngayNhamChuc) {
        this.ngayNhamChuc = ngayNhamChuc;
    }
}
