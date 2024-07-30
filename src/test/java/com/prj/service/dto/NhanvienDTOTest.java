package com.prj.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.prj.web.rest.TestUtil;

public class NhanvienDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NhanvienDTO.class);
        NhanvienDTO nhanvienDTO1 = new NhanvienDTO();
        nhanvienDTO1.setId("id1");
        NhanvienDTO nhanvienDTO2 = new NhanvienDTO();
        assertThat(nhanvienDTO1).isNotEqualTo(nhanvienDTO2);
        nhanvienDTO2.setId(nhanvienDTO1.getId());
        assertThat(nhanvienDTO1).isEqualTo(nhanvienDTO2);
        nhanvienDTO2.setId("id2");
        assertThat(nhanvienDTO1).isNotEqualTo(nhanvienDTO2);
        nhanvienDTO1.setId(null);
        assertThat(nhanvienDTO1).isNotEqualTo(nhanvienDTO2);
    }
}
