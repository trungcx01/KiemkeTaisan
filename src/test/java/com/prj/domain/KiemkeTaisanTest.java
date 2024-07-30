package com.prj.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.prj.web.rest.TestUtil;

public class KiemkeTaisanTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(KiemkeTaisan.class);
        KiemkeTaisan kiemkeTaisan1 = new KiemkeTaisan();
        kiemkeTaisan1.setId("id1");
        KiemkeTaisan kiemkeTaisan2 = new KiemkeTaisan();
        kiemkeTaisan2.setId(kiemkeTaisan1.getId());
        assertThat(kiemkeTaisan1).isEqualTo(kiemkeTaisan2);
        kiemkeTaisan2.setId("id2");
        assertThat(kiemkeTaisan1).isNotEqualTo(kiemkeTaisan2);
        kiemkeTaisan1.setId(null);
        assertThat(kiemkeTaisan1).isNotEqualTo(kiemkeTaisan2);
    }
}
