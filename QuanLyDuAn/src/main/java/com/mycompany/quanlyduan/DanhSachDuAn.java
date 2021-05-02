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
import java.util.Scanner;

/**
 *
 * @author HP
 */
public class DanhSachDuAn {

    private static Scanner in = new Scanner(System.in);
    private List<DuAn> dsDuAn = new ArrayList<>();
    
    // updating.....   
    public void themDuAn() {
        System.out.print("nhap maDuAn :");
        System.out.print("nhap tenDuAn :");
        System.out.print("nhap maDuAn :");
        System.out.print("nhap maDuAn :");
        System.out.print("nhap maDuAn :");
        System.out.print("nhap maDuAn :");
    }
    public void showDuAn() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oop", "root", "12345678");
        String sql = " SELECT maDuAn from duan";
        PreparedStatement str = conn.prepareStatement(sql);
        ResultSet rs = str.executeQuery();
        while(rs.next()){
            System.out.printf("%d \t" , rs.getInt("maDuAn"));
        }
    }
    //done
    public void showNhanVienDuAn(int maDuAn) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oop", "root", "12345678");
        String sql = "SELECT nv.* FROM nhanvien nv "
                + "INNER JOIN duan_nhanvien danv ON nv.id = danv.nhanvien_id "
                + "INNER JOIN duan d ON danv.duan_id = d.maDuAn "
                + "WHERE d.maDuAn = ?";
        PreparedStatement str = conn.prepareStatement(sql);
        str.setInt(1, maDuAn);
        ResultSet rs = str.executeQuery();
        while (rs.next()) {
            System.out.printf("-ID:%d\tName:%s\tEmail:%s \tSEX:%s \t\tPosition:%s",
                    rs.getInt("id"), rs.getString("ten"), rs.getString("email"),
                    rs.getString("gioitinh"),
                    rs.getString("loainhanvien"));
            System.out.println();
        }

    }
    public void timKiemDuAn (String name) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oop", "root", "12345678");
        String sql = "SELECT * FROM duan WHERE tenDuAn like concat('%',?,'%')";
        PreparedStatement str = conn.prepareStatement(sql);
        str.setString(1, name);
        ResultSet rs = str.executeQuery();
        while(rs.next()){
            System.out.printf("ID: %d \tNAME: %s \tSTART: %s \tFINISH: %s \tMONEY: %f\n" ,
                    rs.getInt("maDuAn") , rs.getString("tenDuAn") , rs.getDate("ngayBatDau") ,
                    rs.getDate("ngayKetThuc"), rs.getDouble("tongkinhphi"));
        }
    }

    /**
     * @return the dsDuAn
     */
    public List<DuAn> getDsDuAn() {
        return dsDuAn;
    }

    /**
     * @param dsDuAn the dsDuAn to set
     */
    public void setDsDuAn(List<DuAn> dsDuAn) {
        this.dsDuAn = dsDuAn;
    }
}
