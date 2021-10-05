/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Process;

import Data.Connect;
import DON_KNOW.ContractTF;
import DON_KNOW.HD;
import Data.Contracts;
import Data.DKNghi;
import Data.TypeContract;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author admink
 */
public class ContractTFDAO {

    public List<ContractTF> GetAdmin(String str) {
        List<ContractTF> ls = new ArrayList<>();

        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ContractTF e = new ContractTF();
                e.setContractID(rs.getInt("ContractID"));
                e.setID(rs.getString("ID"));
                e.setFullName(rs.getString("FullName"));
                e.setSalary(rs.getFloat("Salary"));
                e.setDayStar(rs.getDate("DayStar"));
                e.setDayEnd(rs.getDate("DayEnd"));
                e.setTypeID(rs.getString("NameType"));

                ls.add(e);
            }
            return ls;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return null;
    }
    
    
    public List<ContractTF> GetHD() {
        List<ContractTF> ls = new ArrayList<>();
        String str = "select * FROM Contracts";
       //   					
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ContractTF e = new ContractTF();
                e.setContractID(rs.getInt("ContractID"));
                e.setID(rs.getString("ID"));
                e.setSalary(rs.getFloat("Salary"));
                e.setDayStar(rs.getDate("DayStar"));
                e.setDayEnd(rs.getDate("DayEnd"));
                e.setTypeID(rs.getString("TypeID"));
                ls.add(e);
            }
            return ls;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<ContractTF> getAllContractses() {
        List<ContractTF> ls = new ArrayList<>();
        String str = "select c.ContractID,c.Salary,c.DayStar,c.DayEnd,e.FullName,c.ID, tp.NameType FROM dbo.Employees AS e ,Contracts AS c , TypeContract AS tp WHERE c.ID = e.ID AND tp.TypeID = c.TypeID and e.TrangThai = 1";
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ContractTF e = new ContractTF();

                e.setContractID(rs.getInt("ContractID"));
                e.setID(rs.getString("ID"));
                e.setFullName(rs.getString("FullName"));
                e.setSalary(rs.getFloat("Salary"));
                e.setDayStar(rs.getDate("DayStar"));
                e.setDayEnd(rs.getDate("DayEnd"));
                e.setTypeID(rs.getString("NameType"));
                ls.add(e);
            }
            return ls;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<ContractTF> get1Contractses(String Acc) {
        List<ContractTF> ls = new ArrayList<>();
        String str = " select c.ContractID,c.Salary,c.DayStar,c.DayEnd,e.FullName,c.ID, tp.NameType FROM dbo.Employees AS e ,Contracts AS c , TypeContract AS tp , Acc as a WHERE c.ID = e.ID and a.idnv = e.ID AND tp.TypeID = c.TypeID and e.TrangThai = 1 and a.acc = ? ";
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {
            pst.setString(1, Acc);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ContractTF e = new ContractTF();

                e.setContractID(rs.getInt("ContractID"));
                e.setID(rs.getString("ID"));
                e.setFullName(rs.getString("FullName"));
                e.setSalary(rs.getFloat("Salary"));
                e.setDayStar(rs.getDate("DayStar"));
                e.setDayEnd(rs.getDate("DayEnd"));
                e.setTypeID(rs.getString("NameType"));
                ls.add(e);
            }
            return ls;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean Insert(ContractTF contracts) throws Exception {

        String str = "  INSERT INTO dbo.Contracts(Salary,DayStar,DayEnd,ID,TypeID) VALUES (?,?,?,?,?) ";
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {
            pst.setFloat(1, contracts.getSalary());
            pst.setDate(2, new java.sql.Date(contracts.getDayStar().getYear(), contracts.getDayStar().getMonth(), contracts.getDayStar().getDay()));
            pst.setDate(3, new java.sql.Date(contracts.getDayEnd().getYear(), contracts.getDayEnd().getMonth(), contracts.getDayEnd().getDay()));
            pst.setString(4, contracts.getID());
            pst.setString(5, String.valueOf(contracts.getTypeID()));

            return pst.executeUpdate() > 0;
        }
    }

    public boolean Delete(int ID) throws Exception {

        String str = "  Delete from dbo.Contracts where ID = ? ";
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {

            pst.setInt(1, ID);

            return pst.executeUpdate() > 0;
        }
    }

    public boolean DeletebyNV(int emplo) throws Exception {

        String str = "  Delete from dbo.Contracts where ID = ? ";
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {

            pst.setInt(1, emplo);

            return pst.executeUpdate() > 0;
        }
    }

    public ContractTF Find(int ContractID) throws Exception {

        String str = "  select * from dbo.Contracts where ContractID = ?  ";
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {

            pst.setInt(1, ContractID);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                ContractTF e = new ContractTF();
                e.setID(rs.getString("ID"));

                e.setSalary(rs.getFloat("Salary"));
                e.setContractID(rs.getInt("ContractID"));
                e.setTypeID(rs.getString("TypeID"));
                e.setDayStar(rs.getDate("DayStar"));
                e.setDayEnd(rs.getDate("DayEnd"));
                return e;
            }
            return null;
        }
    }

    public List<ContractTF> getAllDayOffFind(int EmpID) {
        List<ContractTF> ls = new ArrayList<>();
        String str = " select c.ContractID,c.Salary,c.DayStar,c.DayEnd,e.FullName,c.ID, tp.NameType FROM dbo.Employees AS e ,Contracts AS c , TypeContract AS tp WHERE c.ID = e.ID AND tp.TypeID = c.TypeID and E.TrangThai = 1 AND c.ID like '%" + EmpID + "%'";
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ContractTF e = new ContractTF();
                e.setContractID(rs.getInt("ContractID"));
                e.setID(rs.getString("ID"));
                e.setFullName(rs.getString("FullName"));
                e.setSalary(rs.getFloat("Salary"));
                e.setDayStar(rs.getDate("DayStar"));
                e.setDayEnd(rs.getDate("DayEnd"));
                e.setTypeID(rs.getString("NameType"));
                ls.add(e);
            }
            return ls;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
 public HD FindString(String ContractID) throws Exception {

        String str = "  select * from dbo.Contracts where ContractID = ?  ";
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {

            pst.setString(1, ContractID);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                HD e = new HD();
                e.setID(rs.getString("ID"));

                e.setSalary(rs.getLong("Salary"));
                e.setContractID(rs.getInt("ContractID"));
                e.setTypeID(rs.getString("TypeID"));
                e.setDayStar(rs.getDate("DayStar"));
                e.setDayEnd(rs.getDate("DayEnd"));
                return e;
            }
            return null;
        }
    }
}
