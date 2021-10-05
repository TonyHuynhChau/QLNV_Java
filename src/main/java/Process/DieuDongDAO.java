package Process;

import Data.ClassDieuDong;
import Data.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DieuDongDAO {

    public boolean DieuDong(String ChucVuMoi, String PhongBanMoi, int id, String Ngay_Dieu_Dong, String Lydo) throws Exception {
        
        String str = "   EXEC USP_DieuDong ? , ? , ? , ? , ?";
        try (Connection connection = Connect.getConnection(); PreparedStatement pst = connection.prepareStatement(str);) {
            pst.setString(1, ChucVuMoi);
            pst.setString(2, PhongBanMoi);
            pst.setInt(3, id);
            pst.setString(4, Ngay_Dieu_Dong);
            pst.setString(5, Lydo);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Nhân Viên Đã Được Điều Động");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return false;
    }

    public List<ClassDieuDong> getDiemDanh(String str,long trang) {
        List<ClassDieuDong> ls = new ArrayList<>();
       
        try (Connection connection = Connect.getConnection(); PreparedStatement pst = connection.prepareStatement(str);) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ClassDieuDong e = new ClassDieuDong();
                e.setID(rs.getInt("ID"));
                e.setIdnv(rs.getInt("idnv"));
                e.setNgay_Dieu_Dong(rs.getString("Ngay_Dieu_Dong"));
                e.setPhongBanCu(rs.getString("PhongBanCu"));
                e.setPhongBanmoi(rs.getString("PhongBanmoi"));
                e.setChucVuCu(rs.getString("ChucVuCu"));
                e.setChucVuMoi(rs.getString("ChucVuMoi"));
                e.setLyDo(rs.getString("LyDo"));
                ls.add(e);
            }
            return ls;
        } catch (SQLException ex) {

        }
        return null;
    }

    public List<ClassDieuDong> find(int idnv ,String date) {
        List<ClassDieuDong> ls = new ArrayList<>();
        String str = " SELECT *  FROM DieuDong WHERE idnv like '%"+ idnv +"%' AND Ngay_Dieu_Dong = ? ";
        try (Connection connection = Connect.getConnection(); PreparedStatement pst = connection.prepareStatement(str);) {
            pst.setString(1, date);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ClassDieuDong e = new ClassDieuDong();
                e.setID(rs.getInt("ID"));
                e.setIdnv(rs.getInt("idnv"));
                e.setNgay_Dieu_Dong(rs.getString("Ngay_Dieu_Dong"));
                e.setPhongBanCu(rs.getString("PhongBanCu"));
                e.setPhongBanmoi(rs.getString("PhongBanmoi"));
                e.setChucVuCu(rs.getString("ChucVuCu"));
                e.setChucVuMoi(rs.getString("ChucVuMoi"));
                e.setLyDo(rs.getString("LyDo"));
                ls.add(e);
            }
            return ls;
        } catch (SQLException ex) {

        }
        return null;
    }

    public boolean XoaNV(String str, int idnv) throws Exception {

        try (Connection connection = Connect.getConnection(); PreparedStatement pst = connection.prepareStatement(str);) {
            pst.setInt(1, idnv);

            return pst.executeUpdate() > 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return false;
    }
}
