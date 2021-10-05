/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Process;

import Data.ClassDiemDanh;
import Data.Connect;
import Main.DiemDanh;
import static Process.EmployeesDAO.connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class DiemDanhDAO {

    public ClassDiemDanh Find(int id, String date) throws Exception {

        String str = "  select * from dbo.attendance where idnv = ? and attendance_date = ? ";
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {

            pst.setInt(1, id);
            pst.setString(2, date);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                ClassDiemDanh diemDanh = new ClassDiemDanh();
                diemDanh.setIdnv(rs.getInt("idnv"));
                return diemDanh;
            }
            return null;
        }

    }

    public boolean insert(int IDNV, String date) throws Exception {
        String str = "insert into attendance values(?,?,'Không','Không','')";
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {
            pst.setInt(1, IDNV);
            pst.setString(2, date);
            return pst.executeUpdate() > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return false;
    }

    public ClassDiemDanh FindInsert(String date) throws Exception {

        String str = "  select * from dbo.attendance where attendance_date = ? ";
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {

            pst.setString(1, date);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                ClassDiemDanh diemDanh = new ClassDiemDanh();
                diemDanh.setIdnv(rs.getInt("idnv"));
                return diemDanh;
            }
            return null;
        }

    }

    public List<ClassDiemDanh> getDiemDanh(String Start, long trang) {
        List<ClassDiemDanh> ls = new ArrayList<>();
        String str = "select top 5 * FROM dbo.attendance WHERE idnv NOT IN (SELECT TOP " + (trang * 5 - 5) + " ID FROM Employees WHERE TrangThai = 1) and attendance_date = ?";
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {
            pst.setString(1, Start);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ClassDiemDanh e = new ClassDiemDanh();
                e.setIdnv(rs.getInt("idnv"));
                e.setAttendance_date(rs.getString("attendance_date"));
                e.setFirst_time(rs.getString("first_time"));
                e.setSecond_time(rs.getString("second_time"));
                e.setNote(rs.getString("note"));
                ls.add(e);
            }
            return ls;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<ClassDiemDanh> FindDiemDanh(long trang, String Start, int idnv) {
        List<ClassDiemDanh> ls = new ArrayList<>();
        String str = "select top 5 * FROM dbo.attendance WHERE idnv NOT IN (SELECT TOP " + (trang * 5 - 5) + " ID FROM Employees WHERE TrangThai = 1) and attendance_date = '" + Start + "' and idnv =" + idnv;
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ClassDiemDanh e = new ClassDiemDanh();
                e.setIdnv(rs.getInt("idnv"));
                e.setAttendance_date(rs.getString("attendance_date"));
                e.setFirst_time(rs.getString("first_time"));
                e.setSecond_time(rs.getString("second_time"));
                e.setNote(rs.getString("note"));
                ls.add(e);
            }
            return ls;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<ClassDiemDanh> BaoCaoDiemDanhs(String Start) {
        List<ClassDiemDanh> ls = new ArrayList<>();
        String str = "SELECT a.idnv,e.FullName,a.attendance_date,e.Email,e.phone,a.first_time,a.second_time,a.note  FROM attendance AS a ,Employees AS e WHERE a.idnv =e.ID AND a.attendance_date = '" + Start + "'";
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ClassDiemDanh e = new ClassDiemDanh();
                e.setIdnv(rs.getInt("idnv"));
                e.setName(rs.getString("FullName"));
                e.setAttendance_date(rs.getString("attendance_date"));
                e.setEmail(rs.getString("Email"));
                e.setPhone(rs.getString("phone"));
                e.setFirst_time(rs.getString("first_time"));
                e.setSecond_time(rs.getString("second_time"));
                e.setNote(rs.getString("note"));
                ls.add(e);
            }
            return ls;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
