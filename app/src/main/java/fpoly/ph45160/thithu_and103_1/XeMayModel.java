package fpoly.ph45160.thithu_and103_1;

public class XeMayModel {
    private String _id;
    private String ten_xe_ph45160;
    private String mau_sac_ph45160;
    private double gia_ban_ph45160;
    private String mo_ta_ph45160;
    private String hinh_anh_ph45160;

    public XeMayModel(String _id,String ten_xe_ph45160, String mau_sac_ph45160, double gia_ban_ph45160, String mo_ta_ph45160, String hinh_anh_ph45160) {
        this.ten_xe_ph45160 = ten_xe_ph45160;
        this.mau_sac_ph45160 = mau_sac_ph45160;
        this.gia_ban_ph45160 = gia_ban_ph45160;
        this.mo_ta_ph45160 = mo_ta_ph45160;
        this.hinh_anh_ph45160 = hinh_anh_ph45160;
    }

    public XeMayModel(String ten_xe_ph45160, String mau_sac_ph45160, double gia_ban_ph45160, String mo_ta_ph45160, String hinh_anh_ph45160) {
        this.ten_xe_ph45160 = ten_xe_ph45160;
        this.mau_sac_ph45160 = mau_sac_ph45160;
        this.gia_ban_ph45160 = gia_ban_ph45160;
        this.mo_ta_ph45160 = mo_ta_ph45160;
        this.hinh_anh_ph45160 = hinh_anh_ph45160;
    }

    public XeMayModel() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTen_xe_ph45160() {
        return ten_xe_ph45160;
    }

    public void setTen_xe_ph45160(String ten_xe_ph45160) {
        this.ten_xe_ph45160 = ten_xe_ph45160;
    }

    public String getMau_sac_ph45160() {
        return mau_sac_ph45160;
    }

    public void setMau_sac_ph45160(String mau_sac_ph45160) {
        this.mau_sac_ph45160 = mau_sac_ph45160;
    }

    public double getGia_ban_ph45160() {
        return gia_ban_ph45160;
    }

    public void setGia_ban_ph45160(double gia_ban_ph45160) {
        this.gia_ban_ph45160 = gia_ban_ph45160;
    }

    public String getMo_ta_ph45160() {
        return mo_ta_ph45160;
    }

    public void setMo_ta_ph45160(String mo_ta_ph45160) {
        this.mo_ta_ph45160 = mo_ta_ph45160;
    }

    public String getHinh_anh_ph45160() {
        return hinh_anh_ph45160;
    }

    public void setHinh_anh_ph45160(String hinh_anh_ph45160) {
        this.hinh_anh_ph45160 = hinh_anh_ph45160;
    }
}
