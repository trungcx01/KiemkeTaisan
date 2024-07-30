package com.prj.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.prj.web.rest.TestUtil;

public class TaisanTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Taisan.class);
        Taisan taisan1 = new Taisan();
        taisan1.setId("id1");
        Taisan taisan2 = new Taisan();
        taisan2.setId(taisan1.getId());
        assertThat(taisan1).isEqualTo(taisan2);
        taisan2.setId("id2");
        assertThat(taisan1).isNotEqualTo(taisan2);
        taisan1.setId(null);
        assertThat(taisan1).isNotEqualTo(taisan2);
    }
}
