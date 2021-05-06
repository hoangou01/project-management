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
import java.util.List;
import java.util.Scanner;
import java.util.Date;


/**
 *
 * @author HP
 */
public class DanhSachDuAn {

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final Scanner in = new Scanner(System.in);
    private List<DuAn> dsDuAn = new ArrayList<>();

    //done  
    public void insertProject(int maDuAn , String tenDuAn , String dateStart , String dateFinish , double tongKinhPhi , int manvQuanLy) throws ParseException, ClassNotFoundException, SQLException {
        
        java.util.Date ngayBatDau =   FORMAT.parse(dateStart);
        java.util.Date ngayKetThuc =  FORMAT.parse(dateFinish);
        java.sql.Date ngayBatDau1 = new java.sql.Date(ngayBatDau.getTime());
        
        java.sql.Date ngayKetThuc1 = new java.sql.Date(ngayKetThuc.getTime());
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oop", "root", "12345678");
        String mysql = "INSERT INTO duan VALUES (?,?,?,?,?,?)";
        PreparedStatement str = conn.prepareStatement(mysql);
        str.setInt(1, maDuAn);
        str.setString(2, tenDuAn);
        str.setDate(3, ngayBatDau1);
        str.setDate(4, ngayKetThuc1);
        str.setDouble(5, tongKinhPhi);
        str.setInt(6, manvQuanLy);
        str.execute();
    }
    //done
    public void deleteProject(int maDuAn) throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        try ( Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oop", "root", "12345678")) {
            String sql = " DELETE FROM duan WHERE maDuAn = ?";
            PreparedStatement str = conn.prepareStatement(sql);
            str.setInt(1, maDuAn);
            str.executeUpdate();
        }
    }
//done
    public void showListOfProject() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try ( Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oop", "root", "12345678")) {
            String sql = " SELECT * from duan";
            try (PreparedStatement str = conn.prepareStatement(sql)) {
                ResultSet rs = str.executeQuery();
                while (rs.next()) {
                    System.out.printf("-ID: %d \tNAME: %s \tDateStart: %s \tDateFinish: %s \tEXPENSE: %f \tID_managementStaff: %d"
                            , rs.getInt("maDuAn"),rs.getString("tenDuAn")
                            ,rs.getDate("ngayBatDau"),rs.getDate("ngayKetThuc")
                            ,rs.getDouble("tongkinhphi")
                            ,rs.getInt("id_nvQuanLy"));
                    System.out.println("");
                }
            }
        }
    }
    //done
    public void showStaffsOfProject(int maDuAn) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try ( Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oop", "root", "12345678")) {
            String sql = "SELECT nv.* FROM nhanvien nv "
                    + "INNER JOIN duan_nhanvien danv ON nv.id = danv.nhanvien_id "
                    + "INNER JOIN duan d ON danv.duan_id = d.maDuAn "
                    + "WHERE d.maDuAn = ?";
            try (PreparedStatement str = conn.prepareStatement(sql)) {
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
        }
    }
//done
    public void findProjectByName(String name) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try ( Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oop", "root", "12345678")) {
            String sql = "SELECT * FROM duan WHERE tenDuAn like concat('%',?,'%')";
            try (PreparedStatement str = conn.prepareStatement(sql)) {
                str.setString(1, name);
                ResultSet rs = str.executeQuery();
                while (rs.next()) {
                    System.out.printf("ID: %d \tNAME: %s \tSTART: %s \tFINISH: %s \tMONEY: %f\n",
                            rs.getInt("maDuAn"), rs.getString("tenDuAn"), rs.getDate("ngayBatDau"),
                            rs.getDate("ngayKetThuc"), rs.getDouble("tongkinhphi"));
                }
            }
        }
    }
//done
    public void sortProjectByExpense() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oop", "root", "12345678");
        String sql = "SELECT * FROM duan ORDER BY tongkinhphi ASC";
        PreparedStatement str = conn.prepareStatement(sql);
        ResultSet rs = str.executeQuery();
        while (rs.next()) {
            System.out.printf("ID: %d \tNAME: %s \tSTART: %s \tFINISH: %s \tMONEY: %f\n",
                    rs.getInt("maDuAn"), rs.getString("tenDuAn"), rs.getDate("ngayBatDau"),
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
