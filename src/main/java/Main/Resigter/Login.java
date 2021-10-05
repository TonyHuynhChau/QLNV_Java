/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.Resigter;

import Data.Acc;
import Data.Employees;
import Main.Fchinh;
import javax.swing.JOptionPane;
import java.sql.*;
import Process.*;
import java.util.Date;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.*;
import jdk.jfr.Event;

/**
 *
 * @author ASUS
 */
public class Login extends javax.swing.JFrame {
    
    public Login() {
        initComponents();
        setLocationRelativeTo(null);
        time();
        Jconnect.setText(Check.testConnect());
    }
    
    public static ResultSet rs = null;
    public static ResultSet rst = null;
    public static PreparedStatement pst = null;
    private String text;
    private String i;
    
    int hour;
    int second;
    int minute;
    int AM_PM;
    String Day_Night;
    
    public void time() {
        new Thread() {
            
            public void run() {
                while (true) {
                    Calendar ca = new GregorianCalendar();
                    
                    hour = ca.get(Calendar.HOUR_OF_DAY);
                    second = ca.get(Calendar.SECOND);
                    minute = ca.get(Calendar.MINUTE);
                    AM_PM = ca.get(Calendar.AM_PM);
                    Day_Night = "null";
                    if (AM_PM == 1) {
                        Day_Night = "PM";
                    } else {
                        Day_Night = "AM";
                    }
                    String time = hour + " : " + minute + " : " + second + " : " + Day_Night;
                    txtTime.setText(time);
                }
            }
        }.start();
    }
    
    public Login(String text, String i) {
        initComponents();
        this.text = text;
        this.i = i;
        txtTK.setText(this.text);
        txtMK.setText(this.i);
        time();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtTK = new javax.swing.JTextField();
        txtMK = new javax.swing.JPasswordField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        Jconnect = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtTime = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2.setBackground(new java.awt.Color(153, 102, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTK.setBackground(new java.awt.Color(153, 102, 255));
        txtTK.setForeground(new java.awt.Color(255, 255, 255));
        txtTK.setBorder(null);
        txtTK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTKKeyPressed(evt);
            }
        });
        jPanel2.add(txtTK, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 95, 218, 37));

        txtMK.setBackground(new java.awt.Color(153, 102, 255));
        txtMK.setForeground(new java.awt.Color(255, 255, 255));
        txtMK.setBorder(null);
        txtMK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMKKeyPressed(evt);
            }
        });
        jPanel2.add(txtMK, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 230, 37));

        jSeparator1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 270, 310, 10));

        jSeparator2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 310, 10));

        jLabel1.setFont(new java.awt.Font("Berlin Sans FB", 0, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("User");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, 40, 40));

        jLabel2.setFont(new java.awt.Font("Berlin Sans FB", 0, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Password");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 164, -1, -1));

        jPanel3.setBackground(new java.awt.Color(153, 102, 255));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Sign In");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel3MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 340, -1, 30));

        Jconnect.setForeground(new java.awt.Color(255, 255, 255));
        Jconnect.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Jconnect.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel2.add(Jconnect, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 440, 340, 40));

        jPanel4.setBackground(new java.awt.Color(153, 102, 255));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Sign up");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel5MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 340, 110, 30));

        txtTime.setEditable(false);
        txtTime.setBackground(new java.awt.Color(153, 102, 255));
        txtTime.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtTime.setForeground(new java.awt.Color(255, 255, 255));
        txtTime.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTime.setBorder(null);
        jPanel2.add(txtTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 410, 160, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 0, 510, 500));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/employee.gif"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    int type;
    int accid;
    String mk;
    String tk;
    private void jLabel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MousePressed
        try {
            mk = new String(txtMK.getPassword());
            tk = txtTK.getText();
            String bam = mk;
            //Mã Hóa MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(bam.getBytes());
            byte[] bytedata = md.digest();
            
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < bytedata.length; i++) {
                sb.append(Integer.toString((bytedata[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Mã Hóa Xong                 
            if (tk.length() == 0 || mk.length() == 0) {
                JOptionPane.showMessageDialog(null, "Vui Lòng Điền Đầy Đủ Thông Tin");
            } else {
                rs = Check.cLog(tk, sb.toString());
                try {
                    if (rs.next()) {
                        AccDAO c = new AccDAO();
                        Acc a = c.Find(tk);
                        type = a.getType();
                        int idnv = a.getIDnv();
                        Check ck = new Check();
                        Employees e = ck.CheckTTCN(String.valueOf(idnv));
                        if (e != null) {
                            if (e.getTrangThai() == 0) {
                                JOptionPane.showMessageDialog(null, "Nhân Viên Đã Nghỉ Việc");
                                System.exit(0);
                            }
                        }
                        
                        Fchinh f = new Fchinh(type, tk, mk);
                        this.setVisible(false);
                        f.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Sai Tài Khoản Hoặc Mật Khẩu");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jLabel3MousePressed
    
    public void MoGiaoDien() {
        
    }

    private void jLabel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MousePressed
        DangKy dk = new DangKy(this, true);
        dk.setVisible(true);
    }//GEN-LAST:event_jLabel5MousePressed

    private void txtMKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMKKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            MouseEvent mevt = null;
            jLabel3MousePressed(mevt);
        }
    }//GEN-LAST:event_txtMKKeyPressed

    private void txtTKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTKKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            MouseEvent mevt = null;
            jLabel3MousePressed(mevt);
        }
    }//GEN-LAST:event_txtTKKeyPressed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Jconnect;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPasswordField txtMK;
    private javax.swing.JTextField txtTK;
    private javax.swing.JTextField txtTime;
    // End of variables declaration//GEN-END:variables

}
