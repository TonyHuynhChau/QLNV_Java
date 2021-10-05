/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import DON_KNOW.BaoCao;
import DON_KNOW.ContractTF;
import DON_KNOW.HD;
import Data.Acc;
import Data.Connect;
import Data.Contracts;
import Data.Employees;
import Data.TypeContract;
import Process.AccDAO;
import Process.Check;
import Process.ContractTFDAO;
import Process.ContractsDAO;
import Process.EmployeesDAO;
import com.sun.source.tree.BreakTree;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nguye
 */
public class HopDong extends javax.swing.JFrame {

    SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY");

    private int a;
    private String tk;
    private String mk;

    public HopDong(int a, String tk, String MK) {
        initComponents();
        this.a = a;
        this.tk = tk;
        this.mk = MK;
        if (a == 1) {
            designjtable();
            String str1 = "select * FROM dbo.TypeContract";
            combobox(str1, this.cbxTypeCon, "NameType");
            String CD = "select count(*) FROM dbo.Contracts";
            count();
            Trang();
            BangNVCD(1);
        }

        if (a == 0) {
            try {
                txtSearch.setEnabled(false);
                btnTim.setEnabled(false);
                btnSua.setEnabled(false);
                btnThem.setEnabled(false);
                btnXoa.setEnabled(false);
                btnBaocao.setEnabled(false);
                AccDAO dao = new AccDAO();
                Acc acc = dao.Find(tk);

                String str = "SELECT COUNT(*) FROM Contracts where ID = " + acc.getIDnv();
                countNV(str);
                TrangNV();

                taobang1NV(1);

                designjtable();
                if (TBLNVs.getRowCount() <= 0) {
                    JOptionPane.showMessageDialog(null, "Nhân Viên '" + tk + "' Hiện Chưa Có Hợp Đồng Với Công Ty \n" + " Mọi Thắc Mắc Xin Liên Hệ Với Giám Đốc ");
                }
            } catch (Exception e) {
            }

        }

    }

