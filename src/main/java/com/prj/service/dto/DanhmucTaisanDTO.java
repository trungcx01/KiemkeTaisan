package com.prj.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.prj.domain.DanhmucTaisan} entity.
 */
public class DanhmucTaisanDTO implements Serializable {

    private String id;

    @NotNull
    private String maDMTS;

    @NotNull
    private String ten;

    private String mota;

    private LocalDate ngaytao;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaDMTS() {
        return maDMTS;
    }

    public void setMaDMTS(String maDMTS) {
        this.maDMTS = maDMTS;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public LocalDate getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(LocalDate ngaytao) {
        this.ngaytao = ngaytao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DanhmucTaisanDTO danhmucTaisanDTO = (DanhmucTaisanDTO) o;
        if (danhmucTaisanDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), danhmucTaisanDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DanhmucTaisanDTO{" +
            "id=" + getId() +
            ", maDMTS='" + getMaDMTS() + "'" +
            ", ten='" + getTen() + "'" +
            ", mota='" + getMota() + "'" +
            ", ngaytao='" + getNgaytao() + "'" +
            "}";
    }
}
