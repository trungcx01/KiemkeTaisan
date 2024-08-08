package com.prj.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link com.prj.domain.Taisan} entity.
 */
public class TaisanDTO implements Serializable {

    private String id;

    @NotNull
    private String maTaisan;

    @NotNull
    private String tenTaisan;

    @NotNull
    private String tenRutgon;

    private String model;

    private String serial;

    @NotNull
    private Integer loaiTaisan;

    private String mota;

    @NotNull
    private LocalDate ngayNhan;

    private LocalDate ngaySudung;

    private LocalDate ngaySanxuat;

    private Integer thoigianSudung;

    private LocalDate ngayHethan;

    private Integer haomon;

    private BigDecimal nguyengia;

    private String soHopdongMua;

    private String soHoadonMua;

    private LocalDate ngayHoadon;

    private Integer trangthai;

    private String hangSanxuat;

    private String nuocSanxuat;

    private Double congsuatSudung;

    private Double dientichSudung;

    private String donviTinh;

    private BigDecimal giatriConlai;

    private String nguon;

    private String vitri;

    private String donviQuanly;

    private String donviSudung;

    @NotNull
    private Integer soluong;


    private String nguoiQuanlyId;

    private String nguoiQuanlyTen;

    private String danhmucTaisanId;

    private String danhmucTaisanTen;

    private String donviCungcapId;

    private String donviCungcapTen;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaTaisan() {
        return maTaisan;
    }

    public void setMaTaisan(String maTaisan) {
        this.maTaisan = maTaisan;
    }

    public String getTenTaisan() {
        return tenTaisan;
    }

    public void setTenTaisan(String tenTaisan) {
        this.tenTaisan = tenTaisan;
    }

    public String getTenRutgon() {
        return tenRutgon;
    }

