package Data;

import javax.swing.text.PasswordView;

public class Employees {

    private int ID;
    private String fullname;
    private String date;
    private String age;
    private int sex;
    private String email;
    private String address;
    private String phone;
    private int ChucVu;
    private int PhongBan;
    private int TrangThai;

    public Employees() {
    }

    public Employees(int ID, String fullname, String date, String age, int sex, String email, String address, String phone, int ChucVu, int PhongBan, int TrangThai) {
        this.ID = ID;
        this.fullname = fullname;
        this.date = date;
        this.age = age;
        this.sex = sex;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.ChucVu = ChucVu;
        this.PhongBan = PhongBan;
        this.TrangThai = TrangThai;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getChucVu() {
        return ChucVu;
    }

    public void setChucVu(int ChucVu) {
        this.ChucVu = ChucVu;
    }

    public int getPhongBan() {
        return PhongBan;
    }

    public void setPhongBan(int PhongBan) {
        this.PhongBan = PhongBan;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

   
}
