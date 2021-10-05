/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DON_KNOW;

import Data.ChucVu;
import Data.ClassDiemDanh;
import Data.Connect;
import Data.Deparment;
import Data.Employees;
import Process.Check;
import Process.DeparmentDAO;
import Process.EmployeesDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ASUS
 */
public class TableDiemDanh extends AbstractTableModel {

    List<ClassDiemDanh> emplo;
    int a;
    public final Class[] classes = {Integer.class, String.class, String.class, String.class, String.class,
        String.class, String.class, String.class};
    public final String[] dm = {"ID", "Họ Và Tên", "Ngày Điểm Danh", "Email ", "phone", "Buổi Sáng ", "Buổi Chiểu",
        "Ghi Chú"};

    public TableDiemDanh(List<ClassDiemDanh> allEmplotees, int a) {
        this.a = a;
        this.emplo = allEmplotees;
    }

    @Override
    public int getRowCount() {
        return emplo.size();
    }

    @Override
    public int getColumnCount() {
        return dm.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ClassDiemDanh e = emplo.get(rowIndex);
        EmployeesDAO dao = new EmployeesDAO();
        Employees employees = dao.Truy(e.getIdnv());
        switch (columnIndex) {
            case 0:
                return e.getIdnv();
            case 1:
                return employees.getFullname();
            case 2:
                return e.getAttendance_date();
            case 3:
                return employees.getEmail();
            case 4:
                return employees.getPhone();
            case 5:
                return e.getFirst_time();
            case 6:
                return e.getSecond_time();
            case 7:
                return e.getNote();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        ClassDiemDanh em = emplo.get(rowIndex);

        int ID = (int) getValueAt(rowIndex, 0);
        switch (columnIndex) {
            case 5:
                String Sang = "UPDATE attendance SET first_time = ? where idnv = ?";
                try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(Sang);) {
                    pst.setString(1, (String) aValue);
                    pst.setInt(2, ID);
                    pst.executeUpdate();
                    em.setFirst_time((String) aValue);
                    break;
                } catch (SQLException ex) {
                }
            case 6:
                String Chieu = "UPDATE attendance SET second_time = ? where idnv = ?";
                try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(Chieu);) {

                    pst.setString(1, (String) aValue);
                    pst.setInt(2, ID);
                    pst.executeUpdate();
                    em.setSecond_time((String) aValue);
                    break;
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            case 7:
                String GhiChu = "UPDATE attendance SET note = ? where idnv = ?";
                try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(GhiChu);) {

                    pst.setString(1, (String) aValue);
                    pst.setInt(2, ID);
                    pst.executeUpdate();
                    em.setNote((String) aValue);
                    break;
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (a == 1) {
            if (columnIndex == 5 || columnIndex == 6 || columnIndex == 7) {
                return true;
            }

        }
        return false;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return classes[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return dm[column];
    }
}
