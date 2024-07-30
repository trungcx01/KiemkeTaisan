package com.prj.service.mapper;

import com.prj.domain.*;
import com.prj.service.dto.BanghiKiemkeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link BanghiKiemke} and its DTO {@link BanghiKiemkeDTO}.
 */
@Mapper(componentModel = "spring", uses = {TaisanMapper.class, KiemkeTaisanMapper.class})
public interface BanghiKiemkeMapper extends EntityMapper<BanghiKiemkeDTO, BanghiKiemke> {

    @Mapping(source = "taisan.id", target = "taisanId")
    @Mapping(source = "taisan.tenTaisan", target = "taisanTenTaisan")
    @Mapping(source = "kiemkeTaisan.id", target = "kiemkeTaisanId")
    BanghiKiemkeDTO toDto(BanghiKiemke banghiKiemke);

    @Mapping(source = "taisanId", target = "taisan")
    @Mapping(source = "kiemkeTaisanId", target = "kiemkeTaisan")
    BanghiKiemke toEntity(BanghiKiemkeDTO banghiKiemkeDTO);

    default BanghiKiemke fromId(String id) {
        if (id == null) {
            return null;
        }
        BanghiKiemke banghiKiemke = new BanghiKiemke();
        banghiKiemke.setId(id);
        return banghiKiemke;
    }
}
