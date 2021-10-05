/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Data.Acc;
import Data.ClassDieuDong;
import Data.Connect;
import Data.Employees;
import Process.AccDAO;
import Process.DieuDongDAO;
import Process.EmployeesDAO;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author admink
 */
public class DieuDong extends javax.swing.JFrame {

    private int A;
    private String tkString;
    private String mkString;

    public DieuDong(int a, String TK, String MK) {
        initComponents();
        this.A = a;
        this.tkString = TK;
        this.mkString = MK;
        try {
            LoadPhongBan();
            LoadChucVu();
            designjtable();
            String str1 = "select * FROM dbo.Employees where TrangThai = 1";
            combobox(str1, this.CboboxID, "ID");

            changeChucVu();
            changePhongBan();

            Date now = new Date();
            Jdatechoosengayapdung.setDate(now);
            count();
            Trang();
            taobang(1);
            countDate();
            countID();
            TrangID();
            TrangDate();
        } catch (Exception ex) {

        }

    }

//<editor-fold desc=" Phân Trang ">
    public void Trang() {
        if (SoNV % 5 == 0) {
            sotrang = SoNV / 5;
        } else {
            sotrang = SoNV / 5 + 1;
        }
        TxtTrang.setText("1/" + sotrang);
    }

    long SoNV, sotrang, trang = 1;

