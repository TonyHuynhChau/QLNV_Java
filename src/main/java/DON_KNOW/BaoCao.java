/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DON_KNOW;

import Data.ChucVu;
import Data.ClassDiemDanh;
import Data.DKNghi;
import Data.Deparment;
import Data.Employees;
import Data.TypeContract;
import Main.DiemDanh;
import Process.Check;
import Process.ChucVuDAO;
import Process.ContractTFDAO;
import Process.DKNghiDAO;
import Process.DeparmentDAO;
import Process.DiemDanhDAO;
import Process.EmployeesDAO;
import Process.TypeHĐAO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class BaoCao {
//<editor-fold desc=" Báo Cáo Danh Sách Nhân Viên">

    public void XuatBaoCao(String filename) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet("Nhân Viên");

            XSSFRow row = null;
            Cell cell = null;
            //"ID", "Họ Và Tên", "Tuổi ", "Giới Tính", "Email ", "Địa Chỉ","Số ĐT ", "Phòng Ban(ID)", " Chức Vụ(ID)"
            row = spreadsheet.createRow((short) 1);
            row.setHeight((short) 500);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("DANH SÁCH NHÂN VIÊN");

            row = spreadsheet.createRow((short) 2);
            row.setHeight((short) 500);

            //1
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Mã Nhân Viên");

            //2
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Họ và tên");

            //3
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Ngày Sinh");

            //4
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Tuổi");

            //5
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Giới Tính");

            //6
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Email");

            //7
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Địa Chỉ");

            //8
            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("Số điện thoại");

            //9
            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue("Phòng Ban");

            //10
            cell = row.createCell(9, CellType.STRING);
            cell.setCellValue("Chức Vụ");

            //11
            cell = row.createCell(10, CellType.STRING);
            cell.setCellValue("Trạng Thái");

            EmployeesDAO dao = new EmployeesDAO();

            List<Employees> listItem = dao.BaocaoEmployeeses();

            for (int i = 0; i < listItem.size(); i++) {
                Employees e = listItem.get(i);
                row = spreadsheet.createRow((short) 3 + i);
                row.setHeight((short) 400);
                //1
                row.createCell(0).setCellValue(e.getID());

                //2
                row.createCell(1).setCellValue(e.getFullname());

                //3
                row.createCell(2).setCellValue(e.getDate());

                //4
                row.createCell(3).setCellValue(e.getAge().toString());

                //5
                if (e.getSex() == 1) {
                    row.createCell(4).setCellValue("Nam");
                }
                if (e.getSex() == 0) {
                    row.createCell(4).setCellValue("Nữ");
                }

                //6           
                row.createCell(5).setCellValue(e.getEmail());

                //7              
                row.createCell(6).setCellValue(e.getAddress());

                //8
                row.createCell(7).setCellValue(e.getPhone());

                //9
                DeparmentDAO depdao = new DeparmentDAO();
                Deparment dep = depdao.Truy(e.getPhongBan());
                if (dep == null) {
                    row.createCell(8).setCellValue("");
                } else {
                    row.createCell(8).setCellValue(dep.getDeparment());
                }

                //10
                Check ck = new Check();
                ChucVu cv = ck.Truy(e.getChucVu());
                if (cv == null) {
                    row.createCell(9).setCellValue("");
                } else {
                    row.createCell(9).setCellValue(cv.getChucvu());
                }

                //11
                if (e.getTrangThai() == 1) {
                    row.createCell(10).setCellValue("Vẫn Đang Làm");
                } else {
                    row.createCell(10).setCellValue("Đã Nghỉ");
                }

            }

            FileOutputStream out = new FileOutputStream(new File(filename));
            workbook.write(out);
            out.close();
            JOptionPane.showMessageDialog(null, "Lưu Thành Công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    public List<Employees> Them_Bao_Cao_Vao_Jtable(String filename) {
        try {
            File f = new File(filename);
            FileInputStream fis = new FileInputStream(f);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet("Nhân Viên");

            int rowcount = sheet.getPhysicalNumberOfRows();
            for (int i = 3; i <= rowcount; i++) {
                XSSFRow row = sheet.getRow(i);

                int cellcount = row.getPhysicalNumberOfCells();

//Họ Và Tên--------------------------------------------------------------------------------------------------------------------------
                XSSFCell fullnameCell = row.getCell(1);
                String fullname = getvaluecell(fullnameCell);
                if (fullname == null) {
                    break;
                }
//Ngày Sinh--------------------------------------------------------------------------------------------------------------------------
                XSSFCell DateCell = row.getCell(2);
                Date date = getvaluecellDate(DateCell);
                if (date == null) {
                    break;
                }
//Tuổi--------------------------------------------------------------------------------------------------------------------------
                XSSFCell ageceCell = row.getCell(3);
                int ageint = getvaluecellnum(ageceCell);
                if (ageint == -1) {
                    break;
                }
                String age = String.valueOf(ageint);

//Giới Tính--------------------------------------------------------------------------------------------------------------------------
                XSSFCell sexCell = row.getCell(4);
                String sex = getvaluecell(sexCell);
                if (sex == null) {
                    break;
                }

//Email--------------------------------------------------------------------------------------------------------------------------
                XSSFCell EmailCell = row.getCell(5);
                String Email = getvaluecell(EmailCell);
                if (Email == null) {
                    break;
                }

//Địa Chỉ--------------------------------------------------------------------------------------------------------------------------
                XSSFCell diachiCell = row.getCell(6);
                String diachi = getvaluecell(diachiCell);
                if (diachi == null) {
                    break;
                }

//Số ĐT--------------------------------------------------------------------------------------------------------------------------
                XSSFCell phoneCell = row.getCell(7);
                String phone = getvaluecell(phoneCell);
                if (phone == null) {
                    break;
                }

//Phòng Ban--------------------------------------------------------------------------------------------------------------------------
                XSSFCell phongbanCell = row.getCell(8);
                String phongban = getvaluecell(phongbanCell);
                if (phongban == null) {
                    break;
                }

//Chức Vụ-------------------------------------------------------------------------------------------------------------------
                XSSFCell chucvuCell = row.getCell(9);
                String chucvu = getvaluecell(chucvuCell);
                if (chucvu == null) {
                    break;
                }

//--------------------------------------------------------------------------------------------------------------------------
                if (fullname == "" || date == null || age == "" || sex == "" || Email == "" || diachi == "" || phone == "" || phongban == "" || chucvu == "") {
                    break;
                } else {
//Tìm ID Của Phòng Ban---------------------------------------------------------------------------------------------------------------------------------
                    DeparmentDAO depdao = new DeparmentDAO();
                    Deparment dep = depdao.PhongBan(phongban);
                    int idphongban = dep.getID();

//Tìm ID Chức Vụ---------------------------------------------------------------------------------------------------------------------------------
                    Check cvdao = new Check();
                    ChucVu cv = cvdao.chucVu(chucvu);
                    int idchucvu = cv.getID();

                    System.out.println(" ||  " + fullname + " , " + date + " , " + age + " , " + sex + " , " + Email + " , " + diachi + " , " + phone + " , " + phongban + "'" + idphongban + "'" + " , " + chucvu + "'" + idchucvu + "'");

                    System.out.println();

//Insert Dữ Liệu Vào Database---------------------------------------------------------------------------------------------------------------------------------
                    int GioiTinh = -1;
                    if (sex.equals("Nam")) {
                        GioiTinh = 1;
                    }
                    if (sex.equals("Nữ")) {
                        GioiTinh = 0;
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String NgaySinh = sdf.format(date);
                    EmployeesDAO edao = new EmployeesDAO();
                    String NhapNV = "INSERT INTO Employees(FullName,Ngay_Sinh,age,sex,Email,[address],phone,DepID,ChucvuID,TrangThai)VALUES(?,?,?,?,?,?,?,?,?,?)";

                    edao.INSERTNV(NhapNV, fullname, NgaySinh, age, GioiTinh, Email, diachi, phone, idphongban, idchucvu, 1);
                }

            }

            EmployeesDAO employeesDAO = new EmployeesDAO();
            JOptionPane.showMessageDialog(null, "Mở Thành Công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return null;
    }

    public static String getvaluecell(XSSFCell cell) {
        if (cell != null) {
            switch (cell.getCellType()) {
                case STRING:
                    return cell.getStringCellValue();
                case NUMERIC:
                    return String.valueOf(cell.getNumericCellValue());
                default:
                    return cell.getStringCellValue();
            }
        }
        return null;
    }

    public static Date getvaluecellDate(XSSFCell cell) {
        if (cell != null) {
            switch (cell.getCellType()) {
                case STRING:
                    return cell.getDateCellValue();
                default:
                    return cell.getDateCellValue();
            }
        }
        return null;
    }

    public static int getvaluecellnum(XSSFCell cell) {
        if (cell != null) {
            switch (cell.getCellType()) {
                case NUMERIC:
                    return (int) cell.getNumericCellValue();
                default:
                    return (int) cell.getNumericCellValue();
            }
        }
        return -1;
    }
//</editor-fold>

//<editor-fold desc=" Bao Cao Don Nghi Phep">
    public void BaoCaoDonNghiPhep(String filename, String date, String CuoiThang) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet("Đơn Nghỉ Phép");

            XSSFRow row = null;
            Cell cell = null;
            //"STT", "Họ Và Tên", "Ngày Sinh ", "Phone", "Phòng Ban", "Loại Nghỉ","Số Ngày Nghỉ", "Ngày Nộp Đơn", "Trạng Thái"
            row = spreadsheet.createRow((short) 1);
            row.setHeight((short) 500);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("DANH SÁCH ĐƠN XIN NGHỈ PHÉP");

            row = spreadsheet.createRow((short) 2);
            row.setHeight((short) 500);

            //1
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");

            //2
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Họ và tên");

            //3
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Ngày Sinh");

            //4
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Số Điện Thoại");

            //5
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Phòng Ban");

            //6
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Loại Nghỉ");

            //7
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Số Ngày Nghỉ");

            //8
            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("Ngày Nộp Đơn");

            //9
            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue("Ngày Duyệt Đơn");

            //10
            cell = row.createCell(9, CellType.STRING);
            cell.setCellValue("Trạng Thái");

            DKNghiDAO dao = new DKNghiDAO();
            String str = "SELECT * FROM DKNghi WHERE NgayNop >= '" + date + "' and NgayNop <= '" + CuoiThang + "'";
            List<DKNghi> listItem = dao.GetAdmin(str);

            for (int i = 0; i < listItem.size(); i++) {
                DKNghi e = listItem.get(i);
                row = spreadsheet.createRow((short) 3 + i);
                row.setHeight((short) 400);
                //1
                row.createCell(0).setCellValue(e.getSTT());

                //2
                EmployeesDAO employeesDAO = new EmployeesDAO();
                Employees employees = employeesDAO.Find(e.getIDNV());
                row.createCell(1).setCellValue(employees.getFullname());

                //3
                row.createCell(2).setCellValue(e.getDate());

                //4
                row.createCell(3).setCellValue(e.getPhone());

                //5
                row.createCell(4).setCellValue(e.getDepID());

                //6           
                row.createCell(5).setCellValue(e.getLoaiNghi());

                //7              
                row.createCell(6).setCellValue(e.getThoiHan());

                //8
                row.createCell(7).setCellValue(e.getNgayNopDon());

                //9
                row.createCell(8).setCellValue(e.getNgayDuyetDon());

                //10
                if (e.getTrangThai() == 0) {
                    row.createCell(9).setCellValue("Chưa Duyệt");
                }
                if (e.getTrangThai() == 1) {
                    row.createCell(9).setCellValue("Đã Duyệt");
                }
            }

            FileOutputStream out = new FileOutputStream(new File(filename));
            workbook.write(out);
            out.close();
            JOptionPane.showMessageDialog(null, "Lưu Thành Công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }
    //</editor-fold>

//<editor-fold desc=" Bao Cao Hợp Đồng">
    public void BaoCaoHD(String filename) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet("Danh Sách Hợp Đồng");

            XSSFRow row = null;
            Cell cell = null;

            row = spreadsheet.createRow((short) 1);
            row.setHeight((short) 500);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("DANH SÁCH ĐƠN XIN NGHỈ PHÉP");

            row = spreadsheet.createRow((short) 2);
            row.setHeight((short) 500);

            //1
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Mã Hợp Đồng");

            //2
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Họ và tên");

            //3
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Lương");

            //4
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Ngày Bắt Đầu");

            //5
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Ngày Kết Thúc");

            //6
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Loại Hợp Đông");

            ContractTFDAO dao = new ContractTFDAO();

            List<ContractTF> listItem = dao.GetHD();

            for (int i = 0; i < listItem.size(); i++) {
                ContractTF e = listItem.get(i);
                row = spreadsheet.createRow((short) 3 + i);
                row.setHeight((short) 400);
                //1
                row.createCell(0).setCellValue(e.getContractID());

                //2
                EmployeesDAO employeesDAO = new EmployeesDAO();
                Employees employees = employeesDAO.Find(Integer.valueOf(e.getID()));
                row.createCell(1).setCellValue(employees.getFullname());

                //3
                row.createCell(2).setCellValue(e.getSalary());

                //4
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                row.createCell(3).setCellValue(sdf.format(e.getDayStar()));

                //5
                row.createCell(4).setCellValue(sdf.format(e.getDayEnd()));

                //6           
                TypeHĐAO typeHĐAO = new TypeHĐAO();
                TypeContract typeContract = typeHĐAO.getHD(Integer.valueOf(e.getTypeID()));
                row.createCell(5).setCellValue(typeContract.getNameType());
            }

            FileOutputStream out = new FileOutputStream(new File(filename));
            workbook.write(out);
            out.close();
            JOptionPane.showMessageDialog(null, "Lưu Thành Công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }
    //</editor-fold>

//<editor-fold desc=" Bao Cao Điểm Danh">
    public void BaoCaoDiemDanh(String filename, String date) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet("Đơn Nghỉ Phép");

            XSSFRow row = null;
            Cell cell = null;
            //"STT", "Họ Và Tên", "Ngày Sinh ", "Phone", "Phòng Ban", "Loại Nghỉ","Số Ngày Nghỉ", "Ngày Nộp Đơn", "Trạng Thái"
            row = spreadsheet.createRow((short) 1);
            row.setHeight((short) 500);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("DANH SÁCH Điểm Danh");

            row = spreadsheet.createRow((short) 2);
            row.setHeight((short) 500);

            //1
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");

            //2
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Họ Và Tên");

            //3
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Ngày Điểm Danh");

            //4
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Email");

            //5
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Số Điện Thoại");

            //6
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Buổi Sáng");

            //7
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Buổi Chiều");

            //8
            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("Ghi Chú");
//
            DiemDanhDAO dao = new DiemDanhDAO();

            List<ClassDiemDanh> listItem = dao.BaoCaoDiemDanhs(date);

            for (int i = 0; i < listItem.size(); i++) {
                ClassDiemDanh e = listItem.get(i);
                row = spreadsheet.createRow((short) 3 + i);
                row.setHeight((short) 400);
                //1
                row.createCell(0).setCellValue(e.getIdnv());

                //2
                row.createCell(1).setCellValue(e.getName());

                //3
                row.createCell(2).setCellValue(e.getAttendance_date());

                //4
                row.createCell(3).setCellValue(e.getEmail());

                //5
                row.createCell(4).setCellValue(e.getPhone());

                //6           
                row.createCell(5).setCellValue(e.getFirst_time());

                //7              
                row.createCell(6).setCellValue(e.getSecond_time());

                //8
                row.createCell(7).setCellValue(e.getNote());

            }

            FileOutputStream out = new FileOutputStream(new File(filename));
            workbook.write(out);
            out.close();
            JOptionPane.showMessageDialog(null, "Lưu Thành Công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }
    //</editor-fold>
}
