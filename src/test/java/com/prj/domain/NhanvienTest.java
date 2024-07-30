package com.prj.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.prj.web.rest.TestUtil;

public class NhanvienTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Nhanvien.class);
        Nhanvien nhanvien1 = new Nhanvien();
        nhanvien1.setId("id1");
        Nhanvien nhanvien2 = new Nhanvien();
        nhanvien2.setId(nhanvien1.getId());
        assertThat(nhanvien1).isEqualTo(nhanvien2);
        nhanvien2.setId("id2");
        assertThat(nhanvien1).isNotEqualTo(nhanvien2);
        nhanvien1.setId(null);
        assertThat(nhanvien1).isNotEqualTo(nhanvien2);
    }
}
