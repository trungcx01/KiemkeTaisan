package com.prj.service.mapper;

import com.prj.domain.*;
import com.prj.service.dto.DonviCungcapDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DonviCungcap} and its DTO {@link DonviCungcapDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DonviCungcapMapper extends EntityMapper<DonviCungcapDTO, DonviCungcap> {


    @Mapping(target = "taisans", ignore = true)
    @Mapping(target = "removeTaisan", ignore = true)
    DonviCungcap toEntity(DonviCungcapDTO donviCungcapDTO);

    default DonviCungcap fromId(String id) {
        if (id == null) {
            return null;
        }
        DonviCungcap donviCungcap = new DonviCungcap();
        donviCungcap.setId(id);
        return donviCungcap;
    }
}
