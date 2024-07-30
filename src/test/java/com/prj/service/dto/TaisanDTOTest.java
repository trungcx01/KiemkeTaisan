package com.prj.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.prj.web.rest.TestUtil;

public class TaisanDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaisanDTO.class);
        TaisanDTO taisanDTO1 = new TaisanDTO();
        taisanDTO1.setId("id1");
        TaisanDTO taisanDTO2 = new TaisanDTO();
        assertThat(taisanDTO1).isNotEqualTo(taisanDTO2);
        taisanDTO2.setId(taisanDTO1.getId());
        assertThat(taisanDTO1).isEqualTo(taisanDTO2);
        taisanDTO2.setId("id2");
        assertThat(taisanDTO1).isNotEqualTo(taisanDTO2);
        taisanDTO1.setId(null);
        assertThat(taisanDTO1).isNotEqualTo(taisanDTO2);
    }
}
