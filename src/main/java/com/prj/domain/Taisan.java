package com.prj.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Taisan.
 */
@Document(collection = "taisan")
public class Taisan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("ma_taisan")
    private String maTaisan;

    @NotNull
    @Field("ten_taisan")
    private String tenTaisan;

    @NotNull
    @Field("ten_rutgon")
    private String tenRutgon;

    @Field("model")
    private String model;

    @Field("serial")
    private String serial;

    @NotNull
    @Field("loai_taisan")
    private Integer loaiTaisan;

    @Field("mota")
    private String mota;

    @NotNull
    @Field("ngay_nhan")
    private LocalDate ngayNhan;

    @Field("ngay_sudung")
    private LocalDate ngaySudung;

    @Field("ngay_sanxuat")
    private LocalDate ngaySanxuat;

    @Field("thoigian_sudung")
    private Integer thoigianSudung;

    @Field("ngay_hethan")
    private LocalDate ngayHethan;

    @Field("haomon")
    private Integer haomon;

    @Field("nguyengia")
    private BigDecimal nguyengia;

    @Field("so_hopdong_mua")
    private String soHopdongMua;

    @Field("so_hoadon_mua")
    private String soHoadonMua;

    @Field("ngay_hoadon")
    private LocalDate ngayHoadon;

    @Field("trangthai")
    private Integer trangthai;

    @Field("hang_sanxuat")
    private String hangSanxuat;

    @Field("nuoc_sanxuat")
    private String nuocSanxuat;

    @Field("congsuat_sudung")
    private Double congsuatSudung;

    @Field("dientich_sudung")
    private Double dientichSudung;

    @Field("donvi_tinh")
    private String donviTinh;

    @Field("giatri_conlai")
    private Integer giatriConlai;

    @Field("tang_nguyengia")
    private BigDecimal tangNguyengia;

    @Field("nguon")
    private String nguon;

    @Field("vitri")
    private String vitri;

    @Field("donvi_quanly")
    private String donviQuanly;

    @Field("donvi_sudung")
    private String donviSudung;

    @NotNull
    @Field("soluong")
    private Integer soluong;

    @DBRef
    @Field("nguoiQuanly")
    private Nhanvien nguoiQuanly;

    @DBRef
    @Field("banghiKiemke")
    private Set<BanghiKiemke> banghiKiemkes = new HashSet<>();

    @DBRef
    @Field("danhmucTaisan")
    @JsonIgnoreProperties("taisans")
    private DanhmucTaisan danhmucTaisan;

    @DBRef
    @Field("donviCungcap")
    @JsonIgnoreProperties("taisans")
    private DonviCungcap donviCungcap;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaTaisan() {
        return maTaisan;
    }

    public Taisan maTaisan(String maTaisan) {
        this.maTaisan = maTaisan;
        return this;
    }

    public void setMaTaisan(String maTaisan) {
        this.maTaisan = maTaisan;
    }

    public String getTenTaisan() {
        return tenTaisan;
    }

    public Taisan tenTaisan(String tenTaisan) {
        this.tenTaisan = tenTaisan;
        return this;
    }

    public void setTenTaisan(String tenTaisan) {
        this.tenTaisan = tenTaisan;
    }

    public String getTenRutgon() {
        return tenRutgon;
    }

    public Taisan tenRutgon(String tenRutgon) {
        this.tenRutgon = tenRutgon;
        return this;
    }

    public void setTenRutgon(String tenRutgon) {
        this.tenRutgon = tenRutgon;
    }

    public String getModel() {
        return model;
    }

    public Taisan model(String model) {
        this.model = model;
        return this;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerial() {
        return serial;
    }

    public Taisan serial(String serial) {
        this.serial = serial;
        return this;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Integer getLoaiTaisan() {
        return loaiTaisan;
    }

    public Taisan loaiTaisan(Integer loaiTaisan) {
        this.loaiTaisan = loaiTaisan;
        return this;
    }

    public void setLoaiTaisan(Integer loaiTaisan) {
        this.loaiTaisan = loaiTaisan;
    }

    public String getMota() {
        return mota;
    }

    public Taisan mota(String mota) {
        this.mota = mota;
        return this;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public LocalDate getNgayNhan() {
        return ngayNhan;
    }

    public Taisan ngayNhan(LocalDate ngayNhan) {
        this.ngayNhan = ngayNhan;
        return this;
    }

    public void setNgayNhan(LocalDate ngayNhan) {
        this.ngayNhan = ngayNhan;
    }

    public LocalDate getNgaySudung() {
        return ngaySudung;
    }

    public Taisan ngaySudung(LocalDate ngaySudung) {
        this.ngaySudung = ngaySudung;
        return this;
    }

    public void setNgaySudung(LocalDate ngaySudung) {
        this.ngaySudung = ngaySudung;
    }

    public LocalDate getNgaySanxuat() {
        return ngaySanxuat;
    }

    public Taisan ngaySanxuat(LocalDate ngaySanxuat) {
        this.ngaySanxuat = ngaySanxuat;
        return this;
    }

    public void setNgaySanxuat(LocalDate ngaySanxuat) {
        this.ngaySanxuat = ngaySanxuat;
    }

    public Integer getThoigianSudung() {
        return thoigianSudung;
    }

    public Taisan thoigianSudung(Integer thoigianSudung) {
        this.thoigianSudung = thoigianSudung;
        return this;
    }

    public void setThoigianSudung(Integer thoigianSudung) {
        this.thoigianSudung = thoigianSudung;
    }

    public LocalDate getNgayHethan() {
        return ngayHethan;
    }

    public Taisan ngayHethan(LocalDate ngayHethan) {
        this.ngayHethan = ngayHethan;
        return this;
    }

    public void setNgayHethan(LocalDate ngayHethan) {
        this.ngayHethan = ngayHethan;
    }

    public Integer getHaomon() {
        return haomon;
    }

    public Taisan haomon(Integer haomon) {
        this.haomon = haomon;
        return this;
    }

    public void setHaomon(Integer haomon) {
        this.haomon = haomon;
    }

    public BigDecimal getNguyengia() {
        return nguyengia;
    }

    public Taisan nguyengia(BigDecimal nguyengia) {
        this.nguyengia = nguyengia;
        return this;
    }

    public void setNguyengia(BigDecimal nguyengia) {
        this.nguyengia = nguyengia;
    }

    public String getSoHopdongMua() {
        return soHopdongMua;
    }

    public Taisan soHopdongMua(String soHopdongMua) {
        this.soHopdongMua = soHopdongMua;
        return this;
    }

    public void setSoHopdongMua(String soHopdongMua) {
        this.soHopdongMua = soHopdongMua;
    }

    public String getSoHoadonMua() {
        return soHoadonMua;
    }

    public Taisan soHoadonMua(String soHoadonMua) {
        this.soHoadonMua = soHoadonMua;
        return this;
    }

    public void setSoHoadonMua(String soHoadonMua) {
        this.soHoadonMua = soHoadonMua;
    }

    public LocalDate getNgayHoadon() {
        return ngayHoadon;
    }

    public Taisan ngayHoadon(LocalDate ngayHoadon) {
        this.ngayHoadon = ngayHoadon;
        return this;
    }

    public void setNgayHoadon(LocalDate ngayHoadon) {
        this.ngayHoadon = ngayHoadon;
    }

    public Integer getTrangthai() {
        return trangthai;
    }

    public Taisan trangthai(Integer trangthai) {
        this.trangthai = trangthai;
        return this;
    }

    public void setTrangthai(Integer trangthai) {
        this.trangthai = trangthai;
    }

    public String getHangSanxuat() {
        return hangSanxuat;
    }

    public Taisan hangSanxuat(String hangSanxuat) {
        this.hangSanxuat = hangSanxuat;
        return this;
    }

    public void setHangSanxuat(String hangSanxuat) {
        this.hangSanxuat = hangSanxuat;
    }

    public String getNuocSanxuat() {
        return nuocSanxuat;
    }

    public Taisan nuocSanxuat(String nuocSanxuat) {
        this.nuocSanxuat = nuocSanxuat;
        return this;
    }

    public void setNuocSanxuat(String nuocSanxuat) {
        this.nuocSanxuat = nuocSanxuat;
    }

    public Double getCongsuatSudung() {
        return congsuatSudung;
    }

    public Taisan congsuatSudung(Double congsuatSudung) {
        this.congsuatSudung = congsuatSudung;
        return this;
    }

    public void setCongsuatSudung(Double congsuatSudung) {
        this.congsuatSudung = congsuatSudung;
    }

    public Double getDientichSudung() {
        return dientichSudung;
    }

    public Taisan dientichSudung(Double dientichSudung) {
        this.dientichSudung = dientichSudung;
        return this;
    }

    public void setDientichSudung(Double dientichSudung) {
        this.dientichSudung = dientichSudung;
    }

    public String getDonviTinh() {
        return donviTinh;
    }

    public Taisan donviTinh(String donviTinh) {
        this.donviTinh = donviTinh;
        return this;
    }

    public void setDonviTinh(String donviTinh) {
        this.donviTinh = donviTinh;
    }

    public Integer getGiatriConlai() {
        return giatriConlai;
    }

    public Taisan giatriConlai(Integer giatriConlai) {
        this.giatriConlai = giatriConlai;
        return this;
    }

    public void setGiatriConlai(Integer giatriConlai) {
        this.giatriConlai = giatriConlai;
    }

    public BigDecimal getTangNguyengia() {
        return tangNguyengia;
    }

    public Taisan tangNguyengia(BigDecimal tangNguyengia) {
        this.tangNguyengia = tangNguyengia;
        return this;
    }

    public void setTangNguyengia(BigDecimal tangNguyengia) {
        this.tangNguyengia = tangNguyengia;
    }

    public String getNguon() {
        return nguon;
    }

    public Taisan nguon(String nguon) {
        this.nguon = nguon;
        return this;
    }

    public void setNguon(String nguon) {
        this.nguon = nguon;
    }

    public String getVitri() {
        return vitri;
    }

    public Taisan vitri(String vitri) {
        this.vitri = vitri;
        return this;
    }

    public void setVitri(String vitri) {
        this.vitri = vitri;
    }

    public String getDonviQuanly() {
        return donviQuanly;
    }

    public Taisan donviQuanly(String donviQuanly) {
        this.donviQuanly = donviQuanly;
        return this;
    }

    public void setDonviQuanly(String donviQuanly) {
        this.donviQuanly = donviQuanly;
    }

    public String getDonviSudung() {
        return donviSudung;
    }

    public Taisan donviSudung(String donviSudung) {
        this.donviSudung = donviSudung;
        return this;
    }

    public void setDonviSudung(String donviSudung) {
        this.donviSudung = donviSudung;
    }

    public Integer getSoluong() {
        return soluong;
    }

    public Taisan soluong(Integer soluong) {
        this.soluong = soluong;
        return this;
    }

    public void setSoluong(Integer soluong) {
        this.soluong = soluong;
    }

    public Nhanvien getNguoiQuanly() {
        return nguoiQuanly;
    }

    public Taisan nguoiQuanly(Nhanvien nhanvien) {
        this.nguoiQuanly = nhanvien;
        return this;
    }

    public void setNguoiQuanly(Nhanvien nhanvien) {
        this.nguoiQuanly = nhanvien;
    }

    public Set<BanghiKiemke> getBanghiKiemkes() {
        return banghiKiemkes;
    }

    public Taisan banghiKiemkes(Set<BanghiKiemke> banghiKiemkes) {
        this.banghiKiemkes = banghiKiemkes;
        return this;
    }

    public Taisan addBanghiKiemke(BanghiKiemke banghiKiemke) {
        this.banghiKiemkes.add(banghiKiemke);
        banghiKiemke.setTaisan(this);
        return this;
    }

    public Taisan removeBanghiKiemke(BanghiKiemke banghiKiemke) {
        this.banghiKiemkes.remove(banghiKiemke);
        banghiKiemke.setTaisan(null);
        return this;
    }

    public void setBanghiKiemkes(Set<BanghiKiemke> banghiKiemkes) {
        this.banghiKiemkes = banghiKiemkes;
    }

    public DanhmucTaisan getDanhmucTaisan() {
        return danhmucTaisan;
    }

    public Taisan danhmucTaisan(DanhmucTaisan danhmucTaisan) {
        this.danhmucTaisan = danhmucTaisan;
        return this;
    }

    public void setDanhmucTaisan(DanhmucTaisan danhmucTaisan) {
        this.danhmucTaisan = danhmucTaisan;
    }

    public DonviCungcap getDonviCungcap() {
        return donviCungcap;
    }

    public Taisan donviCungcap(DonviCungcap donviCungcap) {
        this.donviCungcap = donviCungcap;
        return this;
    }

    public void setDonviCungcap(DonviCungcap donviCungcap) {
        this.donviCungcap = donviCungcap;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Taisan)) {
            return false;
        }
        return id != null && id.equals(((Taisan) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Taisan{" +
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
            ", tangNguyengia=" + getTangNguyengia() +
            ", nguon='" + getNguon() + "'" +
            ", vitri='" + getVitri() + "'" +
            ", donviQuanly='" + getDonviQuanly() + "'" +
            ", donviSudung='" + getDonviSudung() + "'" +
            ", soluong=" + getSoluong() +
            "}";
    }
}
