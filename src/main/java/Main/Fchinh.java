package Main;

import Data.*;
import Main.Resigter.Login;
import Process.*;
import java.awt.Color;
import java.awt.MenuItem;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

public class Fchinh extends javax.swing.JFrame {

    public Fchinh() {
        initComponents();
        setLocationRelativeTo(null);

    }

    private int a;
    private String TkString;
    private String MkString;

    public Fchinh(int a, String tk, String mk) {
        initComponents();

        this.a = a;
        this.TkString = tk;
        this.MkString = mk;
        Check_TK.setText(tk);
        if (a == 1) {
            Check_Type.setText("Admin");
        }
        setLocationRelativeTo(null);

        if (a == 0) {
            Check_Type.setText("Nhân Viên");
            JPChange.setEnabled(false);
            jLabel12.setEnabled(false);
            JPChat.setEnabled(false);
            jLabel14.setEnabled(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        Check_TK = new javax.swing.JLabel();
        Check_Type = new javax.swing.JLabel();
        JPSetting = new javax.swing.JPanel();
        asd = new javax.swing.JLabel();
        JPContract = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        JPChange = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        JPChat = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        JPNV = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        JPDonNghiPhep = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        JPCheck = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ezgif.com-gif-maker (3).gif"))); // NOI18N
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 33)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 153, 0));
        jLabel22.setText("Tiger Fang 87 Co.op");
        jPanel2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 70, -1, -1));

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 153, 0));
        jLabel23.setText("Be Strong Like A Tiger");
        jPanel2.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 120, -1, -1));

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, 50, 190));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Admin Portal");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(724, 169, -1, -1));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Admin.png"))); // NOI18N
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 30, 130, -1));

        Check_TK.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Check_TK.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(Check_TK, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 160, 180, 20));

        Check_Type.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Check_Type.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(Check_Type, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 190, 180, 20));

        JPSetting.setBackground(new java.awt.Color(51, 51, 51));
        JPSetting.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPSettingMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPSettingMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPSettingMousePressed(evt);
            }
        });

        asd.setBackground(new java.awt.Color(204, 204, 204));
        asd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Setting_32.png"))); // NOI18N

        javax.swing.GroupLayout JPSettingLayout = new javax.swing.GroupLayout(JPSetting);
        JPSetting.setLayout(JPSettingLayout);
        JPSettingLayout.setHorizontalGroup(
            JPSettingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(asd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        JPSettingLayout.setVerticalGroup(
            JPSettingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(asd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel2.add(JPSetting, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, -1, 37));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1059, -1));

        JPContract.setBackground(new java.awt.Color(102, 102, 102));
        JPContract.setPreferredSize(new java.awt.Dimension(205, 150));
        JPContract.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPContractMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPContractMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPContractMousePressed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(51, 102, 255));
        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 26)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Hợp Đồng");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/HopDong.png"))); // NOI18N

        javax.swing.GroupLayout JPContractLayout = new javax.swing.GroupLayout(JPContract);
        JPContract.setLayout(JPContractLayout);
        JPContractLayout.setHorizontalGroup(
            JPContractLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPContractLayout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addGroup(JPContractLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPContractLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(38, 38, 38))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPContractLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(57, 57, 57))))
        );
        JPContractLayout.setVerticalGroup(
            JPContractLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPContractLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(23, 23, 23))
        );

        jPanel1.add(JPContract, new org.netbeans.lib.awtextra.AbsoluteConstraints(569, 234, -1, 160));

        JPChange.setBackground(new java.awt.Color(102, 102, 102));
        JPChange.setPreferredSize(new java.awt.Dimension(205, 150));
        JPChange.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPChangeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPChangeMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPChangeMousePressed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(51, 102, 255));
        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 26)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Điều Động");

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/DieuDong_64.png"))); // NOI18N

        javax.swing.GroupLayout JPChangeLayout = new javax.swing.GroupLayout(JPChange);
        JPChange.setLayout(JPChangeLayout);
        JPChangeLayout.setHorizontalGroup(
            JPChangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPChangeLayout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(41, 41, 41))
            .addGroup(JPChangeLayout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JPChangeLayout.setVerticalGroup(
            JPChangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPChangeLayout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addGap(23, 23, 23))
        );

        jPanel1.add(JPChange, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 234, -1, 160));

        JPChat.setBackground(new java.awt.Color(102, 102, 102));
        JPChat.setPreferredSize(new java.awt.Dimension(205, 150));
        JPChat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPChatMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPChatMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPChatMousePressed(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(51, 102, 255));
        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 26)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Liên Lạc");

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Mail.png"))); // NOI18N

        javax.swing.GroupLayout JPChatLayout = new javax.swing.GroupLayout(JPChat);
        JPChat.setLayout(JPChatLayout);
        JPChatLayout.setHorizontalGroup(
            JPChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPChatLayout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addGroup(JPChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPChatLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(53, 53, 53))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPChatLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(66, 66, 66))))
        );
        JPChatLayout.setVerticalGroup(
            JPChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPChatLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGap(23, 23, 23))
        );

        jPanel1.add(JPChat, new org.netbeans.lib.awtextra.AbsoluteConstraints(829, 234, -1, 160));

        JPNV.setBackground(new java.awt.Color(102, 102, 102));
        JPNV.setPreferredSize(new java.awt.Dimension(205, 150));
        JPNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPNVMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPNVMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPNVMousePressed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(51, 102, 255));
        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 26)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nhân Viên");

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/NhanVien.png"))); // NOI18N

        javax.swing.GroupLayout JPNVLayout = new javax.swing.GroupLayout(JPNV);
        JPNV.setLayout(JPNVLayout);
        JPNVLayout.setHorizontalGroup(
            JPNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPNVLayout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(JPNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPNVLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(40, 40, 40))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPNVLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(63, 63, 63))))
        );
        JPNVLayout.setVerticalGroup(
            JPNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPNVLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(22, 22, 22))
        );

        jPanel1.add(JPNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(299, 234, -1, 160));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 414, 1014, 10));

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 224, 10, 400));

        JPDonNghiPhep.setBackground(new java.awt.Color(102, 102, 102));
        JPDonNghiPhep.setPreferredSize(new java.awt.Dimension(205, 150));
        JPDonNghiPhep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPDonNghiPhepMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPDonNghiPhepMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPDonNghiPhepMousePressed(evt);
            }
        });

        jLabel20.setBackground(new java.awt.Color(51, 102, 255));
        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 26)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Đơn Xin Nghỉ");

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/VangMat.png"))); // NOI18N

        javax.swing.GroupLayout JPDonNghiPhepLayout = new javax.swing.GroupLayout(JPDonNghiPhep);
        JPDonNghiPhep.setLayout(JPDonNghiPhepLayout);
        JPDonNghiPhepLayout.setHorizontalGroup(
            JPDonNghiPhepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPDonNghiPhepLayout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(JPDonNghiPhepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPDonNghiPhepLayout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPDonNghiPhepLayout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(57, 57, 57))))
        );
        JPDonNghiPhepLayout.setVerticalGroup(
            JPDonNghiPhepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPDonNghiPhepLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addGap(23, 23, 23))
        );

        jPanel1.add(JPDonNghiPhep, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 440, -1, 160));

        JPCheck.setBackground(new java.awt.Color(102, 102, 102));
        JPCheck.setPreferredSize(new java.awt.Dimension(205, 150));
        JPCheck.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPCheckMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPCheckMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPCheckMousePressed(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(51, 102, 255));
        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 26)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Điểm Danh");

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/DiemDanh.png"))); // NOI18N

        javax.swing.GroupLayout JPCheckLayout = new javax.swing.GroupLayout(JPCheck);
        JPCheck.setLayout(JPCheckLayout);
        JPCheckLayout.setHorizontalGroup(
            JPCheckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPCheckLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(41, 41, 41))
            .addGroup(JPCheckLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JPCheckLayout.setVerticalGroup(
            JPCheckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPCheckLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(23, 23, 23))
        );

        jPanel1.add(JPCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 440, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void JPNVMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPNVMousePressed
        try {
            if (this.a == 0) {
                EmployeesDAO dao = new EmployeesDAO();
                Employees e = dao.GetTTNV(TkString);

                TTCN ttcn = new TTCN(e, TkString, MkString, a);
                ttcn.setVisible(true);
                this.setVisible(false);
            }

            if (this.a == 1) {
                TTNV t = new TTNV(TkString, a, MkString);
                t.setVisible(true);
                this.setVisible(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }


    }//GEN-LAST:event_JPNVMousePressed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int YN = JOptionPane.showConfirmDialog(this, "Do You Want To Continue", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (YN == JOptionPane.YES_OPTION) {
            Login lo = new Login();
            lo.setVisible(true);
            this.setVisible(false);
        }

    }//GEN-LAST:event_formWindowClosing

    //<editor-fold desc=" Event Mouse">

    private void JPChatMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPChatMousePressed
        if (a == 1) {
            Mail m = new Mail(a, TkString, MkString);
            m.setVisible(true);
            this.setVisible(false);
        }

    }//GEN-LAST:event_JPChatMousePressed

    private void JPNVMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPNVMouseEntered
        this.JPNV.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_JPNVMouseEntered

    private void JPNVMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPNVMouseExited
        this.JPNV.setBackground(new Color(102, 102, 102));
    }//GEN-LAST:event_JPNVMouseExited

    private void JPContractMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPContractMousePressed
        HopDong HD = new HopDong(a, TkString, MkString);
        HD.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_JPContractMousePressed


    private void JPContractMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPContractMouseEntered
        this.JPContract.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_JPContractMouseEntered

    private void JPContractMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPContractMouseExited
        this.JPContract.setBackground(new Color(102, 102, 102));
    }//GEN-LAST:event_JPContractMouseExited

    private void JPChatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPChatMouseEntered
        this.JPChat.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_JPChatMouseEntered

    private void JPChatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPChatMouseExited
        this.JPChat.setBackground(new Color(102, 102, 102));
    }//GEN-LAST:event_JPChatMouseExited

    private void JPSettingMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPSettingMouseEntered
        this.JPSetting.setBackground(new Color(102, 102, 102));
    }//GEN-LAST:event_JPSettingMouseEntered

    private void JPSettingMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPSettingMouseExited
        this.JPSetting.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_JPSettingMouseExited

    private void JPSettingMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPSettingMousePressed
        String MatMa = JOptionPane.showInputDialog("Nhập Mật Mã");
        if (MatMa == null) {
        }
        if (MatMa.equals("BAOMATTHONGTIN")) {
            DSACC acc = new DSACC(this, true);
            acc.setVisible(true);
        }


    }//GEN-LAST:event_JPSettingMousePressed

    private void JPChangeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPChangeMousePressed
        if (a == 1) {
            DieuDong dd = new DieuDong(a, TkString, MkString);
            dd.setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_JPChangeMousePressed

    private void JPChangeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPChangeMouseExited
        this.JPChange.setBackground(new Color(102, 102, 102));
    }//GEN-LAST:event_JPChangeMouseExited

    private void JPChangeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPChangeMouseEntered
        this.JPChange.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_JPChangeMouseEntered

    private void JPDonNghiPhepMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPDonNghiPhepMouseEntered
        this.JPDonNghiPhep.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_JPDonNghiPhepMouseEntered

    private void JPDonNghiPhepMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPDonNghiPhepMouseExited
        this.JPDonNghiPhep.setBackground(new Color(102, 102, 102));
    }//GEN-LAST:event_JPDonNghiPhepMouseExited

    private void JPDonNghiPhepMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPDonNghiPhepMousePressed
        DonXinPhep xinNghi = new DonXinPhep(this, false, TkString, a);
        xinNghi.setVisible(true);
    }//GEN-LAST:event_JPDonNghiPhepMousePressed

    private void JPCheckMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPCheckMouseEntered
        this.JPCheck.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_JPCheckMouseEntered

    private void JPCheckMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPCheckMouseExited
        this.JPCheck.setBackground(new Color(102, 102, 102));
    }//GEN-LAST:event_JPCheckMouseExited

    private void JPCheckMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPCheckMousePressed
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            DiemDanhDAO dAO = new DiemDanhDAO();
            Date today = new Date();
            EmployeesDAO employeesDAO = new EmployeesDAO();
            if (dAO.FindInsert(sdf.format(today)) == null) {
                for (Employees employees : employeesDAO.ThemDiemDanhEmployeeses()) {
                    dAO.insert(employees.getID(), sdf.format(today));
                }
            }
            DiemDanh diemDanh = new DiemDanh(a, TkString, MkString);
            diemDanh.setVisible(true);
            this.setVisible(false);
        } catch (Exception e) {
        }

    }//GEN-LAST:event_JPCheckMousePressed
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
            java.util.logging.Logger.getLogger(Fchinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Fchinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Fchinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Fchinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Fchinh().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Check_TK;
    private javax.swing.JLabel Check_Type;
    private javax.swing.JPanel JPChange;
    private javax.swing.JPanel JPChat;
    private javax.swing.JPanel JPCheck;
    private javax.swing.JPanel JPContract;
    private javax.swing.JPanel JPDonNghiPhep;
    private javax.swing.JPanel JPNV;
    private javax.swing.JPanel JPSetting;
    private javax.swing.JLabel asd;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    // End of variables declaration//GEN-END:variables
}
