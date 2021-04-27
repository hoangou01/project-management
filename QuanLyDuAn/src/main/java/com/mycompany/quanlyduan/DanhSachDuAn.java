/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.quanlyduan;

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
    
    public void themDuAn(){
        System.out.print("nhap maDuAn :");
        System.out.print("nhap tenDuAn :");
        System.out.print("nhap maDuAn :");
        System.out.print("nhap maDuAn :");
        System.out.print("nhap maDuAn :");
        System.out.print("nhap maDuAn :");
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
