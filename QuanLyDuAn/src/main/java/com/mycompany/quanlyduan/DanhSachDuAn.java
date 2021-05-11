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
    private static final SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    private static final Scanner in = new Scanner(System.in);
    private List<DuAn> dsDuAn = new ArrayList<>();

    //them du an bang cach nhap tung du lieu  
    public void insertProject(int maDuAn, String tenDuAn, String dateStart, String dateFinish, double tongKinhPhi, int manvQuanLy) throws ParseException, ClassNotFoundException, SQLException {

        java.util.Date ngayBatDau = FORMAT.parse(dateStart);
        java.util.Date ngayKetThuc = FORMAT.parse(dateFinish);

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oop", "root", "12345678");
        String mysql = "INSERT INTO duan VALUES (?,?,?,?,?,?)";
        PreparedStatement str = conn.prepareStatement(mysql);
        str.setInt(1, maDuAn);
        str.setString(2, tenDuAn);
        str.setDate(3, new java.sql.Date(ngayBatDau.getTime()));
        str.setDate(4, new java.sql.Date(ngayKetThuc.getTime()));
        str.setDouble(5, tongKinhPhi);
        str.setInt(6, manvQuanLy);
        str.execute();
    }

    // xoa du an 
    public void deleteProject(int maDuAn) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try ( Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oop", "root", "12345678")) {
            String sql = " DELETE FROM duan WHERE maDuAn = ?";
            try ( PreparedStatement str = conn.prepareStatement(sql)) {
                str.setInt(1, maDuAn);
                int dem = str.executeUpdate();
                if (dem != 0) {
                    System.out.printf("DA XOA DU AN %d\n", maDuAn);
                    System.out.println("=================CAP NHAT DU AN ================");
                    this.showListOfProject();
                }
                str.close();

            } catch (Exception e) {
                System.out.printf("KHONG THE XOA  DU AN %d VI CO THE GAY ANH HUONG DEN DU LIEU!\n", maDuAn);
                int chooseDelete;

                System.out.println("MUON TIEP TUC XOA NHAN PHIM 1 :");
                chooseDelete = in.nextInt();
                if (chooseDelete == 1) {
                    switch (chooseDelete) {
                        case 1:
                            this.deleteProjectUnderSafeMode(maDuAn);
                    }

                }
            }
            conn.close();
        }

    }
//xuat danh sach du an hien dang co

    public void showListOfProject() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try ( Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oop", "root", "12345678")) {
            String sql = " SELECT maDuAn, tenDuAn, DATE_FORMAT(ngayBatDau , '%d-%m-%y') AS dateStart"
                    + " , DATE_FORMAT(ngayKetThuc , '%d-%m-%y') AS dateFinish ,"
                    + " tongKinhPhi , id_nvQuanLy from duan";
            try ( PreparedStatement str = conn.prepareStatement(sql)) {
                ResultSet rs = str.executeQuery();
                while (rs.next()) {
                    System.out.printf("-ID: %d \t\tNAME: %s \t\tDateStart: %s \t\tDateFinish: %s \t\tEXPENSE: %f \t\tID_managementStaff: %d\n",
                            rs.getInt("maDuAn"), rs.getString("tenDuAn"),
                            rs.getString("dateStart"), rs.getString("dateFinish"),
                            rs.getDouble("tongkinhphi"),
                            rs.getInt("id_nvQuanLy"));
                    System.out.println("");
                }
                str.close();
            }
            conn.close();
        }
    }

    //xuat danh sach nhan vien lam trong du an
    public void showStaffsOfProject(int maDuAn) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try ( Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oop", "root", "12345678")) {
            String sql = "SELECT nv.* FROM nhanvien nv "
                    + "INNER JOIN duan_nhanvien danv ON nv.id = danv.nhanvien_id "
                    + "INNER JOIN duan d ON danv.duan_id = d.maDuAn "
                    + "WHERE d.maDuAn = ?";
            try ( PreparedStatement str = conn.prepareStatement(sql)) {
                str.setInt(1, maDuAn);
                ResultSet rs = str.executeQuery();
                if (!rs.next()) {
                    System.out.printf("KHONG CO NHAN VIEN LAM TRONG DU AN %d \n", maDuAn);
                } else {
                    System.out.printf("---------------- DANH SACH NHAN VIEN CO TRONG DU AN %d ---------------\n", maDuAn);
                    do {
                        System.out.printf("-ID:%d\tName:%s\tEmail:%s \tSEX:%s \t\tPosition:%s \t\tSALARY :%f \n",
                                rs.getInt("id"), rs.getString("ten"), rs.getString("email"),
                                rs.getString("gioitinh"),
                                rs.getString("loainhanvien"), rs.getDouble("luong"));
                        System.out.println();
                    } while (rs.next());
                }
                str.close();
            }
            conn.close();
        }
    }
