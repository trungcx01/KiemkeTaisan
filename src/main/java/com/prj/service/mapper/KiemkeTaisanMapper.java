package com.prj.service.mapper;

import com.prj.domain.*;
import com.prj.service.dto.KiemkeTaisanDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link KiemkeTaisan} and its DTO {@link KiemkeTaisanDTO}.
 */
@Mapper(componentModel = "spring", uses = {NhanvienKiemkeMapper.class, BanghiKiemkeMapper.class})
public interface KiemkeTaisanMapper extends EntityMapper<KiemkeTaisanDTO, KiemkeTaisan> {


    @Mapping(target = "nhanvienKiemkes", ignore = false)
    @Mapping(target = "removeNhanvienKiemkes", ignore = false)
    @Mapping(target = "banghiKiemkes", ignore = false)
    @Mapping(target = "removeBanghiKiemkes", ignore = false)
    KiemkeTaisan toEntity(KiemkeTaisanDTO kiemkeTaisanDTO);

    default KiemkeTaisan fromId(String id) {
        if (id == null) {
            return null;
        }
        KiemkeTaisan kiemkeTaisan = new KiemkeTaisan();
        kiemkeTaisan.setId(id);
        return kiemkeTaisan;
    }
}
