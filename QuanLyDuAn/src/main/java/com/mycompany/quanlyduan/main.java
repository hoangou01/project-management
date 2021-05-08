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
// ================================  NHAN VIEN    =====================================    
        NhanVien s1 = new LapTrinhVien(1, "huy", "huyou01@gmail.com", "nam", 2, "sp002", 4);
//      s1.insertStaff();
        NhanVien s2 = new NhanVien(2, "huong", "huongou01@gmail.com", "nu", 3, "sp001");
//      s1.insertStaff();
        NhanVien s3 = new LapTrinhVien(3, "huy", "huyou01@gmail.com", "nam", 2, "sp001", 3);
//      s1.insertStaff();
        NhanVien s4 = new ThietKeVien(4, "thuy", "thuyou01@gmai.com", "nu", 2, "sp002", 5000000);
//      s1.insertStaff();
        NhanVien s5 = new KiemThuVien(5, "romeo", "romeo01@gmail.com", "nam", 3, "sp002", 10);
//     s1.insertStaff();
        NhanVien s6 = new ThietKeVien(6, "thanh", "thanh01@gmai.com", "nam", 2, "sp002", 3000000);
//      s1.insertStaff();
        NhanVien s7 = new NhanVien(7, "justin", "biber01@gmail.com", "nam", 2, "sp001");
//     s1.insertStaff();
        NhanVien s8 = new LapTrinhVien(8, "hung", "hungou01@gmail.com", "nam", 2, "sp001", 4);
//       s1.insertStaff();
//   ================================ DU AN =======================================
        DuAn a1 = new DuAn(112, "cong trinh landmark81", "2018-03-12", "2020-01-24", 250000000, s2);
//      a1.insertProject();
        DuAn a2 = new DuAn(225, "khu do thi sala", "2019-08-25", "2022-03-30", 50000000, s4);
//      a2.insertProject();
//  ================================ NHAN VIEN TRUONG ============================================
        NhanVien t1 = new NhanVienTruong(11, "hoang", "hoangou01@gmail.com", "nam", "2024-11-25", 1.5, "sp001");
//      s1.insertStaff();
//      t1.insertManager();
        NhanVien t2 = new NhanVienTruong(12, "ha", "haou01@gmail.com", "nu", "2024-12-20", 1, "sp002");
//      s1.insertStaff();
//      t2.insertManager();
//  ================================= PHONG BAN ==================================================
        PhongBan  p1 = new PhongBan("sp001", t1);
//      p1.insertDepartment();
        PhongBan  p2 = new PhongBan("sp002", t2);
