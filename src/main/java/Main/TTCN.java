package Main;

import DON_KNOW.TableEmployees;
import Data.*;
import Main.Resigter.*;
import Process.*;
import java.sql.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.*;
import java.util.regex.*;
import javax.naming.spi.DirStateFactory;
import javax.swing.*;
import jdk.jfr.Event;

public class TTCN extends javax.swing.JFrame {

    // <editor-fold defaultstate="collapsed" desc="ComBoBox"> 
    private int ChucVuID;
    private int PhongBanID;
    private String NameChucVu;
    private String NamePhongBan;
    private String NameChucVulammoi;
    private String NamePhongBanlammoi;
//Hàm Truy ID Chung Cho ComBoBox

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

    private void ComboxAge() {
        EmployeesDAO e = new EmployeesDAO();
        List<Integer> data = e.getdata();
        for (int s : data) {
            cbxage.addItem(String.valueOf(s));
        }
    }
//

    public String TruyName(String str, int ID, String bien) {
        try {

            Connection connection = Connect.getConnection();
            PreparedStatement pst = connection.prepareStatement(str);

            pst.setInt(1, ID);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String Name = rs.getString(bien);
                return Name;

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return null;
    }
// </editor-fold> 

    //<editor-fold desc=" Thêm Nhân Viên">
    public TTCN() {

    }

//</editor-fold>
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel25 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jTextField2 = new javax.swing.JTextField();
        jPanel = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        cbxchucvu = new javax.swing.JComboBox<>();
        cbxage = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        TXTHOTEN = new javax.swing.JTextField();
        TxtAcc = new javax.swing.JTextField();
        Txtpass = new javax.swing.JTextField();
        txtsdt = new javax.swing.JTextField();
        txtaddress = new javax.swing.JTextField();
        btnupdate = new javax.swing.JButton();
        rdbNu = new javax.swing.JRadioButton();
        rdbNam = new javax.swing.JRadioButton();
        jLabel29 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cbxPhongBan = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        Txtdate = new com.toedter.calendar.JDateChooser();

        jLabel25.setText("jLabel25");

        jTextField1.setText("jTextField1");

        jTextField3.setText("jTextField3");

        jTextField2.setText("jTextField2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Cá Nhân", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14))); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Họ Tên :");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setText("User :");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel23.setText("Pass :");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel24.setText("Giới Tính :");

        cbxchucvu.setEnabled(false);

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel26.setText("SDT :");

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel27.setText("Địa Chỉ :");

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel28.setText("Emai :");

        TxtAcc.setEditable(false);

        Txtpass.setEditable(false);

        btnupdate.setText("Cập Nhật");
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdbNu);
        rdbNu.setText("Nữ");

        buttonGroup1.add(rdbNam);
        rdbNam.setText("Nam");

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel29.setText("Tuổi :");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Chức Vụ :");

        cbxPhongBan.setEnabled(false);

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel30.setText("Phòng Ban :");

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel31.setText("Ngày Sinh :");

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel23)
                        .addGap(50, 50, 50)
                        .addComponent(Txtpass, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addGap(18, 18, 18)
                                .addComponent(Txtdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addGap(48, 48, 48)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(33, 33, 33)
                                .addComponent(TXTHOTEN, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(50, 50, 50)
                                .addComponent(TxtAcc, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addGap(10, 10, 10)
                                .addComponent(cbxPhongBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addGap(26, 26, 26)
                                .addComponent(rdbNam)
                                .addGap(13, 13, 13)
                                .addComponent(rdbNu))
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addGap(51, 51, 51)
                                .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addGap(35, 35, 35)
                                .addComponent(txtaddress, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addGap(51, 51, 51)
                                .addComponent(cbxage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(24, 24, 24)
                                .addComponent(cbxchucvu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(43, 43, 43))
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(btnupdate)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(TXTHOTEN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(TxtAcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(Txtpass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addComponent(Txtdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(cbxchucvu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addComponent(cbxage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel30))
                    .addComponent(cbxPhongBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(rdbNam)
                    .addComponent(rdbNu))
                .addGap(27, 27, 27)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(txtaddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(btnupdate)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // <editor-fold defaultstate="collapsed" desc="Thông Tin Cá Nhân Của Nhân Viên ">       
    private Employees e;
    private String tk2;
    private String mk2;
    private int a;

    public TTCN(Employees e, String tk2, String mk2, int a) {
        initComponents();
        this.e = e;
        this.tk2 = tk2;
        this.a = a;
        this.mk2 = mk2;

        String str1 = "select * FROM dbo.chucvu";
        combobox(str1, cbxchucvu, "Chucvu");
        String str2 = "select * FROM dbo.Deparment";
        combobox(str2, cbxPhongBan, "Depname");
        ComboxAge();
        set();
        Txtpass.setEnabled(true);
        TxtAcc.setEditable(true);
        CheckFrom = 0;      
    }

    public void set() {
        //
        try {
            String str = "select c.Chucvu FROM dbo.chucvu AS c WHERE c.ID = ?";
            NameChucVu = TruyName(str, e.getChucVu(), "Chucvu");
            String str3 = "SELECT Depname FROM dbo.Deparment WHERE ID = ?";
            NamePhongBan = TruyName(str3, e.getPhongBan(), "Depname");
            //
            this.TXTHOTEN.setText(e.getFullname());
            this.txtsdt.setText(e.getPhone());
            this.txtEmail.setText(e.getEmail());
            this.txtaddress.setText(e.getAddress());
            this.cbxage.setSelectedItem(e.getAge());
            if (e.getSex() == 1) {
                rdbNam.setSelected(true);
            } else {
                rdbNu.setSelected(true);
            }
            SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd");

            cbxchucvu.setSelectedItem(NameChucVu);
            cbxPhongBan.setSelectedItem(NamePhongBan);
            cbxage.setSelectedItem(e.getAge());
            Txtpass.setText(this.mk2);
            TxtAcc.setText(tk2);
            Txtdate.setDate(adf.parse(e.getDate()));
        } catch (Exception e) {
        }

    }

    public void lammoi() throws Exception {
        EmployeesDAO employeesDAO = new EmployeesDAO();
        Employees employees = employeesDAO.GetTTNV(tk2);

        String str = "select c.Chucvu FROM dbo.chucvu AS c WHERE c.ID = ?";
        NameChucVulammoi = TruyName(str, employees.getChucVu(), "Chucvu");
        String str3 = "SELECT Depname FROM dbo.Deparment WHERE ID = ?";
        NamePhongBanlammoi = TruyName(str3, employees.getPhongBan(), "Depname");

        this.TXTHOTEN.setText(employees.getFullname());
        this.txtsdt.setText(employees.getPhone());
        this.txtEmail.setText(employees.getEmail());
        this.txtaddress.setText(employees.getAddress());
        this.cbxage.setSelectedItem(employees.getAge());
        if (employees.getSex() == 1) {
            rdbNam.setSelected(true);
        } else {
            rdbNu.setSelected(true);
        }
        cbxchucvu.setSelectedItem(NameChucVulammoi);
        cbxPhongBan.setSelectedItem(NamePhongBanlammoi);
        cbxage.setSelectedItem(employees.getAge());
    }

// Nút Cập Nhật Thông Tin
    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        StringBuilder sb = new StringBuilder();
        if (TXTHOTEN.getText().equals("")) {
            sb.append("Họ Tên Trống\n");
            TXTHOTEN.requestFocus();

        }
        if (txtaddress.getText().equals("")) {
            sb.append("Địa Chỉ Trống\n");
            txtaddress.requestFocus();

        }
        if (txtEmail.getText().equals("")) {
            sb.append("Email Trống\n");
            txtEmail.requestFocus();

        }
        if (txtsdt.getText().equals("")) {
            sb.append("Số Điện Thoại Trống");
            txtsdt.requestFocus();

        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
            return;
        } else {
            try {

                String ageString = String.valueOf(cbxage.getSelectedItem());

                String relax = "^\\w+[a-z0-9]*@\\w+mail.com$";

                Pattern pattern = Pattern.compile(relax);
                Matcher matcher = pattern.matcher(txtEmail.getText());
                if (matcher.find()) {
                    EmployeesDAO nhap = new EmployeesDAO();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String NgaySinh = sdf.format(Txtdate.getDate());
                    if (PhongBanID != 0 && ChucVuID != 0) {
                        nhap.updatebyAcc(TXTHOTEN.getText(), NgaySinh, ageString, rdbNam.isSelected() ? 1 : 0, txtEmail.getText().toString(), txtaddress.getText().toString(), txtsdt.getText().toString(), PhongBanID, ChucVuID, e.getID());
                        lammoi();
                        AccDAO c = new AccDAO();

                        if (tk2.toString().equals(TxtAcc.getText().toString())) {
                        } else {
                            Check ck = new Check();
                            ck.updateAcc(TxtAcc.getText(), tk2);
                            new Main.Resigter.Login(TxtAcc.getText(), Txtpass.getText()).setVisible(true);
                            this.setVisible(false);
                            return;
                        }
                        JOptionPane.showMessageDialog(this, "Nhân Viên đã được cập nhật");
                    } else {
                        nhap.updatebyAcc(TXTHOTEN.getText(), NgaySinh, ageString, rdbNam.isSelected() ? 1 : 0, txtEmail.getText().toString(), txtaddress.getText().toString(), txtsdt.getText().toString(), e.getPhongBan(), e.getChucVu(), e.getID());
                        lammoi();

                        if (tk2.toString().equals(TxtAcc.getText().toString())) {
                        } else {
                            Check ck = new Check();
                            ck.updateAcc(TxtAcc.getText(), tk2);
                            new Main.Resigter.Login(TxtAcc.getText(), Txtpass.getText()).setVisible(true);
                            this.setVisible(false);
                            return;
                        }
                        JOptionPane.showMessageDialog(this, "Nhân Viên đã được cập nhật");
                    }

                } else {
                    JOptionPane.showConfirmDialog(this, "Nhập Email Theo Dạng : X+@+X+mail.com\n " + "với X là 1 Chữ Cái Hoặc Số Bất Kỳ");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error" + e.getMessage());
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnupdateActionPerformed
// </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc="Closing">   
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (CheckFrom == 0) {
            int YN = JOptionPane.showConfirmDialog(this, "Do You Want To Continue", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (YN == JOptionPane.YES_OPTION) {
                Fchinh f = new Fchinh(a, TxtAcc.getText(), mk2);
                f.setVisible(true);
                this.setVisible(false);
            }
        }

    }//GEN-LAST:event_formWindowClosing
    // </editor-fold>
    int CheckFrom;

    //<editor-fold defaultstate="collapsed" desc=" Main ">
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
            java.util.logging.Logger.getLogger(TTCN.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TTCN.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TTCN.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TTCN.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TTCN().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TXTHOTEN;
    private javax.swing.JTextField TxtAcc;
    private com.toedter.calendar.JDateChooser Txtdate;
    private javax.swing.JTextField Txtpass;
    private javax.swing.JButton btnupdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbxPhongBan;
    private javax.swing.JComboBox<String> cbxage;
    private javax.swing.JComboBox<String> cbxchucvu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JPanel jPanel;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JRadioButton rdbNam;
    private javax.swing.JRadioButton rdbNu;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtaddress;
    private javax.swing.JTextField txtsdt;
    // End of variables declaration//GEN-END:variables

//</editor-fold>
}
