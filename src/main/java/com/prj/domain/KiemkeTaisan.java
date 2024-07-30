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
 * A KiemkeTaisan.
 */
@Document(collection = "kiemke_taisan")
public class KiemkeTaisan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("sophieu")
    private String sophieu;

    @NotNull
    @Field("ngay_lapphieu")
    private LocalDate ngayLapphieu;

    @NotNull
    @Field("ngay_kiemke")
    private LocalDate ngayKiemke;

    @NotNull
    @Field("donvi_sudung")
    private String donviSudung;

    @Field("ghichu")
    private String ghichu;

    @DBRef
    @Field("nhanvienKiemkes")
    private Set<NhanvienKiemke> nhanvienKiemkes = new HashSet<>();

    @DBRef
    @Field("banghiKiemkes")
    private Set<BanghiKiemke> banghiKiemkes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSophieu() {
        return sophieu;
    }

    public KiemkeTaisan sophieu(String sophieu) {
        this.sophieu = sophieu;
        return this;
    }

    public void setSophieu(String sophieu) {
        this.sophieu = sophieu;
    }

    public LocalDate getNgayLapphieu() {
        return ngayLapphieu;
    }

    public KiemkeTaisan ngayLapphieu(LocalDate ngayLapphieu) {
        this.ngayLapphieu = ngayLapphieu;
        return this;
    }

    public void setNgayLapphieu(LocalDate ngayLapphieu) {
        this.ngayLapphieu = ngayLapphieu;
    }

    public LocalDate getNgayKiemke() {
        return ngayKiemke;
    }

    public KiemkeTaisan ngayKiemke(LocalDate ngayKiemke) {
        this.ngayKiemke = ngayKiemke;
        return this;
    }

    public void setNgayKiemke(LocalDate ngayKiemke) {
        this.ngayKiemke = ngayKiemke;
    }

    public String getDonviSudung() {
        return donviSudung;
    }

    public KiemkeTaisan donviSudung(String donviSudung) {
        this.donviSudung = donviSudung;
        return this;
    }

    public void setDonviSudung(String donviSudung) {
        this.donviSudung = donviSudung;
    }

    public String getGhichu() {
        return ghichu;
    }

    public KiemkeTaisan ghichu(String ghichu) {
        this.ghichu = ghichu;
        return this;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public Set<NhanvienKiemke> getNhanvienKiemkes() {
        return nhanvienKiemkes;
    }

    public KiemkeTaisan nhanvienKiemkes(Set<NhanvienKiemke> nhanvienKiemkes) {
        this.nhanvienKiemkes = nhanvienKiemkes;
        return this;
    }

    public KiemkeTaisan addNhanvienKiemkes(NhanvienKiemke nhanvienKiemke) {
        this.nhanvienKiemkes.add(nhanvienKiemke);
        nhanvienKiemke.setKiemkeTaisan(this);
        return this;
    }

    public KiemkeTaisan removeNhanvienKiemkes(NhanvienKiemke nhanvienKiemke) {
        this.nhanvienKiemkes.remove(nhanvienKiemke);
        nhanvienKiemke.setKiemkeTaisan(null);
        return this;
    }

    public void setNhanvienKiemkes(Set<NhanvienKiemke> nhanvienKiemkes) {
        this.nhanvienKiemkes = nhanvienKiemkes;
    }

    public Set<BanghiKiemke> getBanghiKiemkes() {
        return banghiKiemkes;
    }

    public KiemkeTaisan banghiKiemkes(Set<BanghiKiemke> banghiKiemkes) {
        this.banghiKiemkes = banghiKiemkes;
        return this;
    }

    public KiemkeTaisan addBanghiKiemkes(BanghiKiemke banghiKiemke) {
        this.banghiKiemkes.add(banghiKiemke);
        banghiKiemke.setKiemkeTaisan(this);
        return this;
    }

    public KiemkeTaisan removeBanghiKiemkes(BanghiKiemke banghiKiemke) {
        this.banghiKiemkes.remove(banghiKiemke);
        banghiKiemke.setKiemkeTaisan(null);
        return this;
    }

    public void setBanghiKiemkes(Set<BanghiKiemke> banghiKiemkes) {
        this.banghiKiemkes = banghiKiemkes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof KiemkeTaisan)) {
            return false;
        }
        return id != null && id.equals(((KiemkeTaisan) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "KiemkeTaisan{" +
            "id=" + getId() +
            ", sophieu='" + getSophieu() + "'" +
            ", ngayLapphieu='" + getNgayLapphieu() + "'" +
            ", ngayKiemke='" + getNgayKiemke() + "'" +
            ", donviSudung='" + getDonviSudung() + "'" +
            ", ghichu='" + getGhichu() + "'" +
            "}";
    }
}