    public void setTenRutgon(String tenRutgon) {
        this.tenRutgon = tenRutgon;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Integer getLoaiTaisan() {
        return loaiTaisan;
    }

    public void setLoaiTaisan(Integer loaiTaisan) {
        this.loaiTaisan = loaiTaisan;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public LocalDate getNgayNhan() {
        return ngayNhan;
    }

    public void setNgayNhan(LocalDate ngayNhan) {
        this.ngayNhan = ngayNhan;
    }

    public LocalDate getNgaySudung() {
        return ngaySudung;
    }

    public void setNgaySudung(LocalDate ngaySudung) {
        this.ngaySudung = ngaySudung;
    }

    public LocalDate getNgaySanxuat() {
        return ngaySanxuat;
    }

    public void setNgaySanxuat(LocalDate ngaySanxuat) {
        this.ngaySanxuat = ngaySanxuat;
    }

    public Integer getThoigianSudung() {
        return thoigianSudung;
    }

    public void setThoigianSudung(Integer thoigianSudung) {
        this.thoigianSudung = thoigianSudung;
    }

    public LocalDate getNgayHethan() {
        return ngayHethan;
    }

    public void setNgayHethan(LocalDate ngayHethan) {
        this.ngayHethan = ngayHethan;
    }

    public Integer getHaomon() {
        return haomon;
    }

    public void setHaomon(Integer haomon) {
        this.haomon = haomon;
    }

    public BigDecimal getNguyengia() {
        return nguyengia;
    }

    public void setNguyengia(BigDecimal nguyengia) {
        this.nguyengia = nguyengia;
    }

    public String getSoHopdongMua() {
        return soHopdongMua;
    }

    public void setSoHopdongMua(String soHopdongMua) {
        this.soHopdongMua = soHopdongMua;
    }

    public String getSoHoadonMua() {
        return soHoadonMua;
    }

    public void setSoHoadonMua(String soHoadonMua) {
        this.soHoadonMua = soHoadonMua;
    }

    public LocalDate getNgayHoadon() {
        return ngayHoadon;
    }

    public void setNgayHoadon(LocalDate ngayHoadon) {
        this.ngayHoadon = ngayHoadon;
    }

    public Integer getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(Integer trangthai) {
        this.trangthai = trangthai;
    }

    public String getHangSanxuat() {
        return hangSanxuat;
    }

    public void setHangSanxuat(String hangSanxuat) {
        this.hangSanxuat = hangSanxuat;
    }

    public String getNuocSanxuat() {
        return nuocSanxuat;
    }

    public void setNuocSanxuat(String nuocSanxuat) {
        this.nuocSanxuat = nuocSanxuat;
    }

    public Double getCongsuatSudung() {
        return congsuatSudung;
    }

    public void setCongsuatSudung(Double congsuatSudung) {
        this.congsuatSudung = congsuatSudung;
    }

    public Double getDientichSudung() {
        return dientichSudung;
    }

    public void setDientichSudung(Double dientichSudung) {
        this.dientichSudung = dientichSudung;
    }

    public String getDonviTinh() {
        return donviTinh;
    }

    public void setDonviTinh(String donviTinh) {
        this.donviTinh = donviTinh;
    }

    public BigDecimal getGiatriConlai() {
        return giatriConlai;
    }

    public void setGiatriConlai(BigDecimal giatriConlai) {
        this.giatriConlai = giatriConlai;
    }

    public String getNguon() {
        return nguon;
    }

    public void setNguon(String nguon) {
        this.nguon = nguon;
    }

    public String getVitri() {
        return vitri;
    }

    public void setVitri(String vitri) {
        this.vitri = vitri;
    }

    public String getDonviQuanly() {
        return donviQuanly;
    }

    public void setDonviQuanly(String donviQuanly) {
        this.donviQuanly = donviQuanly;
    }

    public String getDonviSudung() {
        return donviSudung;
    }

    public void setDonviSudung(String donviSudung) {
        this.donviSudung = donviSudung;
    }

    public Integer getSoluong() {
        return soluong;
    }

    public void setSoluong(Integer soluong) {
        this.soluong = soluong;
    }

    public String getNguoiQuanlyId() {
        return nguoiQuanlyId;
    }

    public void setNguoiQuanlyId(String nhanvienId) {
        this.nguoiQuanlyId = nhanvienId;
    }

    public String getNguoiQuanlyTen() {
        return nguoiQuanlyTen;
    }

    public void setNguoiQuanlyTen(String nhanvienTen) {
        this.nguoiQuanlyTen = nhanvienTen;
    }

    public String getDanhmucTaisanId() {
        return danhmucTaisanId;
    }

    public void setDanhmucTaisanId(String danhmucTaisanId) {
        this.danhmucTaisanId = danhmucTaisanId;
    }

    public String getDanhmucTaisanTen() {
        return danhmucTaisanTen;
    }

    public void setDanhmucTaisanTen(String danhmucTaisanTen) {
        this.danhmucTaisanTen = danhmucTaisanTen;
    }

    public String getDonviCungcapId() {
        return donviCungcapId;
    }

    public void setDonviCungcapId(String donviCungcapId) {
        this.donviCungcapId = donviCungcapId;
    }

    public String getDonviCungcapTen() {
        return donviCungcapTen;
    }

    public void setDonviCungcapTen(String donviCungcapTen) {
        this.donviCungcapTen = donviCungcapTen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TaisanDTO taisanDTO = (TaisanDTO) o;
        if (taisanDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), taisanDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TaisanDTO{" +
            "id=" + getId() +
            ", maTaisan='" + getMaTaisan() + "'" +
            ", tenTaisan='" + getTenTaisan() + "'" +
            ", tenRutgon='" + getTenRutgon() + "'" +
            ", model='" + getModel() + "'" +
            ", serial='" + getSerial() + "'" +
            ", loaiTaisan=" + getLoaiTaisan() +
            ", mota='" + getMota() + "'" +
            ", ngayNhan='" + getNgayNhan() + "'" +
            ", ngaySudung='" + getNgaySudung() + "'" +
            ", ngaySanxuat='" + getNgaySanxuat() + "'" +
            ", thoigianSudung=" + getThoigianSudung() +
            ", ngayHethan='" + getNgayHethan() + "'" +
            ", haomon=" + getHaomon() +
            ", nguyengia=" + getNguyengia() +
            ", soHopdongMua='" + getSoHopdongMua() + "'" +
            ", soHoadonMua='" + getSoHoadonMua() + "'" +
            ", ngayHoadon='" + getNgayHoadon() + "'" +
            ", trangthai=" + getTrangthai() +
            ", hangSanxuat='" + getHangSanxuat() + "'" +
            ", nuocSanxuat='" + getNuocSanxuat() + "'" +
            ", congsuatSudung=" + getCongsuatSudung() +
            ", dientichSudung=" + getDientichSudung() +
            ", donviTinh='" + getDonviTinh() + "'" +
            ", giatriConlai=" + getGiatriConlai() +
            ", nguon='" + getNguon() + "'" +
            ", vitri='" + getVitri() + "'" +
            ", donviQuanly='" + getDonviQuanly() + "'" +
            ", donviSudung='" + getDonviSudung() + "'" +
            ", soluong=" + getSoluong() +
            ", nguoiQuanlyId=" + getNguoiQuanlyId() +
            ", nguoiQuanlyTen='" + getNguoiQuanlyTen() + "'" +
            ", danhmucTaisanId=" + getDanhmucTaisanId() +
            ", danhmucTaisanTen='" + getDanhmucTaisanTen() + "'" +
            ", donviCungcapId=" + getDonviCungcapId() +
            ", donviCungcapTen='" + getDonviCungcapTen() + "'" +
            "}";
    }
}
