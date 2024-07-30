package com.prj.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.prj.web.rest.TestUtil;

public class NhanvienKiemkeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NhanvienKiemke.class);
        NhanvienKiemke nhanvienKiemke1 = new NhanvienKiemke();
        nhanvienKiemke1.setId("id1");
        NhanvienKiemke nhanvienKiemke2 = new NhanvienKiemke();
        nhanvienKiemke2.setId(nhanvienKiemke1.getId());
        assertThat(nhanvienKiemke1).isEqualTo(nhanvienKiemke2);
        nhanvienKiemke2.setId("id2");
        assertThat(nhanvienKiemke1).isNotEqualTo(nhanvienKiemke2);
        nhanvienKiemke1.setId(null);
        assertThat(nhanvienKiemke1).isNotEqualTo(nhanvienKiemke2);
    }
}
