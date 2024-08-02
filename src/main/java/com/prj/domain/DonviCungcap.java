package com.prj.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DonviCungcap.
 */
@Document(collection = "donvi_cungcap")
public class DonviCungcap implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("ma_dvcc")
    private String maDVCC;

    @NotNull
    @Field("ten")
    private String ten;

    @Field("diachi")
    private String diachi;

    @Field("so_dien_thoai")
    private String soDienThoai;

    @NotNull
    @Field("email")
    private String email;

    @DBRef
    @Field("taisan")
    private Set<Taisan> taisans = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaDVCC() {
        return maDVCC;
    }

    public DonviCungcap maDVCC(String maDVCC) {
        this.maDVCC = maDVCC;
        return this;
    }

    public void setMaDVCC(String maDVCC) {
        this.maDVCC = maDVCC;
    }

    public String getTen() {
        return ten;
    }

    public DonviCungcap ten(String ten) {
        this.ten = ten;
        return this;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDiachi() {
        return diachi;
    }

    public DonviCungcap diachi(String diachi) {
        this.diachi = diachi;
        return this;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public DonviCungcap soDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
        return this;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public DonviCungcap email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Taisan> getTaisans() {
        return taisans;
    }

    public DonviCungcap taisans(Set<Taisan> taisans) {
        this.taisans = taisans;
        return this;
    }

    public DonviCungcap addTaisan(Taisan taisan) {
        this.taisans.add(taisan);
        taisan.setDonviCungcap(this);
        return this;
    }

    public DonviCungcap removeTaisan(Taisan taisan) {
        this.taisans.remove(taisan);
        taisan.setDonviCungcap(null);
        return this;
    }

    public void setTaisans(Set<Taisan> taisans) {
        this.taisans = taisans;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DonviCungcap)) {
            return false;
        }
        return id != null && id.equals(((DonviCungcap) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DonviCungcap{" +
            "id=" + getId() +
            ", maDVCC='" + getMaDVCC() + "'" +
            ", ten='" + getTen() + "'" +
            ", diachi='" + getDiachi() + "'" +
            ", soDienThoai='" + getSoDienThoai() + "'" +
            ", email='" + getEmail() + "'" +
            "}";
    }
}
