package com.prj.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class DanhmucTaisanMapperTest {

    private DanhmucTaisanMapper danhmucTaisanMapper;

    @BeforeEach
    public void setUp() {
        danhmucTaisanMapper = new DanhmucTaisanMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id2";
        assertThat(danhmucTaisanMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(danhmucTaisanMapper.fromId(null)).isNull();
    }
}
