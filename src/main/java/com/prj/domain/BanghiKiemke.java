package com.prj.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A BanghiKiemke.
 */
@Document(collection = "banghi_kiemke")
public class BanghiKiemke implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("soluong")
    private Integer soluong;

    @NotNull
    @Field("nguyengia")
    private BigDecimal nguyengia;

    @NotNull
    @Field("giatri_conlai")
    private BigDecimal giatriConlai;

    @Field("ghichu")
    private String ghichu;

    @Field("tinhtrang_sudung")
    private Integer tinhtrangSudung;

    @Field("hinhthuc_xuly")
    private Integer hinhthucXuly;

    @DBRef
    @Field("taisan")
    @JsonIgnoreProperties("banghiKiemkes")
    private Taisan taisan;

    @DBRef
    @Field("kiemkeTaisan")
    @JsonIgnoreProperties("banghiKiemkes")
    private KiemkeTaisan kiemkeTaisan;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSoluong() {
        return soluong;
    }

    public BanghiKiemke soluong(Integer soluong) {
        this.soluong = soluong;
        return this;
    }

    public void setSoluong(Integer soluong) {
        this.soluong = soluong;
    }

    public BigDecimal getNguyengia() {
        return nguyengia;
    }

    public BanghiKiemke nguyengia(BigDecimal nguyengia) {
        this.nguyengia = nguyengia;
        return this;
    }

    public void setNguyengia(BigDecimal nguyengia) {
        this.nguyengia = nguyengia;
    }

    public BigDecimal getGiatriConlai() {
        return giatriConlai;
    }

    public BanghiKiemke giatriConlai(BigDecimal giatriConlai) {
        this.giatriConlai = giatriConlai;
        return this;
    }

    public void setGiatriConlai(BigDecimal giatriConlai) {
        this.giatriConlai = giatriConlai;
    }

    public String getGhichu() {
        return ghichu;
    }

    public BanghiKiemke ghichu(String ghichu) {
        this.ghichu = ghichu;
        return this;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public Integer getTinhtrangSudung() {
        return tinhtrangSudung;
    }

    public BanghiKiemke tinhtrangSudung(Integer tinhtrangSudung) {
        this.tinhtrangSudung = tinhtrangSudung;
        return this;
    }

    public void setTinhtrangSudung(Integer tinhtrangSudung) {
        this.tinhtrangSudung = tinhtrangSudung;
    }

    public Integer getHinhthucXuly() {
        return hinhthucXuly;
    }

    public BanghiKiemke hinhthucXuly(Integer hinhthucXuly) {
        this.hinhthucXuly = hinhthucXuly;
        return this;
    }

    public void setHinhthucXuly(Integer hinhthucXuly) {
        this.hinhthucXuly = hinhthucXuly;
    }

    public Taisan getTaisan() {
        return taisan;
    }

    public BanghiKiemke taisan(Taisan taisan) {
        this.taisan = taisan;
        return this;
    }

    public void setTaisan(Taisan taisan) {
        this.taisan = taisan;
    }

    public KiemkeTaisan getKiemkeTaisan() {
        return kiemkeTaisan;
    }

    public BanghiKiemke kiemkeTaisan(KiemkeTaisan kiemkeTaisan) {
        this.kiemkeTaisan = kiemkeTaisan;
        return this;
    }

    public void setKiemkeTaisan(KiemkeTaisan kiemkeTaisan) {
        this.kiemkeTaisan = kiemkeTaisan;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BanghiKiemke)) {
            return false;
        }
        return id != null && id.equals(((BanghiKiemke) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "BanghiKiemke{" +
            "id=" + getId() +
            ", soluong=" + getSoluong() +
            ", nguyengia=" + getNguyengia() +
            ", giatriConlai=" + getGiatriConlai() +
            ", ghichu='" + getGhichu() + "'" +
            ", tinhtrangSudung=" + getTinhtrangSudung() +
            ", hinhthucXuly=" + getHinhthucXuly() +
            "}";
    }
}
