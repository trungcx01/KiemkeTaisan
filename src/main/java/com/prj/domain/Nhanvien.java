package com.prj.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Nhanvien.
 */
@Document(collection = "nhanvien")
public class Nhanvien implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("ma_nv")
    private String maNV;

    @NotNull
    @Field("ten")
    private String ten;

    @Field("chucvu")
    private String chucvu;

    @Field("diachi")
    private String diachi;

    @NotNull
    @Field("gioitinh")
    private Integer gioitinh;

    @Size(max = 11)
    @Field("sdt")
    private String sdt;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaNV() {
        return maNV;
    }

    public Nhanvien maNV(String maNV) {
        this.maNV = maNV;
        return this;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTen() {
        return ten;
    }

    public Nhanvien ten(String ten) {
        this.ten = ten;
        return this;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getChucvu() {
        return chucvu;
    }

    public Nhanvien chucvu(String chucvu) {
        this.chucvu = chucvu;
        return this;
    }

    public void setChucvu(String chucvu) {
        this.chucvu = chucvu;
    }

    public String getDiachi() {
        return diachi;
    }

    public Nhanvien diachi(String diachi) {
        this.diachi = diachi;
        return this;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public Integer getGioitinh() {
        return gioitinh;
    }

    public Nhanvien gioitinh(Integer gioitinh) {
        this.gioitinh = gioitinh;
        return this;
    }

    public void setGioitinh(Integer gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getSdt() {
        return sdt;
    }

    public Nhanvien sdt(String sdt) {
        this.sdt = sdt;
        return this;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Nhanvien)) {
            return false;
        }
        return id != null && id.equals(((Nhanvien) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Nhanvien{" +
            "id=" + getId() +
            ", maNV='" + getMaNV() + "'" +
            ", ten='" + getTen() + "'" +
            ", chucvu='" + getChucvu() + "'" +
            ", diachi='" + getDiachi() + "'" +
            ", gioitinh=" + getGioitinh() +
            ", sdt='" + getSdt() + "'" +
            "}";
    }
}