package Main;

import DON_KNOW.BaoCao;
import Data.Acc;
import Data.ChucVu;
import Data.Connect;
import Data.DKNghi;
import Data.Deparment;
import Data.Employees;
import Process.AccDAO;
import Process.Check;
import Process.DKNghiDAO;
import Process.DeparmentDAO;
import Process.EmployeesDAO;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.SimpleDoc;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class DonXinPhep extends javax.swing.JDialog {

    private DonXinPhep(JFrame jFrame, boolean b) {

    }
    //<editor-fold desc=" Phân Trang ">

    public void TrangFind() {
        if (countFind % 5 == 0) {
            sotrangFind = countFind / 5;
        } else {
            sotrangFind = countFind / 5 + 1;
        }
        TxtTrang.setText("1/" + sotrangFind);
    }

    long countFind, sotrangFind, trangFind = 1;

    public void TimCountFind(String str) {

        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                countFind = rs.getLong(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void TrangCD() {
        if (countCD % 5 == 0) {
            sotrangCD = countCD / 5;
        } else {
            sotrangCD = countCD / 5 + 1;
        }
        TxtTrang.setText("1/" + sotrangCD);
    }

    long countCD, sotrangCD, trangCD = 1;

    public void TimCountCD(String str) {

        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                countCD = rs.getLong(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void TrangDD() {
        if (countDD % 5 == 0) {
            sotrangDD = countDD / 5;
        } else {
            sotrangDD = countDD / 5 + 1;
        }
        TxtTrangDD.setText("1/" + sotrangDD);
    }

    long countDD, sotrangDD, trangDD = 1;

    public void TimCountDD(String str) {

        try ( Connection connection = Connect.getConnection();  PreparedStatement pst = connection.prepareStatement(str);) {
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                countDD = rs.getLong(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//</editor-fold>
    private String tk;
    private int idnv;
    private int a;
    String TimKiemTheoThang;

    public void designjtable(JTable jtb) {
        jtb.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        jtb.getTableHeader().setOpaque(false);
        jtb.getTableHeader().setBackground(new Color(32, 136, 203));
        jtb.getTableHeader().setForeground(new Color(255, 255, 255));
        jtb.setRowHeight(25);
    }

    public DonXinPhep(java.awt.Frame parent, boolean modal, String TkString, int a) {
        super(parent, modal);
        initComponents();
        try {
            designjtable(TbChuaDuyet);
            designjtable(TbDaDuyet);
            TxtLyDoKhac.setVisible(false);
            this.tk = TkString;
            this.a = a;
            Date now = new Date();
            DateSearch.setDate(now);

            SimpleDateFormat sdfMonth = new SimpleDateFormat("M");
            SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
            int Month = Integer.valueOf(sdfMonth.format(DateSearch.getDate()));
            int Year = Integer.valueOf(sdfYear.format(DateSearch.getDate()));
            SwitchDay(Month, Year);

            SimpleDateFormat sdfMonthDateFormat_Year = new SimpleDateFormat("yyyy-MM");
            TimKiemTheoThang = sdfMonthDateFormat_Year.format(DateSearch.getDate()) + "-" + day;
            jLabel18.setText(TimKiemTheoThang);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (a == 0) {
                AccDAO dao = new AccDAO();
                Acc acc = dao.Find(tk);
                idnv = acc.getIDnv();
                set();
                TxtTime.setText("183 Ngày");
                BtnDuyet.setEnabled(false);
                BaoCao.setEnabled(false);
                //Chưa Duyệt
                String CD = "SELECT COUNT(*) FROM DKNghi where TrangThai = 0 And NgayNop >= '" + sdf.format(DateSearch.getDate()) + "' and NgayNop <= '" + TimKiemTheoThang + "'  And IDNV =" + idnv;
                TimCountCD(CD);
                TrangCD();
                BangNVCD(1, sdf.format(DateSearch.getDate()), TimKiemTheoThang);
                //Đã Duyệt  
                String DD = "SELECT COUNT(*) FROM DKNghi where TrangThai = 1 And NgayDuyet >='" + sdf.format(DateSearch.getDate()) + "' and NgayNop <= '" + TimKiemTheoThang + "' And IDNV = " + idnv;
                TimCountDD(DD);
                TrangDD();
                BangNVDD(1, sdf.format(DateSearch.getDate()), TimKiemTheoThang);
            }
            if (a == 1) {
                String CD = "SELECT COUNT(*) FROM DKNghi where TrangThai = 0 And NgayNop >= '" + sdf.format(DateSearch.getDate()) + "' and NgayNop <= '" + TimKiemTheoThang + "'";
                CbxLoaiNghi.setEnabled(false);
                TimCountCD(CD);
                TrangCD();
                BangadminCD(1, sdf.format(DateSearch.getDate()), TimKiemTheoThang);

                //Đã Duyệt   
                String DD = "SELECT COUNT(*) FROM DKNghi where TrangThai = 1  And NgayDuyet >='" + sdf.format(DateSearch.getDate()) + "' and NgayNop <= '" + TimKiemTheoThang + "'";
                TimCountDD(DD);
                TrangDD();
                BangadminDD(1, sdf.format(DateSearch.getDate()), TimKiemTheoThang);
                btnnopdon.setEnabled(false);
            }

        } catch (Exception ex) {

        }
    }

    public void set() {
        try {
            if (idnv == 0) {

            } else {
                EmployeesDAO dao = new EmployeesDAO();
                Employees e = dao.GetTTNV(tk);

                TxtHoTen.setText(e.getFullname());
                TxtNgaySinh.setText(e.getDate());
                TxtDiachi.setText(e.getAddress());
                Check cvdao = new Check();
                ChucVu cv = cvdao.Truy(e.getChucVu());
                TxtChucVu.setText(cv.getChucvu());
                DeparmentDAO depdao = new DeparmentDAO();
                Deparment dep = depdao.Truy(e.getPhongBan());
                TxtPhongBan.setText(dep.getDeparment());
                TxtPhone.setText(e.getPhone());
            }

        } catch (Exception e) {
        }

    }

    //<editor-fold desc=" Chưa Duyệt">
    //<editor-fold desc=" Admin">
    public void BangadminCD(long Trang, String Date, String Date2) {
        DefaultTableModel amdminCD = (DefaultTableModel) TbChuaDuyet.getModel();
        amdminCD.setRowCount(0);
        try {
            DKNghiDAO cdao = new DKNghiDAO();
            String str = "select top 5 * FROM DKNghi WHERE ID NOT IN (SELECT TOP " + (Trang * 5 - 5) + " ID FROM DKNghi)  and NgayNop >= '" + Date + "' and TrangThai = 0 and NgayNop <= '" + Date2 + "'";
            for (DKNghi i : cdao.GetAdmin(str)) {
                Object[] datarow = new Object[9];
                datarow[0] = i.getSTT();
                EmployeesDAO dao = new EmployeesDAO();
                Employees e = dao.Find(i.getIDNV());
                datarow[1] = e.getFullname();
                datarow[2] = i.getDate();
                datarow[3] = i.getPhone();
                datarow[4] = i.getDepID();
                datarow[5] = i.getLoaiNghi();
                datarow[6] = i.getThoiHan();
                datarow[7] = i.getNgayNopDon();
                if (i.getTrangThai() == 0) {
                    datarow[8] = "Chưa Duyệt";
                }
                if (i.getTrangThai() == 1) {
                    datarow[8] = "Đã Duyệt";
                }

                amdminCD.addRow(datarow);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    //</editor-fold>
    //<editor-fold desc=" Nhân Viên">
    public void BangNVCD(long Trang, String Date, String Date2) {
        DefaultTableModel NVCD = (DefaultTableModel) TbChuaDuyet.getModel();
        NVCD.setRowCount(0);
        try {
            DKNghiDAO cdao = new DKNghiDAO();
            String str = "select top 5 * FROM DKNghi WHERE ID NOT IN (SELECT TOP " + (Trang * 5 - 5) + " ID FROM DKNghi) and NgayNop >='" + Date + "' and TrangThai = 0 AND IDNV = " + idnv + "and NgayNop <= '" + Date2 + "'";
            for (DKNghi i : cdao.GetAdmin(str)) {
                Object[] datarow = new Object[9];
                datarow[0] = i.getSTT();
                EmployeesDAO dao = new EmployeesDAO();
                Employees e = dao.Find(i.getIDNV());
                datarow[1] = e.getFullname();
                datarow[2] = i.getDate();
                datarow[3] = i.getPhone();
                datarow[4] = i.getDepID();
                datarow[5] = i.getLoaiNghi();
                datarow[6] = i.getThoiHan();
                datarow[7] = i.getNgayNopDon();
                if (i.getTrangThai() == 0) {
                    datarow[8] = "Chưa Duyệt";
                }
                if (i.getTrangThai() == 1) {
                    datarow[8] = "Đã Duyệt";
                }

                NVCD.addRow(datarow);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    //</editor-fold>
    //</editor-fold>
    //<editor-fold desc=" Đã Duyệt">
    //<editor-fold desc=" Admin">
    public void BangadminDD(long Trang, String Date, String Date2) {
        DefaultTableModel adminDD = (DefaultTableModel) TbDaDuyet.getModel();
        adminDD.setRowCount(0);
        try {
            DKNghiDAO cdao = new DKNghiDAO();
            String str = "select top 5 * FROM DKNghi WHERE ID NOT IN (SELECT TOP " + (Trang * 5 - 5) + " ID FROM DKNghi) and NgayDuyet >='" + Date + "' and TrangThai = 1 and NgayDuyet <= '" + Date2 + "'";
            for (DKNghi i : cdao.GetAdmin(str)) {
                Object[] datarow = new Object[9];
                datarow[0] = i.getSTT();
                EmployeesDAO dao = new EmployeesDAO();
                Employees e = dao.Find(i.getIDNV());
                datarow[1] = e.getFullname();
                datarow[2] = i.getDate();
                datarow[3] = i.getPhone();
                datarow[4] = i.getDepID();
                datarow[5] = i.getLoaiNghi();
                datarow[6] = i.getThoiHan();
                datarow[7] = i.getNgayDuyetDon();
                if (i.getTrangThai() == 0) {
                    datarow[8] = "Chưa Duyệt";
                }
                if (i.getTrangThai() == 1) {
                    datarow[8] = "Đã Duyệt";
                }

                adminDD.addRow(datarow);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    //</editor-fold>
    //<editor-fold desc=" Nhân Viên">
    public void BangNVDD(long Trang, String Date, String Date2) {
        DefaultTableModel NVDD = (DefaultTableModel) TbDaDuyet.getModel();
        NVDD.setRowCount(0);
        try {
            DKNghiDAO cdao = new DKNghiDAO();
            String str = "select top 5 * FROM DKNghi WHERE ID NOT IN (SELECT TOP " + (Trang * 5 - 5) + " ID FROM DKNghi) and NgayDuyet >='" + Date + "' and TrangThai = 1 AND IDNV = " + idnv + "and NgayDuyet <= '" + Date2 + "'";
            for (DKNghi i : cdao.GetAdmin(str)) {
                Object[] datarow = new Object[9];
                datarow[0] = i.getSTT();
                EmployeesDAO dao = new EmployeesDAO();
                Employees e = dao.Find(i.getIDNV());
                datarow[1] = e.getFullname();
                datarow[2] = i.getDate();
                datarow[3] = i.getPhone();
                datarow[4] = i.getDepID();
                datarow[5] = i.getLoaiNghi();
                datarow[6] = i.getThoiHan();
                datarow[7] = i.getNgayDuyetDon();
                if (i.getTrangThai() == 0) {
                    datarow[8] = "Chưa Duyệt";
                }
                if (i.getTrangThai() == 1) {
                    datarow[8] = "Đã Duyệt";
                }

                NVDD.addRow(datarow);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    //</editor-fold>
    //</editor-fold>
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        TxtHoTen = new javax.swing.JTextField();
        TxtChucVu = new javax.swing.JTextField();
        TxtNgaySinh = new javax.swing.JTextField();
        TxtPhongBan = new javax.swing.JTextField();
        TxtDiachi = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        TxtTime = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        btnnopdon = new javax.swing.JButton();
        CbxLoaiNghi = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TxtLyDoKhac = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        TxtPhone = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        TBPane = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        BtnDuyet = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TbChuaDuyet = new javax.swing.JTable();
        NeXtCD = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        TrangCuoiCD = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        LuiCD = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        TrangDauCD = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        TxtTrang = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TbDaDuyet = new javax.swing.JTable();
        TxtTrangDD = new javax.swing.JLabel();
        TrangDauDD = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        LuiDD = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        TrangCuoiDD = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        NextDD = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        DateSearch = new com.toedter.calendar.JDateChooser();
        jLabel18 = new javax.swing.JLabel();
        BaoCao = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Đăng Ký", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Anh/Chị :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Sinh Ngày :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Chức Vụ :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Vị Trí Công Tác :");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Địa Chỉ Hiện Tại :");

        TxtHoTen.setEditable(false);
        TxtHoTen.setBackground(new java.awt.Color(204, 204, 204));
        TxtHoTen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TxtHoTen.setBorder(null);

        TxtChucVu.setEditable(false);
        TxtChucVu.setBackground(new java.awt.Color(204, 204, 204));
        TxtChucVu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TxtChucVu.setBorder(null);

        TxtNgaySinh.setEditable(false);
        TxtNgaySinh.setBackground(new java.awt.Color(204, 204, 204));
        TxtNgaySinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TxtNgaySinh.setBorder(null);

        TxtPhongBan.setEditable(false);
        TxtPhongBan.setBackground(new java.awt.Color(204, 204, 204));
        TxtPhongBan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TxtPhongBan.setBorder(null);

        TxtDiachi.setEditable(false);
        TxtDiachi.setBackground(new java.awt.Color(204, 204, 204));
        TxtDiachi.setBorder(null);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Thời Gian Nghỉ :");

        TxtTime.setEditable(false);
        TxtTime.setBackground(new java.awt.Color(204, 204, 204));
        TxtTime.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TxtTime.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TxtTime.setBorder(null);

        btnnopdon.setText("Nộp Đơn");
        btnnopdon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnopdonActionPerformed(evt);
            }
        });

        CbxLoaiNghi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nghỉ Thai Sản", "Covid", "Tai Nạn", "Đám Tiệc", "Khác ......" }));
        CbxLoaiNghi.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                CbxLoaiNghiPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Loại Nghỉ :");

        TxtLyDoKhac.setColumns(20);
        TxtLyDoKhac.setRows(5);
        TxtLyDoKhac.setText("Lý Do.....");
        jScrollPane1.setViewportView(TxtLyDoKhac);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Phone :");

        TxtPhone.setEditable(false);
        TxtPhone.setBackground(new java.awt.Color(204, 204, 204));
        TxtPhone.setBorder(null);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel2)
                .addGap(61, 61, 61)
                .addComponent(TxtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92)
                .addComponent(jLabel3)
                .addGap(65, 65, 65)
                .addComponent(TxtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel4)
                .addGap(56, 56, 56)
                .addComponent(TxtChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92)
                .addComponent(jLabel5)
                .addGap(35, 35, 35)
                .addComponent(TxtPhongBan, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(438, 438, 438)
                .addComponent(btnnopdon))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TxtTime, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator7)
                                    .addComponent(TxtDiachi, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE))))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(63, 63, 63)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(TxtPhone)
                                    .addComponent(CbxLoaiNghi, 0, 120, Short.MAX_VALUE))
                                .addGap(25, 25, 25)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxtChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtPhongBan, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(8, 8, 8)
                            .addComponent(TxtTime, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(6, 6, 6)
                            .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(11, 11, 11)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(TxtDiachi, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9)
                                .addComponent(TxtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(6, 6, 6))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(CbxLoaiNghi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(48, 48, 48)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabel7)
                                .addGap(33, 33, 33)
                                .addComponent(jLabel6))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)))
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(btnnopdon))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/relax.png"))); // NOI18N
        jLabel1.setText("Đăng Ký Xin Phép Nghỉ");

        BtnDuyet.setText("Duyệt Đơn");
        BtnDuyet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDuyetActionPerformed(evt);
            }
        });

        TbChuaDuyet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Họ Tên", "Ngày Sinh", "Phone", "Phòng Ban", "Loại Nghỉ", "Số Ngày Nghỉ", "Ngày Nộp Đơn", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TbChuaDuyet.setIntercellSpacing(new java.awt.Dimension(0, 0));
        TbChuaDuyet.setOpaque(false);
        TbChuaDuyet.setSelectionBackground(new java.awt.Color(237, 57, 95));
        jScrollPane2.setViewportView(TbChuaDuyet);
        if (TbChuaDuyet.getColumnModel().getColumnCount() > 0) {
            TbChuaDuyet.getColumnModel().getColumn(0).setResizable(false);
            TbChuaDuyet.getColumnModel().getColumn(0).setPreferredWidth(20);
            TbChuaDuyet.getColumnModel().getColumn(1).setResizable(false);
            TbChuaDuyet.getColumnModel().getColumn(2).setResizable(false);
            TbChuaDuyet.getColumnModel().getColumn(3).setResizable(false);
            TbChuaDuyet.getColumnModel().getColumn(4).setResizable(false);
            TbChuaDuyet.getColumnModel().getColumn(5).setResizable(false);
            TbChuaDuyet.getColumnModel().getColumn(6).setResizable(false);
            TbChuaDuyet.getColumnModel().getColumn(8).setResizable(false);
        }

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

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_chevron_right_32.png"))); // NOI18N

        javax.swing.GroupLayout NeXtCDLayout = new javax.swing.GroupLayout(NeXtCD);
        NeXtCD.setLayout(NeXtCDLayout);
        NeXtCDLayout.setHorizontalGroup(
            NeXtCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10)
        );
        NeXtCDLayout.setVerticalGroup(
            NeXtCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NeXtCDLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel10))
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

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_fast_forward_32.png"))); // NOI18N

        javax.swing.GroupLayout TrangCuoiCDLayout = new javax.swing.GroupLayout(TrangCuoiCD);
        TrangCuoiCD.setLayout(TrangCuoiCDLayout);
        TrangCuoiCDLayout.setHorizontalGroup(
            TrangCuoiCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11)
        );
        TrangCuoiCDLayout.setVerticalGroup(
            TrangCuoiCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TrangCuoiCDLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel11))
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
            .addComponent(jLabel12)
        );
        LuiCDLayout.setVerticalGroup(
            LuiCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LuiCDLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel12))
        );

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
            .addComponent(jLabel13)
        );
        TrangDauCDLayout.setVerticalGroup(
            TrangDauCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TrangDauCDLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel13))
        );

        TxtTrang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TxtTrang.setText("jLabel14");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 353, Short.MAX_VALUE)
                        .addComponent(TrangDauCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(LuiCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(TxtTrang)
                        .addGap(18, 18, 18)
                        .addComponent(NeXtCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(TrangCuoiCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(308, 308, 308)
                        .addComponent(BtnDuyet))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(BtnDuyet)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 13, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(NeXtCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TrangCuoiCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(LuiCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TrangDauCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(TxtTrang)
                                .addGap(27, 27, 27)))))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        TBPane.addTab("Chưa Duyệt", jPanel1);

        TbDaDuyet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Họ Tên", "Ngày Sinh", "Phone", "Phòng Ban", "Loại Nghỉ", "Số Ngày Nghỉ", "Ngày Duyệt", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TbDaDuyet.setIntercellSpacing(new java.awt.Dimension(0, 0));
        TbDaDuyet.setOpaque(false);
        TbDaDuyet.setSelectionBackground(new java.awt.Color(237, 57, 95));
        TbDaDuyet.setShowVerticalLines(false);
        TbDaDuyet.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(TbDaDuyet);
        if (TbDaDuyet.getColumnModel().getColumnCount() > 0) {
            TbDaDuyet.getColumnModel().getColumn(0).setResizable(false);
            TbDaDuyet.getColumnModel().getColumn(0).setPreferredWidth(20);
            TbDaDuyet.getColumnModel().getColumn(1).setResizable(false);
            TbDaDuyet.getColumnModel().getColumn(2).setResizable(false);
            TbDaDuyet.getColumnModel().getColumn(3).setResizable(false);
            TbDaDuyet.getColumnModel().getColumn(4).setResizable(false);
            TbDaDuyet.getColumnModel().getColumn(5).setResizable(false);
            TbDaDuyet.getColumnModel().getColumn(6).setResizable(false);
            TbDaDuyet.getColumnModel().getColumn(7).setResizable(false);
            TbDaDuyet.getColumnModel().getColumn(8).setResizable(false);
        }

        TxtTrangDD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TxtTrangDD.setText("jLabel14");

        TrangDauDD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TrangDauDDMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                TrangDauDDMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TrangDauDDMousePressed(evt);
            }
        });

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_rewind_32.png"))); // NOI18N

        javax.swing.GroupLayout TrangDauDDLayout = new javax.swing.GroupLayout(TrangDauDD);
        TrangDauDD.setLayout(TrangDauDDLayout);
        TrangDauDDLayout.setHorizontalGroup(
            TrangDauDDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14)
        );
        TrangDauDDLayout.setVerticalGroup(
            TrangDauDDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TrangDauDDLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel14))
        );

        LuiDD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LuiDDMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LuiDDMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                LuiDDMousePressed(evt);
            }
        });

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_chevron_left_32.png"))); // NOI18N

        javax.swing.GroupLayout LuiDDLayout = new javax.swing.GroupLayout(LuiDD);
        LuiDD.setLayout(LuiDDLayout);
        LuiDDLayout.setHorizontalGroup(
            LuiDDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15)
        );
        LuiDDLayout.setVerticalGroup(
            LuiDDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LuiDDLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel15))
        );

        TrangCuoiDD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TrangCuoiDDMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                TrangCuoiDDMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TrangCuoiDDMousePressed(evt);
            }
        });

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_fast_forward_32.png"))); // NOI18N

        javax.swing.GroupLayout TrangCuoiDDLayout = new javax.swing.GroupLayout(TrangCuoiDD);
        TrangCuoiDD.setLayout(TrangCuoiDDLayout);
        TrangCuoiDDLayout.setHorizontalGroup(
            TrangCuoiDDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel16)
        );
        TrangCuoiDDLayout.setVerticalGroup(
            TrangCuoiDDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TrangCuoiDDLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel16))
        );

        NextDD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                NextDDMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                NextDDMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                NextDDMousePressed(evt);
            }
        });

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_chevron_right_32.png"))); // NOI18N

        javax.swing.GroupLayout NextDDLayout = new javax.swing.GroupLayout(NextDD);
        NextDD.setLayout(NextDDLayout);
        NextDDLayout.setHorizontalGroup(
            NextDDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17)
        );
        NextDDLayout.setVerticalGroup(
            NextDDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NextDDLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel17))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 997, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(360, 360, 360)
                .addComponent(TrangDauDD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(LuiDD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(TxtTrangDD)
                .addGap(18, 18, 18)
                .addComponent(NextDD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(TrangCuoiDD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NextDD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TrangCuoiDD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LuiDD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TrangDauDD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(TxtTrangDD)
                        .addGap(9, 9, 9)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        TBPane.addTab("Đã Duyệt", jPanel3);

        DateSearch.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                DateSearchPropertyChange(evt);
            }
        });

        jLabel18.setText("jLabel18");

        BaoCao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_microsoft_excel_2019_32.png"))); // NOI18N
        BaoCao.setText("Xuất Báo Cáo");
        BaoCao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BaoCaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(334, 334, 334)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(TBPane)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(BaoCao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(DateSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(DateSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18)))
                    .addComponent(BaoCao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TBPane, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnnopdonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnopdonActionPerformed
        try {
            DKNghiDAO dao = new DKNghiDAO();
            Date now = new Date();
            SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd");
            if (CbxLoaiNghi.getSelectedIndex() == 4) {
                if (TxtTime.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Thời Gian Nghỉ Trống");
                } else {
                    dao.INSERT(idnv, TxtNgaySinh.getText(), TxtPhone.getText(), TxtLyDoKhac.getText(), TxtTime.getText(), simp.format(now), TxtPhongBan.getText());
                    String CD = "SELECT COUNT(*) FROM DKNghi where TrangThai = 0 And IDNV =" + idnv;
                    trangCD = 1;
                    TimCountCD(CD);
                    TrangCD();
                    BangNVCD(trangCD, simp.format(DateSearch.getDate()), TimKiemTheoThang);
                    JOptionPane.showMessageDialog(null, "Đăng Ký Thành Công");
                }
            } else {
                dao.INSERT(idnv, TxtNgaySinh.getText(), TxtPhone.getText(), CbxLoaiNghi.getSelectedItem().toString(), TxtTime.getText(), simp.format(now), TxtPhongBan.getText());
                String CD = "SELECT COUNT(*) FROM DKNghi where TrangThai = 0 And IDNV =" + idnv;
                trangCD = 1;
                TimCountCD(CD);
                TrangCD();
                BangNVCD(trangCD, simp.format(DateSearch.getDate()), TimKiemTheoThang);
                JOptionPane.showMessageDialog(null, "Đăng Ký Thành Công");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btnnopdonActionPerformed

    private void CbxLoaiNghiPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_CbxLoaiNghiPopupMenuWillBecomeInvisible
        String NgayNghi;
        if (CbxLoaiNghi.getSelectedIndex() == 4) {
            TxtLyDoKhac.setVisible(true);
            NgayNghi = "";
            TxtTime.requestFocus();
            TxtTime.setText(NgayNghi.toString());
            TxtTime.setEditable(true);
        } else {
            TxtLyDoKhac.setVisible(false);
            TxtTime.setEditable(false);
        }
        if (CbxLoaiNghi.getSelectedIndex() == 0) {
            NgayNghi = "183 Ngày";
            TxtTime.setText(NgayNghi.toString());
        }
        if (CbxLoaiNghi.getSelectedIndex() == 1) {
            NgayNghi = "14 Ngày";
            TxtTime.setText(NgayNghi.toString());
        }
        if (CbxLoaiNghi.getSelectedIndex() == 2) {
            NgayNghi = "14 Ngày";
            TxtTime.setText(NgayNghi.toString());
        }
        if (CbxLoaiNghi.getSelectedIndex() == 3) {
            NgayNghi = "3 Ngày";
            TxtTime.setText(NgayNghi.toString());
        }
    }//GEN-LAST:event_CbxLoaiNghiPopupMenuWillBecomeInvisible

    private void NeXtCDMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NeXtCDMouseEntered
        NeXtCD.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_NeXtCDMouseEntered

    private void NeXtCDMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NeXtCDMouseExited
        NeXtCD.setBackground(new Color(240, 240, 240));
    }//GEN-LAST:event_NeXtCDMouseExited

    private void TrangCuoiCDMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangCuoiCDMouseEntered
        TrangCuoiCD.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_TrangCuoiCDMouseEntered

    private void TrangCuoiCDMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangCuoiCDMouseExited
        TrangCuoiCD.setBackground(new Color(240, 240, 240));
    }//GEN-LAST:event_TrangCuoiCDMouseExited

    private void LuiCDMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LuiCDMouseEntered
        LuiCD.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_LuiCDMouseEntered

    private void LuiCDMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LuiCDMouseExited
        LuiCD.setBackground(new Color(240, 240, 240));
    }//GEN-LAST:event_LuiCDMouseExited

    private void LuiCDMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LuiCDMousePressed
        SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd");

        if (a == 0) {
            if (trangCD > 1) {
                trangCD--;
                BangNVCD(trangCD, simp.format(DateSearch.getDate()), TimKiemTheoThang);
                TxtTrang.setText(trangCD + "/" + sotrangCD);
            }
        }
        if (a == 1) {
            if (trangCD > 1) {
                trangCD--;
                BangadminCD(trangCD, simp.format(DateSearch.getDate()), TimKiemTheoThang);
                TxtTrang.setText(trangCD + "/" + sotrangCD);
            }
        }
    }//GEN-LAST:event_LuiCDMousePressed

    private void TrangDauCDMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangDauCDMouseEntered
        TrangDauCD.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_TrangDauCDMouseEntered

    private void TrangDauCDMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangDauCDMouseExited
        TrangDauCD.setBackground(new Color(240, 240, 240));
    }//GEN-LAST:event_TrangDauCDMouseExited

    private void TrangDauCDMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangDauCDMousePressed
        SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd");
        if (a == 0) {
            trangCD = 1;
            BangNVCD(trangCD, simp.format(DateSearch.getDate()), TimKiemTheoThang);
            TxtTrang.setText(1 + "/" + sotrangCD);
        }
        if (a == 1) {
            trangCD = 1;
            BangadminCD(trangCD, simp.format(DateSearch.getDate()), TimKiemTheoThang);
            TxtTrang.setText(1 + "/" + sotrangCD);
        }
    }//GEN-LAST:event_TrangDauCDMousePressed

    private void NeXtCDMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NeXtCDMousePressed
        SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd");
        if (a == 0) {
            if (trangCD < sotrangCD) {
                trangCD++;
                BangNVCD(trangCD, simp.format(DateSearch.getDate()), TimKiemTheoThang);
                TxtTrang.setText(trangCD + "/" + sotrangCD);
            }
        }
        if (a == 1) {
            if (trangCD < sotrangCD) {
                trangCD++;
                BangadminCD(trangCD, simp.format(DateSearch.getDate()), TimKiemTheoThang);
                TxtTrang.setText(trangCD + "/" + sotrangCD);
            }
        }

    }//GEN-LAST:event_NeXtCDMousePressed

    private void TrangCuoiCDMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangCuoiCDMousePressed
        SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd");
        if (sotrangCD == 0) {

        } else {
            if (a == 0) {
                trangCD = sotrangCD;
                BangNVCD(trangCD, simp.format(DateSearch.getDate()), TimKiemTheoThang);
                TxtTrang.setText(trangCD + "/" + sotrangCD);
            }
            if (a == 1) {
                trangCD = sotrangCD;
                BangadminCD(trangCD, simp.format(DateSearch.getDate()), TimKiemTheoThang);
                TxtTrang.setText(trangCD + "/" + sotrangCD);
            }
        }


    }//GEN-LAST:event_TrangCuoiCDMousePressed

    private void BtnDuyetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDuyetActionPerformed
        DKNghiDAO dao = new DKNghiDAO();
        Date now = new Date();
        SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd");
        dao.Update(Integer.valueOf(TbChuaDuyet.getValueAt(TbChuaDuyet.getSelectedRow(), 0).toString()), simp.format(now));
        JOptionPane.showMessageDialog(null, "Đã Duyệt Đơn Của " + TbChuaDuyet.getValueAt(TbChuaDuyet.getSelectedRow(), 1));

        trangCD = 1;
        trangDD = 1;
        //Load Bảng Chưa Duyệt 
        String CD = "SELECT COUNT(*) FROM DKNghi where TrangThai = 0";
        TimCountCD(CD);
        TrangCD();
        BangadminCD(trangCD, simp.format(DateSearch.getDate()), TimKiemTheoThang);
        //Load Bảng Đã Duyệt
        String DD = "SELECT COUNT(*) FROM DKNghi where TrangThai = 1";
        TimCountDD(DD);
        TrangDD();
        BangadminDD(trangDD, simp.format(DateSearch.getDate()), TimKiemTheoThang);
    }//GEN-LAST:event_BtnDuyetActionPerformed

    int day = 0;

    public void SwitchDay(int Month, int Year) {
        switch (Month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                day = 30;
                break;
            case 2:
                if ((Year % 4 == 0 && Year % 100 != 0) || (Year % 400 == 0)) {
                    day = 29;
                    break;
                } else {
                    day = 28;
                    break;
                }
            default:
                System.out.println("Lỗi");
        }
    }

    private void DateSearchPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_DateSearchPropertyChange
        if (DateSearch.getDate() != null) {
            SimpleDateFormat sdfMonth = new SimpleDateFormat("M");
            SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
            int Month = Integer.valueOf(sdfMonth.format(DateSearch.getDate()));
            int Year = Integer.valueOf(sdfYear.format(DateSearch.getDate()));
            SwitchDay(Month, Year);
            SimpleDateFormat sdfMonthDateFormat_Year = new SimpleDateFormat("yyyy-MM");
            TimKiemTheoThang = sdfMonthDateFormat_Year.format(DateSearch.getDate()) + "-" + day;
            trangCD = 1;
            trangDD = 1;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (a == 0) {
                //Chưa Duyệt
                String CD = "SELECT COUNT(*) FROM DKNghi where TrangThai = 0 And NgayNop >= '" + sdf.format(DateSearch.getDate()) + "' and NgayNop <= '" + TimKiemTheoThang + "'  And IDNV =" + idnv;
                TimCountCD(CD);
                TrangCD();
                BangNVCD(1, sdf.format(DateSearch.getDate()), TimKiemTheoThang);
                //Đã Duyệt  
                String DD = "SELECT COUNT(*) FROM DKNghi where TrangThai = 1 And NgayDuyet >='" + sdf.format(DateSearch.getDate()) + "' and NgayNop <= '" + TimKiemTheoThang + "' And IDNV = " + idnv;
                TimCountDD(DD);
                TrangDD();
                BangNVDD(1, sdf.format(DateSearch.getDate()), TimKiemTheoThang);
            }
            if (a == 1) {
                String CD = "SELECT COUNT(*) FROM DKNghi where TrangThai = 0 And NgayNop >= '" + sdf.format(DateSearch.getDate()) + "' and NgayNop <= '" + TimKiemTheoThang + "'";
                CbxLoaiNghi.setEnabled(false);
                TimCountCD(CD);
                TrangCD();
                BangadminCD(1, sdf.format(DateSearch.getDate()), TimKiemTheoThang);

                //Đã Duyệt   
                String DD = "SELECT COUNT(*) FROM DKNghi where TrangThai = 1  And NgayDuyet >='" + sdf.format(DateSearch.getDate()) + "' and NgayNop <= '" + TimKiemTheoThang + "'";
                TimCountDD(DD);
                TrangDD();
                BangadminDD(1, sdf.format(DateSearch.getDate()), TimKiemTheoThang);
                btnnopdon.setEnabled(false);
            }
            jLabel18.setText(TimKiemTheoThang);
        }

    }//GEN-LAST:event_DateSearchPropertyChange

    //<editor-fold desc=" Evt Mouse">
    private void TrangDauDDMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangDauDDMouseEntered
        TrangDauDD.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_TrangDauDDMouseEntered

    private void TrangDauDDMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangDauDDMouseExited
        TrangDauDD.setBackground(new Color(240, 240, 240));
    }//GEN-LAST:event_TrangDauDDMouseExited

    private void LuiDDMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LuiDDMouseEntered
        LuiDD.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_LuiDDMouseEntered

    private void LuiDDMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LuiDDMouseExited
        LuiDD.setBackground(new Color(240, 240, 240));
    }//GEN-LAST:event_LuiDDMouseExited

    private void TrangCuoiDDMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangCuoiDDMouseEntered
        TrangCuoiDD.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_TrangCuoiDDMouseEntered

    private void TrangCuoiDDMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangCuoiDDMouseExited
        TrangCuoiDD.setBackground(new Color(240, 240, 240));
    }//GEN-LAST:event_TrangCuoiDDMouseExited

    private void NextDDMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NextDDMouseEntered
        NextDD.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_NextDDMouseEntered

    private void NextDDMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NextDDMouseExited
        NextDD.setBackground(new Color(240, 240, 240));
    }//GEN-LAST:event_NextDDMouseExited

    private void LuiDDMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LuiDDMousePressed
        SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd");

        if (a == 0) {
            if (trangDD > 1) {
                trangDD--;
                BangNVDD(trangDD, simp.format(DateSearch.getDate()), TimKiemTheoThang);
                TxtTrang.setText(trangDD + "/" + sotrangDD);
            }
        }
        if (a == 1) {
            if (trangDD > 1) {
                trangDD--;
                BangadminDD(trangDD, simp.format(DateSearch.getDate()), TimKiemTheoThang);
                TxtTrang.setText(trangDD + "/" + sotrangDD);
            }
        }
    }//GEN-LAST:event_LuiDDMousePressed

    private void TrangDauDDMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangDauDDMousePressed
        SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd");
        if (a == 0) {
            trangDD = 1;
            BangNVDD(trangDD, simp.format(DateSearch.getDate()), TimKiemTheoThang);
            TxtTrang.setText(1 + "/" + sotrangDD);
        }
        if (a == 1) {
            trangDD = 1;
            BangadminDD(trangDD, simp.format(DateSearch.getDate()), TimKiemTheoThang);
            TxtTrang.setText(1 + "/" + sotrangDD);
        }
    }//GEN-LAST:event_TrangDauDDMousePressed

    private void TrangCuoiDDMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangCuoiDDMousePressed
        SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd");
        if (sotrangDD == 0) {
        } else {
            if (a == 0) {
                trangDD = sotrangDD;
                BangNVDD(trangDD, simp.format(DateSearch.getDate()), TimKiemTheoThang);
                TxtTrang.setText(trangDD + "/" + sotrangDD);
            }
            if (a == 1) {
                trangDD = sotrangDD;
                BangadminDD(trangDD, simp.format(DateSearch.getDate()), TimKiemTheoThang);
                TxtTrang.setText(trangDD + "/" + sotrangDD);
            }
        }

    }//GEN-LAST:event_TrangCuoiDDMousePressed

    private void NextDDMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NextDDMousePressed
        SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd");
        if (a == 0) {
            if (trangDD < sotrangDD) {
                trangDD++;
                BangNVDD(trangDD, simp.format(DateSearch.getDate()), TimKiemTheoThang);
                TxtTrang.setText(trangDD + "/" + sotrangDD);
            }
        }
        if (a == 1) {
            if (trangDD < sotrangDD) {
                trangDD++;
                BangadminDD(trangDD, simp.format(DateSearch.getDate()), TimKiemTheoThang);
                TxtTrang.setText(trangDD + "/" + sotrangDD);
            }
        }
    }//GEN-LAST:event_NextDDMousePressed

    private void BaoCaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BaoCaoActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(this);
        File f = fileChooser.getSelectedFile();
        String filename = f.getAbsolutePath();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        BaoCao xbc = new BaoCao();
        xbc.BaoCaoDonNghiPhep(filename + ".xlsx", sdf.format(DateSearch.getDate()), TimKiemTheoThang);
    }//GEN-LAST:event_BaoCaoActionPerformed
    //</editor-fold>

    public static void main(String args[]) {
        //<editor-fold >
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DonXinPhep.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DonXinPhep.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DonXinPhep.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DonXinPhep.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DonXinPhep dialog = new DonXinPhep(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton BaoCao;
    private javax.swing.JButton BtnDuyet;
    private javax.swing.JComboBox<String> CbxLoaiNghi;
    private com.toedter.calendar.JDateChooser DateSearch;
    private javax.swing.JPanel LuiCD;
    private javax.swing.JPanel LuiDD;
    private javax.swing.JPanel NeXtCD;
    private javax.swing.JPanel NextDD;
    private javax.swing.JTabbedPane TBPane;
    private javax.swing.JTable TbChuaDuyet;
    private javax.swing.JTable TbDaDuyet;
    private javax.swing.JPanel TrangCuoiCD;
    private javax.swing.JPanel TrangCuoiDD;
    private javax.swing.JPanel TrangDauCD;
    private javax.swing.JPanel TrangDauDD;
    private javax.swing.JTextField TxtChucVu;
    private javax.swing.JTextField TxtDiachi;
    private javax.swing.JTextField TxtHoTen;
    private javax.swing.JTextArea TxtLyDoKhac;
    private javax.swing.JTextField TxtNgaySinh;
    private javax.swing.JTextField TxtPhone;
    private javax.swing.JTextField TxtPhongBan;
    private javax.swing.JTextField TxtTime;
    private javax.swing.JLabel TxtTrang;
    private javax.swing.JLabel TxtTrangDD;
    private javax.swing.JButton btnnopdon;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    // End of variables declaration//GEN-END:variables
}
