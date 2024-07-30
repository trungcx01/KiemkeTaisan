package com.prj.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class NhanvienKiemkeMapperTest {

    private NhanvienKiemkeMapper nhanvienKiemkeMapper;

    @BeforeEach
    public void setUp() {
        nhanvienKiemkeMapper = new NhanvienKiemkeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id2";
        assertThat(nhanvienKiemkeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(nhanvienKiemkeMapper.fromId(null)).isNull();
    }
}
