package com.prj.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.prj.web.rest.TestUtil;

public class BanghiKiemkeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BanghiKiemkeDTO.class);
        BanghiKiemkeDTO banghiKiemkeDTO1 = new BanghiKiemkeDTO();
        banghiKiemkeDTO1.setId("id1");
        BanghiKiemkeDTO banghiKiemkeDTO2 = new BanghiKiemkeDTO();
        assertThat(banghiKiemkeDTO1).isNotEqualTo(banghiKiemkeDTO2);
        banghiKiemkeDTO2.setId(banghiKiemkeDTO1.getId());
        assertThat(banghiKiemkeDTO1).isEqualTo(banghiKiemkeDTO2);
        banghiKiemkeDTO2.setId("id2");
        assertThat(banghiKiemkeDTO1).isNotEqualTo(banghiKiemkeDTO2);
        banghiKiemkeDTO1.setId(null);
        assertThat(banghiKiemkeDTO1).isNotEqualTo(banghiKiemkeDTO2);
    }
}
