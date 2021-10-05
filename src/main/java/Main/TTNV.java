/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import DON_KNOW.TableEmployees;
import DON_KNOW.BaoCao;
import Data.*;
import Process.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.security.MessageDigest;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.table.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nguye
 */
public class TTNV extends javax.swing.JFrame {

    private String TkString;
    private int a;
    private String MkString;

    public TTNV(String tkString, int type, String mkString) {
        initComponents();
        setLocationRelativeTo(null);
        this.TkString = tkString;
        this.MkString = mkString;
        this.a = type;

        //<editor-fold desc=" Gọi Hàm ">
        designjtable();
        count();
        Trang();
        setTable();
        ComboxAge();
        LoadChucVu();
        LoadPhongBan();
        taobangNV();
        //</editor-fold>
    }

    public TTNV() {
        initComponents();

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
        String str = "SELECT COUNT(*) FROM Employees where TrangThai = 1";
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
    //<editor-fold desc=" Phân Trang Cho Tìm Kiếm ">

    public void TrangFind() {
        if (SoNVFind % 5 == 0) {
            sotrangFind = SoNVFind / 5;
        } else {
            sotrangFind = SoNVFind / 5 + 1;
        }
        TxtTrang.setText("1/" + sotrangFind);
    }

    long SoNVFind, sotrangFind, trangFind = 1;

    public void countFind(String name) {
        String str = "select count(*) FROM dbo.Employees where TrangThai = 1 and ID like '%" + name + "%' OR FullName LIKE N'%" + name + "%'";

        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                SoNVFind = rs.getLong(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//</editor-fold>

    //<editor-fold desc=" Combox-1 ">
    private void LoadPhongBan() {
        String str2 = "select * FROM dbo.Deparment";
        combobox(str2, this.cbxPhongBan, "Depname");
    }

    private void LoadChucVu() {
        String str1 = "select * FROM dbo.chucvu";
        combobox(str1, this.cbxChucVu, "Chucvu");
    }

    private void ComboxAge() {
        EmployeesDAO e = new EmployeesDAO();
        List<Integer> data = e.getdata();
        for (int s : data) {
            cbxAge.addItem(String.valueOf(s));
        }
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

    //</editor-fold>
    //<editor-fold desc=" ComboBox-2 ">
    private int ChucVuID;
    private int PhongBanID;
    private int GioiTinh;
    String cell;

    public int TruyID(String srt, JComboBox cb, String bien) {
        try {
            Connection connection = Connect.getConnection();
            PreparedStatement pst = connection.prepareStatement(srt);

            pst.setString(1, cb.getSelectedItem().toString());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int i = rs.getInt(bien);
                return i;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
        return -1;
    }

    public void combobox(String Str, JComboBox cb, String bien, int i) {
        try {
            Connection connection = Connect.getConnection();
            PreparedStatement pst = connection.prepareStatement(Str);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                if (i == 0) {
                    cb.addItem(rs.getString(bien));
                } else {
                    cb.addItem(rs.getString(bien));
                }

            }
        } catch (Exception e) {
        }

    }

    //</editor-fold>
    //<editor-fold desc="Căn Vị Trí Dữ Liệu Trong TableEmployees ">
    public void CanDuLieu() {
        DefaultTableCellRenderer rigthCellRenderer = new DefaultTableCellRenderer();
        rigthCellRenderer.setHorizontalAlignment(JLabel.LEFT);
        for (int j = 0; j < 10; j++) {
            TBLNVs.getColumnModel().getColumn(j).setCellRenderer(rigthCellRenderer);
        }
        JTableHeader header = TBLNVs.getTableHeader();
        header.setPreferredSize(new Dimension(100, 23));
    }
//</editor-fold>

    //<editor-fold desc=" taobangNV">
    public void taobangNV() {
        String srt = "select * FROM dbo.chucvu ";
        JComboBox combo = new JComboBox();
        combobox(srt, combo, "Chucvu", 0);
        TableColumn tbColumn = TBLNVs.getColumnModel().getColumn(9);
        tbColumn.setCellEditor(new DefaultCellEditor(combo));

        try {
            String srtPhongBan = "select * FROM dbo.Deparment ";
            JComboBox PhongbanComboBox = new JComboBox();
            combobox(srtPhongBan, PhongbanComboBox, "Depname", 0);
            TableColumn PhongBanColumn = TBLNVs.getColumnModel().getColumn(8);
            PhongBanColumn.setCellEditor(new DefaultCellEditor(PhongbanComboBox));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        try {
            JComboBox AccBox = new JComboBox();
            AccBox.addItem("Nam");
            AccBox.addItem("Nữ");
            TableColumn PhongBanColumn = TBLNVs.getColumnModel().getColumn(4);
            PhongBanColumn.setCellEditor(new DefaultCellEditor(AccBox));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }
    //</editor-fold>

    //<editor-fold desc=" Hàm Bla,Bla,......">
    public void designjtable() {
        TBLNVs.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        TBLNVs.getTableHeader().setOpaque(false);
        TBLNVs.getTableHeader().setBackground(new Color(32, 136, 203));
        TBLNVs.getTableHeader().setForeground(new Color(255, 255, 255));
        TBLNVs.setRowHeight(25);
    }

    public int Reset(String str) {
        try {
            Connection connection = Connect.getConnection();
            PreparedStatement pst = connection.prepareStatement(str);
            pst.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return -1;
    }

    public void setTable() {
        EmployeesDAO e = new EmployeesDAO();
        TableEmployees tb = new TableEmployees(e.PhanTrang(trang));

        TBLNVs.setModel(tb);
        taobangNV();
        CanDuLieu();
    }
    //</editor-fold>

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TXTID = new javax.swing.JTextField();
        BTNXOA = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TBLNVs = new javax.swing.JTable();
        btnSearch = new javax.swing.JButton();
        btnLoadDS = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        TxtName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cbxAge = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbxChucVu = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cbxPhongBan = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        TxtEmail = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        TxtAddress = new javax.swing.JTextField();
        txtSDt = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cbxSex = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        JPThemChucVu = new javax.swing.JPanel();
        LbThemChucVu1 = new javax.swing.JLabel();
        JPThemPhongBan = new javax.swing.JPanel();
        LbThemChucVu = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        TxtDate = new com.toedter.calendar.JDateChooser();
        TxtTrang = new javax.swing.JLabel();
        TrangTiepTheo = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        TrangCuoi = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        TrangDau = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        LuiTrang = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/1.jpg"))); // NOI18N
        jLabel1.setText("Quản Lý Nhân Viên");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Search ID :");

        TXTID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TXTIDKeyPressed(evt);
            }
        });

        BTNXOA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BTNXOA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_delete_16.png"))); // NOI18N
        BTNXOA.setText("Xóa");
        BTNXOA.setPreferredSize(new java.awt.Dimension(97, 25));
        BTNXOA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNXOAActionPerformed(evt);
            }
        });

        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_reset_16.png"))); // NOI18N
        btnReset.setText("Reset MK");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        TBLNVs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        TBLNVs.setToolTipText("");
        TBLNVs.setGridColor(new java.awt.Color(255, 255, 255));
        TBLNVs.setIntercellSpacing(new java.awt.Dimension(0, 0));
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
        });
        jScrollPane1.setViewportView(TBLNVs);

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-search-client-24_1.png"))); // NOI18N
        btnSearch.setText("Tìm Kiếm");
        btnSearch.setPreferredSize(new java.awt.Dimension(97, 25));
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnLoadDS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_reset_16.png"))); // NOI18N
        btnLoadDS.setText("Load DS");
        btnLoadDS.setPreferredSize(new java.awt.Dimension(97, 25));
        btnLoadDS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadDSActionPerformed(evt);
            }
        });

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_save_16.png"))); // NOI18N
        btnThem.setText("Thêm Mới");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Họ & Tên :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Tuổi :");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Chức Vụ :");

        cbxChucVu.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbxChucVuPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Phòng Ban :");

        cbxPhongBan.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbxPhongBanPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Email :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Nơi Ở :");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Số Điện Thoại :");

        cbxSex.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Giới Tính :");

        JPThemChucVu.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        JPThemChucVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPThemChucVuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPThemChucVuMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPThemChucVuMousePressed(evt);
            }
        });

        LbThemChucVu1.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        LbThemChucVu1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LbThemChucVu1.setText("+");

        javax.swing.GroupLayout JPThemChucVuLayout = new javax.swing.GroupLayout(JPThemChucVu);
        JPThemChucVu.setLayout(JPThemChucVuLayout);
        JPThemChucVuLayout.setHorizontalGroup(
            JPThemChucVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPThemChucVuLayout.createSequentialGroup()
                .addComponent(LbThemChucVu1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        JPThemChucVuLayout.setVerticalGroup(
            JPThemChucVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPThemChucVuLayout.createSequentialGroup()
                .addComponent(LbThemChucVu1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        JPThemPhongBan.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        JPThemPhongBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPThemPhongBanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPThemPhongBanMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPThemPhongBanMousePressed(evt);
            }
        });

        LbThemChucVu.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        LbThemChucVu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LbThemChucVu.setText("+");

        javax.swing.GroupLayout JPThemPhongBanLayout = new javax.swing.GroupLayout(JPThemPhongBan);
        JPThemPhongBan.setLayout(JPThemPhongBanLayout);
        JPThemPhongBanLayout.setHorizontalGroup(
            JPThemPhongBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
            .addGroup(JPThemPhongBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(JPThemPhongBanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(LbThemChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        JPThemPhongBanLayout.setVerticalGroup(
            JPThemPhongBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(JPThemPhongBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(JPThemPhongBanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(LbThemChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_microsoft_excel_2019_32.png"))); // NOI18N
        jButton1.setText("Xuất Báo Cáo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Ngày Sinh:");

        TxtTrang.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        TxtTrang.setText("jLabel12");

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

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/chevron_right_64px.png"))); // NOI18N

        javax.swing.GroupLayout TrangTiepTheoLayout = new javax.swing.GroupLayout(TrangTiepTheo);
        TrangTiepTheo.setLayout(TrangTiepTheoLayout);
        TrangTiepTheoLayout.setHorizontalGroup(
            TrangTiepTheoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel16)
        );
        TrangTiepTheoLayout.setVerticalGroup(
            TrangTiepTheoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel16)
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

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_fast_forward_64.png"))); // NOI18N

        javax.swing.GroupLayout TrangCuoiLayout = new javax.swing.GroupLayout(TrangCuoi);
        TrangCuoi.setLayout(TrangCuoiLayout);
        TrangCuoiLayout.setHorizontalGroup(
            TrangCuoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15)
        );
        TrangCuoiLayout.setVerticalGroup(
            TrangCuoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_rewind_64_2.png"))); // NOI18N

        javax.swing.GroupLayout TrangDauLayout = new javax.swing.GroupLayout(TrangDau);
        TrangDau.setLayout(TrangDauLayout);
        TrangDauLayout.setHorizontalGroup(
            TrangDauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TrangDauLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel12))
        );
        TrangDauLayout.setVerticalGroup(
            TrangDauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/chevron_left_64px.png"))); // NOI18N

        javax.swing.GroupLayout LuiTrangLayout = new javax.swing.GroupLayout(LuiTrang);
        LuiTrang.setLayout(LuiTrangLayout);
        LuiTrangLayout.setHorizontalGroup(
            LuiTrangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13)
        );
        LuiTrangLayout.setVerticalGroup(
            LuiTrangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(642, 642, 642)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 1610, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel3)
                .addGap(16, 16, 16)
                .addComponent(TXTID, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(137, 137, 137)
                .addComponent(btnLoadDS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(129, 129, 129)
                .addComponent(btnThem)
                .addGap(0, 0, 0)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110)
                .addComponent(BTNXOA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(133, 133, 133)
                .addComponent(btnReset)
                .addGap(63, 63, 63)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1610, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1590, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(TxtName, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(TxtDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(45, 45, 45)
                        .addComponent(TxtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(TxtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(txtSDt, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(29, 29, 29)
                        .addComponent(cbxAge, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(cbxSex, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(cbxChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(JPThemChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addGap(31, 31, 31)
                        .addComponent(cbxPhongBan, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(JPThemPhongBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(562, 562, 562)
                .addComponent(TrangDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(LuiTrang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(TxtTrang)
                .addGap(71, 71, 71)
                .addComponent(TrangTiepTheo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(TrangCuoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(TXTID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnLoadDS, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTNXOA, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(TxtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(cbxAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel11))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(cbxSex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(cbxChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(JPThemChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(cbxPhongBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(JPThemPhongBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(jLabel10))
                            .addComponent(TxtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(TxtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(txtSDt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(TrangTiepTheo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(24, 24, 24)
                            .addComponent(TxtTrang))
                        .addComponent(TrangCuoi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(TrangDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LuiTrang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //<editor-fold desc=" Event">  
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int YN = JOptionPane.showConfirmDialog(this, "Do You Want To Continue", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (YN == JOptionPane.YES_OPTION) {
            Fchinh lo = new Fchinh(a, TkString, MkString);
            lo.setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_formWindowClosing

    private void TBLNVsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TBLNVsMousePressed
        int row = TBLNVs.getSelectedRow();
        if (row >= 0) {
            cell = String.valueOf(TBLNVs.getValueAt(row, 0));
        }

    }//GEN-LAST:event_TBLNVsMousePressed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed

        if (cell == null) {
            EmployeesDAO dao = new EmployeesDAO();
            for (Employees e : dao.BaocaoEmployeeses()) {
                try {
                    String Bam = e.getPhone();
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    md.update(Bam.getBytes());
                    byte[] bytedata = md.digest();

                    StringBuffer NewMK = new StringBuffer();
                    for (int i = 0; i < bytedata.length; i++) {
                        NewMK.append(Integer.toString((bytedata[i] & 0xff) + 0x100, 16).substring(1));
                    }
                    AccDAO accDAO = new AccDAO();
                    for (Acc acc : accDAO.ListAcc()) {
                        if (acc.getIDnv() == e.getID()) {
                            String str = "UPDATE Acc SET pass = '" + NewMK + "' WHERE type = 0 and idnv = " + e.getID();

                            Reset(str);
                        }
                    }
                } catch (Exception ex) {
                }
            }
        } else {
            try {
                EmployeesDAO dao = new EmployeesDAO();
                Employees e = dao.Truy(Integer.valueOf(cell));
                String Bam = e.getPhone();
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(Bam.getBytes());
                byte[] bytedata = md.digest();

                StringBuffer NewMK = new StringBuffer();
                for (int i = 0; i < bytedata.length; i++) {
                    NewMK.append(Integer.toString((bytedata[i] & 0xff) + 0x100, 16).substring(1));
                }
                AccDAO accDAO = new AccDAO();
                String str = "UPDATE Acc SET pass = '" + NewMK + "' WHERE type = 0 and idnv = " + cell;
                Reset(str);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

        }

        JOptionPane.showMessageDialog(null, " Reset Thành Công ");
    }//GEN-LAST:event_btnResetActionPerformed

    public void Xoa() {
        try {
            if (cell.equals(null)) {
                JOptionPane.showMessageDialog(this, "Lỗi");
            } else {
                DKNghiDAO dao = new DKNghiDAO();
                dao.Xoa(Integer.valueOf(cell));

                EmployeesDAO nhap = new EmployeesDAO();
                nhap.Delete(Integer.valueOf(cell));

                JOptionPane.showMessageDialog(this, "Đã Xóa Nhân Viên");
                setTable();
            }

        } catch (Exception e) {
            if (e.getMessage() == "The DELETE statement conflicted with the REFERENCE constraint \"FK__Contracts__ID__22AA2996\". The conflict occurred in database \"TestQLNV\", table \"dbo.Contracts\", column 'ID'") {
                JOptionPane.showMessageDialog(null, "Hợp Đồng Của Nhân Viên Này Chưa Xóa ");
            }
            JOptionPane.showMessageDialog(this, "Error" + e.getMessage());
            e.printStackTrace();
        }
    }
    private void BTNXOAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNXOAActionPerformed
        Xoa();
    }//GEN-LAST:event_BTNXOAActionPerformed
    int SetFind;

    public void find() {
        EmployeesDAO e = new EmployeesDAO();
        TableEmployees tb = new TableEmployees(e.getEmploteesID(TXTID.getText(), trangFind));
        TBLNVs.setModel(tb);
        taobangNV();
        CanDuLieu();
    }
    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        trangFind = 1;
        countFind(TXTID.getText());
        TrangFind();
        find();
        SetFind = -1;
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnLoadDSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadDSActionPerformed
        trang = 1;
        count();
        Trang();
        EmployeesDAO e = new EmployeesDAO();
        TableEmployees tb = new TableEmployees(e.PhanTrang(1));
        SetFind = 0;
        TBLNVs.setModel(tb);
        taobangNV();
        CanDuLieu();
    }//GEN-LAST:event_btnLoadDSActionPerformed

    String NhapNV;
    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        StringBuilder sb = new StringBuilder();
        if (TxtName.getText().equals("")) {
            sb.append("Họ Tên Trống\n");
            TxtName.requestFocus();

        }
        if (TxtAddress.getText().equals("")) {
            sb.append("Địa Chỉ Trống\n");
            TxtAddress.requestFocus();

        }
        if (TxtEmail.getText().equals("")) {
            sb.append("Email Trống\n");
            TxtEmail.requestFocus();

        }
        if (txtSDt.getText().equals("")) {
            sb.append("Số Điện Thoại Trống");
            txtSDt.requestFocus();

        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
            return;
        } else {
            try {
                String ageString = String.valueOf(cbxAge.getSelectedItem());
                // String type = String.valueOf(cbxchucvu.getSelectedItem());
                String relax = "^\\w+[a-z0-9]*@\\w+mail.com$";
                Pattern pattern = Pattern.compile(relax);
                Matcher matcher = pattern.matcher(TxtEmail.getText());
                if (matcher.find()) {
                    EmployeesDAO nhap = new EmployeesDAO();
                    if (ChucVuID == 0) {
                        ChucVuID = 1;
                    }
                    if (PhongBanID == 0) {
                        PhongBanID = 1;
                    }
                    if (cbxSex.getSelectedItem() == "Nam") {
                        GioiTinh = 1;
                    }
                    if (cbxSex.getSelectedItem() == "Nữ") {
                        GioiTinh = 0;
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    NhapNV = "INSERT INTO Employees(FullName,Ngay_Sinh,age,sex,Email,[address],phone,DepID,ChucvuID,TrangThai)VALUES(?,?,?,?,?,?,?,?,?,?)";
                    nhap.INSERTNV(NhapNV, TxtName.getText(), sdf.format(TxtDate.getDate()), ageString, GioiTinh, TxtEmail.getText(), TxtAddress.getText(), txtSDt.getText(), PhongBanID, ChucVuID, 1);
                    JOptionPane.showMessageDialog(this, "Đăng Ký Thành Công\n");
                    lammoi();
                    count();
                    Trang();
                    setTable();
                } else {
                    JOptionPane.showConfirmDialog(this, "Nhập Email Theo Dạng : X+@+X+mail.com\n " + "với X là 1 Chữ Cái Hoặc Số Bất Kỳ");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error" + e.getMessage());
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed
    private int TimID;
    private void cbxChucVuPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbxChucVuPopupMenuWillBecomeInvisible
        String srt = "select * FROM dbo.chucvu WHERE Chucvu = ? ";
        ChucVuID = TruyID(srt, cbxChucVu, "ID");
        if (ChucVuID == -1) {
            JOptionPane.showMessageDialog(rootPane, "Không Truy Được ID Của Chức Vụ Này");
        }
    }//GEN-LAST:event_cbxChucVuPopupMenuWillBecomeInvisible

    private void cbxPhongBanPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbxPhongBanPopupMenuWillBecomeInvisible
        String srt = "select * FROM dbo.Deparment WHERE Depname = ? ";
        PhongBanID = TruyID(srt, cbxPhongBan, "ID");
        if (PhongBanID == -1) {
            JOptionPane.showMessageDialog(rootPane, "Không Truy Được ID Của Phòng Ban Này");
        }
    }//GEN-LAST:event_cbxPhongBanPopupMenuWillBecomeInvisible

    private void TBLNVsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TBLNVsKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {

            try {
                String Name = String.valueOf(TBLNVs.getValueAt(TBLNVs.getSelectedRow(), 1));
                int ThongBao = JOptionPane.showConfirmDialog(this, "Xóa Nhân Viên " + Name, "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (JOptionPane.YES_OPTION == ThongBao) {
                    Xoa();
                } else {

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

        }
    }//GEN-LAST:event_TBLNVsKeyPressed

    private void JPThemChucVuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPThemChucVuMouseEntered
        JPThemChucVu.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_JPThemChucVuMouseEntered

    private void JPThemChucVuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPThemChucVuMouseExited
        JPThemChucVu.setBackground(new Color(240, 240, 240));
    }//GEN-LAST:event_JPThemChucVuMouseExited

    private void JPThemPhongBanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPThemPhongBanMouseExited
        JPThemPhongBan.setBackground(new Color(240, 240, 240));
    }//GEN-LAST:event_JPThemPhongBanMouseExited

    private void JPThemPhongBanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPThemPhongBanMouseEntered
        JPThemPhongBan.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_JPThemPhongBanMouseEntered

    private void JPThemChucVuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPThemChucVuMousePressed
        JComboBox cb = new JComboBox();
        JTextField TF = new JTextField();
        String str2 = "select * FROM dbo.Deparment";
        combobox(str2, cb, "Depname");
        int Option = JOptionPane.showConfirmDialog(this, TF, " Chức Vụ Mới ", JOptionPane.DEFAULT_OPTION);
        if (Option == JOptionPane.OK_OPTION) {
            String ChucVuMoi = TF.getText();
            int input = JOptionPane.showConfirmDialog(this, cb, "Thuộc Phòng Ban", JOptionPane.DEFAULT_OPTION);
            if (input == JOptionPane.OK_OPTION) {
                String PhongBan = (String) cb.getSelectedItem();
                DeparmentDAO deparmentDAO = new DeparmentDAO();
                Deparment deparment = deparmentDAO.PhongBan(PhongBan);
                int TimDuoc = deparment.getID();
                ChucVuDAO chucVuDAO = new ChucVuDAO();
                chucVuDAO.INSERT(ChucVuMoi, TimDuoc);
                cbxChucVu.addItem(ChucVuMoi);
            }
        }


    }//GEN-LAST:event_JPThemChucVuMousePressed

    private void JPThemPhongBanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPThemPhongBanMousePressed
        JTextField TF = new JTextField();
        int Option = JOptionPane.showConfirmDialog(this, TF, " Phòng Ban Mới ", JOptionPane.DEFAULT_OPTION);
        if (Option == JOptionPane.OK_OPTION) {
            String PhongBanString = TF.getText();
            DeparmentDAO pAO = new DeparmentDAO();
            pAO.INSERT(PhongBanString);
            cbxPhongBan.addItem(PhongBanString);
        }
    }//GEN-LAST:event_JPThemPhongBanMousePressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(this);
        File f = fileChooser.getSelectedFile();
        String filename = f.getAbsolutePath();

        BaoCao xbc = new BaoCao();
        xbc.XuatBaoCao(filename + ".xlsx");

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(this);
        File file = fileChooser.getSelectedFile();
        String filename = file.getAbsolutePath();

        BaoCao baoCao = new BaoCao();
        baoCao.Them_Bao_Cao_Vao_Jtable(filename);

        EmployeesDAO e = new EmployeesDAO();
        trang = 1;
        count();
        Trang();
        TableEmployees tb = new TableEmployees(e.PhanTrang(1));
        TBLNVs.setModel(tb);
        taobangNV();
        CanDuLieu();
    }//GEN-LAST:event_jButton2ActionPerformed
    //</editor-fold>

    //<editor-fold desc=" Sự Kiện Nút Chuyển Trang">  

    private void LuiTrangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LuiTrangMouseEntered
        LuiTrang.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_LuiTrangMouseEntered

    private void TrangTiepTheoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangTiepTheoMouseEntered
        TrangTiepTheo.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_TrangTiepTheoMouseEntered

    private void TrangTiepTheoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangTiepTheoMouseExited
        TrangTiepTheo.setBackground(new Color(240, 240, 240));
    }//GEN-LAST:event_TrangTiepTheoMouseExited

    private void TrangTiepTheoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangTiepTheoMousePressed
        if (SetFind == -1) {
            if (trangFind < sotrangFind) {
                trangFind++;
                find();
                TxtTrang.setText(trangFind + "/" + sotrangFind);
            }
        } else {
            if (trang < sotrang) {
                trang++;
                setTable();
                TxtTrang.setText(trang + "/" + sotrang);
            }
        }

    }//GEN-LAST:event_TrangTiepTheoMousePressed

    private void TrangDauMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangDauMouseEntered
        TrangDau.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_TrangDauMouseEntered

    private void TrangDauMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangDauMouseExited
        TrangDau.setBackground(new Color(240, 240, 240));
    }//GEN-LAST:event_TrangDauMouseExited

    private void TrangCuoiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangCuoiMouseEntered
        TrangCuoi.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_TrangCuoiMouseEntered

    private void TrangCuoiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangCuoiMouseExited
        TrangCuoi.setBackground(new Color(240, 240, 240));
    }//GEN-LAST:event_TrangCuoiMouseExited

    private void TrangCuoiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangCuoiMousePressed
        if (SetFind == -1) {
            trangFind = sotrangFind;
            find();
            TxtTrang.setText(trangFind + "/" + sotrangFind);
        } else {
            trang = sotrang;
            setTable();
            TxtTrang.setText(trang + "/" + sotrang);
        }

    }//GEN-LAST:event_TrangCuoiMousePressed

    private void TrangDauMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangDauMousePressed
        if (SetFind == -1) {
            trangFind = 1;
            find();
            TxtTrang.setText(1 + "/" + sotrangFind);
        } else {
            trang = 1;
            setTable();
            TxtTrang.setText(trang + "/" + sotrang);
        }

    }//GEN-LAST:event_TrangDauMousePressed

    private void LuiTrangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LuiTrangMouseExited
        LuiTrang.setBackground(new Color(240, 240, 240));
    }//GEN-LAST:event_LuiTrangMouseExited

    private void LuiTrangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LuiTrangMousePressed
        if (SetFind == -1) {
            if (trangFind > 1) {
                trangFind--;
                find();
                TxtTrang.setText(trangFind + "/" + sotrangFind);
            }
        } else {
            if (trang > 1) {
                trang--;
                setTable();
                TxtTrang.setText(trang + "/" + sotrang);
            }
        }

    }//GEN-LAST:event_LuiTrangMousePressed

    private void TXTIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTIDKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ActionEvent actionevt = null;
            btnSearchActionPerformed(actionevt);
        }
    }//GEN-LAST:event_TXTIDKeyPressed

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        cell = null;
    }//GEN-LAST:event_jPanel1MousePressed
    //</editor-fold>  

    //<editor-fold desc=" main">
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
            java.util.logging.Logger.getLogger(TTNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TTNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TTNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TTNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TTNV().setVisible(true);
            }
        });
    }
    //</editor-fold>

    //<editor-fold desc=" Variables declaration - do not modify">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNXOA;
    private javax.swing.JPanel JPThemChucVu;
    private javax.swing.JPanel JPThemPhongBan;
    private javax.swing.JLabel LbThemChucVu;
    private javax.swing.JLabel LbThemChucVu1;
    private javax.swing.JPanel LuiTrang;
    private javax.swing.JTable TBLNVs;
    private javax.swing.JTextField TXTID;
    private javax.swing.JPanel TrangCuoi;
    private javax.swing.JPanel TrangDau;
    private javax.swing.JPanel TrangTiepTheo;
    private javax.swing.JTextField TxtAddress;
    private com.toedter.calendar.JDateChooser TxtDate;
    private javax.swing.JTextField TxtEmail;
    private javax.swing.JTextField TxtName;
    private javax.swing.JLabel TxtTrang;
    private javax.swing.JButton btnLoadDS;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnThem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbxAge;
    private javax.swing.JComboBox<String> cbxChucVu;
    private javax.swing.JComboBox<String> cbxPhongBan;
    private javax.swing.JComboBox<String> cbxSex;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField txtSDt;
    // End of variables declaration//GEN-END:variables

    private void lammoi() {
        TXTID.setText("");
        TxtAddress.setText("");
        TxtEmail.setText("");
        TxtName.setText("");
        txtSDt.setText("");
        TxtDate.setDate(null);
        cbxAge.setSelectedIndex(0);
        cbxChucVu.setSelectedIndex(0);
        cbxPhongBan.setSelectedIndex(0);
        cbxSex.setSelectedIndex(0);
    }

}
//</editor-fold>
