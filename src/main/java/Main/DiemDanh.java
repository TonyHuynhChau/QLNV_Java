/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import DON_KNOW.BaoCao;
import DON_KNOW.TableDiemDanh;
import Data.Acc;
import Data.ClassDiemDanh;
import Data.Connect;
import Data.Employees;
import Process.AccDAO;
import Process.DiemDanhDAO;
import Process.EmployeesDAO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.PopupMenuEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author nguye
 */
public class DiemDanh extends javax.swing.JFrame {

    public DiemDanh() {
    }

    private int a;
    private String tk;
    private String mk;

    public DiemDanh(int A, String TK, String MK) {
        initComponents();
        try {
            this.a = A;
            this.tk = TK;
            this.mk = MK;
            designjtable();

            //
            AccDAO dao = new AccDAO();
            Acc acc = dao.Find(TK);
            int idnv = acc.getIDnv();
            //
            String str1 = "select * FROM dbo.Employees";
            combobox(str1, this.CbxIDNV, "ID");
            CbxIDNV.setSelectedItem(String.valueOf(idnv));

            //
            String str = "select * FROM dbo.Employees Where ID = ?";
            TruyID(str, idnv, "FullName");
            TXTName.setText(FullName);
            //
            Date today = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            ChooseStart.setDate(today);
            if (a == 1) {

                count();
                Trang();
                setTable(1);

            }
            if (a == 0) {
                CbxIDNV.setEnabled(false);
                jButton2.setEnabled(false);
                countFind(sdf.format(today), idnv);
                TrangFind();
                setTableChoNV(1);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
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
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str = "SELECT COUNT(*) FROM attendance where attendance_date ='" + sdf.format(today) + "'";
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
    //<editor-fold desc=" Phân Trang Tìm Kiếm ">
    int checkfrom;

    public void TrangFind() {
        if (SoNV % 5 == 0) {
            sotrang = SoNV / 5;
        } else {
            sotrang = SoNV / 5 + 1;
        }
        TxtTrang.setText("1/" + sotrang);
        trang = 1;
        checkfrom = 0;
    }

    public void countFind(String today, int nv) {
        String str = "SELECT COUNT(*) FROM attendance where attendance_date ='" + today + "' and idnv = " + nv;
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

    public void CanDuLieu() {
        DefaultTableCellRenderer rigthCellRenderer = new DefaultTableCellRenderer();
        rigthCellRenderer.setHorizontalAlignment(JLabel.LEFT);
        for (int j = 0; j < 8; j++) {
            TbDiemDanh.getColumnModel().getColumn(j).setCellRenderer(rigthCellRenderer);
        }
        JTableHeader header = TbDiemDanh.getTableHeader();
        header.setPreferredSize(new Dimension(100, 23));
    }
    //<editor-fold desc=" taobangNV">

    public void taobangNV() {
        JComboBox combo = new JComboBox();
        combo.addItem("Có");
        combo.addItem("Không");
        TableColumn Sang = TbDiemDanh.getColumnModel().getColumn(5);
        TableColumn Chieu = TbDiemDanh.getColumnModel().getColumn(6);
        Sang.setCellEditor(new DefaultCellEditor(combo));
        Chieu.setCellEditor(new DefaultCellEditor(combo));
    }

    //</editor-fold>
    public void setTable(long Trang) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String Start = sdf.format(ChooseStart.getDate());
        DiemDanhDAO e = new DiemDanhDAO();
        TableDiemDanh tb = new TableDiemDanh(e.getDiemDanh(Start, Trang), a);

        TbDiemDanh.setModel(tb);
        taobangNV();
        CanDuLieu();
    }

    public void setTableChoNV(long Trang) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String Start = sdf.format(ChooseStart.getDate());
        DiemDanhDAO e = new DiemDanhDAO();
        TableDiemDanh tb = new TableDiemDanh(e.FindDiemDanh(Trang, Start, Integer.valueOf(CbxIDNV.getSelectedItem().toString())), a);

        TbDiemDanh.setModel(tb);
        taobangNV();
        CanDuLieu();
    }

    public void designjtable() {
        TbDiemDanh.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        TbDiemDanh.getTableHeader().setOpaque(false);
        TbDiemDanh.getTableHeader().setBackground(new Color(32, 136, 203));
        TbDiemDanh.getTableHeader().setForeground(new Color(255, 255, 255));
        TbDiemDanh.setRowHeight(25);
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
    String FullName;

    public String TruyID(String srt, int id, String bien) {
        try {
            Connection connection = Connect.getConnection();
            PreparedStatement pst = connection.prepareStatement(srt);

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                FullName = rs.getString(bien);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
        return null;
    }

    public void TaoBangChoNV() {
        setTable(trang);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TbDiemDanh = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        ChooseStart = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        CbxIDNV = new javax.swing.JComboBox<>();
        TXTName = new javax.swing.JTextField();
        TxtTrang = new javax.swing.JLabel();
        TrangDauCD = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        LuiCD = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        TrangCuoiCD = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        NeXtCD = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        BtnBaoCao = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-attendance-48.png"))); // NOI18N
        jLabel1.setText("Điểm Danh Nhân Viên");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Nhân Viên :");

        TbDiemDanh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Nhân Viên", "Họ Tên", "Ngày", "Email", "Phone", "Buổi Sáng", "Buổi Chiều", "Ghỉ Chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TbDiemDanh.setFocusable(false);
        TbDiemDanh.setIntercellSpacing(new java.awt.Dimension(0, 0));
        TbDiemDanh.setOpaque(false);
        TbDiemDanh.setRowHeight(25);
        TbDiemDanh.setSelectionBackground(new java.awt.Color(237, 57, 95));
        TbDiemDanh.setShowVerticalLines(false);
        TbDiemDanh.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(TbDiemDanh);

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_reset_32.png"))); // NOI18N
        jButton2.setText("Load");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(255, 51, 51));
        jLabel2.setText("Từ Ngày");

        CbxIDNV.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                CbxIDNVPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

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

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_rewind_32.png"))); // NOI18N

        javax.swing.GroupLayout TrangDauCDLayout = new javax.swing.GroupLayout(TrangDauCD);
        TrangDauCD.setLayout(TrangDauCDLayout);
        TrangDauCDLayout.setHorizontalGroup(
            TrangDauCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel22)
        );
        TrangDauCDLayout.setVerticalGroup(
            TrangDauCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TrangDauCDLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel22))
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

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_chevron_left_32.png"))); // NOI18N

        javax.swing.GroupLayout LuiCDLayout = new javax.swing.GroupLayout(LuiCD);
        LuiCD.setLayout(LuiCDLayout);
        LuiCDLayout.setHorizontalGroup(
            LuiCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel21)
        );
        LuiCDLayout.setVerticalGroup(
            LuiCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LuiCDLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel21))
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

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_fast_forward_32.png"))); // NOI18N

