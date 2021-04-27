/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.quanlyduan;

import java.sql.Connection;
import java.sql.DriverManager;
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
    public void showDsNhanVien() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oop", "root", "12345678");
        System.out.println("connected");
        Statement str = conn.createStatement();
        ResultSet rs =  str.executeQuery("SELECT * FROM nhanvien");
        while(rs.next()){
            System.out.printf("-ID:%d\tName:%s\tEmail:%s \tSEX:%s \tPosition:%s",
                    rs.getInt("id"),rs.getString("ten"),rs.getString("email"),
                    rs.getString("gioitinh"),
                    rs.getString("loainhanvien"));
            System.out.println();
        }
        str.close();
        conn.close();
    }
    public void themNhanVien(NhanVien d){
        this.dsNhanVien.add(d);
    }
    public void hienThiDs(){
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
