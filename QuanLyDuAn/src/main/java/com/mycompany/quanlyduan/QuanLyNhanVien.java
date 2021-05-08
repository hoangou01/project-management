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
public class QuanLyNhanVien {

    private List<NhanVien> dsNhanVien = new ArrayList<>();
// xem danh sach nhan vien
    public void showListOfStaff() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oop", "root", "12345678");
        System.out.println("connected");
        Statement str = conn.createStatement();
        ResultSet rs = str.executeQuery("SELECT * FROM nhanvien");
        while (rs.next()) {
            System.out.printf("-ID:%d\tName:%s\t\tEmail:%s \t\tSEX:%s \t\tPosition:%s \t\tSALARY: %f\n",
                    rs.getInt("id"), rs.getString("ten"), rs.getString("email"),
                    rs.getString("gioitinh"),
                    rs.getString("loainhanvien"),
                    rs.getDouble("luong"));
            System.out.println();
        }
        str.close();
        conn.close();
    }

    // hien thi du an cua 1 nhan vien
    public void showProjectsOfStaff(int maNhanVien) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try ( Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oop", "root", "12345678")) {
            String sql = "SELECT da.maDuAn ,da.tenDuAn ,DATE_FORMAT(ngayBatDau ,'%d-%m-%y') AS dateStart ,DATE_FORMAT(ngayKetThuc ,'%d-%m-%y') AS dateFinish ,da.tongkinhphi ,da.id_nvQuanLy FROM duan da "
                    + "INNER JOIN duan_nhanvien danv ON da.maDuAn = danv.duan_id "
                    + "INNER JOIN nhanvien nv ON danv.nhanvien_id = nv.id "
                    + "WHERE nv.id = ?";
            try ( PreparedStatement str = conn.prepareStatement(sql)) {
                str.setInt(1, maNhanVien);
                ResultSet rs = str.executeQuery();
               if (!rs.next()){
                   System.out.println("KHONG CO NHAN VIEN %d TRONG CONG TY");
               }
               else{
                   do{
                       System.out.printf("ID : %d \tNAME : %s \t\tSTART : %s \tFINISH : %s \tMONEY : %f \tID_MANAGER : %d\n",
                             rs.getInt("maDuAn"), rs.getString("tenDuAn"),
                             rs.getString("dateStart"), rs.getString("dateFinish"),
                            rs.getDouble("tongkinhphi"),rs.getInt("id_nvQuanLy"));
                   }while(rs.next());
               }
            }
        }
    }
// tim kiem nhan vien bang ten
    public void findStaffByName(String name) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try ( Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oop", "root", "12345678")) {
            String mysql = "SELECT * FROM nhanvien WHERE ten like concat ('%',?,'%')";
            try ( PreparedStatement str = conn.prepareStatement(mysql)) {
                str.setString(1, name);
                ResultSet rs = str.executeQuery();
                if (!rs.next()){
                    System.out.printf("KHONG CO NHAN VIEN NAO TEN %s \n" , name);
                }
                else{
                    do{
                        System.out.printf("ID: %d \tNAME: %s \tEMAIL: %s \tSEX: %s \tPOSSION:%s \tSALARY: %f\n",
                             rs.getInt("id"), rs.getString("ten"), rs.getString("email"),
                             rs.getString("gioitinh"), rs.getString("loainhanvien"),
                             rs.getDouble("luong"));
                    }while(rs.next());
                }
            }
        }
    }
// tim kiem nhan vien bang ten phong 
    public void findStaffByDepartment(String tenPhongBan) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try ( Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oop", "root", "12345678")) {
            String mysql = "SELECT * FROM nhanvien WHERE pb_ten like concat ('%',?,'%')";
            try ( PreparedStatement str = conn.prepareStatement(mysql)) {
                str.setString(1, tenPhongBan);
                ResultSet rs = str.executeQuery();
               if (!rs.next()){
                   System.out.printf("KHONG CO NHAN VIEN NAO THUOC PHONG BAN %s \n" , tenPhongBan);
               }
               else{
                   do{
                       System.out.printf("ID: %d \tNAME: %s \tEMAIL: %s \tSEX: %s \tPOSSION:%s \tSALARY: %f\n",
                             rs.getInt("id"), rs.getString("ten"), rs.getString("email"),
                             rs.getString("gioitinh"), rs.getString("loainhanvien"),
                             rs.getDouble("luong"));
                   }while(rs.next());
               }
            }
        }
    }
    

    public void themNhanVien(NhanVien d) {
        this.dsNhanVien.add(d);
    }

    public void hienThiDs() {
        this.dsNhanVien.forEach(x -> System.out.println(x));
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

}