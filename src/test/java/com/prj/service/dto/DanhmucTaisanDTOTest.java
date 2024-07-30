package com.prj.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.prj.web.rest.TestUtil;

public class DanhmucTaisanDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhmucTaisanDTO.class);
        DanhmucTaisanDTO danhmucTaisanDTO1 = new DanhmucTaisanDTO();
        danhmucTaisanDTO1.setId("id1");
        DanhmucTaisanDTO danhmucTaisanDTO2 = new DanhmucTaisanDTO();
        assertThat(danhmucTaisanDTO1).isNotEqualTo(danhmucTaisanDTO2);
        danhmucTaisanDTO2.setId(danhmucTaisanDTO1.getId());
        assertThat(danhmucTaisanDTO1).isEqualTo(danhmucTaisanDTO2);
        danhmucTaisanDTO2.setId("id2");
        assertThat(danhmucTaisanDTO1).isNotEqualTo(danhmucTaisanDTO2);
        danhmucTaisanDTO1.setId(null);
        assertThat(danhmucTaisanDTO1).isNotEqualTo(danhmucTaisanDTO2);
    }
}
