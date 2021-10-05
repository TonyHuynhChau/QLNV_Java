/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.Resigter;

import java.awt.Color;
import Data.Acc;
import Data.Employees;
import Main.TTCN;
import Process.AccDAO;
import Process.Check;
import Process.EmployeesDAO;
import com.sun.source.tree.BreakTree;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.security.MessageDigest;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class DangKy extends javax.swing.JDialog {

    /**
     * Creates new form DangKy
     */
    public DangKy(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtidnv = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        TxtTK = new javax.swing.JTextField();
        TxtMK = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        btnResigter = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đăng Ký", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));

        txtidnv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtidnvMousePressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Mã Nhân Viên");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Tài Khoản");

        TxtTK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TxtTKMousePressed(evt);
            }
        });
        TxtTK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtTKKeyReleased(evt);
            }
        });

        TxtMK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TxtMKMousePressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Mật Khẩu");

        btnResigter.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnResigter.setText("Đăng Ký");
        btnResigter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResigterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TxtTK)
                    .addComponent(TxtMK)
                    .addComponent(txtidnv, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnResigter)
                .addGap(166, 166, 166))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtidnv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TxtTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TxtMK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnResigter)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnResigterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResigterActionPerformed
        try {
            String mk = new String(TxtMK.getPassword());
            String tk = TxtTK.getText().toUpperCase();
            String bam = mk.toString();
            //Check Lỗi Bỏ Trống
            StringBuffer sThongBao = new StringBuffer();

            if (txtidnv.getText().equals("")) {
                sThongBao.append("Họ Và Tên Trống \n");
                txtidnv.setBackground(Color.red);
                txtidnv.requestFocus();
            } else {
                txtidnv.setBackground(new Color(255, 255, 255));
            }
            //
            if (tk.equals("")) {
                sThongBao.append("Tài Khoản Trống \n");
                TxtTK.setBackground(Color.red);
            } else {
                TxtTK.setBackground(new Color(255, 255, 255));
                TxtTK.requestFocus();
            }
            //
            if (mk.equals("")) {
                sThongBao.append("Mật Khẩu Trống \n");
                TxtMK.setBackground(Color.red);
            } else {
                TxtMK.setBackground(new Color(255, 255, 255));
                TxtMK.requestFocus();
            }
            if (sThongBao.length() > 0) {
                JOptionPane.showMessageDialog(null, sThongBao.toString());
            }
            //
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(bam.getBytes());
            byte[] bytedata = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < bytedata.length; i++) {
                sb.append(Integer.toString((bytedata[i] & 0xff) + 0x100, 16).substring(1));
            }
            Check ck = new Check();

            //
            Employees e = ck.CheckTTCN(txtidnv.getText());
            if (e == null) {
                 JOptionPane.showMessageDialog(null, "Nhân Viên Chưa Có Trong Danh Sách\n" + "Vui Lòng Liên Hệ Với Admin Để Biết Thêm Thông Tin");
                return;
            } else {
                int TrangThai = e.getTrangThai();
                if (TrangThai == 0 ) {
                    JOptionPane.showMessageDialog(null, "Nhân Viên Đã Nghỉ Việc");
                    return;
                }
                String name = e.getFullname();
                AccDAO dao = new AccDAO();
                Acc Error_Tk = dao.Find(tk);
                //Check Lỗi Tài Khoản
                if (Error_Tk == null) {
                    Acc Error_ID = dao.Truyidnv(Integer.valueOf(txtidnv.getText()));
                    //Check Lỗi Nhân Viên Đã Có Tài Khoản
                    if (Error_ID == null) {
                        boolean Error = ck.Resigter(Integer.valueOf(txtidnv.getText()), tk, sb.toString(), TxtTK.getText().toUpperCase(), txtidnv.getText());
                        if (Error == false) {
                        } else {
                            JOptionPane.showMessageDialog(null, "Đăng Ký Thành Công");
                            dispose();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Nhân Viên Đã Có Tài Khoản");
                        return;
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Tài Khoản Đã Tồn Tại!!");
                    return;
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnResigterActionPerformed

    private void TxtMKMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TxtMKMousePressed
        TxtMK.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_TxtMKMousePressed

    private void TxtTKMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TxtTKMousePressed
        TxtTK.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_TxtTKMousePressed

    private void txtidnvMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtidnvMousePressed
        txtidnv.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_txtidnvMousePressed

    private void TxtTKKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtTKKeyReleased

    }//GEN-LAST:event_TxtTKKeyReleased

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DangKy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DangKy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DangKy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DangKy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DangKy dialog = new DangKy(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField TxtMK;
    private javax.swing.JTextField TxtTK;
    private javax.swing.JButton btnResigter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtidnv;
    // End of variables declaration//GEN-END:variables
}
