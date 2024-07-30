package com.prj.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.prj.web.rest.TestUtil;

public class DonviCungcapDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DonviCungcapDTO.class);
        DonviCungcapDTO donviCungcapDTO1 = new DonviCungcapDTO();
        donviCungcapDTO1.setId("id1");
        DonviCungcapDTO donviCungcapDTO2 = new DonviCungcapDTO();
        assertThat(donviCungcapDTO1).isNotEqualTo(donviCungcapDTO2);
        donviCungcapDTO2.setId(donviCungcapDTO1.getId());
        assertThat(donviCungcapDTO1).isEqualTo(donviCungcapDTO2);
        donviCungcapDTO2.setId("id2");
        assertThat(donviCungcapDTO1).isNotEqualTo(donviCungcapDTO2);
        donviCungcapDTO1.setId(null);
        assertThat(donviCungcapDTO1).isNotEqualTo(donviCungcapDTO2);
    }
}
