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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class PhongBan {
    private String tenPhongBan;
    private List<NhanVien> dsNhanVien = new ArrayList<>();
    private NhanVien nhanVienTruong;
    //hien thi nhan vien cua 1 phong ban

    public PhongBan(String tenPhongBan, NhanVien nhanVienTruong) {
        this.tenPhongBan = tenPhongBan;
        this.nhanVienTruong = nhanVienTruong;
    }
    public void mysql() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oop", "root", "12345678");
        System.out.println("connected");
        String sql = "INSERT INTO phongban VALUES (?,?)";
        PreparedStatement str = conn.prepareStatement(sql);
        str.setString(1, this.tenPhongBan);
        str.setInt(2, this.nhanVienTruong.getMaNhanVien());
        str.execute();
        str.close();
        conn.close();        
    }
    public void hienThi(String ten) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oop", "root", "12345678");
        String sql = "SELECT * FROM nhanvien WHERE pb_ten like concat ('%',?,'%')";
        PreparedStatement str = conn.prepareStatement(sql);
        str.setString(1, ten);
        ResultSet rs = str.executeQuery();
        while(rs.next()){
            System.out.printf("-ID:%d\t,Name:%s\t,Email:%s\t,Position:%s\t,Salary:%.2f\t,PhongBan:%s",
                    rs.getInt("id"),rs.getString("name"),rs.getString("email"),
                    rs.getString("loainhanvien"),rs.getDouble("luong"),rs.getString("pb_ten"));
        }
    }
    /**
     * @return the tenPhongBan
     */
    
    public void themNhanVien(NhanVien h){
        dsNhanVien.add(h);
    }
    public String getTenPhongBan() {
        return tenPhongBan;
    }

    /**
     * @param tenPhongBan the tenPhongBan to set
     */
    public void setTenPhongBan(String tenPhongBan) {
        this.tenPhongBan = tenPhongBan;
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
     * @return the nhanVienTruong
     */
    public NhanVien getNhanVienTruong() {
        return nhanVienTruong;
    }

    /**
     * @param nhanVienTruong the nhanVienTruong to set
     */
    public void setNhanVienTruong(NhanVien nhanVienTruong) {
        this.nhanVienTruong = nhanVienTruong;
    }
    
}
