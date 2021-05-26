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
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author HP
 */
public class main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {
        Scanner in = new Scanner(System.in);
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
//  ================================ NHAN VIEN TRUONG ============================================
        NhanVienTruong t1 = new NhanVienTruong(11, "hoang", "hoangou01@gmail.com", "nam", "2024-11-25", 1.5, null);
//      s1.insertStaff();
//      t1.insertManager();
        NhanVienTruong t2 = new NhanVienTruong(12, "ha", "haou01@gmail.com", "nu", "2024-12-20", 1, null);
//      s1.insertStaff();
//      t2.insertManager();
//  ================================= PHONG BAN ==================================================
        PhongBan p1 = new PhongBan("sp001", t1);
//      p1.insertDepartment();
        PhongBan p2 = new PhongBan("sp002", t2);
//      p2.insertDepartment();
// ================================  NHAN VIEN    =====================================    
        NhanVien s1 = new LapTrinhVien(1, "huy", "huyou01@gmail.com", "nam", 2, p2, 4);
//      s1.insertStaff();
        NhanVien s2 = new NhanVien(2, "huong", "huongou01@gmail.com", "nu", 3, p1);
//      s1.insertStaff();
        NhanVien s3 = new LapTrinhVien(3, "huy", "huyou01@gmail.com", "nam", 2, p1, 3);
//      s1.insertStaff();
        NhanVien s4 = new ThietKeVien(4, "thuy", "thuyou01@gmai.com", "nu", 2, p2, 5000000);
//      s1.insertStaff();
        NhanVien s5 = new KiemThuVien(5, "romeo", "romeo01@gmail.com", "nam", 3, p2, 10);
//     s1.insertStaff();
        NhanVien s6 = new ThietKeVien(6, "thanh", "thanh01@gmai.com", "nam", 2, p2, 3000000);
//      s1.insertStaff();
        NhanVien s7 = new NhanVien(7, "justin", "biber01@gmail.com", "nam", 2, p1);
//     s1.insertStaff();
        NhanVien s8 = new LapTrinhVien(8, "hung", "hungou01@gmail.com", "nam", 2, p1, 4);
//       s1.insertStaff();
//   ================================ DU AN =======================================
        DuAn a1 = new DuAn(112, "cong trinh landmark81", "2018-03-12", "2020-01-24", 250000000, s2);
//      a1.insertProject();
        DuAn a2 = new DuAn(225, "khu do thi sala", "2019-08-25", "2022-03-30", 50000000, s4);
//      a2.insertProject();


//   ================================= QUAN LY NHAN VIEN =======================================
        QuanLyNhanVien ql = new QuanLyNhanVien();
        DanhSachDuAn qlda = new DanhSachDuAn();

