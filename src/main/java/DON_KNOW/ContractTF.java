/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DON_KNOW;

import java.util.*;
/**
 *
 * @author admink
 */
public class ContractTF {
    private int ContractID;
    private float Salary;
    private Date DayStar;
    private Date DayEnd;    
    private String ID;
    private String FullName;
    private String TypeID;

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }
    

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

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTypeID() {
        return TypeID;
    }

    public void setTypeID(String TypeID) {
        this.TypeID = TypeID;
    }

    public ContractTF(int ContractID, float Salary, Date DayStar, Date DayEnd, String ID, String FullName, String TypeID) {
        this.ContractID = ContractID;
        this.DayStar = DayStar;
        this.DayEnd = DayEnd;
        this.ID = ID;
        this.TypeID = TypeID;
        this.FullName = FullName;
        this.Salary = Salary;
    }

    public ContractTF() {
    }
    
    public Object[] toArray(){
        
        return new Object[]{ContractID,Salary,DayStar,DayEnd,ID,TypeID};
        
    }
}
