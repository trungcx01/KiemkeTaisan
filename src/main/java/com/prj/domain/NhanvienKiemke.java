package com.prj.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A NhanvienKiemke.
 */
@Document(collection = "nhanvien_kiemke")
public class NhanvienKiemke implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("daidien")
    private String daidien;

    @NotNull
    @Field("vaitro")
    private Integer vaitro;

    @DBRef
    @Field("nhanvien")
    private Nhanvien nhanvien;

    @DBRef
    @Field("kiemkeTaisan")
    @JsonIgnoreProperties("nhanvienKiemkes")
    private KiemkeTaisan kiemkeTaisan;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDaidien() {
        return daidien;
    }

    public NhanvienKiemke daidien(String daidien) {
        this.daidien = daidien;
        return this;
    }

    public void setDaidien(String daidien) {
        this.daidien = daidien;
    }

    public Integer getVaitro() {
        return vaitro;
    }

    public NhanvienKiemke vaitro(Integer vaitro) {
        this.vaitro = vaitro;
        return this;
    }

    public void setVaitro(Integer vaitro) {
        this.vaitro = vaitro;
    }

    public Nhanvien getNhanvien() {
        return nhanvien;
    }

    public NhanvienKiemke nhanvien(Nhanvien nhanvien) {
        this.nhanvien = nhanvien;
        return this;
    }

    public void setNhanvien(Nhanvien nhanvien) {
        this.nhanvien = nhanvien;
    }

    public KiemkeTaisan getKiemkeTaisan() {
        return kiemkeTaisan;
    }

    public NhanvienKiemke kiemkeTaisan(KiemkeTaisan kiemkeTaisan) {
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
        if (!(o instanceof NhanvienKiemke)) {
            return false;
        }
        return id != null && id.equals(((NhanvienKiemke) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "NhanvienKiemke{" +
            "id=" + getId() +
            ", daidien='" + getDaidien() + "'" +
            ", vaitro=" + getVaitro() +
            "}";
    }
}
