package com.prj.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.prj.web.rest.TestUtil;

public class KiemkeTaisanDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(KiemkeTaisanDTO.class);
        KiemkeTaisanDTO kiemkeTaisanDTO1 = new KiemkeTaisanDTO();
        kiemkeTaisanDTO1.setId("id1");
        KiemkeTaisanDTO kiemkeTaisanDTO2 = new KiemkeTaisanDTO();
        assertThat(kiemkeTaisanDTO1).isNotEqualTo(kiemkeTaisanDTO2);
        kiemkeTaisanDTO2.setId(kiemkeTaisanDTO1.getId());
        assertThat(kiemkeTaisanDTO1).isEqualTo(kiemkeTaisanDTO2);
        kiemkeTaisanDTO2.setId("id2");
        assertThat(kiemkeTaisanDTO1).isNotEqualTo(kiemkeTaisanDTO2);
        kiemkeTaisanDTO1.setId(null);
        assertThat(kiemkeTaisanDTO1).isNotEqualTo(kiemkeTaisanDTO2);
    }
}
