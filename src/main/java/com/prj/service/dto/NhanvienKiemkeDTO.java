package com.prj.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.prj.domain.NhanvienKiemke} entity.
 */
public class NhanvienKiemkeDTO implements Serializable {

    private String id;

    @NotNull
    private String daidien;

    @NotNull
    private Integer vaitro;


    private String nhanvienId;

    private String nhanvienTen;

    private String kiemkeTaisanId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDaidien() {
        return daidien;
    }

    public void setDaidien(String daidien) {
        this.daidien = daidien;
    }

    public Integer getVaitro() {
        return vaitro;
    }

    public void setVaitro(Integer vaitro) {
        this.vaitro = vaitro;
    }

    public String getNhanvienId() {
        return nhanvienId;
    }

    public void setNhanvienId(String nhanvienId) {
        this.nhanvienId = nhanvienId;
    }

    public String getNhanvienTen() {
        return nhanvienTen;
    }

    public void setNhanvienTen(String nhanvienTen) {
        this.nhanvienTen = nhanvienTen;
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

        NhanvienKiemkeDTO nhanvienKiemkeDTO = (NhanvienKiemkeDTO) o;
        if (nhanvienKiemkeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nhanvienKiemkeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NhanvienKiemkeDTO{" +
            "id=" + getId() +
            ", daidien='" + getDaidien() + "'" +
            ", vaitro=" + getVaitro() +
            ", nhanvienId=" + getNhanvienId() +
            ", nhanvienTen='" + getNhanvienTen() + "'" +
            ", kiemkeTaisanId=" + getKiemkeTaisanId() +
            "}";
    }
}
