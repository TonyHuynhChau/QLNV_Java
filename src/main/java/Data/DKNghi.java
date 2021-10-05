/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *
 * @author ASUS
 */
public class DKNghi {

    private int STT;
    private int IDNV;
    private String Phone;
    private String Date;
    private String LoaiNghi;
    private String ThoiHan;
    private String NgayNopDon;
    private String NgayDuyetDon;
    private String DepID;
    private int TrangThai;

    public DKNghi() {
    }

    public DKNghi(int STT, int IDNV, String Phone, String Date, String LoaiNghi, String ThoiHan, String NgayNopDon, String NgayDuyetDon, String DepID, int TrangThai) {
        this.STT = STT;
        this.IDNV = IDNV;
        this.Phone = Phone;
        this.Date = Date;
        this.LoaiNghi = LoaiNghi;
        this.ThoiHan = ThoiHan;
        this.NgayNopDon = NgayNopDon;
        this.NgayDuyetDon = NgayDuyetDon;
        this.DepID = DepID;
        this.TrangThai = TrangThai;
    }

    public int getSTT() {
        return STT;
    }

    public void setSTT(int STT) {
        this.STT = STT;
    }

    public int getIDNV() {
        return IDNV;
    }

    public void setIDNV(int IDNV) {
        this.IDNV = IDNV;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getLoaiNghi() {
        return LoaiNghi;
    }

    public void setLoaiNghi(String LoaiNghi) {
        this.LoaiNghi = LoaiNghi;
    }

    public String getThoiHan() {
        return ThoiHan;
    }

    public void setThoiHan(String ThoiHan) {
        this.ThoiHan = ThoiHan;
    }

    public String getNgayNopDon() {
        return NgayNopDon;
    }

    public void setNgayNopDon(String NgayNopDon) {
        this.NgayNopDon = NgayNopDon;
    }

    public String getNgayDuyetDon() {
        return NgayDuyetDon;
    }

    public void setNgayDuyetDon(String NgayDuyetDon) {
        this.NgayDuyetDon = NgayDuyetDon;
    }

    public String getDepID() {
        return DepID;
    }

    public void setDepID(String DepID) {
        this.DepID = DepID;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

   
}
