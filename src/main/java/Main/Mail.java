/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Data.Connect;
import Data.Employees;
import Process.EmployeesDAO;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class Mail extends javax.swing.JFrame {

    /**
     * Creates new form Mail
     */
    public Mail() {
    }
    private int a;
    private String tk;
    private String Mk;

    Mail(int a, String TkString, String MkString) {
        initComponents();
        this.a = a;
        this.tk = TkString;
        this.Mk = MkString;
        setLocationRelativeTo(null);
        count();
        Trang();
        taobangNV();
        designjtable();
    }

    public void designjtable() {
        tbNVS.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tbNVS.getTableHeader().setOpaque(false);
        tbNVS.getTableHeader().setBackground(new Color(32, 136, 203));
        tbNVS.getTableHeader().setForeground(new Color(255, 255, 255));
        tbNVS.setRowHeight(25);
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
        String str = "SELECT COUNT(*) FROM Employees";
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

    public void taobangNV() {
        DefaultTableModel m = (DefaultTableModel) tbNVS.getModel();
        m.setRowCount(0);
        EmployeesDAO e = new EmployeesDAO();
        for (Employees i : e.PhanTrang(trang)) {
            Object[] datarow = new Object[7];
            datarow[0] = i.getID();
            datarow[1] = i.getFullname();
            datarow[2] = i.getAge();
            if (i.getSex() == 1) {
                datarow[3] = "Nam";
            } else {
                datarow[3] = "Nữ";
            }
            datarow[4] = i.getEmail();
            datarow[5] = i.getAddress();
            datarow[6] = i.getPhone();
            m.addRow(datarow);

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        TxtVanBan = new javax.swing.JTextArea();
        btnsend = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtFrom = new javax.swing.JTextField();
        TxtPass = new javax.swing.JPasswordField();
        TxtTo = new javax.swing.JTextField();
        TxtCC = new javax.swing.JTextField();
        TxtSubject = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbNVS = new javax.swing.JTable();
        TxtTrang = new javax.swing.JLabel();
        TrangTiepTheo = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        TrangCuoi = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        TrangDau = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        LuiTrang = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jTextField1.setText("jTextField1");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        TxtVanBan.setColumns(20);
        TxtVanBan.setRows(5);
        TxtVanBan.setText("Test Nhá");
        jScrollPane1.setViewportView(TxtVanBan);

        btnsend.setText("Send");
        btnsend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsendActionPerformed(evt);
            }
        });

        jLabel1.setText("From");

        jLabel2.setText("Password");

        jLabel3.setText("To");

        jLabel4.setText("CC");

        jLabel5.setText("Subject");

        jLabel6.setText("Content");

        txtFrom.setText("nkiezet@gmail.com");

        TxtPass.setText("Maimaiyeuem2911");

        TxtSubject.setText("CHao");

        tbNVS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã NV", "Họ Tên", "Tuổi", "Giới Tính", "Email", "Địa Chỉ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbNVS.setFocusable(false);
        tbNVS.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tbNVS.setOpaque(false);
        tbNVS.setSelectionBackground(new java.awt.Color(237, 57, 95));
        tbNVS.setShowVerticalLines(false);
        tbNVS.getTableHeader().setReorderingAllowed(false);
        tbNVS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbNVSMousePressed(evt);
            }
        });
        jScrollPane5.setViewportView(tbNVS);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(54, 54, 54)
                                .addComponent(txtFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(32, 32, 32)
                                .addComponent(TxtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(66, 66, 66)
                                .addComponent(TxtTo, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(64, 64, 64)
                                .addComponent(TxtCC, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(42, 42, 42)
                                .addComponent(TxtSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(42, 42, 42)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(btnsend, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator1)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(86, 86, 86)
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
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel1))
                    .addComponent(txtFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel2))
                    .addComponent(TxtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel3))
                    .addComponent(TxtTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel4))
                    .addComponent(TxtCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel5))
                    .addComponent(TxtSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(btnsend, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(TrangTiepTheo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(24, 24, 24)
                            .addComponent(TxtTrang))
                        .addComponent(TrangCuoi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(TrangDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LuiTrang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnsendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsendActionPerformed
        if (TxtTo.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Ít Nhất 1 Nhân Viên Để Gửi!");
        } else {
            Properties prop = new Properties();
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "465");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.socketFactory.port", "465");
            prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

            Session session = Session.getInstance(prop,
                    new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(txtFrom.getText(), new String(TxtPass.getPassword()));
                }
            });
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(txtFrom.getText()));
                message.setRecipients(
                        Message.RecipientType.TO,
                        InternetAddress.parse(TxtTo.getText())
                );
                message.setSubject(TxtSubject.getText().toString());
                message.setText(TxtVanBan.getText().toString());
                Transport.send(message);
                JOptionPane.showMessageDialog(this, "Email Send!!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnsendActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int YN = JOptionPane.showConfirmDialog(this, "Do You Want To Continue", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (YN == JOptionPane.YES_OPTION) {
            Fchinh f = new Fchinh(a, tk, Mk);
            f.setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_formWindowClosing

    private void tbNVSMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNVSMousePressed

        TxtTo.setText(String.valueOf(tbNVS.getValueAt(tbNVS.getSelectedRow(), 4)));
    }//GEN-LAST:event_tbNVSMousePressed

    private void TrangTiepTheoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangTiepTheoMouseEntered
        TrangTiepTheo.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_TrangTiepTheoMouseEntered

    private void TrangTiepTheoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangTiepTheoMouseExited
        TrangTiepTheo.setBackground(new Color(240, 240, 240));
    }//GEN-LAST:event_TrangTiepTheoMouseExited

    private void TrangTiepTheoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangTiepTheoMousePressed
        if (trang < sotrang) {
            trang++;
            taobangNV();
            TxtTrang.setText(trang + "/" + sotrang);
        }
    }//GEN-LAST:event_TrangTiepTheoMousePressed

    private void TrangCuoiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangCuoiMouseEntered
        TrangCuoi.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_TrangCuoiMouseEntered

    private void TrangCuoiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangCuoiMouseExited
        TrangCuoi.setBackground(new Color(240, 240, 240));
    }//GEN-LAST:event_TrangCuoiMouseExited

    private void TrangCuoiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangCuoiMousePressed
        trang = sotrang;
        taobangNV();
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
        taobangNV();
        TxtTrang.setText(trang + "/" + sotrang);
    }//GEN-LAST:event_TrangDauMousePressed

    private void LuiTrangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LuiTrangMouseEntered
        LuiTrang.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_LuiTrangMouseEntered

    private void LuiTrangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LuiTrangMouseExited
        LuiTrang.setBackground(new Color(240, 240, 240));
    }//GEN-LAST:event_LuiTrangMouseExited

    private void LuiTrangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LuiTrangMousePressed
        if (trang > 1) {
            trang--;
            taobangNV();
            TxtTrang.setText(trang + "/" + sotrang);
        }
    }//GEN-LAST:event_LuiTrangMousePressed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Mail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Mail().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel LuiTrang;
    private javax.swing.JPanel TrangCuoi;
    private javax.swing.JPanel TrangDau;
    private javax.swing.JPanel TrangTiepTheo;
    private javax.swing.JTextField TxtCC;
    private javax.swing.JPasswordField TxtPass;
    private javax.swing.JTextField TxtSubject;
    private javax.swing.JTextField TxtTo;
    private javax.swing.JLabel TxtTrang;
    private javax.swing.JTextArea TxtVanBan;
    private javax.swing.JButton btnsend;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tbNVS;
    private javax.swing.JTextField txtFrom;
    // End of variables declaration//GEN-END:variables
}
