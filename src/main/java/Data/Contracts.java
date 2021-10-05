/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.util.*;

/**
 *
 * @author admink
 */
public class Contracts {    
    
    private int ContractID;
    private float Salary;
    private Date DayStar;
    private Date DayEnd;    
    private int ID;
    private Integer TypeID;

    public int getContractID() {
        return ContractID;
    }

    public void setContractID(int ContractID) {
        this.ContractID = ContractID;
    }

    public float getSalary() {
        return Salary;
    }

    public void setSalary(float Salary) {
        this.Salary = Salary;
    }

    public Date getDayStar() {
        return DayStar;
    }

    public void setDayStar(Date DayStar) {
        this.DayStar = DayStar;
    }

    public Date getDayEnd() {
        return DayEnd;
    }

    public void setDayEnd(Date DayEnd) {
        this.DayEnd = DayEnd;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Integer getTypeID() {
        return TypeID;
    }

    public void setTypeID(Integer TypeID) {
        this.TypeID = TypeID;
    }

    public Contracts(int ContractID, float Salary, Date DayStar, Date DayEnd, int ID, Integer TypeID) {
        this.ContractID = ContractID;
        this.DayStar = DayStar;
        this.DayEnd = DayEnd;
        this.ID = ID;
        this.TypeID = TypeID;
        this.Salary = Salary;
    }

    public Contracts() {
    }
    
    public Object[] toArray(){
        
        return new Object[]{ContractID,Salary,DayStar,DayEnd,ID,TypeID};
        
    }
}
