package com.prj.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link com.prj.domain.BanghiKiemke} entity.
 */
public class BanghiKiemkeDTO implements Serializable {

    private String id;

    @NotNull
    private Integer soluong;

    @NotNull
    private BigDecimal nguyengia;

    @NotNull
    private BigDecimal giatriConlai;

    private String ghichu;

    private Integer tinhtrangSudung;

    private Integer hinhthucXuly;


    private String taisanId;

    private String taisanTenTaisan;

    private String kiemkeTaisanId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSoluong() {
        return soluong;
    }

    public void setSoluong(Integer soluong) {
        this.soluong = soluong;
    }

    public BigDecimal getNguyengia() {
        return nguyengia;
    }

    public void setNguyengia(BigDecimal nguyengia) {
        this.nguyengia = nguyengia;
    }

    public BigDecimal getGiatriConlai() {
        return giatriConlai;
    }

    public void setGiatriConlai(BigDecimal giatriConlai) {
        this.giatriConlai = giatriConlai;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public Integer getTinhtrangSudung() {
        return tinhtrangSudung;
    }

    public void setTinhtrangSudung(Integer tinhtrangSudung) {
        this.tinhtrangSudung = tinhtrangSudung;
    }

    public Integer getHinhthucXuly() {
        return hinhthucXuly;
    }

    public void setHinhthucXuly(Integer hinhthucXuly) {
        this.hinhthucXuly = hinhthucXuly;
    }

    public String getTaisanId() {
        return taisanId;
    }

    public void setTaisanId(String taisanId) {
        this.taisanId = taisanId;
    }

    public String getTaisanTenTaisan() {
        return taisanTenTaisan;
    }

    public void setTaisanTenTaisan(String taisanTenTaisan) {
        this.taisanTenTaisan = taisanTenTaisan;
    }

    public String getKiemkeTaisanId() {
        return kiemkeTaisanId;
    }

    public void setKiemkeTaisanId(String kiemkeTaisanId) {
        this.kiemkeTaisanId = kiemkeTaisanId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BanghiKiemkeDTO banghiKiemkeDTO = (BanghiKiemkeDTO) o;
        if (banghiKiemkeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), banghiKiemkeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BanghiKiemkeDTO{" +
            "id=" + getId() +
            ", soluong=" + getSoluong() +
            ", nguyengia=" + getNguyengia() +
            ", giatriConlai=" + getGiatriConlai() +
            ", ghichu='" + getGhichu() + "'" +
            ", tinhtrangSudung=" + getTinhtrangSudung() +
            ", hinhthucXuly=" + getHinhthucXuly() +
            ", taisanId=" + getTaisanId() +
            ", taisanTenTaisan='" + getTaisanTenTaisan() + "'" +
            ", kiemkeTaisanId=" + getKiemkeTaisanId() +
            "}";
    }
}
