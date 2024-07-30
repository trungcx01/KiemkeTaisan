package com.prj.service.mapper;

import com.prj.domain.*;
import com.prj.service.dto.DanhmucTaisanDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DanhmucTaisan} and its DTO {@link DanhmucTaisanDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DanhmucTaisanMapper extends EntityMapper<DanhmucTaisanDTO, DanhmucTaisan> {


    @Mapping(target = "taisans", ignore = true)
    @Mapping(target = "removeTaisan", ignore = true)
    DanhmucTaisan toEntity(DanhmucTaisanDTO danhmucTaisanDTO);

    default DanhmucTaisan fromId(String id) {
        if (id == null) {
            return null;
        }
        DanhmucTaisan danhmucTaisan = new DanhmucTaisan();
        danhmucTaisan.setId(id);
        return danhmucTaisan;
    }
}
