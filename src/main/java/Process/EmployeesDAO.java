package Process;

import java.sql.*;
import javax.swing.*;
import Data.*;
import static Process.Check.connection;
import static Process.Check.pst;
import static Process.Check.rs;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeesDAO {

    public static Connection connection = null;
    public static ResultSet rs = null;
    public static PreparedStatement pst = null;

    public List<Integer> data = new ArrayList<>();

    public List<Integer> getdata() {

        int s = 21;
        while (s < 55) {
            data.add(s);
            s = s + 1;
        }
        return data;
    }

    public Employees Truy(int ID) {
        String str = "select * FROM dbo.Employees where ID = ?";
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {
            pst.setInt(1, ID);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Employees cv = new Employees();
                cv.setID(rs.getInt("ID"));
                cv.setEmail(rs.getString("Email"));
                cv.setPhone(rs.getString("phone"));
                cv.setFullname(rs.getString("FullName"));
                cv.setChucVu(rs.getInt("ChucVuID"));
                cv.setPhongBan(rs.getInt("DepID"));
                return cv;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Employees> ThemDiemDanhEmployeeses() {
        List<Employees> ls = new ArrayList<>();
        String str = "select * FROM dbo.Employees where TrangThai = 1 ";
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Employees e = new Employees();
                // FullName,age,sex,Email,address,phone
                e.setID(rs.getInt("ID"));
                e.setFullname(rs.getString("FullName"));
                e.setDate(rs.getString("Ngay_Sinh"));
                e.setAge(rs.getString("age"));
                e.setSex(rs.getInt("sex"));
                e.setEmail(rs.getString("Email"));
                e.setAddress(rs.getString("address"));
                e.setPhone(rs.getString("phone"));
                e.setChucVu(rs.getInt("ChucvuID"));
                e.setPhongBan(rs.getInt("DepID"));
                e.setTrangThai(rs.getInt("TrangThai"));
                ls.add(e);
            }
            return ls;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Employees> BaocaoEmployeeses() {
        List<Employees> ls = new ArrayList<>();
        String str = "select * FROM dbo.Employees ";
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Employees e = new Employees();
                // FullName,age,sex,Email,address,phone
                e.setID(rs.getInt("ID"));
                e.setFullname(rs.getString("FullName"));
                e.setDate(rs.getString("Ngay_Sinh"));
                e.setAge(rs.getString("age"));
                e.setSex(rs.getInt("sex"));
                e.setEmail(rs.getString("Email"));
                e.setAddress(rs.getString("address"));
                e.setPhone(rs.getString("phone"));
                e.setChucVu(rs.getInt("ChucvuID"));
                e.setPhongBan(rs.getInt("DepID"));
                e.setTrangThai(rs.getInt("TrangThai"));
                ls.add(e);
            }
            return ls;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
//

    public List<Employees> PhanTrang(long trang) {
        List<Employees> ls = new ArrayList<>();
        String str = "select top 5 * FROM dbo.Employees WHERE ID NOT IN (SELECT TOP " + (trang * 5 - 5) + " ID FROM Employees Where TrangThai = 1) and TrangThai = 1 ";
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Employees e = new Employees();
                // FullName,age,sex,Email,address,phone
                e.setID(rs.getInt("ID"));
                e.setFullname(rs.getString("FullName"));
                e.setDate(rs.getString("Ngay_Sinh"));
                e.setAge(rs.getString("age"));
                e.setSex(rs.getInt("sex"));
                e.setEmail(rs.getString("Email"));
                e.setAddress(rs.getString("address"));
                e.setPhone(rs.getString("phone"));
                e.setChucVu(rs.getInt("ChucvuID"));
                e.setPhongBan(rs.getInt("DepID"));
                ls.add(e);
            }
            return ls;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
//

    public List<Employees> getEmploteesID(String idString, long trang) {
        List<Employees> ls = new ArrayList<>();
        String str = "select top 5 * FROM dbo.Employees WHERE TrangThai = 1 and ID NOT IN (SELECT TOP " + (trang * 5 - 5) + " ID FROM Employees) and ID like '%" + idString + "%' OR FullName NOT IN (SELECT TOP " + (trang * 5 - 5) + " FullName FROM Employees) and  FullName LIKE N'%" + idString + "%'";
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Employees e = new Employees();
                // FullName,age,sex,Email,address,phone
                e.setID(rs.getInt("ID"));
                e.setFullname(rs.getString("FullName"));
                e.setDate(rs.getString("Ngay_Sinh"));
                e.setAge(rs.getString("age"));
                e.setSex(rs.getInt("sex"));
                e.setEmail(rs.getString("Email"));
                e.setAddress(rs.getString("address"));
                e.setPhone(rs.getString("phone"));
                e.setChucVu(rs.getInt("ChucvuID"));
                e.setPhongBan(rs.getInt("DepID"));
                ls.add(e);
            }
            return ls;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return null;
    }

    public static boolean Delete(int ID) throws SQLException {
        AccDAO dao = new AccDAO();
        dao.Delete(ID);
        String str = "UPDATE Employees SET TrangThai = 0 WHERE ID = ?";
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {
            pst.setInt(1, ID);
            return pst.executeUpdate() > 0;
        }
    }

    public Employees Find(int ID) throws Exception {

        String str = "select * from dbo.Employees where ID = ? ";
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {

            pst.setInt(1, ID);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Employees e = new Employees();
                e.setFullname(rs.getString("FullName"));
                e.setAge(rs.getString("age"));
                e.setSex(rs.getInt("sex"));
                e.setEmail(rs.getString("Email"));
                e.setAddress(rs.getString("address"));
                e.setPhone(rs.getString("phone"));
                return e;
            }
            return null;
        }

    }

    public Employees GetTTNV(String user) throws Exception {

        String str = "select e.ID,e.FullName,e.Ngay_Sinh,e.age,e.sex,e.Email,e.[address],e.phone,e.DepID,e.ChucvuID from dbo.Employees AS e ,Acc AS a WHERE e.ID=a.idnv AND a.acc = ? ";
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {

            pst.setString(1, user);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Employees e = new Employees();
                e.setID(rs.getInt("ID"));
                e.setFullname(rs.getString("FullName"));
                e.setDate(rs.getString("Ngay_Sinh"));
                e.setAge(rs.getString("age"));
                e.setSex(rs.getInt("sex"));
                e.setEmail(rs.getString("Email"));
                e.setAddress(rs.getString("address"));
                e.setPhone(rs.getString("phone"));
                e.setChucVu(rs.getInt("ChucvuID"));
                e.setPhongBan(rs.getInt("DepID"));

                return e;
            }
            return null;
        }

    }

    public boolean CapNhatID(String FullName, int ID) throws Exception {
        String str = "update dbo.Employees set AccID =? where FullName = ?";
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {
            pst.setInt(1, ID);
            pst.setString(2, FullName);

            return pst.executeUpdate() > 0;

        }

    }

    public boolean INSERTNV(String str, String FullName, String date, String age, int sex, String Email, String address, String phone, int PhongBanID, int ChucVuID, int trangthai) throws Exception {
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {

            pst.setString(1, FullName);
            pst.setString(2, date);
            pst.setString(3, age);
            pst.setInt(4, sex);
            pst.setString(5, Email);
            pst.setString(6, address);
            pst.setString(7, phone);
            pst.setInt(8, PhongBanID);
            pst.setInt(9, ChucVuID);
            pst.setInt(10, trangthai);
            return pst.executeUpdate() > 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return false;
    }

    public boolean updatebyAcc(String FullName, String date, String age, int sex, String Email, String address, String phone, int DepID, int ChucVuID, int ID) throws Exception {

        String str = "UPDATE Employees SET FullName =?,Ngay_Sinh = ?,age = ?,sex = ?,Email = ?,[address] = ?,phone = ?,DepID=?,ChucvuID = ? where ID =?";
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {

            pst.setString(1, FullName);
            pst.setString(2, date);
            pst.setString(3, age);
            pst.setInt(4, sex);
            pst.setString(5, Email);
            pst.setString(6, address);
            pst.setString(7, phone);
            pst.setInt(8, DepID);
            pst.setInt(9, ChucVuID);
            pst.setInt(10, ID);
            return pst.executeUpdate() > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return pst.executeUpdate() < 0;
    }
}
