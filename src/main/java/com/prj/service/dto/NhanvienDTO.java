package com.prj.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.prj.domain.Nhanvien} entity.
 */
public class NhanvienDTO implements Serializable {

    private String id;

    @NotNull
    private String maNV;

    @NotNull
    private String ten;

    @NotNull
    private String chucvu;

    private String diachi;

    @NotNull
    private Integer gioitinh;

    @NotNull
    @Size(max = 11)
    private String sdt;

    @NotNull
    private String phongban;

    @NotNull
    private String email;

    @NotNull
    private LocalDate ngayThamgia;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getChucvu() {
        return chucvu;
    }

    public void setChucvu(String chucvu) {
        this.chucvu = chucvu;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public Integer getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(Integer gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getPhongban() {
        return phongban;
    }

    public void setPhongban(String phongban) {
        this.phongban = phongban;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getNgayThamgia() {
        return ngayThamgia;
    }

    public void setNgayThamgia(LocalDate ngayThamgia) {
        this.ngayThamgia = ngayThamgia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NhanvienDTO nhanvienDTO = (NhanvienDTO) o;
        if (nhanvienDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nhanvienDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NhanvienDTO{" +
            "id=" + getId() +
            ", maNV='" + getMaNV() + "'" +
            ", ten='" + getTen() + "'" +
            ", chucvu='" + getChucvu() + "'" +
            ", diachi='" + getDiachi() + "'" +
            ", gioitinh=" + getGioitinh() +
            ", sdt='" + getSdt() + "'" +
            ", phongban='" + getPhongban() + "'" +
            ", email='" + getEmail() + "'" +
            ", ngayThamgia='" + getNgayThamgia() + "'" +
            "}";
    }
}
