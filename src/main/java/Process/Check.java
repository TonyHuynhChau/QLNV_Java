/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Process;

import java.sql.*;
import javax.swing.*;
import Data.*;
import Main.Fchinh;
import Main.TTCN;

public class Check {

    public static Connection connection = null;
    public static ResultSet rs = null;
    public static PreparedStatement pst = null;

    public static String testConnect() {
        try {
            connection = Connect.getConnection();
            return "Kết Nối Thành Công";
        } catch (Exception e) {
            return "Kết Nối Thất Bại";
        }
    }

    public ChucVu Truy(int ID) {
        String str = "select * FROM dbo.chucvu where ID = ?";
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {
            pst.setInt(1, ID);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                ChucVu cv = new ChucVu();
                cv.setID(rs.getInt("ID"));
                cv.setChucvu(rs.getString("Chucvu"));
                return cv;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public ChucVu chucVu(String ID) {
        String muon = "select * FROM dbo.chucvu where Chucvu = ?";
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(muon);) {
            pst.setString(1, ID);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                ChucVu cv = new ChucVu();
                cv.setID(rs.getInt("ID"));
                cv.setChucvu(rs.getString("Chucvu"));
                return cv;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static ResultSet cLog(String user, String password) {
        String str = "  select * from Acc where acc = ? and pass = ? ";
        try {
            pst = connection.prepareStatement(str);
            pst.setString(1, user);
            pst.setString(2, password);
            return rs = pst.executeQuery();
        } catch (Exception e) {
            return rs = null;
        }
    }

    public Employees CheckTTCN(String user) {
        String str = " SELECT * FROM Employees where ID = ? ";
        try {
            pst = connection.prepareStatement(str);
            pst.setString(1, user);
            rs = pst.executeQuery();
            if (rs.next()) {
                Employees e = new Employees();
                e.setFullname(rs.getString("FullName"));
                e.setTrangThai(rs.getInt("TrangThai"));
                return e;
            } else {
               
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return null;
    }

    public boolean Resigter(int id, String user, String password, String error_1, String error_2) throws SQLException {
        try {
            String str = "  INSERT INTO Acc(idnv,acc,pass)VALUES(?,?,?) ";

            pst = connection.prepareStatement(str);
            pst.setInt(1, id);
            pst.setString(2, user);
            pst.setString(3, password);
            return pst.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean updateAcc(String a, String ahientai) throws Exception {
        String str = "UPDATE Acc SET acc = ? where acc = ?";
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {

            pst.setString(1, a);
            pst.setString(2, ahientai);

            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Đã Thay Đổi Tài Khoản");
                return pst.executeUpdate() > 0;
            } else {
                JOptionPane.showMessageDialog(null, "Lỗi Khi Thay Đổi Tài Khoản");
                return pst.executeUpdate() < 0;
            }
        }
    }
}
