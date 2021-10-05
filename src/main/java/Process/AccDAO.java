/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Process;

import Data.Acc;
import Data.Connect;
import Data.Employees;
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
public class AccDAO {

    public Acc Find(String acc) throws Exception {

        String str = " select * from dbo.Acc where acc = ? ";
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {

            pst.setString(1, acc);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Acc e = new Acc();
                e.setID(rs.getInt("ID"));
                e.setIDnv(rs.getInt("idnv"));
                e.setType(rs.getInt("type"));

                return e;
            }
            return null;
        }

    }

    public Acc Truyidnv(int ID) {
        String str = "select * FROM dbo.Acc where idnv = ?";
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {
            pst.setInt(1, ID);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Acc cv = new Acc();
                cv.setID(rs.getInt("ID"));
                cv.setAcc(rs.getString("acc"));
                return cv;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public boolean insert(int ID, String Acc) {
        String str = "INSERT INTO Acc(idnv,acc,pass,[type])VALUES(?,?,'202cb962ac59075b964b07152d234b70',1)";
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {
            pst.setInt(1, ID);
            pst.setString(2, Acc);
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return false;
    }

    public List<Acc> ListAcc() {
        String str = "select * FROM dbo.Acc ";
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {
            List<Acc> ls = new ArrayList<>();
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Acc cv = new Acc();
                cv.setID(rs.getInt("ID"));
                cv.setAcc(rs.getString("acc"));
                cv.setIDnv(rs.getInt("idnv"));
                cv.setType(rs.getInt("type"));
                ls.add(cv);
            }
            return ls;
        } catch (Exception e) {
        }
        return null;
    }

    public boolean Delete(int id) throws SQLException {
        String str = " DELETE FROM Acc WHERE idnv = ? ";
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {
            pst.setInt(1, id);
            return pst.executeUpdate() > 0;
        }
    }

    public Acc Truy(int ID) {
        String str = "select * FROM dbo.Acc where ID = ?";
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {
            pst.setInt(1, ID);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Acc cv = new Acc();
                cv.setID(rs.getInt("ID"));
                cv.setAcc(rs.getString("acc"));
                return cv;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public Employees Truyemplo(int ID) {
        String str = "select * FROM dbo.Employees where ID = ?";
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {
            pst.setInt(1, ID);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Employees cv = new Employees();
                cv.setID(rs.getInt("ID"));
                cv.setFullname(rs.getString("FullName"));
                return cv;
            }
        } catch (Exception e) {
        }
        return null;
    }
}
