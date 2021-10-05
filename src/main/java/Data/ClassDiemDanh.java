/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.util.Date;

public class ClassDiemDanh {

    private int idnv;
    private String Name;
    private String attendance_date;
    private String Email;
    private String phone;
    private String first_time;
    private String second_time;
    private String note;

    public ClassDiemDanh() {
    }

    public ClassDiemDanh(int idnv, String Name, String attendance_date, String Email, String phone, String first_time, String second_time, String note) {
        this.idnv = idnv;
        this.Name = Name;
        this.attendance_date = attendance_date;
        this.Email = Email;
        this.phone = phone;
        this.first_time = first_time;
        this.second_time = second_time;
        this.note = note;
    }

    public int getIdnv() {
        return idnv;
    }

    public void setIdnv(int idnv) {
        this.idnv = idnv;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getAttendance_date() {
        return attendance_date;
    }

    public void setAttendance_date(String attendance_date) {
        this.attendance_date = attendance_date;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirst_time() {
        return first_time;
    }

    public void setFirst_time(String first_time) {
        this.first_time = first_time;
    }

    public String getSecond_time() {
        return second_time;
    }

    public void setSecond_time(String second_time) {
        this.second_time = second_time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

  

}
