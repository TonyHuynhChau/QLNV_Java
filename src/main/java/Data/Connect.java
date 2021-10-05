package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Connect {

    public static Connection getConnection() {

        String connectionUrl = "jdbc:sqlserver://DESKTOP-0PC8MT6\\SQLEXPRESS:1433;databaseName=QLNV;user=sa;password=291120";
        try {
            Connection con = DriverManager.getConnection(connectionUrl);
            return con;
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, "Kết Nối Thất Bại");
            System.exit(0);
            return null;
        }

    }
}
