/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Process;

import Data.Connect;
import Data.Deparment;
import static Process.EmployeesDAO.connection;
import static Process.EmployeesDAO.pst;
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
public class DeparmentDAO {

    public Deparment Truy(int ID) {
        String str = "select * FROM dbo.Deparment where ID = ?";
        try (Connection connection = Connect.getConnection(); PreparedStatement pst = connection.prepareStatement(str);) {
            pst.setInt(1, ID);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Deparment cv = new Deparment();
                cv.setID(rs.getInt("ID"));
                cv.setDeparment(rs.getString("Depname"));
                return cv;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public Deparment PhongBan(String ID) {
        String muon = "select * FROM dbo.Deparment where Depname = ?";
        try (Connection connection = Connect.getConnection(); PreparedStatement pst = connection.prepareStatement(muon);) {
            pst.setString(1, ID);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Deparment cv = new Deparment();
                cv.setID(rs.getInt("ID"));
                cv.setDeparment(rs.getString("Depname"));
                return cv;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public boolean INSERT(String FullName) {
        String str = "INSERT INTO Deparment (Depname) VALUES (?)";
        try (Connection connection = Connect.getConnection(); PreparedStatement pst = connection.prepareStatement(str);) {
            pst.setString(1, FullName);
            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return false;
    }
}
