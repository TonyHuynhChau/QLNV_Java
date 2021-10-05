/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Process;

import Data.Connect;
import Data.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ASUS
 */
public class ProjectDAO {
    public Project Truy(int ID) {
        String str = "select * FROM dbo.Project where ID = ?";
        try (Connection connection = Connect.getConnection(); PreparedStatement pst = connection.prepareStatement(str);) {
            pst.setInt(1, ID);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Project cv = new Project();
                cv.setID(rs.getInt("ID"));
                cv.setPrName(rs.getString("prName"));
                return cv;
            }
        } catch (Exception e) {
        }
        return null;
    }
    
     public Project project(String ID) {
        String muon = "select * FROM dbo.Project where prName = ?";
        try (Connection connection = Connect.getConnection(); PreparedStatement pst = connection.prepareStatement(muon);) {
            pst.setString(1, ID);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Project cv = new Project();
                cv.setID(rs.getInt("ID"));
                cv.setPrName(rs.getString("prName"));
                return cv;
            }
        } catch (Exception e) {
        }
        return null;
    }
}
