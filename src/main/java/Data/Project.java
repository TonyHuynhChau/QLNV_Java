/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.util.Date;

public class Project {

    private int ID;
    private String prName;
    private Date Ngay_BD;
    private Date Ngay_KT;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPrName() {
        return prName;
    }

    public void setPrName(String prName) {
        this.prName = prName;
    }

    public Date getNgay_BD() {
        return Ngay_BD;
    }

    public void setNgay_BD(Date Ngay_BD) {
        this.Ngay_BD = Ngay_BD;
    }

    public Date getNgay_KT() {
        return Ngay_KT;
    }

    public void setNgay_KT(Date Ngay_KT) {
        this.Ngay_KT = Ngay_KT;
    }

    public Project() {
    }

    public Project(int ID, String prName, Date Ngay_BD, Date Ngay_KT) {
        this.ID = ID;
        this.prName = prName;
        this.Ngay_BD = Ngay_BD;
        this.Ngay_KT = Ngay_KT;
    }
    
    
}
