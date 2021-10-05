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
public class Acc {

    private int ID;
    private int IDnv;
    private String Acc;
    private String FullName;
    private String Pass;
    private int type;

    public Acc() {
    }

    public Acc(int ID, int IDnv, String Acc, String FullName, String Pass, int type) {
        this.ID = ID;
        this.IDnv = IDnv;
        this.Acc = Acc;
        this.FullName = FullName;
        this.Pass = Pass;
        this.type = type;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDnv() {
        return IDnv;
    }

    public void setIDnv(int IDnv) {
        this.IDnv = IDnv;
    }

    public String getAcc() {
        return Acc;
    }

    public void setAcc(String Acc) {
        this.Acc = Acc;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    
}
