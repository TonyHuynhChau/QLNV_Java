/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DON_KNOW;
//<editor-fold desc=" import">

import Data.*;
import Main.TTNV;
import Process.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.naming.spi.*;
import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
//</editor-fold>

public class TableEmployees extends AbstractTableModel {

    List<Employees> emplo;

    public final Class[] classes = {Integer.class, String.class, String.class, String.class, Integer.class,
        String.class, String.class, String.class, Integer.class, Integer.class};
    public final String[] dm = {"ID", "Họ Và Tên", "Ngày Sinh", "Tuổi ", "Giới Tính", "Email ", "Địa Chỉ",
        "Số ĐT ", "Phòng Ban(ID)", " Chức Vụ(ID)"};

    public TableEmployees(List<Employees> allEmplotees) {
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
        Employees e = emplo.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return e.getID();
            case 1:
                return e.getFullname();
            case 2:
                return e.getDate();
            case 3:
                return e.getAge();
            case 4:
                if (e.getSex() == 1) {
                    return "Nam";
                } else {
                    return "Nữ";
                }
            case 5:
                return e.getEmail();
            case 6:
                return e.getAddress();
            case 7:
                return e.getPhone();
            case 8:
                if (e.getPhongBan() == 0) {
                    return "null";
                } else {
                    String str = "select * FROM dbo.Deparment where ID = ?";
                    DeparmentDAO dao = new DeparmentDAO();
                    Deparment c = dao.Truy(e.getPhongBan());
                    String cv = c.getDeparment();
                    return cv;
                }
            case 9:
                if (e.getChucVu() == 0) {
                    return "null";
                } else {

                    Check ck = new Check();
                    ChucVu c = ck.Truy(e.getChucVu());
                    String cv = c.getChucvu();
                    return cv;
                }
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Employees em = emplo.get(rowIndex);

        int ID = (int) getValueAt(rowIndex, 0);
        switch (columnIndex) {
            case 1:
                String Name = "UPDATE Employees SET FullName = ? where ID = ?";
                try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(Name);) {
                    pst.setString(1, (String) aValue);
                    pst.setInt(2, ID);
                    pst.executeUpdate();
                    em.setFullname((String) aValue);
                    break;
                } catch (SQLException ex) {
                }
            case 2:
                String Date = "UPDATE Employees SET Ngay_Sinh = ? where ID = ?";
                try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(Date);) {

                    pst.setString(1, (String) aValue);
                    pst.setInt(2, ID);
                    pst.executeUpdate();
                    em.setDate((String) aValue);
                    break;
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            case 3:
                String Age = "UPDATE Employees SET age = ? where ID = ?";
                try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(Age);) {
                    pst.setString(1, (String) aValue);
                    pst.setInt(2, ID);
                    pst.executeUpdate();
                    em.setAge((String) aValue);
                    break;
                } catch (SQLException ex) {
                }

            case 4:
                String GioiTinh = (String) aValue;
                int SexSo;
                if (GioiTinh.equals("Nam")) {
                    SexSo = 1;
                } else {
                    SexSo = 0;
                }
                String sex = "UPDATE Employees SET sex = ? where ID = ?";
                try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(sex);) {

                    pst.setInt(1, SexSo);
                    pst.setInt(2, ID);
                    pst.executeUpdate();
                    em.setSex(SexSo);
                    break;
                } catch (SQLException ex) {
                }

            case 5:
                String Email = "UPDATE Employees SET Email = ? where ID = ?";
                try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(Email);) {
                    pst.setString(1, (String) aValue);
                    pst.setInt(2, ID);
                    pst.executeUpdate();
                    em.setEmail((String) aValue);
                    break;
                } catch (SQLException ex) {
                }

            case 6:
                String address = "UPDATE Employees SET [address] = ? where ID = ?";
                try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(address);) {
                    pst.setString(1, (String) aValue);
                    pst.setInt(2, ID);
                    pst.executeUpdate();
                    em.setAddress((String) aValue);
                    break;
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }

            case 7:
                String phone = "UPDATE Employees SET phone = ? where ID = ?";
                try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(phone);) {
                    pst.setString(1, (String) aValue);
                    pst.setInt(2, ID);
                    pst.executeUpdate();
                    em.setPhone((String) aValue);
                    break;
                } catch (SQLException ex) {
                }

            case 8:
               try {
                DeparmentDAO dAO = new DeparmentDAO();
                Deparment dep = dAO.PhongBan((String) aValue);
                int depID = dep.getID();

                String DepID = "UPDATE Employees SET DepID = ?  where ID = ?";
                try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(DepID);) {
                    pst.setInt(1, depID);
                    pst.setInt(2, ID);
                    pst.executeUpdate();
                    em.setPhongBan(depID);
                    break;
                } catch (SQLException ex) {
                }
            } catch (Exception e) {
            }

            case 9:
            try {
                Check ck = new Check();
                ChucVu c = ck.chucVu((String) aValue);
                int timid = c.getID();
                String ChucvuID = "UPDATE Employees SET ChucvuID = ? where ID = ?";
                try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(ChucvuID);) {
                    pst.setInt(1, timid);
                    pst.setInt(2, ID);
                    pst.executeUpdate();
                    em.setChucVu(timid);
                    break;
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return false;
        }
        return true;
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
