/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DON_KNOW;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class HD {

    public int getContractID() {
        return ContractID;
    }

    public void setContractID(int ContractID) {
        this.ContractID = ContractID;
    }

    public long getSalary() {
        return Salary;
    }

    public void setSalary(long Salary) {
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

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getTypeID() {
        return TypeID;
    }

    public void setTypeID(String TypeID) {
        this.TypeID = TypeID;
    }

    public HD(int ContractID, long Salary, Date DayStar, Date DayEnd, String ID, String FullName, String TypeID) {
        this.ContractID = ContractID;
        this.Salary = Salary;
        this.DayStar = DayStar;
        this.DayEnd = DayEnd;
        this.ID = ID;
        this.FullName = FullName;
        this.TypeID = TypeID;
    }

    public HD() {
    }
     private int ContractID;
    private long Salary;
    private Date DayStar;
    private Date DayEnd;    
    private String ID;
    private String FullName;
    private String TypeID;

   
}
