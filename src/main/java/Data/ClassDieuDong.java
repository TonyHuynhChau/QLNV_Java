package Data;

public class ClassDieuDong {

    private int ID;
    private int idnv;
    private String Ngay_Dieu_Dong;
    private String PhongBanCu;
    private String PhongBanmoi;
    private String ChucVuCu;
    private String ChucVuMoi;
    private String LyDo;

    public ClassDieuDong() {
    }

    public ClassDieuDong(int ID, int idnv, String Ngay_Dieu_Dong, String PhongBanCu, String PhongBanmoi, String ChucVuCu, String ChucVuMoi, String LyDo) {
        this.ID = ID;
        this.idnv = idnv;
        this.Ngay_Dieu_Dong = Ngay_Dieu_Dong;
        this.PhongBanCu = PhongBanCu;
        this.PhongBanmoi = PhongBanmoi;
        this.ChucVuCu = ChucVuCu;
        this.ChucVuMoi = ChucVuMoi;
        this.LyDo = LyDo;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIdnv() {
        return idnv;
    }

    public void setIdnv(int idnv) {
        this.idnv = idnv;
    }

    public String getNgay_Dieu_Dong() {
        return Ngay_Dieu_Dong;
    }

    public void setNgay_Dieu_Dong(String Ngay_Dieu_Dong) {
        this.Ngay_Dieu_Dong = Ngay_Dieu_Dong;
    }

    public String getPhongBanCu() {
        return PhongBanCu;
    }

    public void setPhongBanCu(String PhongBanCu) {
        this.PhongBanCu = PhongBanCu;
    }

    public String getPhongBanmoi() {
        return PhongBanmoi;
    }

    public void setPhongBanmoi(String PhongBanmoi) {
        this.PhongBanmoi = PhongBanmoi;
    }

    public String getChucVuCu() {
        return ChucVuCu;
    }

    public void setChucVuCu(String ChucVuCu) {
        this.ChucVuCu = ChucVuCu;
    }

    public String getChucVuMoi() {
        return ChucVuMoi;
    }

    public void setChucVuMoi(String ChucVuMoi) {
        this.ChucVuMoi = ChucVuMoi;
    }

    public String getLyDo() {
        return LyDo;
    }

    public void setLyDo(String LyDo) {
        this.LyDo = LyDo;
    }

   
}
