package com.prj.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.prj.web.rest.TestUtil;

public class NhanvienKiemkeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NhanvienKiemkeDTO.class);
        NhanvienKiemkeDTO nhanvienKiemkeDTO1 = new NhanvienKiemkeDTO();
        nhanvienKiemkeDTO1.setId("id1");
        NhanvienKiemkeDTO nhanvienKiemkeDTO2 = new NhanvienKiemkeDTO();
        assertThat(nhanvienKiemkeDTO1).isNotEqualTo(nhanvienKiemkeDTO2);
        nhanvienKiemkeDTO2.setId(nhanvienKiemkeDTO1.getId());
        assertThat(nhanvienKiemkeDTO1).isEqualTo(nhanvienKiemkeDTO2);
        nhanvienKiemkeDTO2.setId("id2");
        assertThat(nhanvienKiemkeDTO1).isNotEqualTo(nhanvienKiemkeDTO2);
        nhanvienKiemkeDTO1.setId(null);
        assertThat(nhanvienKiemkeDTO1).isNotEqualTo(nhanvienKiemkeDTO2);
    }
}
