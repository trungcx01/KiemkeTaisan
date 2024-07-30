package com.prj.service.mapper;

import com.prj.domain.*;
import com.prj.service.dto.NhanvienDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Nhanvien} and its DTO {@link NhanvienDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NhanvienMapper extends EntityMapper<NhanvienDTO, Nhanvien> {



    default Nhanvien fromId(String id) {
        if (id == null) {
            return null;
        }
        Nhanvien nhanvien = new Nhanvien();
        nhanvien.setId(id);
        return nhanvien;
    }
}
