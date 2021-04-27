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

    private static SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private Date ngayNhamChuc;

    public NhanVienTruong(int maNhanVien, String tenNhanVien, String email, String gioiTinh, String Ngay, double heSo, String phongBan) throws ParseException, SQLException, ClassNotFoundException {
        super(maNhanVien, tenNhanVien, email, gioiTinh, heSo, phongBan);
        this.ngayNhamChuc =  FORMAT.parse(Ngay);
         java.util.Date date_NhamChuc = this.ngayNhamChuc;
         java.sql.Date sqlDate_NhamChuc = new java.sql.Date (date_NhamChuc.getTime());

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oop", "root", "12345678");
        System.out.println("connected");
        String sql = "INSERT INTO nvtruong VALUES(?,?,?,?,?,?,?)";
        PreparedStatement str = conn.prepareStatement(sql);
        str.setInt(1, maNhanVien);
        str.setString(2, tenNhanVien);
        str.setString(3, email);
        str.setString(4, gioiTinh);
        str.setDouble(5, this.tinhLuong());
        str.setDate(6, (java.sql.Date) sqlDate_NhamChuc);
        str.setString(7, this.layLoai());
        str.execute();
        

        str.close();
        conn.close();
    }
    
    public String layLoai() {
        return "Nhan Vien Truong";
    }

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
     * @param aFORMAT the FORMAT to set
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