        int choose;
        do {
            System.out.println("\n\n=================================MENU======================================");
            System.out.print("|  1.xem tat ca nhan vien!                                                |\n" + "|  2.them,xoa,sua du an.                                                  |\n" + "|  3.xem nhan vien cua 1 du an.                                           |\n"
                    + "|  4.xem du an cua 1 nhan vien.                                           |\n" + "|  5.tim kiem du an bang Name                                             |\n"
                    + "|  6.sap xep du an theo kinh phi dau tu                                   |\n" + "|  7.tim kiem nhan vien bang ten or phong ban                             |\n"
                    + "|  8.gan nhan vien cho du an                                              |\n|  9.thoat                                                                |\n"
                    + "===========================================================================\n" + "==BANCHON:\n");
            choose = in.nextInt();
            if (choose >= 1 && choose <= 8) {
                System.out.printf("================================ CASE %d ================================\n\n", choose);
            }
            switch (choose) {
                case 1:
                    ql.showListOfStaff();
                    break;                    
                case 2:
                    int choose1;
                    do {
                        System.out.println(" ---- case 2 menu ----");
                        System.out.println("|   1.them 1 du an    |\n|   2.xoa 1 du an     |\n|   3.sua du an       |\n|   4.THOAT           |\n ---------------------\n==BANCHON:");
                        choose1 = in.nextInt();
                        switch (choose1) {
                            case 1:
                                System.out.println("-NHAP THONG TIN 1 DU AN BAN CAN THEM-");
                                System.out.println("nhap Ma du an :");
                                int maDuAn = in.nextInt();
                                System.out.println("\nnhap ten du an :");
                                String tenDuAn = in.next();
                                in.nextLine();
// nhap ngay bat dau
                                System.out.println("nhap ngay bat dau :");
                                String dateString = in.next();
                                Date date = new SimpleDateFormat("dd-MM-yyyy").parse(dateString);
                                String dateStart = new SimpleDateFormat("yyyy-MM-dd").format(date);
// nhap ngay ket thuc
                                System.out.println("nhap ket thuc :");
                                String dateString1 = in.next();
                                Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(dateString1);
                                String dateFinish = new SimpleDateFormat("yyyy-MM-dd").format(date1);
//nhap tong kinh phi
                                System.out.println("nhap tong kinh phi :");
                                Double tongKinhPhi = in.nextDouble();
// nhap ma nhan vien quan ly
                                System.out.println("nhap ma nhan vien quan ly :");
                                int nvQuanLy = in.nextInt();
// them thong tin da nhap vao du an
                                qlda.insertProject(maDuAn, tenDuAn, dateStart, dateFinish, tongKinhPhi, nvQuanLy);
                                System.out.println("=================CAP NHAT DU AN ================");
                                qlda.showListOfProject();
                                break;
                            case 2:
                                System.out.println("---------------- DANH SACH DU AN HIEN CO ---------------");
                                qlda.showListOfProject();
                                System.out.println("NHAP MA DU AN BAN MUON XOA : ");
                                int deleteDuAn = in.nextInt();
                                if (qlda.isProject(deleteDuAn)) {
                                    qlda.deleteProject(deleteDuAn);
                                    System.out.println("=================CAP NHAT DU AN ================");
                                    qlda.showListOfProject();
                                } else {
                                    System.out.printf("KHONG CO DU AN %d TRONG CONG TY\n", deleteDuAn);
                                }
                                break;
                            case 3:
                                int choose3;
                                System.out.println("---------- HIEN DANG CO CAC DU AN ---------");
                                qlda.showListOfProject();
                                System.out.println("NHAP MA DU AN BAN MUON CAP NHAT : ");
                                int maDuAnUpdate = in.nextInt();
                                while (!qlda.isProject(maDuAnUpdate)) {
                                    System.out.println("\nVUI LONG CHON DUNG MA DU AN!");
                                    System.out.println("\nNHAP LAI MA DU AN BAN MUON CAP NHAT :");
                                    maDuAnUpdate = in.nextInt();
                                }
                                if (qlda.isProject(maDuAnUpdate) == true) {
                                    do {
                                        System.out.println("1.sua ten du an.\n2.sua ngay bat dau. \n3.sua ngay ket thuc. \n4.sua tong kinh phi. \n5.sua ma nhan vien quan ly.\n6.thoat.\n-ban chon :");
                                        choose3 = in.nextInt();
                                        switch (choose3) {
                                            case 1:

                                                System.out.println("NHAP TEN BAN MUON SUA :");
                                                String tenDuAnUpdate = in.next();
                                                in.nextLine();
                                                qlda.updateName(maDuAnUpdate, tenDuAnUpdate);
                                                break;
                                            case 2:
                                                System.out.println("NHAP NGAY BAT DAU MUON SUA THEO FORMAT (YEAR-MONTH-DAY)  :");
                                                dateString = in.next();
                                                date = new SimpleDateFormat("dd-MM-yyyy").parse(dateString);
                                                String dateStartUpdate = new SimpleDateFormat("yyyy-MM-dd").format(date);
                                                qlda.updateDateStart(dateStartUpdate, maDuAnUpdate);
                                                break;
                                            case 3:
                                                System.out.println("NHAP NGAY KET THUC MUON SUA THEO FORMAT (YEAR-MONTH-DAY)  :");
                                                dateString1 = in.next();
                                                date1 = new SimpleDateFormat("dd-MM-yyyy").parse(dateString1);
                                                String dateFinishUpdate = new SimpleDateFormat("yyyy-MM-dd").format(date1);
                                                qlda.updateDateFinish(dateFinishUpdate, maDuAnUpdate);
                                                break;

                                            case 4:
                                                System.out.println("NHAP TONG KINH PHI MUON SUA :");
                                                double expenseUpdate = in.nextDouble();
                                                qlda.updateExpense(maDuAnUpdate, expenseUpdate);
                                                break;
                                            case 5:
                                                System.out.println("NHAP MA NHAN VIEN QUAN LY MUON SUA :");
                                                int id_managerUpdate = in.nextInt();
                                                qlda.updateIdManager(maDuAnUpdate, id_managerUpdate);
                                                break;
                                        }
                                    } while (choose3 >= 1 && choose3 <= 5);
                                }

                        }
                    } while (choose1 >= 1 && choose1 < 4);
                    break;
                case 3:
                    System.out.println("HIEN DANG CO CAC DU AN:");
                    qlda.showListOfProject();
                    System.out.println("\nNHAP MA DU AN BAN MUON XEM :");
                    int maDuAn;
                    maDuAn = in.nextInt();
                    while (!qlda.isProject(maDuAn)) {
                        System.out.println("\nVUI LONG NHAP DUNG!");
                        System.out.println("\nNHAP LAI MA DU AN BAN MUON XEM :");
                        maDuAn = in.nextInt();
                    }
                    if (qlda.isProject(maDuAn)) {
                        qlda.showStaffsOfProject(maDuAn);
                    } else {
                        System.out.printf("KHONG CO DU AN %d VUI LONG NHAP DUNG !", maDuAn);
                    }

                    break;
                case 4:
                    System.out.println("---------- DANH SACH NHAN VIEN HIEN DANG CO -----------");
                    ql.showListOfStaff();
                    System.out.println("NHAP MA NHAN VIEN BAN MUON XEM :");
                    int maNhanVien = in.nextInt();
                    while (!ql.isStaff(maNhanVien)) {
                        System.out.printf("KHONG CO NHAN VIEN %d TRONG CONG TY", maNhanVien);
                        System.out.println("\nVUI LONG NHAP DUNG!");
                        System.out.println("NHAP LAI MA NHAN VIEN BAN MUON XEM :");
                        maNhanVien = in.nextInt();
                    }
                    if (ql.isStaff(maNhanVien)) {
                        ql.showProjectsOfStaff(maNhanVien);
                    }

                    break;
                case 5:
                    System.out.println("NHAP TEN DU AN BAN MUON TIM :");
                    String name;
                    name = in.next();
                    System.out.printf("---------------------- KET QUAN TIM KIEM DU AN %s ----------------------\n", name);
                    qlda.findProjectByName(name);
                    break;
                case 6:
                    System.out.println("---------------- KET QUA SAP XEP DU AN THEO KINH PHI -------------------");
                    qlda.sortProjectByExpense();
                    break;
                case 7:
                    int choose7;
                    do {
                        System.out.println("\n\n ---------------------------------------");
                        System.out.println("|  1.tim kiem nhan vien bang ten        |\n"
                                         + "|  2.tim kiem nhan vien bang phong ban  |"
                                       + "\n|  3.thoat                              |"
                                       + "\n ---------------------------------------"
                                        + "\n==BANCHON:");
                        choose7 = in.nextInt();
                        switch (choose7) {

                            case 1:
                                System.out.println("NHAP TEN NHAN VIEN BAN MUON TIM:");
                                String employeeName;
                                employeeName = in.next();
                                System.out.printf("-------------- KET QUA TIM KIEM NHAN VIEN CO TEN %s ------------\n", employeeName);
                                ql.findStaffByName(employeeName);
                                break;
                            case 2:
                                System.out.println("NHAP TEN PHONG BAN CUA NHAN VIEN MA BAN MUON TIM :");
                                String namePhongBan;
                                namePhongBan = in.next();
                                System.out.printf("--------- KET QUA TIM KIEM NHAN VIEN CO TEN PHONG BAN %s ----------\n", namePhongBan);
                                ql.findStaffByDepartment(namePhongBan);
                                break;
                        }
                    } while (choose7 == 1 || choose7 == 2);
                    break;
                case 8:
                    System.out.println("----------------- DANH SACH NHAN VIEN HIEN CO -----------------");
                    ql.showListOfStaff();
                    System.out.println("----------------- DANH SACH DU AN HIEN CO --------------------");
                    qlda.showListOfProject();
                    System.out.println("NHAP MA NHAN VIEN BAN MUON THEM : ");
                    int maNhanVienThem = in.nextInt();
                    System.out.println("");
                    while (!ql.isStaff(maNhanVienThem)) {
                        System.out.println("VUI LONG NHAP DUNG MA NHAN VIEN!");
                        System.out.println("NHAP MA NHAN VIEN BAN MUON THEM : ");
                        maNhanVienThem = in.nextInt();
                    }
                    System.out.printf("NHAP MA DU AN BAN MUON THEM NHAN VIEN CO MA %d : \n", maNhanVienThem);
                    int maDuAnThem = in.nextInt();
                    while (!qlda.isProject(maDuAnThem)) {
                        System.out.println("VUI LONG NHAP DUNG MA DU AN");
                        System.out.printf("NHAP MA DU AN BAN MUON THEM NHAN VIEN CO MA %d : \n", maNhanVienThem);
                        maDuAnThem = in.nextInt();
                    }
                    qlda.insertProjectAndStaff(maDuAnThem, maNhanVienThem);
                    System.out.println("");
                    break;
                default:
                    System.out.println("GOOD BYE SEE YOU LATER!");
            }

        } while (choose >= 1 && choose <= 8);

    }
}