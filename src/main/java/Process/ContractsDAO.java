/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Process;

import java.sql.*;
import javax.swing.*;
import Data.*;
import static Process.Check.connection;
import static Process.Check.pst;
import static Process.Check.rs;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContractsDAO {

    public static Connection connection = null;
    public static ResultSet rs = null;
    public static PreparedStatement pst = null;

    public static boolean Insert(Contracts contracts) throws Exception {

        String str = "  INSERT INTO dbo.Contracts(Salary,DayStar,DayEnd,ID,TypeID) VALUES (?,?,?,?,?) ";
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {
            pst.setFloat(1, contracts.getSalary());
            pst.setDate(2, new java.sql.Date(contracts.getDayStar().getYear(), contracts.getDayStar().getMonth(), contracts.getDayStar().getDay()));
            pst.setDate(3, new java.sql.Date(contracts.getDayEnd().getYear(), contracts.getDayEnd().getMonth(), contracts.getDayEnd().getDay()));
            pst.setInt(4, contracts.getID());
            pst.setString(5, String.valueOf(contracts.getTypeID()));

            return pst.executeUpdate() > 0;
        }
    }

    public int TypeID(String Name) {
        try {
            String srt = "select * from dbo.TypeContract where NameType =?";
            Connection connection = Connect.getConnection();
            PreparedStatement pst = connection.prepareStatement(srt);
            pst.setString(1, Name);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                return rs.getInt("TypeID");
            }
        } catch (Exception e) {
        }
        return -1;
    }

    public String TypeName(String ID) {
        try {
            String srt = "select * from dbo.TypeContract where TypeID =?";
            Connection connection = Connect.getConnection();
            PreparedStatement pst = connection.prepareStatement(srt);
            pst.setString(1, ID);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                return rs.getString("NameType");
            }
        } catch (Exception e) {
        }
        return null;
    }

    public boolean INSERTHD(float Salary, String daystar, String dayend, int ID, int TypeID) throws Exception {
        String str2 = " INSERT INTO dbo.Contracts(Salary,DayStar,DayEnd,ID,TypeID) VALUES (?,?,?,?,?) ";
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str2);) {

            pst.setFloat(1, Salary);
            pst.setString(2, daystar);
            pst.setString(3, dayend);
            pst.setInt(4, ID);
            pst.setInt(5, TypeID);

            return pst.executeUpdate() > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return false;
    }

    public boolean update(String Salary, String DayStar, String DayEnd, int ID, int TypeID, int contactId) throws Exception {
        String str = "  update dbo.Contracts set Salary =?, DayStar =?, DayEnd =?,ID =?, TypeID=?  where ContractID = ?  ";
        try (Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {
            pst.setInt(6, contactId);
            pst.setString(1, Salary);
            pst.setString(2, DayStar);
            pst.setString(3, DayEnd);
            pst.setInt(4, ID);
            pst.setInt(5, TypeID);

            return pst.executeUpdate() > 0;

        }

    }

    public TypeContract Truy(String Name) {
        String muon = "select * from dbo.TypeContract where NameType = ?";
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(muon);) {
            pst.setString(1, Name);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                TypeContract cv = new TypeContract();
                cv.setTypeID(rs.getInt("TypeID"));
                cv.setTypeName(rs.getString("NameType"));
                return cv;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static List<Contracts> getAllContractses() {
        List<Contracts> ls = new ArrayList<>();
        String str = "   select * from dbo.Contracts";
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Contracts e = new Contracts();

                e.setContractID(rs.getInt("ContractID"));
                e.setSalary(rs.getFloat("Salary"));
                e.setDayStar(rs.getDate("DayStar"));
                e.setDayEnd(rs.getDate("DayEnd"));
                e.setID(rs.getInt("ID"));
                e.setTypeID(rs.getInt("TypeID"));
                ls.add(e);
            }
            return ls;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Contracts> get1Contractses(String Acc) {
        List<Contracts> ls = new ArrayList<>();
        String str = "select c.ContractID,c.Salary,c.DayStar,c.DayEnd,c.ID,c.TypeID from dbo.Contracts AS c,Employees AS e,Acc AS a WHERE c.ID =e.ID AND e.AccID = a.ID AND a.acc = ?";
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {
            pst.setString(1, Acc);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Contracts e = new Contracts();

                // FullName,age,sex,Email,address,phone
                e.setContractID(rs.getInt("ContractID"));
                e.setSalary(rs.getFloat("Salary"));
                e.setDayStar(rs.getDate("DayStar"));
                e.setDayEnd(rs.getDate("DayEnd"));
                e.setID(rs.getInt("ID"));
                e.setTypeID(rs.getInt("TypeID"));
                ls.add(e);
            }
            return ls;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean Delete(int ContractID) throws Exception {

        String str = "  Delete from dbo.Contracts where ContractID = ? ";
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {

            pst.setInt(1, ContractID);
            pst.setInt(1, ContractID);

            return pst.executeUpdate() > 0;
        }
    }

    public Contracts Find(int ContractID) throws Exception {

        String str = "  select * from dbo.Contracts where ContractID = ? ";
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {

            pst.setInt(1, ContractID);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Contracts e = new Contracts();
                e.setID(rs.getInt("ID"));
                e.setSalary(rs.getFloat("Salary"));
                e.setContractID(rs.getInt("ContractID"));
                e.setTypeID(rs.getInt("TypeID"));
                e.setDayStar(rs.getDate("DayStar"));
                e.setDayEnd(rs.getDate("DayEnd"));
                return e;
            }
            return null;
        }
    }

    public Contracts Lammoi(String Acc) throws Exception {

        String str = "  select * from dbo.Contracts where acc = ? ";
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {
            pst.setString(1, Acc);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Contracts e = new Contracts();
                e.setID(rs.getInt("ID"));
                e.setContractID(rs.getInt("ContractID"));
                e.setSalary(rs.getFloat("Salary"));
                e.setTypeID(rs.getInt("TypeID"));
                e.setDayStar(rs.getDate("DayStar"));
                e.setDayEnd(rs.getDate("DayEnd"));
                return e;
            }
            return null;
        }

    }
    public boolean updateByAcc(int ContractID, float Salary, Date DayStar, Date DayEnd, int ID, String TypeID) throws Exception {
        //TXTHOTEN.getText(), ageString, rdbNam.isSelected() ? 1 : 0, TxtAcc.getText(), Txtpass.getText(), txtEmail.getText(), txtaddress.getText(), txtsdt.getText(), e.getAcc()
        String str = "update dbo.Contracts set ContractID =?, DayStar =?, DayEnd =?,ID =?, TypeID=? , Salary =?  where acc = ?";
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {
            pst.setInt(1, ContractID);
            pst.setDate(2, DayStar);
            pst.setDate(3, DayEnd);
            pst.setInt(4, ID);
            pst.setString(5, TypeID);
            pst.setDouble(6, Salary);

            return pst.executeUpdate() > 0;

        }

    }

    public void update(float parseFloat, java.util.Date date, java.util.Date date2, int parseInt, String conType, int parseInt0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