    private HopDong() {
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
        String str = "SELECT COUNT(*) FROM Contracts";
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

    //<editor-fold desc=" Phân Trang tìm kiếm">
    public void TrangFind() {
        if (SoNV % 5 == 0) {
            sotrang = SoNV / 5;
        } else {
            sotrang = SoNV / 5 + 1;
        }
        TxtTrang.setText("1/" + sotrang);
    }
//M tìm Kiếm theo j id nhân vien

    public void countFind() {
        String str = "SELECT COUNT(*) FROM Contracts where ID = " + txtSearch.getText();
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

    //<editor-fold desc=" Phân Trang Cho Nhan Vien">
    public void TrangNV() {
        if (SoNV % 5 == 0) {
            sotrang = SoNV / 5;
        } else {
            sotrang = SoNV / 5 + 1;
        }
        TxtTrang.setText("1/" + sotrang);
    }
//M tìm Kiếm theo j id nhân vien

    public void countNV(String str) {

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

    public void FindBangNVCD(long Trang) {
        DefaultTableModel NVCD = (DefaultTableModel) TBLNVs.getModel();
        NVCD.setRowCount(0);
        try {
            ContractTFDAO cdao = new ContractTFDAO();
            String str = "select top 5 c.ContractID,c.Salary,c.DayStar,c.DayEnd,e.FullName,c.ID, tp.NameType FROM Contracts as c, Employees as e, TypeContract AS tp WHERE c.ContractID NOT IN (SELECT TOP " + (Trang * 5 - 5) + "  ContractID FROM Contracts WHERE ID = " + txtSearch.getText() + ") and e.TrangThai =1 and e.ID=c.ID and c.TypeID=tp.TypeID and c.ID= " + txtSearch.getText();

            for (ContractTF i : cdao.GetAdmin(str)) {
                Object[] datarow = new Object[9];
                datarow[0] = i.getID();
                datarow[1] = i.getFullName();
                Locale locale = new Locale("vi", "VN");
                NumberFormat format = NumberFormat.getCurrencyInstance(locale);

                datarow[2] = format.format(i.getSalary());
                datarow[3] = i.getDayStar();
                datarow[4] = i.getDayEnd();
                datarow[5] = i.getTypeID();
                datarow[6] = i.getContractID();
                NVCD.addRow(datarow);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    public void taobang1NV(long Trang) {
        DefaultTableModel NVCD = (DefaultTableModel) TBLNVs.getModel();
        NVCD.setRowCount(0);
        try {
            ContractTFDAO cdao = new ContractTFDAO();
            AccDAO dao = new AccDAO();
            Acc acc = dao.Find(tk);
            String taobang = "select top 5 c.ContractID,c.Salary,c.DayStar,c.DayEnd,e.FullName,c.ID, tp.NameType FROM Contracts as c, Employees as e, TypeContract AS tp WHERE c.ContractID NOT IN (SELECT TOP " + (Trang * 5 - 5) + "  ContractID FROM Contracts WHERE ID = " + acc.getIDnv() + ") and e.TrangThai =1 and e.ID=c.ID and c.TypeID=tp.TypeID and c.ID= " + acc.getIDnv();

            for (ContractTF i : cdao.GetAdmin(taobang)) {
                Object[] datarow = new Object[9];
                datarow[0] = i.getID();
                datarow[1] = i.getFullName();
                Locale locale = new Locale("vi", "VN");
                NumberFormat format = NumberFormat.getCurrencyInstance(locale);

                datarow[2] = format.format(i.getSalary());
                datarow[3] = i.getDayStar();
                datarow[4] = i.getDayEnd();
                datarow[5] = i.getTypeID();
                datarow[6] = i.getContractID();
                NVCD.addRow(datarow);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void BangNVCD(long Trang) {
        DefaultTableModel NVCD = (DefaultTableModel) TBLNVs.getModel();
        NVCD.setRowCount(0);
        try {
            ContractTFDAO cdao = new ContractTFDAO();
            String str = "select top 5 c.ContractID,c.Salary,c.DayStar,c.DayEnd,e.FullName,c.ID, tp.NameType FROM Contracts as c, Employees as e, TypeContract AS tp WHERE c.ContractID NOT IN (SELECT TOP " + (Trang * 5 - 5) + "  ContractID FROM Contracts) and e.TrangThai =1 and e.ID=c.ID and c.TypeID=tp.TypeID";
            //+ "select top 5 * FROM dbo.Contracts WHERE ID NOT IN (SELECT TOP " + (Trang * 5 - 5) + " ID FROM Contracts) and TrangThai = 1"; //AND IDNV = " + idnv + "and NgayNop <= '" + Date2 + "'";
            for (ContractTF i : cdao.GetAdmin(str)) {
                Object[] datarow = new Object[9];
                datarow[0] = i.getID();
                datarow[1] = i.getFullName();
                Locale locale = new Locale("vi", "VN");
                NumberFormat format = NumberFormat.getCurrencyInstance(locale);

                datarow[2] = format.format(i.getSalary());
                datarow[3] = i.getDayStar();
                datarow[4] = i.getDayEnd();
                datarow[5] = i.getTypeID();
                datarow[6] = i.getContractID();
                NVCD.addRow(datarow);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
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

    public void designjtable() {
        TBLNVs.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        TBLNVs.getTableHeader().setOpaque(false);
        TBLNVs.getTableHeader().setBackground(new Color(32, 136, 203));
        TBLNVs.getTableHeader().setForeground(new Color(255, 255, 255));
        TBLNVs.setRowHeight(25);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtFullName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnTim = new javax.swing.JButton();
        LoadDS = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtSalary = new javax.swing.JTextField();
        Lbel = new javax.swing.JLabel();
        txtDayStar = new com.toedter.calendar.JDateChooser();
        txtDayEnd = new com.toedter.calendar.JDateChooser();
        cbxTypeCon = new javax.swing.JComboBox<>();
        txtSearch = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtConID = new javax.swing.JTextField();
        btnBaocao = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TBLNVs = new javax.swing.JTable();
        TxtTrang = new javax.swing.JLabel();
        TrangDauCD = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        LuiCD = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        NeXtCD = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        TrangCuoiCD = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/kisspng-paper-computer-icons-icon-png-contract-5ab11a06d75321.701651691521555974882.png"))); // NOI18N
        jLabel3.setText("Hợp Đồng");

        jSeparator1.setForeground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Mã Nhân Viên:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Họ Tên :");

        txtFullName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFullNameActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Từ Ngày:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Đến Ngày:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Loại Hợp Đồng");

        btnTim.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnTim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-search-client-24.png"))); // NOI18N
        btnTim.setText("Tìm Kiếm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        LoadDS.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        LoadDS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_reset_16.png"))); // NOI18N
        LoadDS.setText("Tải Lại HD");
        LoadDS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoadDSActionPerformed(evt);
            }
        });

        btnThem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_save_16.png"))); // NOI18N
        btnThem.setText("Thêm Mới");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-delete-26.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-car-service-24.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Mã Hợp Đồng:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Mức Lương Cơ Bản:");

        Lbel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtSearchMousePressed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Tìm Kiếm:");

        btnBaocao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_microsoft_excel_2019_32.png"))); // NOI18N
        btnBaocao.setText("Xuất Báo Cáo");
        btnBaocao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBaocaoActionPerformed(evt);
            }
        });

        TBLNVs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Nhân Viên", "Họ Tên", "Lương", "DayStart", "DayEnd", "TypeID", "Mã Hợp Đồng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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
        jScrollPane1.setViewportView(TBLNVs);

        TxtTrang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TxtTrang.setText("jLabel14");

        TrangDauCD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TrangDauCDMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                TrangDauCDMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TrangDauCDMousePressed(evt);
            }
        });

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_rewind_32.png"))); // NOI18N

        javax.swing.GroupLayout TrangDauCDLayout = new javax.swing.GroupLayout(TrangDauCD);
        TrangDauCD.setLayout(TrangDauCDLayout);
        TrangDauCDLayout.setHorizontalGroup(
            TrangDauCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TrangDauCDLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel13))
        );
        TrangDauCDLayout.setVerticalGroup(
            TrangDauCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TrangDauCDLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel13))
        );

        LuiCD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LuiCDMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LuiCDMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                LuiCDMousePressed(evt);
            }
        });

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_chevron_left_32.png"))); // NOI18N

        javax.swing.GroupLayout LuiCDLayout = new javax.swing.GroupLayout(LuiCD);
        LuiCD.setLayout(LuiCDLayout);
        LuiCDLayout.setHorizontalGroup(
            LuiCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LuiCDLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel12))
        );
        LuiCDLayout.setVerticalGroup(
            LuiCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LuiCDLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel12))
        );

        NeXtCD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                NeXtCDMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                NeXtCDMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                NeXtCDMousePressed(evt);
            }
        });

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_chevron_right_32.png"))); // NOI18N

        javax.swing.GroupLayout NeXtCDLayout = new javax.swing.GroupLayout(NeXtCD);
        NeXtCD.setLayout(NeXtCDLayout);
        NeXtCDLayout.setHorizontalGroup(
            NeXtCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NeXtCDLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel11))
        );
        NeXtCDLayout.setVerticalGroup(
            NeXtCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NeXtCDLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel11))
        );

        TrangCuoiCD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TrangCuoiCDMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                TrangCuoiCDMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TrangCuoiCDMousePressed(evt);
            }
        });

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_fast_forward_32.png"))); // NOI18N

        javax.swing.GroupLayout TrangCuoiCDLayout = new javax.swing.GroupLayout(TrangCuoiCD);
        TrangCuoiCD.setLayout(TrangCuoiCDLayout);
        TrangCuoiCDLayout.setHorizontalGroup(
            TrangCuoiCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TrangCuoiCDLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel14))
        );
        TrangCuoiCDLayout.setVerticalGroup(
            TrangCuoiCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TrangCuoiCDLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel14))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(552, 552, 552)
                .addComponent(TrangDauCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(LuiCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addComponent(TxtTrang)
                .addGap(59, 59, 59)
                .addComponent(NeXtCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(TrangCuoiCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1588, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TrangDauCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LuiCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(TxtTrang))
                    .addComponent(NeXtCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TrangCuoiCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(728, 728, 728)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(567, 567, 567))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel8))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel10)))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDayStar, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                            .addComponent(txtSearch)
                            .addComponent(txtConID))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtDayEnd, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                                    .addComponent(txtID)))
                            .addComponent(Lbel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel2))
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFullName, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addComponent(jLabel6)
                                .addGap(49, 49, 49)
                                .addComponent(cbxTypeCon, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnTim)
                                .addGap(18, 18, 18)
                                .addComponent(btnThem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(74, 74, 74)
                                .addComponent(btnBaocao, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LoadDS))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator1))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTim)
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoa)
                            .addComponent(btnSua)
                            .addComponent(LoadDS, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBaocao, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Lbel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(txtConID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtFullName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(cbxTypeCon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel4))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtDayStar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addComponent(txtDayEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtSalary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)))
                .addGap(26, 26, 26)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtFullNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFullNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFullNameActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        StringBuilder sb = new StringBuilder();
        StringBuilder SB = new StringBuilder();
        Date dt = new Date();

        if (txtSalary.getText().equals("")) {
            sb.append("Lương Trống\n");
            txtSalary.requestFocus();

        }
        if (txtDayStar.getDate().equals("")) {
            boolean equals = dt.equals("Ngày Bắt Đầu Trống\n");
            txtDayStar.requestFocus();

        }
        if (txtDayEnd.getDate().equals("")) {
            dt.equals("Ngày Kết Thúc Trống\n");
            txtDayEnd.requestFocus();

        }
        if (txtID.getText().equals("")) {
            sb.append("Mã Nhân Viên Trống");
            txtID.requestFocus();

        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
            return;
        } else {
            try {
                String conType = (String) cbxTypeCon.getSelectedItem();
                ContractsDAO dao = new ContractsDAO();
                TypeContract typeContract = dao.Truy(conType);
                int NameType = typeContract.getTypeID();

                SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd");
                String dateStar = simp.format(txtDayStar.getDate());
                String DateEnd = simp.format(txtDayEnd.getDate());

                if (SB.length() > 0) {
                    JOptionPane.showMessageDialog(this, SB);
                } else {

                    Check ck = new Check();
                    Employees e = ck.CheckTTCN(txtID.getText());
                    if (e != null) {
                        if (e.getTrangThai() == 0) {
                            JOptionPane.showMessageDialog(null, "Nhân Viên Đã Nghỉ Việc");
                            return;
                        }
                    }

                    dao.INSERTHD(Float.valueOf(txtSalary.getText()), dateStar, DateEnd, Integer.valueOf(txtID.getText()), NameType);

                    JOptionPane.showMessageDialog(this, "Hợp Đồng Đã Được Lưu");
                    lammoi();
                    count();
                    Trang();
                    BangNVCD(trang);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error" + e.getMessage());
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void LoadDSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoadDSActionPerformed
        lammoi();
        CheckPhanTrang = 0;
        count();
        Trang();
        BangNVCD(1);
    }//GEN-LAST:event_LoadDSActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        if (txtSearch.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Nhập ID Nhân Viên Để Tìm ");
            return;
        }
        try {
            ContractTFDAO con = new ContractTFDAO();
            int i = Integer.parseInt(txtSearch.getText());
            ContractTF c = con.Find(i);

            if (txtSearch.getText() != null) {

                String CD = "select count(*) FROM dbo.Contracts where ID=" + i;
                countFind();
                TrangFind();
                trang = 1;
                FindBangNVCD(trang);
                CheckPhanTrang = -1;
            } else {
                JOptionPane.showMessageDialog(this, "Không Tìm Thấy Ngày Vắng Của Nhân Viên");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error" + e.getMessage());
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnTimActionPerformed
    int CheckPhanTrang = 0;
    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

    }//GEN-LAST:event_formWindowClosed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        StringBuilder sb = new StringBuilder();

        Date dt = new Date();
        if (txtSalary.getText().equals("")) {
            sb.append("Lương Trống\n");
            txtSalary.requestFocus();

        }
        if (txtDayStar.getDate().equals("")) {
            boolean equals = dt.equals("Ngày Bắt Đầu Trống\n");
            txtDayStar.requestFocus();

        }
        if (txtDayEnd.getDate().equals("")) {
            dt.equals("Ngày Kết Thúc Trống\n");
            txtDayEnd.requestFocus();

        }
        if (txtID.getText().equals("")) {
            sb.append("Mã Nhân Viên Trống");
            txtID.requestFocus();

        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
            return;
        } else {
            try {

                if (sb.length() > 0) {
                    JOptionPane.showMessageDialog(this, sb);
                } else {
                    String conType = String.valueOf(cbxTypeCon.getSelectedItem());
                    ContractsDAO dao = new ContractsDAO();
                    int ID = dao.TypeID(conType);
                    ContractsDAO nhap = new ContractsDAO();

                    SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd");
                    String dateStar = simp.format(txtDayStar.getDate());
                    String DateEnd = simp.format(txtDayEnd.getDate());

                    nhap.update(txtSalary.getText(), dateStar, DateEnd, Integer.parseInt(txtID.getText()), ID, Integer.parseInt(txtConID.getText()));
                    JOptionPane.showMessageDialog(this, "Hợp Đồng Đã Được Sửa");
                    lammoi();
                    count();
                    Trang();
                    BangNVCD(trang);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error " + e.getMessage());
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        try {
            int i = Integer.parseInt(txtID.getText());
            ContractTFDAO nhap = new ContractTFDAO();
            nhap.Delete(i);
            JOptionPane.showMessageDialog(this, "Đã Xóa Nhân Viên");
            lammoi();
            count();
            Trang();
            BangNVCD(trang);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error" + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    public int Reset() {
        String str = "UPDATE Acc SET pass = 'c4ca4238a0b923820dcc509a6f75849b' WHERE type = 0";
        try {
            Connection connection = Connect.getConnection();
            PreparedStatement pst = connection.prepareStatement(str);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, " Reset Thành Công ");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return -1;
    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Fchinh f = new Fchinh(a, tk, mk);
        f.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_formWindowClosing

    private void txtSearchMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSearchMousePressed
        Lbel.setText("Nhập ID Nhân Viên Để Tìm Hợp Đông ");
    }//GEN-LAST:event_txtSearchMousePressed

    private void btnBaocaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBaocaoActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(this);
        File f = fileChooser.getSelectedFile();
        String filename = f.getAbsolutePath();

        BaoCao xbc = new BaoCao();
        xbc.BaoCaoHD(filename + ".xlsx");
    }//GEN-LAST:event_btnBaocaoActionPerformed

    private void TrangDauCDMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangDauCDMouseEntered
        TrangDauCD.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_TrangDauCDMouseEntered

    private void TrangDauCDMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangDauCDMouseExited
        TrangDauCD.setBackground(new Color(240, 240, 240));
    }//GEN-LAST:event_TrangDauCDMouseExited


    private void TrangDauCDMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangDauCDMousePressed
        if (CheckPhanTrang == -1) {
            trang = 1;
            FindBangNVCD(trang);
            TxtTrang.setText(1 + "/" + sotrang);
        } else {
            if (a == 1) {
                trang = 1;
                BangNVCD(trang);
                TxtTrang.setText(1 + "/" + sotrang);
            }
            if (a == 0) {
                JOptionPane.showMessageDialog(null, "Bạn chỉ có thể xem bản thân");
            }
        }

    }//GEN-LAST:event_TrangDauCDMousePressed

    private void LuiCDMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LuiCDMouseEntered
        LuiCD.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_LuiCDMouseEntered

    private void LuiCDMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LuiCDMouseExited
        LuiCD.setBackground(new Color(240, 240, 240));
    }//GEN-LAST:event_LuiCDMouseExited

    private void LuiCDMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LuiCDMousePressed
        if (CheckPhanTrang == -1) {
            if (trang > 1) {
                trang--;
                FindBangNVCD(trang);
                TxtTrang.setText(trang + "/" + sotrang);
            }
        } else {
            if (a == 1) {
                if (trang > 1) {
                    trang--;
                    BangNVCD(trang);
                    TxtTrang.setText(trang + "/" + sotrang);
                }
            }
            if (a == 0) {
                JOptionPane.showMessageDialog(null, "Bạn chỉ có thể xem bản thân");
            }
        }

    }//GEN-LAST:event_LuiCDMousePressed

    private void NeXtCDMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NeXtCDMouseEntered
        NeXtCD.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_NeXtCDMouseEntered

    private void NeXtCDMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NeXtCDMouseExited
        NeXtCD.setBackground(new Color(240, 240, 240));
    }//GEN-LAST:event_NeXtCDMouseExited

    private void NeXtCDMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NeXtCDMousePressed
        if (CheckPhanTrang == -1) {
            if (trang < sotrang) {
                trang++;
                FindBangNVCD(trang);
                TxtTrang.setText(trang + "/" + sotrang);
            }
        } else {
            if (a == 1) {
                if (trang < sotrang) {
                    trang++;
                    BangNVCD(trang);
                    TxtTrang.setText(trang + "/" + sotrang);
                }
            }
            if (a == 0) {
                JOptionPane.showMessageDialog(null, "Bạn chỉ có thể xem bản thân");
            }
        }

    }//GEN-LAST:event_NeXtCDMousePressed
