package com.prj.service.mapper;

import com.prj.domain.*;
import com.prj.service.dto.NhanvienKiemkeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link NhanvienKiemke} and its DTO {@link NhanvienKiemkeDTO}.
 */
@Mapper(componentModel = "spring", uses = {NhanvienMapper.class, KiemkeTaisanMapper.class})
public interface NhanvienKiemkeMapper extends EntityMapper<NhanvienKiemkeDTO, NhanvienKiemke> {

    @Mapping(source = "nhanvien.id", target = "nhanvienId")
    @Mapping(source = "nhanvien.ten", target = "nhanvienTen")
    @Mapping(source = "kiemkeTaisan.id", target = "kiemkeTaisanId")
    NhanvienKiemkeDTO toDto(NhanvienKiemke nhanvienKiemke);

    @Mapping(source = "nhanvienId", target = "nhanvien")
    @Mapping(source = "kiemkeTaisanId", target = "kiemkeTaisan")
    NhanvienKiemke toEntity(NhanvienKiemkeDTO nhanvienKiemkeDTO);

    default NhanvienKiemke fromId(String id) {
        if (id == null) {
            return null;
        }
        NhanvienKiemke nhanvienKiemke = new NhanvienKiemke();
        nhanvienKiemke.setId(id);
        return nhanvienKiemke;
    }
}
