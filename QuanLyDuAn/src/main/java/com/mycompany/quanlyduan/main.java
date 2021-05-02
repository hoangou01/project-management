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
import java.util.Scanner;
import java.sql.Date;

/**
 *
 * @author HP
 */
public class main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {
        Scanner in = new Scanner(System.in);
// ===========================  NHAN VIEN    =====================================    
        NhanVien s1 = new LapTrinhVien(1, "huy", "huyou01@gmail.com", "nam", 2, "sp002", 4);
//      s1.mysql;
        NhanVien s2 = new NhanVien(2, "huong", "huongou01@gmail.com", "nu", 3, "sp001");
//      s2.mysql();
        NhanVien s3 = new LapTrinhVien(3, "huy", "huyou01@gmail.com", "nam", 2, "sp001", 3);
//      s3.mysql();
        NhanVien s4 = new ThietKeVien(4, "thuy", "thuyxinh01@gmai.com", "nu", 2, "sp002", 5000000);
//      s4.mysql();
        NhanVien s5 = new KiemThuVien(5, "romeo", "romeo01@gmail.com", "nam", 3, "sp002", 10);
 //     s5.mysql();
        NhanVien s6 = new ThietKeVien(6, "thanh", "thanh01@gmai.com", "nam", 2, "sp002", 3000000);
        NhanVien s7 = new NhanVien(7, "justin biber", "biber01@gmail.com", "nam", 2, "sp001");
        NhanVien s8 = new LapTrinhVien(8, "hung", "hungou01@gmail.com", "nam", 2, "sp001", 4);

//   =============================== DU AN =======================================
        DuAn a1 = new DuAn(112, "cong trinh landmark81", "2018-03-12", "2020-01-24", 250000000, s2);
//      a1.mysql();
        DuAn a2 = new DuAn(225, "khu do thi sala", "2019-08-25", "2022-03-30", 50000000, s4);
//      a2.mysql();
//  =============================== NHAN VIEN TRUONG ============================================
        NhanVien t1 = new NhanVienTruong(11, "hoang", "hoangou01@gmail.com", "nam", "2024-11-25", 1.5, "sp001");
//      t1.mysqlnvTruong();
        NhanVien t2 = new NhanVienTruong(12, "ha", "haou01@gmail.com", "nu", "2024-12-20", 1, "sp002");
//      t2.mysqlnvTruong();
//  =============================== PHONG BAN ==================================================
        PhongBan  p1 = new PhongBan("sp001", t1);
//      p1.mysql();
        PhongBan  p2 = new PhongBan("sp002", t2);
//      p2.mysql();
//   ================================= QUAN LY NHAN VIEN =======================================
        QuanLyNhanVien ql = new QuanLyNhanVien();
        ql.themNhanVien(s1);
        ql.themNhanVien(s2);
        ql.themNhanVien(s3);
        ql.themNhanVien(s4);
        DanhSachDuAn qlda = new DanhSachDuAn();
        System.out.println("HIEN DANG CO CAC DU AN:");
        qlda.showDuAn();
        System.out.println("\nNHAP MA DU AN BAN MUON XEM:");
        int maDuAn;
        maDuAn = in.nextInt();
        System.out.printf("=======DANH SACH NHAN VIEN CO TRONG DU AN %d ========\n" , maDuAn);
        qlda.showNhanVienDuAn(maDuAn);
//       Class.forName("com.mysql.cj.jdbc.Driver");
//         Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oop", "root", "12345678");
//         
//         conn.close();
        System.out.println("NHAP MA NHAN VIEN BAN MUON XEM :");
        int maNhanVien = in.nextInt();
        System.out.printf("DANH SACH DU AN NHAN VIEN %d DANG LAM \n"  , maNhanVien);
        ql.showDuanOfNhanVien(maNhanVien);
        System.out.println("NHAP TEN DU AN BAN MUON TIM :");
        String name;
        name = in.next();
        qlda.timKiemDuAn(name);
        int choose = 8;
        do {
            System.out.println("===========MENU=========");
            System.out.print("1.xem tat ca nhan vien!\n2.them,xoa,sua du an.\n3.xem nhan vien cua 1 du an.\n4.xem du an cua 1 nhan vien.\n5.tim kiem du an bang Name\n6.sap xep du an theo kinh phi dau tu\n7.tim kiem nhan vien bang ten or phong ban\n8.thoat\n==BANCHON:");

        } while (choose >= 1 && choose <= 7);
 //       ql.showDsNhanVien();

    }
}
