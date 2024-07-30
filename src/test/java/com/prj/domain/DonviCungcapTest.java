package com.prj.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.prj.web.rest.TestUtil;

public class DonviCungcapTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DonviCungcap.class);
        DonviCungcap donviCungcap1 = new DonviCungcap();
        donviCungcap1.setId("id1");
        DonviCungcap donviCungcap2 = new DonviCungcap();
        donviCungcap2.setId(donviCungcap1.getId());
        assertThat(donviCungcap1).isEqualTo(donviCungcap2);
        donviCungcap2.setId("id2");
        assertThat(donviCungcap1).isNotEqualTo(donviCungcap2);
        donviCungcap1.setId(null);
        assertThat(donviCungcap1).isNotEqualTo(donviCungcap2);
    }
}