//      p2.insertDepartment();
//   ================================= QUAN LY NHAN VIEN =======================================
        QuanLyNhanVien ql = new QuanLyNhanVien();
        ql.themNhanVien(s1);
        ql.themNhanVien(s2);
        ql.themNhanVien(s3);
        ql.themNhanVien(s4);
        ql.themNhanVien(s5);
        ql.themNhanVien(s6);
        ql.themNhanVien(s7);
        ql.themNhanVien(s8);
        ql.themNhanVien(t1);
        ql.themNhanVien(t2);
        DanhSachDuAn qlda = new DanhSachDuAn();
 
        int choose;
        do {
            System.out.println("=================================MENU=================================");
            System.out.print("1.xem tat ca nhan vien!\n" + "2.them,xoa,sua du an.\n" + "3.xem nhan vien cua 1 du an.\n" + "4.xem du an cua 1 nhan vien.\n" + "5.tim kiem du an bang Name\n" + "6.sap xep du an theo kinh phi dau tu\n" + "7.tim kiem nhan vien bang ten or phong ban\n" + "8.gan nhan vien cho du an\n9.thoat\n" + "====================================================================\n" + "BANCHON:\n");
            choose = in.nextInt();
            if(choose <1 && choose >8){
                System.out.println("VUI LONG CHON TU 1 DEN 8");
            }
            switch(choose){
                case 1:
                    ql.showListOfStaff();
                    break;
                case 2:
                    int choose1;
                    do{
                        System.out.println("\n1.them 1 du an\n2.xoa 1 du an.\n3.sua du an\n4.THOAT\n==BANCHON:");
                        choose1 = in.nextInt();
                        switch(choose1){
                            case 1:
                                System.out.println("-NHAP THONG TIN 1 DU AN BAN CAN THEM-");
                                System.out.println("nhap Ma du an :");
                                int maDuAn = in.nextInt();
                                System.out.println("\nnhap ten du an :");
                                String tenDuAn = in.next();
                                in.nextLine();
                                System.out.println("nnhap ngay bat dau :");
                                String dateStart = in.next();                               
                                System.out.println("nhap ket thuc :");
                                String dateFinish = in.next();                        
                                System.out.println("nhap tong kinh phi :");
                                Double tongKinhPhi = in.nextDouble();
                                System.out.println("nhap ma nhan vien quan ly :");
                                int nvQuanLy = in.nextInt();
                                qlda.insertProject(maDuAn , tenDuAn , dateStart , dateFinish , tongKinhPhi,nvQuanLy);
                                System.out.println("=================CAP NHAT DU AN ================");
                                qlda.showListOfProject();
                                break;
                            case 2:
                                System.out.println("NHAP MA DU AN BAN MUON XOA : ");
                                int deleteDuAn = in.nextInt();
                                qlda.deleteProject(deleteDuAn);
                                break;
                        }
                      }while(choose1 >=1 && choose1 <4);
                    break;
                case 3:
                    System.out.println("HIEN DANG CO CAC DU AN:");
                    qlda.showListOfProject();
                    System.out.println("\nNHAP MA DU AN BAN MUON XEM :");
                    int maDuAn;
                    maDuAn = in.nextInt();
                    System.out.printf("=============== DANH SACH NHAN VIEN CO TRONG DU AN %d ================\n" , maDuAn);
                    qlda.showStaffsOfProject(maDuAn);
                    break;
                case 4:
                    System.out.println("NHAP MA NHAN VIEN BAN MUON XEM :");
                    int maNhanVien = in.nextInt();
                    System.out.printf("=============== DANH SACH DU AN NHAN VIEN %d DANG LAM ===================\n"  , maNhanVien);
                    ql.showProjectsOfStaff(maNhanVien);
                    break;
                case 5:
                    System.out.println("NHAP TEN DU AN BAN MUON TIM :");
                    String name;
                    name = in.next();
                    qlda.findProjectByName(name);
                    break;
                case 6:
                    System.out.println("==============KET QUA SAP XEP DU AN THEO KINH PHI====================");
                    qlda.sortProjectByExpense();
                    break;
                case 7:
                    int choose2;
                    do{
                        System.out.println("1.tim kiem nhan vien bang ten\n2.tim kiem nhan vien bang phong ban\n3.thoat\nBANCHON:");
                        choose2 = in.nextInt();
                        switch(choose2){
                        
                            case 1:
                                System.out.println("NHAP TEN NHAN VIEN BAN MUON TIM:");
                                String employeeName;
                                employeeName = in.next();
                                System.out.printf("====KET QUA TIM KIEM NHAN VIEN CO TEN %s ====\n",employeeName);
                                ql.findStaffByName(employeeName);
                                break;
                            case 2:
                                System.out.println("NHAP TEN PHONG BAN CUA NHAN VIEN MA BAN MUON TIM :");
                                String namePhongBan;
                                namePhongBan = in.next();
                                System.out.printf("====KET QUA TIM KIEM NHAN VIEN CO TEN PHONG BAN %s =====\n",namePhongBan);
                                ql.findStaffByDepartment(namePhongBan);
                                break;           
                    }
                    }while(choose2 == 1 || choose2 ==2);
                    break;
                case 8:
                    System.out.println("============= DANH SACH NHAN VIEN HIEN CO ==============");
                    ql.showListOfStaff();
                    System.out.println("============= DANH SAHC DU AN HIEN CO ===================");
                    qlda.showListOfProject();
                    System.out.println("NHAP MA NHAN VIEN BAN MUON THEM : ");
                    int maNhanVienThem = in.nextInt();
                    System.out.println("");
                    System.out.printf("NHAP MA DU AN BAN MUON THEM NHAN VIEN CO MA %d : \n" , maNhanVienThem);
                    int maDuAnThem = in.nextInt();
                    qlda.insertProjectStaff(maDuAnThem, maNhanVienThem);
                    break;
                default:
                    System.out.println("GOOD BYE SEE YOU LATER!");      
            }

        } while (choose >= 1 && choose <= 7);
        

    }
}