//chay thu di
    private void TrangCuoiCDMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangCuoiCDMouseEntered
        TrangCuoiCD.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_TrangCuoiCDMouseEntered

    private void TrangCuoiCDMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangCuoiCDMouseExited
        TrangCuoiCD.setBackground(new Color(240, 240, 240));
    }//GEN-LAST:event_TrangCuoiCDMouseExited

    private void TrangCuoiCDMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangCuoiCDMousePressed

        if (CheckPhanTrang == -1) {

            trang = sotrang;
            FindBangNVCD(trang);
            TxtTrang.setText(trang + "/" + sotrang);

        } else {
            if (a == 1) {
                trang = sotrang;
                BangNVCD(trang);
                TxtTrang.setText(trang + "/" + sotrang);
            }
            if (a == 0) {
                JOptionPane.showMessageDialog(null, "Bạn chỉ có thể xem bản thân");
            }
        }


    }//GEN-LAST:event_TrangCuoiCDMousePressed

    private void TBLNVsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TBLNVsMousePressed

        try {
            String id = TBLNVs.getValueAt(TBLNVs.getSelectedRow(), 0).toString();
            txtConID.setText(TBLNVs.getValueAt(TBLNVs.getSelectedRow(), 6).toString());
            txtFullName.setText(TBLNVs.getValueAt(TBLNVs.getSelectedRow(), 1).toString());
            txtID.setText(id);

            ContractTFDAO dao = new ContractTFDAO();
            HD con = dao.FindString(TBLNVs.getValueAt(TBLNVs.getSelectedRow(), 6).toString());
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date start;
            Date end = sdf.parse(TBLNVs.getValueAt(TBLNVs.getSelectedRow(), 4).toString());
            start = sdf.parse(TBLNVs.getValueAt(TBLNVs.getSelectedRow(), 3).toString());

            txtDayStar.setDate(start);
            txtDayEnd.setDate(end);

            txtSalary.setText(String.valueOf(con.getSalary()));
            cbxTypeCon.setSelectedItem(TBLNVs.getValueAt(TBLNVs.getSelectedRow(), 5).toString());
        } catch (ParseException ex) {
            Logger.getLogger(HopDong.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(HopDong.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_TBLNVsMousePressed

    private int ProjectID;

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

    public void lammoi() {
        txtDayEnd.setDate(null);
        txtDayStar.setDate(null);
        txtID.setText("");
        txtFullName.setText("");
        cbxTypeCon.setSelectedIndex(0);
        txtSalary.setText("");
    }

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
            java.util.logging.Logger.getLogger(HopDong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HopDong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HopDong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HopDong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HopDong().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Lbel;
    private javax.swing.JButton LoadDS;
    private javax.swing.JPanel LuiCD;
    private javax.swing.JPanel NeXtCD;
    private javax.swing.JTable TBLNVs;
    private javax.swing.JPanel TrangCuoiCD;
    private javax.swing.JPanel TrangDauCD;
    private javax.swing.JLabel TxtTrang;
    private javax.swing.JButton btnBaocao;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbxTypeCon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField txtConID;
    private com.toedter.calendar.JDateChooser txtDayEnd;
    private com.toedter.calendar.JDateChooser txtDayStar;
    private javax.swing.JTextField txtFullName;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtSalary;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