    public void count() {
        String str = "SELECT COUNT(*) FROM DieuDong";
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                SoNV = rs.getLong(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//</editor-fold>

//<editor-fold desc=" Phân Trang Theo Ngay ">
    public void TrangDate() {
        if (SoNV % 5 == 0) {
            sotrang = SoNV / 5;
        } else {
            sotrang = SoNV / 5 + 1;
        }
        TxtTrang.setText("1/" + sotrang);
    }

   

    public void countDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(jDateChooser1.getDate());
        String str = "SELECT COUNT(*) FROM DieuDong where Ngay_Dieu_Dong = '" + date + "'";
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                SoNV = rs.getLong(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//</editor-fold>

//<editor-fold desc=" Phân Trang Theo Idnv ">
    public void TrangID() {
        if (SoNVID % 5 == 0) {
            sotrangID = SoNVID / 5;
        } else {
            sotrangID = SoNVID / 5 + 1;
        }
        TxtTrang.setText("1/" + sotrangID);
    }

    long SoNVID, sotrangID, trangID = 1;

    public void countID() {
        String str = "SELECT COUNT(*) FROM DieuDong where idnv =" + CboboxID.getSelectedItem();
        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                SoNVID = rs.getLong(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//</editor-fold>

    public String TruyID(String srt, int id, String bien, JComboBox cb) {
        try {
            Connection connection = Connect.getConnection();
            PreparedStatement pst = connection.prepareStatement(srt);

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String i = rs.getString(bien);
                cb.setSelectedItem(i);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
        return null;
    }
    String truyname;
    int phongbantruy;
    int chucvutruy;

    public String TruyName(String srt, String Name, String bien, String bien2, String bien3, JTextField cb) {
        try {
            Connection connection = Connect.getConnection();
            PreparedStatement pst = connection.prepareStatement(srt);

            pst.setString(1, Name);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                truyname = rs.getString(bien);
                cb.setText(truyname);
                phongbantruy = rs.getInt(bien2);
                chucvutruy = rs.getInt(bien3);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
        return null;
    }

    private DieuDong() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void designjtable() {
        TBLNVs.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        TBLNVs.getTableHeader().setOpaque(false);
        TBLNVs.getTableHeader().setBackground(new Color(32, 136, 203));
        TBLNVs.getTableHeader().setForeground(new Color(255, 255, 255));
        TBLNVs.setRowHeight(25);
    }

    private void LoadPhongBan() {
        String str2 = "select * FROM dbo.Deparment";
        combobox(str2, this.cbxPhongCu, "Depname");
        combobox(str2, this.cbxPhongMoi, "Depname");
    }

    private void LoadChucVu() {
        String str1 = "select * FROM dbo.chucvu";
        combobox(str1, this.cbxChucCu, "Chucvu");
        combobox(str1, this.cbxChucMoi, "Chucvu");
    }

    public void combobox(String Str, JComboBox cb, String bien) {

        try {
            Connection connection = Connect.getConnection();
            PreparedStatement pst = connection.prepareStatement(Str);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                cb.addItem(rs.getString(bien));
            }
        } catch (Exception e) {
        }
    }

    public void taobang(long Trang) {
        DefaultTableModel m = (DefaultTableModel) TBLNVs.getModel();
        m.setRowCount(0);
        DieuDongDAO cdao = new DieuDongDAO();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String Start = sdf.format(Jdatechoosengayapdung.getDate());
        String str = "select top 5 * FROM DieuDong WHERE ID NOT IN (SELECT TOP " + (trang * 5 - 5) + " ID FROM DieuDong)";
        for (ClassDieuDong i : cdao.getDiemDanh(str, Trang)) {
            Object[] datarow = new Object[8];
            datarow[0] = i.getID();
            datarow[1] = i.getIdnv();
            datarow[2] = i.getNgay_Dieu_Dong();
            datarow[3] = i.getChucVuCu();
            datarow[4] = i.getPhongBanCu();
            datarow[5] = i.getChucVuMoi();
            datarow[6] = i.getPhongBanmoi();
            datarow[7] = i.getLyDo();
            m.addRow(datarow);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        TxtHoTen = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TBLNVs = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        cbxPhongCu = new javax.swing.JComboBox<>();
        cbxChucCu = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        cbxPhongMoi = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        cbxChucMoi = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        txtLyDo = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        BTNXOA = new javax.swing.JButton();
        btnDieuDong = new javax.swing.JButton();
        Jdatechoosengayapdung = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        CboboxID = new javax.swing.JComboBox<>();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        TrangTiepTheo = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        LuiTrang = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        TrangCuoi = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        TrangDau = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        TxtTrang = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/transfer.png"))); // NOI18N
        jLabel1.setText("Điều Động Nhân Viên");

        TxtHoTen.setEditable(false);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("ID");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Chức Vụ Cũ");

        TBLNVs.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TBLNVs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Mã Nhân Viên", "Ngày Điều Động", "Chức Vụ Cũ", "Phòng Ban Cũ", "Chức Vụ Mới", "Phòng Ban Mới", "Lý Do"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TBLNVs.setFocusable(false);
        TBLNVs.setIntercellSpacing(new java.awt.Dimension(0, 0));
        TBLNVs.setOpaque(false);
        TBLNVs.setRowHeight(25);
        TBLNVs.setSelectionBackground(new java.awt.Color(237, 57, 95));
        TBLNVs.setShowVerticalLines(false);
        TBLNVs.getTableHeader().setReorderingAllowed(false);
        TBLNVs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TBLNVsMousePressed(evt);
            }
        });
        TBLNVs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TBLNVsKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TBLNVsKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(TBLNVs);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Phòng Ban Cũ");

        cbxPhongCu.setEnabled(false);

        cbxChucCu.setEnabled(false);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Phòng Ban Mới");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Chức Vụ Mới");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Ngày Áp Dụng");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Lý Do");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/exchange.png"))); // NOI18N

        BTNXOA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_delete_16.png"))); // NOI18N
        BTNXOA.setText("Xóa");
        BTNXOA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNXOAActionPerformed(evt);
            }
        });

