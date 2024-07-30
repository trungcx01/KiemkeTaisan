package com.prj.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class BanghiKiemkeMapperTest {

    private BanghiKiemkeMapper banghiKiemkeMapper;

    @BeforeEach
    public void setUp() {
        banghiKiemkeMapper = new BanghiKiemkeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id2";
        assertThat(banghiKiemkeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(banghiKiemkeMapper.fromId(null)).isNull();
    }
}
