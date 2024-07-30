package com.prj.service.mapper;

import com.prj.domain.*;
import com.prj.service.dto.TaisanDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Taisan} and its DTO {@link TaisanDTO}.
 */
@Mapper(componentModel = "spring", uses = {NhanvienMapper.class, DanhmucTaisanMapper.class, DonviCungcapMapper.class})
public interface TaisanMapper extends EntityMapper<TaisanDTO, Taisan> {

    @Mapping(source = "nguoiQuanly.id", target = "nguoiQuanlyId")
    @Mapping(source = "nguoiQuanly.ten", target = "nguoiQuanlyTen")
    @Mapping(source = "danhmucTaisan.id", target = "danhmucTaisanId")
    @Mapping(source = "danhmucTaisan.ten", target = "danhmucTaisanTen")
    @Mapping(source = "donviCungcap.id", target = "donviCungcapId")
    @Mapping(source = "donviCungcap.ten", target = "donviCungcapTen")
    TaisanDTO toDto(Taisan taisan);

    @Mapping(source = "nguoiQuanlyId", target = "nguoiQuanly")
    @Mapping(target = "banghiKiemkes", ignore = true)
    @Mapping(target = "removeBanghiKiemke", ignore = true)
    @Mapping(source = "danhmucTaisanId", target = "danhmucTaisan")
    @Mapping(source = "donviCungcapId", target = "donviCungcap")
    Taisan toEntity(TaisanDTO taisanDTO);

    default Taisan fromId(String id) {
        if (id == null) {
            return null;
        }
        Taisan taisan = new Taisan();
        taisan.setId(id);
        return taisan;
    }
}
