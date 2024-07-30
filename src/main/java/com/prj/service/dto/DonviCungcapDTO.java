package com.prj.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.prj.domain.DonviCungcap} entity.
 */
public class DonviCungcapDTO implements Serializable {

    private String id;

    @NotNull
    private String maDVCC;

    @NotNull
    private String ten;

    private String diachi;

    private String soDienThoai;

    private String email;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaDVCC() {
        return maDVCC;
    }

    public void setMaDVCC(String maDVCC) {
        this.maDVCC = maDVCC;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DonviCungcapDTO donviCungcapDTO = (DonviCungcapDTO) o;
        if (donviCungcapDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), donviCungcapDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DonviCungcapDTO{" +
            "id=" + getId() +
            ", maDVCC='" + getMaDVCC() + "'" +
            ", ten='" + getTen() + "'" +
            ", diachi='" + getDiachi() + "'" +
            ", soDienThoai='" + getSoDienThoai() + "'" +
            ", email='" + getEmail() + "'" +
            "}";
    }
}
