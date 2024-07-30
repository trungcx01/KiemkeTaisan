package com.prj.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link com.prj.domain.KiemkeTaisan} entity.
 */
public class KiemkeTaisanDTO implements Serializable {

    private String id;

    @NotNull
    private String sophieu;

    @NotNull
    private LocalDate ngayLapphieu;

    @NotNull
    private LocalDate ngayKiemke;

    @NotNull
    private String donviSudung;

    private String ghichu;

     private Set<NhanvienKiemkeDTO> nhanvienKiemkes;

    private Set<BanghiKiemkeDTO> banghiKiemkes;

    public Set<BanghiKiemkeDTO> getBanghiKiemkes() {
        return banghiKiemkes;
    }

    public void setBanghiKiemkes(Set<BanghiKiemkeDTO> banghiKiemkes) {
        this.banghiKiemkes = banghiKiemkes;
    }

    public Set<NhanvienKiemkeDTO> getNhanvienKiemkes() {
        return nhanvienKiemkes;
    }

    public void setNhanvienKiemkes(Set<NhanvienKiemkeDTO> nhanvienKiemkes) {
        this.nhanvienKiemkes = nhanvienKiemkes;
    }

  



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSophieu() {
        return sophieu;
    }

    public void setSophieu(String sophieu) {
        this.sophieu = sophieu;
    }

    public LocalDate getNgayLapphieu() {
        return ngayLapphieu;
    }

    public void setNgayLapphieu(LocalDate ngayLapphieu) {
        this.ngayLapphieu = ngayLapphieu;
    }

    public LocalDate getNgayKiemke() {
        return ngayKiemke;
    }

    public void setNgayKiemke(LocalDate ngayKiemke) {
        this.ngayKiemke = ngayKiemke;
    }

    public String getDonviSudung() {
        return donviSudung;
    }

    public void setDonviSudung(String donviSudung) {
        this.donviSudung = donviSudung;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        KiemkeTaisanDTO kiemkeTaisanDTO = (KiemkeTaisanDTO) o;
        if (kiemkeTaisanDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), kiemkeTaisanDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "KiemkeTaisanDTO{" +
            "id=" + getId() +
            ", sophieu='" + getSophieu() + "'" +
            ", ngayLapphieu='" + getNgayLapphieu() + "'" +
            ", ngayKiemke='" + getNgayKiemke() + "'" +
            ", donviSudung='" + getDonviSudung() + "'" +
            ", ghichu='" + getGhichu() + "'" +
            "}";
    }
}
