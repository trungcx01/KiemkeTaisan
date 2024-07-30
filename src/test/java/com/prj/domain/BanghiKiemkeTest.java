package com.prj.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.prj.web.rest.TestUtil;

public class BanghiKiemkeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BanghiKiemke.class);
        BanghiKiemke banghiKiemke1 = new BanghiKiemke();
        banghiKiemke1.setId("id1");
        BanghiKiemke banghiKiemke2 = new BanghiKiemke();
        banghiKiemke2.setId(banghiKiemke1.getId());
        assertThat(banghiKiemke1).isEqualTo(banghiKiemke2);
        banghiKiemke2.setId("id2");
        assertThat(banghiKiemke1).isNotEqualTo(banghiKiemke2);
        banghiKiemke1.setId(null);
        assertThat(banghiKiemke1).isNotEqualTo(banghiKiemke2);
    }
}
