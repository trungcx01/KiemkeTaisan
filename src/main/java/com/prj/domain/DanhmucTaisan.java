package com.prj.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A DanhmucTaisan.
 */
@Document(collection = "danhmuc_taisan")
public class DanhmucTaisan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("ma_dmts")
    private String maDMTS;

    @NotNull
    @Field("ten")
    private String ten;

    @Field("mota")
    private String mota;

    @Field("ngaytao")
    private LocalDate ngaytao;

    @Field("ngay_capnhat")
    private LocalDate ngayCapnhat;

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

    public String getMaDMTS() {
        return maDMTS;
    }

    public DanhmucTaisan maDMTS(String maDMTS) {
        this.maDMTS = maDMTS;
        return this;
    }

    public void setMaDMTS(String maDMTS) {
        this.maDMTS = maDMTS;
    }

    public String getTen() {
        return ten;
    }

    public DanhmucTaisan ten(String ten) {
        this.ten = ten;
        return this;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMota() {
        return mota;
    }

    public DanhmucTaisan mota(String mota) {
        this.mota = mota;
        return this;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public LocalDate getNgaytao() {
        return ngaytao;
    }

    public DanhmucTaisan ngaytao(LocalDate ngaytao) {
        this.ngaytao = ngaytao;
        return this;
    }

    public void setNgaytao(LocalDate ngaytao) {
        this.ngaytao = ngaytao;
    }

    public LocalDate getNgayCapnhat() {
        return ngayCapnhat;
    }

    public DanhmucTaisan ngayCapnhat(LocalDate ngayCapnhat) {
        this.ngayCapnhat = ngayCapnhat;
        return this;
    }

    public void setNgayCapnhat(LocalDate ngayCapnhat) {
        this.ngayCapnhat = ngayCapnhat;
    }

    public Set<Taisan> getTaisans() {
        return taisans;
    }

    public DanhmucTaisan taisans(Set<Taisan> taisans) {
        this.taisans = taisans;
        return this;
    }

    public DanhmucTaisan addTaisan(Taisan taisan) {
        this.taisans.add(taisan);
        taisan.setDanhmucTaisan(this);
        return this;
    }

    public DanhmucTaisan removeTaisan(Taisan taisan) {
        this.taisans.remove(taisan);
        taisan.setDanhmucTaisan(null);
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
        if (!(o instanceof DanhmucTaisan)) {
            return false;
        }
        return id != null && id.equals(((DanhmucTaisan) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DanhmucTaisan{" +
            "id=" + getId() +
            ", maDMTS='" + getMaDMTS() + "'" +
            ", ten='" + getTen() + "'" +
            ", mota='" + getMota() + "'" +
            ", ngaytao='" + getNgaytao() + "'" +
            ", ngayCapnhat='" + getNgayCapnhat() + "'" +
            "}";
    }
}