        javax.swing.GroupLayout TrangCuoiCDLayout = new javax.swing.GroupLayout(TrangCuoiCD);
        TrangCuoiCD.setLayout(TrangCuoiCDLayout);
        TrangCuoiCDLayout.setHorizontalGroup(
            TrangCuoiCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel20)
        );
        TrangCuoiCDLayout.setVerticalGroup(
            TrangCuoiCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TrangCuoiCDLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel20))
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

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_chevron_right_32.png"))); // NOI18N

        javax.swing.GroupLayout NeXtCDLayout = new javax.swing.GroupLayout(NeXtCD);
        NeXtCD.setLayout(NeXtCDLayout);
        NeXtCDLayout.setHorizontalGroup(
            NeXtCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel19)
        );
        NeXtCDLayout.setVerticalGroup(
            NeXtCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NeXtCDLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel19))
        );

        BtnBaoCao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_microsoft_excel_2019_32.png"))); // NOI18N
        BtnBaoCao.setText("Báo Cáo");
        BtnBaoCao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBaoCaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1560, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(640, 640, 640)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(664, 664, 664)
                        .addComponent(TrangDauCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(LuiCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(TxtTrang)
                        .addGap(18, 18, 18)
                        .addComponent(NeXtCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(TrangCuoiCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ChooseStart, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(106, 106, 106)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CbxIDNV, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TXTName, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnBaoCao, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(198, 198, 198))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnBaoCao, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ChooseStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(CbxIDNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(TXTName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NeXtCD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TrangCuoiCD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LuiCD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TrangDauCD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(TxtTrang)
                        .addGap(9, 9, 9)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        checkfrom = 0;
        count();
        Trang();
        setTable(1);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void CbxIDNVPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_CbxIDNVPopupMenuWillBecomeInvisible
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        countFind(sdf.format(ChooseStart.getDate()), Integer.valueOf(CbxIDNV.getSelectedItem().toString()));
        TrangFind();
        setTableChoNV(trang);
        //
        String str = "select * FROM dbo.Employees Where ID = ?";
        TruyID(str, Integer.valueOf(CbxIDNV.getSelectedItem().toString()), "FullName");
        TXTName.setText(FullName);
    }//GEN-LAST:event_CbxIDNVPopupMenuWillBecomeInvisible

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Fchinh f = new Fchinh(a, tk, mk);
        f.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_formWindowClosing

    private void TrangDauCDMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangDauCDMouseEntered
        TrangDauCD.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_TrangDauCDMouseEntered

    private void TrangDauCDMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangDauCDMouseExited
        TrangDauCD.setBackground(new Color(240, 240, 240));
    }//GEN-LAST:event_TrangDauCDMouseExited

    private void TrangDauCDMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangDauCDMousePressed
        if (checkfrom == 0) {
            trang = 1;
            setTable(trang);
            TxtTrang.setText(1 + "/" + sotrang);
        }


    }//GEN-LAST:event_TrangDauCDMousePressed

    private void LuiCDMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LuiCDMouseEntered
        LuiCD.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_LuiCDMouseEntered

    private void LuiCDMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LuiCDMouseExited
        LuiCD.setBackground(new Color(240, 240, 240));
    }//GEN-LAST:event_LuiCDMouseExited

    private void LuiCDMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LuiCDMousePressed
        if (checkfrom == 0) {
            if (trang > 1) {
                trang--;
                setTable(trang);
                TxtTrang.setText(trang + "/" + sotrang);
            }
        }


    }//GEN-LAST:event_LuiCDMousePressed

    private void TrangCuoiCDMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangCuoiCDMouseEntered
        TrangCuoiCD.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_TrangCuoiCDMouseEntered

    private void TrangCuoiCDMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangCuoiCDMouseExited
        TrangCuoiCD.setBackground(new Color(240, 240, 240));
    }//GEN-LAST:event_TrangCuoiCDMouseExited

    private void TrangCuoiCDMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangCuoiCDMousePressed
        if (checkfrom == 0) {
            trang = sotrang;
            setTable(trang);
            TxtTrang.setText(trang + "/" + sotrang);
        }

    }//GEN-LAST:event_TrangCuoiCDMousePressed

    private void NeXtCDMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NeXtCDMouseEntered
        NeXtCD.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_NeXtCDMouseEntered

    private void NeXtCDMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NeXtCDMouseExited
        NeXtCD.setBackground(new Color(240, 240, 240));
    }//GEN-LAST:event_NeXtCDMouseExited

    private void NeXtCDMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NeXtCDMousePressed
        if (checkfrom == 0) {
            if (trang < sotrang) {
                trang++;
                setTable(trang);
                TxtTrang.setText(trang + "/" + sotrang);
            }
        }

    }//GEN-LAST:event_NeXtCDMousePressed

    private void BtnBaoCaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBaoCaoActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(this);
        File f = fileChooser.getSelectedFile();
        String filename = f.getAbsolutePath();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        BaoCao xbc = new BaoCao();
        xbc.BaoCaoDiemDanh(filename + ".xlsx", sdf.format(ChooseStart.getDate()));
    }//GEN-LAST:event_BtnBaoCaoActionPerformed

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
            java.util.logging.Logger.getLogger(DiemDanh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DiemDanh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DiemDanh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DiemDanh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DiemDanh().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BaoCao;
    private javax.swing.JButton BtnBaoCao;
    private javax.swing.JComboBox<String> CbxIDNV;
    private com.toedter.calendar.JDateChooser ChooseStart;
    private javax.swing.JPanel LuiCD;
    private javax.swing.JPanel NeXtCD;
    private javax.swing.JTextField TXTName;
    private javax.swing.JTable TbDiemDanh;
    private javax.swing.JPanel TrangCuoiCD;
    private javax.swing.JPanel TrangDauCD;
    private javax.swing.JLabel TxtTrang;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
