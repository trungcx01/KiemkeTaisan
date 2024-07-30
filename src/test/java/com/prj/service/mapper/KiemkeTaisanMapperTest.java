package com.prj.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class KiemkeTaisanMapperTest {

    private KiemkeTaisanMapper kiemkeTaisanMapper;

    @BeforeEach
    public void setUp() {
        kiemkeTaisanMapper = new KiemkeTaisanMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id2";
        assertThat(kiemkeTaisanMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(kiemkeTaisanMapper.fromId(null)).isNull();
    }
}
