/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *
 * @author ASUS
 */
public class ChucVu {
    private int ID;
    private String Chucvu;
    private  int PhongBanID;

    public ChucVu(int ID, String Chucvu, int PhongBanID) {
        this.ID = ID;
        this.Chucvu = Chucvu;
        this.PhongBanID = PhongBanID;
    }

    public ChucVu() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getChucvu() {
        return Chucvu;
    }

    public void setChucvu(String Chucvu) {
        this.Chucvu = Chucvu;
    }

    public int getPhongBanID() {
        return PhongBanID;
    }

    public void setPhongBanID(int PhongBanID) {
        this.PhongBanID = PhongBanID;
    }
    
    
}