//tim kiem du an bang ten

    public void findProjectByName(String name) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try ( Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oop", "root", "12345678")) {
            String sql = "SELECT maDuAn ,tenDuAn ,DATE_FORMAT(ngayBatDau , '%d-%m-%y') AS dateStart , DATE_FORMAT(ngayKetThuc ,'%d-%m-%y') AS dateFinish , tongkinhphi , id_nvQuanLy FROM duan WHERE tenDuAn like concat('%',?,'%')";
            try ( PreparedStatement str = conn.prepareStatement(sql)) {
                str.setString(1, name);
                ResultSet rs = str.executeQuery();
                if (!rs.next()) {
                    System.out.println("KHONG TIM THAY KET QUA NAO");
                } else {
                    do {
                        System.out.printf("ID: %d \tNAME: %s \tSTART: %s \tFINISH: %s \tMONEY: %f \tID_MANAGER: %d\n",
                                rs.getInt("maDuAn"), rs.getString("tenDuAn"), rs.getString("dateStart"),
                                rs.getString("dateFinish"), rs.getDouble("tongkinhphi"), rs.getInt("id_nvQuanLy"));
                    } while (rs.next());
                }
                str.close();

            }
            conn.close();
        }
    }
// sap xep du an theo kinh phi

    public void sortProjectByExpense() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try ( Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oop", "root", "12345678")) {
            String sql = "SELECT maDuAn ,tenDuAn ,DATE_FORMAT(ngayBatDau , '%d-%m-%y') AS dateStart"
                    + " , DATE_FORMAT(ngayKetThuc ,'%d-%m-%y') AS dateFinish "
                    + ", tongkinhphi , id_nvQuanLy FROM duan ORDER BY tongkinhphi ASC";
            try ( PreparedStatement str = conn.prepareStatement(sql)) {
                ResultSet rs = str.executeQuery();
                while (rs.next()) {
                    System.out.printf("ID: %d \tNAME: %s \t\tSTART: %s \t\tFINISH: %s \t\tMONEY: %f \t\tID_MANAGER: %d\n",
                            rs.getInt("maDuAn"), rs.getString("tenDuAn"), rs.getString("dateStart"),
                            rs.getString("dateFinish"), rs.getDouble("tongkinhphi"), rs.getInt("id_nvQuanLy"));
                    System.out.println("");
                }
                str.close();
            }

            conn.close();

        }

    }

    public void insertProjectAndStaff(int maDuAn, int maNhanVien) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try ( Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oop", "root", "12345678")) {
            String sql = "INSERT INTO duan_nhanvien VALUES (?,?)";
            try ( PreparedStatement str = conn.prepareStatement(sql)) {
                str.setInt(1, maDuAn);
                str.setInt(2, maNhanVien);
                int execute = str.executeUpdate();
                if (execute != 0) {
                    System.out.printf("DA THEM NHAN VIEN %d VAO DU AN %d", maNhanVien, maDuAn);
                }
                str.close();
            } catch (SQLException e) {
                System.out.printf("\nKHONG THE THEM ! VI NHAN VIEN %d DA VA DANG LAM TRONG DU AN %d\n", maNhanVien, maDuAn);
            }
            conn.close();
        }
    }

    public boolean isProject(int maDuAn) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try ( Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oop", "root", "12345678")) {
            String sql = "SELECT * from duan WHERE maDuAn = ?";
            PreparedStatement str = conn.prepareStatement(sql);
            str.setInt(1, maDuAn);
            ResultSet rs = str.executeQuery();
            if (rs.next()) {
                return true;
            } else {

                return false;
            }

        }
    }

    public void updateName(int maDuAn, String name) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try ( Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oop", "root", "12345678")) {
            String sql = "UPDATE duan SET tenDuAn = ? WHERE maDuAn = ? ";
            try ( PreparedStatement str = conn.prepareStatement(sql)) {
                str.setString(1, name);
                str.setInt(2, maDuAn);
                str.execute();
                System.out.println("-------------CAP NHAP DU AN-----------");
                this.showListOfProject();
                str.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("KHONG THE CAP NHAT !");
            }
            conn.close();
        }
    }

    public void updateDateStart(String ngayBatDau, int maDuAn) throws ClassNotFoundException, SQLException, ParseException {
        java.util.Date dateStart = FORMAT.parse(ngayBatDau);

        Class.forName("com.mysql.cj.jdbc.Driver");
        try ( Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oop", "root", "12345678")) {
            String sql = "UPDATE duan SET ngayBatDau = ? WHERE maDuAn = ? ";
            try ( PreparedStatement str = conn.prepareStatement(sql)) {
                str.setDate(1, new java.sql.Date(dateStart.getTime()));
                str.setInt(2, maDuAn);
                str.execute();
                System.out.println("-------------CAP NHAP DU AN-----------");
                this.showListOfProject();
                str.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("KHONG THE CAP NHAT !");
            }
            conn.close();
        }
    }

    public void updateDateFinish(String ngayKetThuc, int maDuAn) throws ClassNotFoundException, SQLException, ParseException {
        java.util.Date dateFinish = FORMAT.parse(ngayKetThuc);

        Class.forName("com.mysql.cj.jdbc.Driver");
        try ( Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oop", "root", "12345678")) {
            String sql = "UPDATE duan SET ngayKetThuc = ? WHERE maDuAn = ? ";
            try ( PreparedStatement str = conn.prepareStatement(sql)) {
                str.setDate(1, new java.sql.Date(dateFinish.getTime()));
                str.setInt(2, maDuAn);
                str.execute();
                System.out.println("-------------CAP NHAP DU AN-----------");
                this.showListOfProject();
                str.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("KHONG THE CAP NHAT !");
            }
            conn.close();
        }
    }

    public void updateExpense(int maDuAn, Double tongKinhPhi) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try ( Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oop", "root", "12345678")) {
            String sql = "UPDATE duan SET tongKinhPhi = ? WHERE maDuAn = ? ";
            try ( PreparedStatement str = conn.prepareStatement(sql)) {
                str.setDouble(1, tongKinhPhi);
                str.setInt(2, maDuAn);
                str.execute();
                System.out.println("-------------CAP NHAP DU AN-----------");
                this.showListOfProject();
                str.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("KHONG THE CAP NHAT !");
            }
            conn.close();
        }
    }

    public void updateIdManager(int maDuAn, int id_nvQuanLy) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try ( Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oop", "root", "12345678")) {
            String sql = "UPDATE duan SET id_nvQuanLy = ? WHERE maDuAn = ? ";
            try ( PreparedStatement str = conn.prepareStatement(sql)) {
                str.setInt(1, id_nvQuanLy);
                str.setInt(2, maDuAn);
                str.execute();
                System.out.println("-------------CAP NHAP DU AN-----------");
                this.showListOfProject();
                str.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("KHONG THE CAP NHAT !");
            }
            conn.close();
        }
    }

   public void deleteProjectUnderSafeMode(int maDuAn) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try ( Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oop", "root", "12345678")) {
            String sql = "SET SQL_SAFE_UPDATES = 0;"
                    + "DELETE FROM duan WHERE maDuAn = ?; "
                    + "SET SQL_SAFE_UPDATES = 1;";
            try ( PreparedStatement str = conn.prepareStatement(sql)) {
                str.setInt(1, maDuAn);
                int dem = str.executeUpdate();
                if (dem != 0) {
                    System.out.printf("\nDA XOA THANH CONG DU AN %d", maDuAn);
                }
                str.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("\nKO THE DELETE");
            }
            conn.close();
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
