package com.prj.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.prj.web.rest.TestUtil;

public class DanhmucTaisanTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhmucTaisan.class);
        DanhmucTaisan danhmucTaisan1 = new DanhmucTaisan();
        danhmucTaisan1.setId("id1");
        DanhmucTaisan danhmucTaisan2 = new DanhmucTaisan();
        danhmucTaisan2.setId(danhmucTaisan1.getId());
        assertThat(danhmucTaisan1).isEqualTo(danhmucTaisan2);
        danhmucTaisan2.setId("id2");
        assertThat(danhmucTaisan1).isNotEqualTo(danhmucTaisan2);
        danhmucTaisan1.setId(null);
        assertThat(danhmucTaisan1).isNotEqualTo(danhmucTaisan2);
    }
}
