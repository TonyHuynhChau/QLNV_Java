/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Process;

import Data.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class ChucVuDAO {

    public boolean INSERT(String FullName, int ID) {
        String str = "INSERT INTO chucvu (Chucvu,PhongBanID) VALUES (?,?)";
        try (Connection connection = Connect.getConnection(); PreparedStatement pst = connection.prepareStatement(str);) {
            pst.setString(1, FullName);
            pst.setInt(2, ID);
            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return false;
    }
}