        btnDieuDong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_new_16.png"))); // NOI18N
        btnDieuDong.setText("Điều Động");
        btnDieuDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDieuDongActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 51));
        jLabel4.setText("*");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 51));
        jLabel5.setText("*");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 51));
        jLabel8.setText("*");

        CboboxID.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                CboboxIDPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jDateChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser1PropertyChange(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Tìm Kiếm");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(600, 600, 600)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel11)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(10, 10, 10)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cbxPhongCu, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbxChucCu, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(20, 20, 20)
                                    .addComponent(btnDieuDong, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(260, 260, 260)
                                    .addComponent(jLabel2)
                                    .addGap(258, 258, 258)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel16)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(216, 216, 216)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtLyDo, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbxPhongMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbxChucMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(120, 120, 120)
                                    .addComponent(BTNXOA, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(10, 10, 10)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel8)
                                .addComponent(jLabel4))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1277, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(80, 80, 80)
                                .addComponent(CboboxID, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(TxtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(597, 597, 597)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(Jdatechoosengayapdung, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel5))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(624, 624, 624)
                        .addComponent(jLabel3)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(CboboxID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(Jdatechoosengayapdung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel11)
                        .addGap(23, 23, 23)
                        .addComponent(jLabel6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(cbxPhongCu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbxChucCu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(btnDieuDong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel16)
                        .addGap(23, 23, 23)
                        .addComponent(jLabel12)
                        .addGap(23, 23, 23)
                        .addComponent(jLabel13))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(txtLyDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(cbxPhongMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbxChucMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(BTNXOA, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        TrangTiepTheo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TrangTiepTheoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                TrangTiepTheoMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TrangTiepTheoMousePressed(evt);
            }
        });

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/chevron_right_64px.png"))); // NOI18N

        javax.swing.GroupLayout TrangTiepTheoLayout = new javax.swing.GroupLayout(TrangTiepTheo);
        TrangTiepTheo.setLayout(TrangTiepTheoLayout);
        TrangTiepTheoLayout.setHorizontalGroup(
            TrangTiepTheoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17)
        );
        TrangTiepTheoLayout.setVerticalGroup(
            TrangTiepTheoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17)
        );

        LuiTrang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LuiTrangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LuiTrangMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                LuiTrangMousePressed(evt);
            }
        });

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/chevron_left_64px.png"))); // NOI18N

        javax.swing.GroupLayout LuiTrangLayout = new javax.swing.GroupLayout(LuiTrang);
        LuiTrang.setLayout(LuiTrangLayout);
        LuiTrangLayout.setHorizontalGroup(
            LuiTrangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14)
        );
        LuiTrangLayout.setVerticalGroup(
            LuiTrangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        TrangCuoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TrangCuoiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                TrangCuoiMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TrangCuoiMousePressed(evt);
            }
        });

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_fast_forward_64.png"))); // NOI18N

        javax.swing.GroupLayout TrangCuoiLayout = new javax.swing.GroupLayout(TrangCuoi);
        TrangCuoi.setLayout(TrangCuoiLayout);
        TrangCuoiLayout.setHorizontalGroup(
            TrangCuoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel18)
        );
        TrangCuoiLayout.setVerticalGroup(
            TrangCuoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        TrangDau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TrangDauMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                TrangDauMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TrangDauMousePressed(evt);
            }
        });

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_rewind_64_2.png"))); // NOI18N

        javax.swing.GroupLayout TrangDauLayout = new javax.swing.GroupLayout(TrangDau);
        TrangDau.setLayout(TrangDauLayout);
        TrangDauLayout.setHorizontalGroup(
            TrangDauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TrangDauLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel19))
        );
        TrangDauLayout.setVerticalGroup(
            TrangDauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        TxtTrang.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        TxtTrang.setText("jLabel12");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(420, 420, 420)
                        .addComponent(TrangDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(LuiTrang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(TxtTrang)
                        .addGap(71, 71, 71)
                        .addComponent(TrangTiepTheo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(TrangCuoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(TrangTiepTheo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(24, 24, 24)
                            .addComponent(TxtTrang))
                        .addComponent(TrangCuoi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(TrangDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LuiTrang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BTNXOAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNXOAActionPerformed
        xoa();
        taobang(trang);
    }//GEN-LAST:event_BTNXOAActionPerformed


    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int YN = JOptionPane.showConfirmDialog(this, "Do You Want To Continue", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (YN == JOptionPane.YES_OPTION) {
            Fchinh f = new Fchinh(A, tkString, mkString);
            f.setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_formWindowClosing

    private void btnDieuDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDieuDongActionPerformed

        try {
            
            DieuDongDAO dieuDongDAO = new DieuDongDAO();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(Jdatechoosengayapdung.getDate());
            String getcomboboxString = CboboxID.getSelectedItem().toString();
            int idnv = Integer.valueOf(getcomboboxString);
            dieuDongDAO.DieuDong((String) cbxChucMoi.getSelectedItem(), (String) cbxPhongMoi.getSelectedItem(), idnv, date, txtLyDo.getText());
            count();
            Trang();
            taobang(trang);
            changeChucVu();
            changePhongBan();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }//GEN-LAST:event_btnDieuDongActionPerformed

    private void CboboxIDPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_CboboxIDPopupMenuWillBecomeInvisible
        changeChucVu();
        changePhongBan();

    }//GEN-LAST:event_CboboxIDPopupMenuWillBecomeInvisible
    private String cell;
    private void TBLNVsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TBLNVsMousePressed
        int row = TBLNVs.getSelectedRow();
        if (row >= 0) {
            cell = String.valueOf(TBLNVs.getValueAt(row, 0));

        }
    }//GEN-LAST:event_TBLNVsMousePressed

    private void TBLNVsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TBLNVsKeyReleased

    }//GEN-LAST:event_TBLNVsKeyReleased

    private void TBLNVsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TBLNVsKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            xoa();
        }
    }//GEN-LAST:event_TBLNVsKeyPressed

    private void TrangTiepTheoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangTiepTheoMouseEntered
        TrangTiepTheo.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_TrangTiepTheoMouseEntered

    private void TrangTiepTheoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangTiepTheoMouseExited
        TrangTiepTheo.setBackground(new Color(240, 240, 240));
    }//GEN-LAST:event_TrangTiepTheoMouseExited

    private void TrangTiepTheoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangTiepTheoMousePressed
        if (trang < sotrang) {
            trang++;
            taobang(trang);
            TxtTrang.setText(trang + "/" + sotrang);
        }
    }//GEN-LAST:event_TrangTiepTheoMousePressed

    private void LuiTrangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LuiTrangMouseEntered
        LuiTrang.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_LuiTrangMouseEntered

    private void LuiTrangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LuiTrangMouseExited
        LuiTrang.setBackground(new Color(240, 240, 240));
    }//GEN-LAST:event_LuiTrangMouseExited

    private void LuiTrangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LuiTrangMousePressed
        if (trang > 1) {
            trang--;
            taobang(trang);
            TxtTrang.setText(trang + "/" + sotrang);
        }
    }//GEN-LAST:event_LuiTrangMousePressed

    private void TrangCuoiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangCuoiMouseEntered
        TrangCuoi.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_TrangCuoiMouseEntered

    private void TrangCuoiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangCuoiMouseExited
        TrangCuoi.setBackground(new Color(240, 240, 240));
    }//GEN-LAST:event_TrangCuoiMouseExited

    private void TrangCuoiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangCuoiMousePressed
        trang = sotrang;
        taobang(trang);
        TxtTrang.setText(trang + "/" + sotrang);
    }//GEN-LAST:event_TrangCuoiMousePressed

    private void TrangDauMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangDauMouseEntered
        TrangDau.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_TrangDauMouseEntered

    private void TrangDauMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangDauMouseExited
        TrangDau.setBackground(new Color(240, 240, 240));
    }//GEN-LAST:event_TrangDauMouseExited

    private void TrangDauMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangDauMousePressed
        trang = 1;
        taobang(trang);
        TxtTrang.setText(trang + "/" + sotrang);
    }//GEN-LAST:event_TrangDauMousePressed

    private void jDateChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser1PropertyChange
        if (jDateChooser1.getDate() != null) {
            DieuDongDAO dieuDongDAO = new DieuDongDAO();
            DefaultTableModel m = (DefaultTableModel) TBLNVs.getModel();
            m.setRowCount(0);
            DieuDongDAO cdao = new DieuDongDAO();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(jDateChooser1.getDate());
            
            countDate();
            TrangDate();
            
            String str = "select top 5 * FROM DieuDong WHERE ID NOT IN (SELECT TOP " + (trang * 5 - 5) + " ID FROM DieuDong) and Ngay_Dieu_Dong = '" + date + "'";
            for (ClassDieuDong i : cdao.getDiemDanh(str, trang)) {
                Object[] datarow = new Object[8];
                datarow[0] = i.getID();
                datarow[1] = i.getIdnv();
                datarow[2] = i.getNgay_Dieu_Dong();
                datarow[3] = i.getChucVuCu();
                datarow[4] = i.getPhongBanCu();
                datarow[5] = i.getChucVuMoi();
                datarow[6] = i.getPhongBanmoi();
                datarow[7] = i.getLyDo();
                m.addRow(datarow);
            }
        }
    }//GEN-LAST:event_jDateChooser1PropertyChange

    public void xoa() {
        try {

            DieuDongDAO dieuDongDAO = new DieuDongDAO();
            String str = "   DELETE FROM DieuDong WHERE ID = ?";
            dieuDongDAO.XoaNV(str, Integer.valueOf(cell));
            JOptionPane.showMessageDialog(null, "Đã Xóa Nhân Viên");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    //<editor-fold  desc=" Load ComboBox ">
    public void changePhongBan() {
        String str = "SELECT * FROM Employees WHERE ID = ?";
        TruyName(str, (String) CboboxID.getSelectedItem(), "FullName", "DepID", "ChucVuID", TxtHoTen);

        String strphongban = "SELECT d.Depname FROM Deparment AS d WHERE d.ID = ?";
        TruyID(strphongban, phongbantruy, "Depname", cbxPhongCu);
        TruyID(strphongban, phongbantruy, "Depname", cbxPhongMoi);
    }

    public void changeChucVu() {
        String str = "SELECT * FROM Employees WHERE ID = ?";
        TruyName(str, (String) CboboxID.getSelectedItem(), "FullName", "DepID", "ChucVuID", TxtHoTen);

        String strchucvu = "SELECT c.Chucvu FROM chucvu AS c WHERE c.ID=?";
        TruyID(strchucvu, chucvutruy, "Chucvu", cbxChucCu);
        TruyID(strchucvu, chucvutruy, "Chucvu", cbxChucMoi);

    }

    //</editor-fold>
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DieuDong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DieuDong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DieuDong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DieuDong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DieuDong().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNXOA;
    private javax.swing.JComboBox<String> CboboxID;
    private com.toedter.calendar.JDateChooser Jdatechoosengayapdung;
    private javax.swing.JPanel LuiTrang;
    private javax.swing.JTable TBLNVs;
    private javax.swing.JPanel TrangCuoi;
    private javax.swing.JPanel TrangDau;
    private javax.swing.JPanel TrangTiepTheo;
    private javax.swing.JTextField TxtHoTen;
    private javax.swing.JLabel TxtTrang;
    private javax.swing.JButton btnDieuDong;
    private javax.swing.JComboBox<String> cbxChucCu;
    private javax.swing.JComboBox<String> cbxChucMoi;
    private javax.swing.JComboBox<String> cbxPhongCu;
    private javax.swing.JComboBox<String> cbxPhongMoi;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField txtLyDo;
    // End of variables declaration//GEN-END:variables
}
