package com.prj.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class NhanvienMapperTest {

    private NhanvienMapper nhanvienMapper;

    @BeforeEach
    public void setUp() {
        nhanvienMapper = new NhanvienMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id2";
        assertThat(nhanvienMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(nhanvienMapper.fromId(null)).isNull();
    }
}
