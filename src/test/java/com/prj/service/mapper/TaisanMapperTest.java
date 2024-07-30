package com.prj.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class TaisanMapperTest {

    private TaisanMapper taisanMapper;

    @BeforeEach
    public void setUp() {
        taisanMapper = new TaisanMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id2";
        assertThat(taisanMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(taisanMapper.fromId(null)).isNull();
    }
}
