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
public class Deparment {
    private int ID;
    private String Deparment;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDeparment() {
        return Deparment;
    }

    public void setDeparment(String Deparment) {
        this.Deparment = Deparment;
    }

    public Deparment() {
    }

    public Deparment(int ID, String Deparment) {
        this.ID = ID;
        this.Deparment = Deparment;
    }
    
    
}
