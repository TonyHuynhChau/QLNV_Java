/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Process;

import Data.Connect;
import Data.DKNghi;
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
 * @author ASUS
 */
public class DKNghiDAO {

    public List<DKNghi> GetAdmin(String str) {
        List<DKNghi> ls = new ArrayList<>();

        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                DKNghi e = new DKNghi();
                e.setSTT(rs.getInt("ID"));
                e.setIDNV(rs.getInt("IDNV"));
                e.setPhone(rs.getString("Phone"));
                e.setDate(rs.getString("NgaySinh"));
                e.setLoaiNghi(rs.getString("LoaiNghi"));
                e.setThoiHan(rs.getString("ThoiHan"));
                e.setDepID(rs.getString("DepID"));
                e.setNgayNopDon(rs.getString("NgayNop"));
                e.setNgayDuyetDon(rs.getString("NgayDuyet"));
                e.setTrangThai(rs.getInt("TrangThai"));

                ls.add(e);
            }
            return ls;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return null;
    }

    public boolean INSERT(int idnv, String date, String phone, String LoaiNghi, String ThoiHan,String Date, String Dep) {
        String str = "INSERT INTO DKNghi(IDNV,NgaySinh,Phone,LoaiNghi,ThoiHan,NgayNop,DepID,TrangThai)VALUES(?,?,?,?,?,?,?,0)";
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {
            pst.setInt(1, idnv);
            pst.setString(2, date);
            pst.setString(3, phone);
            pst.setString(4, LoaiNghi);
            pst.setString(5, ThoiHan);
            pst.setString(6, Date);
            pst.setString(7, Dep);
            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return false;
    }
    
    
    
    public boolean Update(int ID,String date) {
        String str = "UPDATE DKNghi SET NgayDuyet =?, TrangThai = 1 WHERE ID = " + ID;
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {
            pst.setString(1, date);
            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return false;
    }
    
      public boolean Xoa(int ID) {
        String str = "  DELETE FROM DKNghi WHERE IDNV = " + ID;
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {
            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return false;
    }
}
