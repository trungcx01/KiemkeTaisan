package com.prj.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class DonviCungcapMapperTest {

    private DonviCungcapMapper donviCungcapMapper;

    @BeforeEach
    public void setUp() {
        donviCungcapMapper = new DonviCungcapMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id2";
        assertThat(donviCungcapMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(donviCungcapMapper.fromId(null)).isNull();
    }
}
