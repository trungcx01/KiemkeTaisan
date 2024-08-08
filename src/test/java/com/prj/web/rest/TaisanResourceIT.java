package com.prj.web.rest;

import com.prj.TaisanApp;
import com.prj.domain.Taisan;
import com.prj.domain.Nhanvien;
import com.prj.domain.DanhmucTaisan;
import com.prj.repository.TaisanRepository;
import com.prj.service.TaisanService;
import com.prj.service.dto.TaisanDTO;
import com.prj.service.mapper.TaisanMapper;
import com.prj.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.prj.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link TaisanResource} REST controller.
 */
@SpringBootTest(classes = TaisanApp.class)
public class TaisanResourceIT {

    private static final String DEFAULT_MA_TAISAN = "AAAAAAAAAA";
    private static final String UPDATED_MA_TAISAN = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_TAISAN = "AAAAAAAAAA";
    private static final String UPDATED_TEN_TAISAN = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_RUTGON = "AAAAAAAAAA";
    private static final String UPDATED_TEN_RUTGON = "BBBBBBBBBB";

    private static final String DEFAULT_MODEL = "AAAAAAAAAA";
    private static final String UPDATED_MODEL = "BBBBBBBBBB";

    private static final String DEFAULT_SERIAL = "AAAAAAAAAA";
    private static final String UPDATED_SERIAL = "BBBBBBBBBB";

    private static final Integer DEFAULT_LOAI_TAISAN = 1;
    private static final Integer UPDATED_LOAI_TAISAN = 2;

    private static final String DEFAULT_MOTA = "AAAAAAAAAA";
    private static final String UPDATED_MOTA = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NGAY_NHAN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_NHAN = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_NGAY_SUDUNG = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_SUDUNG = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_NGAY_SANXUAT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_SANXUAT = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_THOIGIAN_SUDUNG = 1;
    private static final Integer UPDATED_THOIGIAN_SUDUNG = 2;

    private static final LocalDate DEFAULT_NGAY_HETHAN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_HETHAN = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_HAOMON = 1;
    private static final Integer UPDATED_HAOMON = 2;

    private static final BigDecimal DEFAULT_NGUYENGIA = new BigDecimal(1);
    private static final BigDecimal UPDATED_NGUYENGIA = new BigDecimal(2);

    private static final String DEFAULT_SO_HOPDONG_MUA = "AAAAAAAAAA";
    private static final String UPDATED_SO_HOPDONG_MUA = "BBBBBBBBBB";

    private static final String DEFAULT_SO_HOADON_MUA = "AAAAAAAAAA";
    private static final String UPDATED_SO_HOADON_MUA = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NGAY_HOADON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_HOADON = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_TRANGTHAI = 1;
    private static final Integer UPDATED_TRANGTHAI = 2;

    private static final String DEFAULT_HANG_SANXUAT = "AAAAAAAAAA";
    private static final String UPDATED_HANG_SANXUAT = "BBBBBBBBBB";

    private static final String DEFAULT_NUOC_SANXUAT = "AAAAAAAAAA";
    private static final String UPDATED_NUOC_SANXUAT = "BBBBBBBBBB";

    private static final Double DEFAULT_CONGSUAT_SUDUNG = 1D;
    private static final Double UPDATED_CONGSUAT_SUDUNG = 2D;

    private static final Double DEFAULT_DIENTICH_SUDUNG = 1D;
    private static final Double UPDATED_DIENTICH_SUDUNG = 2D;

    private static final String DEFAULT_DONVI_TINH = "AAAAAAAAAA";
    private static final String UPDATED_DONVI_TINH = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_GIATRI_CONLAI = new BigDecimal(1);
    private static final BigDecimal UPDATED_GIATRI_CONLAI = new BigDecimal(2);

    private static final String DEFAULT_NGUON = "AAAAAAAAAA";
    private static final String UPDATED_NGUON = "BBBBBBBBBB";

    private static final String DEFAULT_VITRI = "AAAAAAAAAA";
    private static final String UPDATED_VITRI = "BBBBBBBBBB";

    private static final String DEFAULT_DONVI_QUANLY = "AAAAAAAAAA";
    private static final String UPDATED_DONVI_QUANLY = "BBBBBBBBBB";

    private static final String DEFAULT_DONVI_SUDUNG = "AAAAAAAAAA";
    private static final String UPDATED_DONVI_SUDUNG = "BBBBBBBBBB";

    private static final Integer DEFAULT_SOLUONG = 1;
    private static final Integer UPDATED_SOLUONG = 2;

    @Autowired
    private TaisanRepository taisanRepository;

    @Autowired
    private TaisanMapper taisanMapper;

    @Autowired
    private TaisanService taisanService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restTaisanMockMvc;

    private Taisan taisan;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TaisanResource taisanResource = new TaisanResource(taisanService);
        this.restTaisanMockMvc = MockMvcBuilders.standaloneSetup(taisanResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Taisan createEntity() {
        Taisan taisan = new Taisan()
            .maTaisan(DEFAULT_MA_TAISAN)
            .tenTaisan(DEFAULT_TEN_TAISAN)
            .tenRutgon(DEFAULT_TEN_RUTGON)
            .model(DEFAULT_MODEL)
            .serial(DEFAULT_SERIAL)
            .loaiTaisan(DEFAULT_LOAI_TAISAN)
            .mota(DEFAULT_MOTA)
            .ngayNhan(DEFAULT_NGAY_NHAN)
            .ngaySudung(DEFAULT_NGAY_SUDUNG)
            .ngaySanxuat(DEFAULT_NGAY_SANXUAT)
            .thoigianSudung(DEFAULT_THOIGIAN_SUDUNG)
            .ngayHethan(DEFAULT_NGAY_HETHAN)
            .haomon(DEFAULT_HAOMON)
            .nguyengia(DEFAULT_NGUYENGIA)
            .soHopdongMua(DEFAULT_SO_HOPDONG_MUA)
            .soHoadonMua(DEFAULT_SO_HOADON_MUA)
            .ngayHoadon(DEFAULT_NGAY_HOADON)
            .trangthai(DEFAULT_TRANGTHAI)
            .hangSanxuat(DEFAULT_HANG_SANXUAT)
            .nuocSanxuat(DEFAULT_NUOC_SANXUAT)
            .congsuatSudung(DEFAULT_CONGSUAT_SUDUNG)
            .dientichSudung(DEFAULT_DIENTICH_SUDUNG)
            .donviTinh(DEFAULT_DONVI_TINH)
            .giatriConlai(DEFAULT_GIATRI_CONLAI)
            .nguon(DEFAULT_NGUON)
            .vitri(DEFAULT_VITRI)
            .donviQuanly(DEFAULT_DONVI_QUANLY)
            .donviSudung(DEFAULT_DONVI_SUDUNG)
            .soluong(DEFAULT_SOLUONG);
        // Add required entity
        Nhanvien nhanvien;
        nhanvien = NhanvienResourceIT.createEntity();
        nhanvien.setId("fixed-id-for-tests");
        taisan.setNguoiQuanly(nhanvien);
        // Add required entity
        DanhmucTaisan danhmucTaisan;
        danhmucTaisan = DanhmucTaisanResourceIT.createEntity();
        danhmucTaisan.setId("fixed-id-for-tests");
        taisan.setDanhmucTaisan(danhmucTaisan);
        return taisan;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Taisan createUpdatedEntity() {
        Taisan taisan = new Taisan()
            .maTaisan(UPDATED_MA_TAISAN)
            .tenTaisan(UPDATED_TEN_TAISAN)
            .tenRutgon(UPDATED_TEN_RUTGON)
            .model(UPDATED_MODEL)
            .serial(UPDATED_SERIAL)
            .loaiTaisan(UPDATED_LOAI_TAISAN)
            .mota(UPDATED_MOTA)
            .ngayNhan(UPDATED_NGAY_NHAN)
            .ngaySudung(UPDATED_NGAY_SUDUNG)
            .ngaySanxuat(UPDATED_NGAY_SANXUAT)
            .thoigianSudung(UPDATED_THOIGIAN_SUDUNG)
            .ngayHethan(UPDATED_NGAY_HETHAN)
            .haomon(UPDATED_HAOMON)
            .nguyengia(UPDATED_NGUYENGIA)
            .soHopdongMua(UPDATED_SO_HOPDONG_MUA)
            .soHoadonMua(UPDATED_SO_HOADON_MUA)
            .ngayHoadon(UPDATED_NGAY_HOADON)
            .trangthai(UPDATED_TRANGTHAI)
            .hangSanxuat(UPDATED_HANG_SANXUAT)
            .nuocSanxuat(UPDATED_NUOC_SANXUAT)
            .congsuatSudung(UPDATED_CONGSUAT_SUDUNG)
            .dientichSudung(UPDATED_DIENTICH_SUDUNG)
            .donviTinh(UPDATED_DONVI_TINH)
            .giatriConlai(UPDATED_GIATRI_CONLAI)
            .nguon(UPDATED_NGUON)
            .vitri(UPDATED_VITRI)
            .donviQuanly(UPDATED_DONVI_QUANLY)
            .donviSudung(UPDATED_DONVI_SUDUNG)
            .soluong(UPDATED_SOLUONG);
        // Add required entity
        Nhanvien nhanvien;
        nhanvien = NhanvienResourceIT.createUpdatedEntity();
        nhanvien.setId("fixed-id-for-tests");
        taisan.setNguoiQuanly(nhanvien);
        // Add required entity
        DanhmucTaisan danhmucTaisan;
        danhmucTaisan = DanhmucTaisanResourceIT.createUpdatedEntity();
        danhmucTaisan.setId("fixed-id-for-tests");
        taisan.setDanhmucTaisan(danhmucTaisan);
        return taisan;
    }

    @BeforeEach
    public void initTest() {
        taisanRepository.deleteAll();
        taisan = createEntity();
    }

    @Test
    public void createTaisan() throws Exception {
        int databaseSizeBeforeCreate = taisanRepository.findAll().size();

        // Create the Taisan
        TaisanDTO taisanDTO = taisanMapper.toDto(taisan);
        restTaisanMockMvc.perform(post("/api/taisans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taisanDTO)))
            .andExpect(status().isCreated());

        // Validate the Taisan in the database
        List<Taisan> taisanList = taisanRepository.findAll();
        assertThat(taisanList).hasSize(databaseSizeBeforeCreate + 1);
        Taisan testTaisan = taisanList.get(taisanList.size() - 1);
        assertThat(testTaisan.getMaTaisan()).isEqualTo(DEFAULT_MA_TAISAN);
        assertThat(testTaisan.getTenTaisan()).isEqualTo(DEFAULT_TEN_TAISAN);
        assertThat(testTaisan.getTenRutgon()).isEqualTo(DEFAULT_TEN_RUTGON);
        assertThat(testTaisan.getModel()).isEqualTo(DEFAULT_MODEL);
        assertThat(testTaisan.getSerial()).isEqualTo(DEFAULT_SERIAL);
        assertThat(testTaisan.getLoaiTaisan()).isEqualTo(DEFAULT_LOAI_TAISAN);
        assertThat(testTaisan.getMota()).isEqualTo(DEFAULT_MOTA);
        assertThat(testTaisan.getNgayNhan()).isEqualTo(DEFAULT_NGAY_NHAN);
        assertThat(testTaisan.getNgaySudung()).isEqualTo(DEFAULT_NGAY_SUDUNG);
        assertThat(testTaisan.getNgaySanxuat()).isEqualTo(DEFAULT_NGAY_SANXUAT);
        assertThat(testTaisan.getThoigianSudung()).isEqualTo(DEFAULT_THOIGIAN_SUDUNG);
        assertThat(testTaisan.getNgayHethan()).isEqualTo(DEFAULT_NGAY_HETHAN);
        assertThat(testTaisan.getHaomon()).isEqualTo(DEFAULT_HAOMON);
        assertThat(testTaisan.getNguyengia()).isEqualTo(DEFAULT_NGUYENGIA);
        assertThat(testTaisan.getSoHopdongMua()).isEqualTo(DEFAULT_SO_HOPDONG_MUA);
        assertThat(testTaisan.getSoHoadonMua()).isEqualTo(DEFAULT_SO_HOADON_MUA);
        assertThat(testTaisan.getNgayHoadon()).isEqualTo(DEFAULT_NGAY_HOADON);
        assertThat(testTaisan.getTrangthai()).isEqualTo(DEFAULT_TRANGTHAI);
        assertThat(testTaisan.getHangSanxuat()).isEqualTo(DEFAULT_HANG_SANXUAT);
        assertThat(testTaisan.getNuocSanxuat()).isEqualTo(DEFAULT_NUOC_SANXUAT);
        assertThat(testTaisan.getCongsuatSudung()).isEqualTo(DEFAULT_CONGSUAT_SUDUNG);
        assertThat(testTaisan.getDientichSudung()).isEqualTo(DEFAULT_DIENTICH_SUDUNG);
        assertThat(testTaisan.getDonviTinh()).isEqualTo(DEFAULT_DONVI_TINH);
        assertThat(testTaisan.getGiatriConlai()).isEqualTo(DEFAULT_GIATRI_CONLAI);
        assertThat(testTaisan.getNguon()).isEqualTo(DEFAULT_NGUON);
        assertThat(testTaisan.getVitri()).isEqualTo(DEFAULT_VITRI);
        assertThat(testTaisan.getDonviQuanly()).isEqualTo(DEFAULT_DONVI_QUANLY);
        assertThat(testTaisan.getDonviSudung()).isEqualTo(DEFAULT_DONVI_SUDUNG);
        assertThat(testTaisan.getSoluong()).isEqualTo(DEFAULT_SOLUONG);
    }

    @Test
    public void createTaisanWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = taisanRepository.findAll().size();

        // Create the Taisan with an existing ID
        taisan.setId("existing_id");
        TaisanDTO taisanDTO = taisanMapper.toDto(taisan);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTaisanMockMvc.perform(post("/api/taisans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taisanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Taisan in the database
        List<Taisan> taisanList = taisanRepository.findAll();
        assertThat(taisanList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkMaTaisanIsRequired() throws Exception {
        int databaseSizeBeforeTest = taisanRepository.findAll().size();
        // set the field null
        taisan.setMaTaisan(null);

        // Create the Taisan, which fails.
        TaisanDTO taisanDTO = taisanMapper.toDto(taisan);

        restTaisanMockMvc.perform(post("/api/taisans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taisanDTO)))
            .andExpect(status().isBadRequest());

        List<Taisan> taisanList = taisanRepository.findAll();
        assertThat(taisanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenTaisanIsRequired() throws Exception {
        int databaseSizeBeforeTest = taisanRepository.findAll().size();
        // set the field null
        taisan.setTenTaisan(null);

        // Create the Taisan, which fails.
        TaisanDTO taisanDTO = taisanMapper.toDto(taisan);

        restTaisanMockMvc.perform(post("/api/taisans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taisanDTO)))
            .andExpect(status().isBadRequest());

        List<Taisan> taisanList = taisanRepository.findAll();
        assertThat(taisanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenRutgonIsRequired() throws Exception {
        int databaseSizeBeforeTest = taisanRepository.findAll().size();
        // set the field null
        taisan.setTenRutgon(null);

        // Create the Taisan, which fails.
        TaisanDTO taisanDTO = taisanMapper.toDto(taisan);

        restTaisanMockMvc.perform(post("/api/taisans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taisanDTO)))
            .andExpect(status().isBadRequest());

        List<Taisan> taisanList = taisanRepository.findAll();
        assertThat(taisanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLoaiTaisanIsRequired() throws Exception {
        int databaseSizeBeforeTest = taisanRepository.findAll().size();
        // set the field null
        taisan.setLoaiTaisan(null);

        // Create the Taisan, which fails.
        TaisanDTO taisanDTO = taisanMapper.toDto(taisan);

        restTaisanMockMvc.perform(post("/api/taisans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taisanDTO)))
            .andExpect(status().isBadRequest());

        List<Taisan> taisanList = taisanRepository.findAll();
        assertThat(taisanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkNgayNhanIsRequired() throws Exception {
        int databaseSizeBeforeTest = taisanRepository.findAll().size();
        // set the field null
        taisan.setNgayNhan(null);

        // Create the Taisan, which fails.
        TaisanDTO taisanDTO = taisanMapper.toDto(taisan);

        restTaisanMockMvc.perform(post("/api/taisans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taisanDTO)))
            .andExpect(status().isBadRequest());

        List<Taisan> taisanList = taisanRepository.findAll();
        assertThat(taisanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkSoluongIsRequired() throws Exception {
        int databaseSizeBeforeTest = taisanRepository.findAll().size();
        // set the field null
        taisan.setSoluong(null);

        // Create the Taisan, which fails.
        TaisanDTO taisanDTO = taisanMapper.toDto(taisan);

        restTaisanMockMvc.perform(post("/api/taisans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taisanDTO)))
            .andExpect(status().isBadRequest());

        List<Taisan> taisanList = taisanRepository.findAll();
        assertThat(taisanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllTaisans() throws Exception {
        // Initialize the database
        taisanRepository.save(taisan);

        // Get all the taisanList
        restTaisanMockMvc.perform(get("/api/taisans?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(taisan.getId())))
            .andExpect(jsonPath("$.[*].maTaisan").value(hasItem(DEFAULT_MA_TAISAN)))
            .andExpect(jsonPath("$.[*].tenTaisan").value(hasItem(DEFAULT_TEN_TAISAN)))
            .andExpect(jsonPath("$.[*].tenRutgon").value(hasItem(DEFAULT_TEN_RUTGON)))
            .andExpect(jsonPath("$.[*].model").value(hasItem(DEFAULT_MODEL)))
            .andExpect(jsonPath("$.[*].serial").value(hasItem(DEFAULT_SERIAL)))
            .andExpect(jsonPath("$.[*].loaiTaisan").value(hasItem(DEFAULT_LOAI_TAISAN)))
            .andExpect(jsonPath("$.[*].mota").value(hasItem(DEFAULT_MOTA)))
            .andExpect(jsonPath("$.[*].ngayNhan").value(hasItem(DEFAULT_NGAY_NHAN.toString())))
            .andExpect(jsonPath("$.[*].ngaySudung").value(hasItem(DEFAULT_NGAY_SUDUNG.toString())))
            .andExpect(jsonPath("$.[*].ngaySanxuat").value(hasItem(DEFAULT_NGAY_SANXUAT.toString())))
            .andExpect(jsonPath("$.[*].thoigianSudung").value(hasItem(DEFAULT_THOIGIAN_SUDUNG)))
            .andExpect(jsonPath("$.[*].ngayHethan").value(hasItem(DEFAULT_NGAY_HETHAN.toString())))
            .andExpect(jsonPath("$.[*].haomon").value(hasItem(DEFAULT_HAOMON)))
            .andExpect(jsonPath("$.[*].nguyengia").value(hasItem(DEFAULT_NGUYENGIA.intValue())))
            .andExpect(jsonPath("$.[*].soHopdongMua").value(hasItem(DEFAULT_SO_HOPDONG_MUA)))
            .andExpect(jsonPath("$.[*].soHoadonMua").value(hasItem(DEFAULT_SO_HOADON_MUA)))
            .andExpect(jsonPath("$.[*].ngayHoadon").value(hasItem(DEFAULT_NGAY_HOADON.toString())))
            .andExpect(jsonPath("$.[*].trangthai").value(hasItem(DEFAULT_TRANGTHAI)))
            .andExpect(jsonPath("$.[*].hangSanxuat").value(hasItem(DEFAULT_HANG_SANXUAT)))
            .andExpect(jsonPath("$.[*].nuocSanxuat").value(hasItem(DEFAULT_NUOC_SANXUAT)))
            .andExpect(jsonPath("$.[*].congsuatSudung").value(hasItem(DEFAULT_CONGSUAT_SUDUNG.doubleValue())))
            .andExpect(jsonPath("$.[*].dientichSudung").value(hasItem(DEFAULT_DIENTICH_SUDUNG.doubleValue())))
            .andExpect(jsonPath("$.[*].donviTinh").value(hasItem(DEFAULT_DONVI_TINH)))
            .andExpect(jsonPath("$.[*].giatriConlai").value(hasItem(DEFAULT_GIATRI_CONLAI.intValue())))
            .andExpect(jsonPath("$.[*].nguon").value(hasItem(DEFAULT_NGUON)))
            .andExpect(jsonPath("$.[*].vitri").value(hasItem(DEFAULT_VITRI)))
            .andExpect(jsonPath("$.[*].donviQuanly").value(hasItem(DEFAULT_DONVI_QUANLY)))
            .andExpect(jsonPath("$.[*].donviSudung").value(hasItem(DEFAULT_DONVI_SUDUNG)))
            .andExpect(jsonPath("$.[*].soluong").value(hasItem(DEFAULT_SOLUONG)));
    }
    
    @Test
    public void getTaisan() throws Exception {
        // Initialize the database
        taisanRepository.save(taisan);

        // Get the taisan
        restTaisanMockMvc.perform(get("/api/taisans/{id}", taisan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(taisan.getId()))
            .andExpect(jsonPath("$.maTaisan").value(DEFAULT_MA_TAISAN))
            .andExpect(jsonPath("$.tenTaisan").value(DEFAULT_TEN_TAISAN))
            .andExpect(jsonPath("$.tenRutgon").value(DEFAULT_TEN_RUTGON))
            .andExpect(jsonPath("$.model").value(DEFAULT_MODEL))
            .andExpect(jsonPath("$.serial").value(DEFAULT_SERIAL))
            .andExpect(jsonPath("$.loaiTaisan").value(DEFAULT_LOAI_TAISAN))
            .andExpect(jsonPath("$.mota").value(DEFAULT_MOTA))
            .andExpect(jsonPath("$.ngayNhan").value(DEFAULT_NGAY_NHAN.toString()))
            .andExpect(jsonPath("$.ngaySudung").value(DEFAULT_NGAY_SUDUNG.toString()))
            .andExpect(jsonPath("$.ngaySanxuat").value(DEFAULT_NGAY_SANXUAT.toString()))
            .andExpect(jsonPath("$.thoigianSudung").value(DEFAULT_THOIGIAN_SUDUNG))
            .andExpect(jsonPath("$.ngayHethan").value(DEFAULT_NGAY_HETHAN.toString()))
            .andExpect(jsonPath("$.haomon").value(DEFAULT_HAOMON))
            .andExpect(jsonPath("$.nguyengia").value(DEFAULT_NGUYENGIA.intValue()))
            .andExpect(jsonPath("$.soHopdongMua").value(DEFAULT_SO_HOPDONG_MUA))
            .andExpect(jsonPath("$.soHoadonMua").value(DEFAULT_SO_HOADON_MUA))
            .andExpect(jsonPath("$.ngayHoadon").value(DEFAULT_NGAY_HOADON.toString()))
            .andExpect(jsonPath("$.trangthai").value(DEFAULT_TRANGTHAI))
            .andExpect(jsonPath("$.hangSanxuat").value(DEFAULT_HANG_SANXUAT))
            .andExpect(jsonPath("$.nuocSanxuat").value(DEFAULT_NUOC_SANXUAT))
            .andExpect(jsonPath("$.congsuatSudung").value(DEFAULT_CONGSUAT_SUDUNG.doubleValue()))
            .andExpect(jsonPath("$.dientichSudung").value(DEFAULT_DIENTICH_SUDUNG.doubleValue()))
            .andExpect(jsonPath("$.donviTinh").value(DEFAULT_DONVI_TINH))
            .andExpect(jsonPath("$.giatriConlai").value(DEFAULT_GIATRI_CONLAI.intValue()))
            .andExpect(jsonPath("$.nguon").value(DEFAULT_NGUON))
            .andExpect(jsonPath("$.vitri").value(DEFAULT_VITRI))
            .andExpect(jsonPath("$.donviQuanly").value(DEFAULT_DONVI_QUANLY))
            .andExpect(jsonPath("$.donviSudung").value(DEFAULT_DONVI_SUDUNG))
            .andExpect(jsonPath("$.soluong").value(DEFAULT_SOLUONG));
    }

    @Test
    public void getNonExistingTaisan() throws Exception {
        // Get the taisan
        restTaisanMockMvc.perform(get("/api/taisans/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateTaisan() throws Exception {
        // Initialize the database
        taisanRepository.save(taisan);

        int databaseSizeBeforeUpdate = taisanRepository.findAll().size();

        // Update the taisan
        Taisan updatedTaisan = taisanRepository.findById(taisan.getId()).get();
        updatedTaisan
            .maTaisan(UPDATED_MA_TAISAN)
            .tenTaisan(UPDATED_TEN_TAISAN)
            .tenRutgon(UPDATED_TEN_RUTGON)
            .model(UPDATED_MODEL)
            .serial(UPDATED_SERIAL)
            .loaiTaisan(UPDATED_LOAI_TAISAN)
            .mota(UPDATED_MOTA)
            .ngayNhan(UPDATED_NGAY_NHAN)
            .ngaySudung(UPDATED_NGAY_SUDUNG)
            .ngaySanxuat(UPDATED_NGAY_SANXUAT)
            .thoigianSudung(UPDATED_THOIGIAN_SUDUNG)
            .ngayHethan(UPDATED_NGAY_HETHAN)
            .haomon(UPDATED_HAOMON)
            .nguyengia(UPDATED_NGUYENGIA)
            .soHopdongMua(UPDATED_SO_HOPDONG_MUA)
            .soHoadonMua(UPDATED_SO_HOADON_MUA)
            .ngayHoadon(UPDATED_NGAY_HOADON)
            .trangthai(UPDATED_TRANGTHAI)
            .hangSanxuat(UPDATED_HANG_SANXUAT)
            .nuocSanxuat(UPDATED_NUOC_SANXUAT)
            .congsuatSudung(UPDATED_CONGSUAT_SUDUNG)
            .dientichSudung(UPDATED_DIENTICH_SUDUNG)
            .donviTinh(UPDATED_DONVI_TINH)
            .giatriConlai(UPDATED_GIATRI_CONLAI)
            .nguon(UPDATED_NGUON)
            .vitri(UPDATED_VITRI)
            .donviQuanly(UPDATED_DONVI_QUANLY)
            .donviSudung(UPDATED_DONVI_SUDUNG)
            .soluong(UPDATED_SOLUONG);
        TaisanDTO taisanDTO = taisanMapper.toDto(updatedTaisan);

        restTaisanMockMvc.perform(put("/api/taisans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taisanDTO)))
            .andExpect(status().isOk());

        // Validate the Taisan in the database
        List<Taisan> taisanList = taisanRepository.findAll();
        assertThat(taisanList).hasSize(databaseSizeBeforeUpdate);
        Taisan testTaisan = taisanList.get(taisanList.size() - 1);
        assertThat(testTaisan.getMaTaisan()).isEqualTo(UPDATED_MA_TAISAN);
        assertThat(testTaisan.getTenTaisan()).isEqualTo(UPDATED_TEN_TAISAN);
        assertThat(testTaisan.getTenRutgon()).isEqualTo(UPDATED_TEN_RUTGON);
        assertThat(testTaisan.getModel()).isEqualTo(UPDATED_MODEL);
        assertThat(testTaisan.getSerial()).isEqualTo(UPDATED_SERIAL);
        assertThat(testTaisan.getLoaiTaisan()).isEqualTo(UPDATED_LOAI_TAISAN);
        assertThat(testTaisan.getMota()).isEqualTo(UPDATED_MOTA);
        assertThat(testTaisan.getNgayNhan()).isEqualTo(UPDATED_NGAY_NHAN);
        assertThat(testTaisan.getNgaySudung()).isEqualTo(UPDATED_NGAY_SUDUNG);
        assertThat(testTaisan.getNgaySanxuat()).isEqualTo(UPDATED_NGAY_SANXUAT);
        assertThat(testTaisan.getThoigianSudung()).isEqualTo(UPDATED_THOIGIAN_SUDUNG);
        assertThat(testTaisan.getNgayHethan()).isEqualTo(UPDATED_NGAY_HETHAN);
        assertThat(testTaisan.getHaomon()).isEqualTo(UPDATED_HAOMON);
        assertThat(testTaisan.getNguyengia()).isEqualTo(UPDATED_NGUYENGIA);
        assertThat(testTaisan.getSoHopdongMua()).isEqualTo(UPDATED_SO_HOPDONG_MUA);
        assertThat(testTaisan.getSoHoadonMua()).isEqualTo(UPDATED_SO_HOADON_MUA);
        assertThat(testTaisan.getNgayHoadon()).isEqualTo(UPDATED_NGAY_HOADON);
        assertThat(testTaisan.getTrangthai()).isEqualTo(UPDATED_TRANGTHAI);
        assertThat(testTaisan.getHangSanxuat()).isEqualTo(UPDATED_HANG_SANXUAT);
        assertThat(testTaisan.getNuocSanxuat()).isEqualTo(UPDATED_NUOC_SANXUAT);
        assertThat(testTaisan.getCongsuatSudung()).isEqualTo(UPDATED_CONGSUAT_SUDUNG);
        assertThat(testTaisan.getDientichSudung()).isEqualTo(UPDATED_DIENTICH_SUDUNG);
        assertThat(testTaisan.getDonviTinh()).isEqualTo(UPDATED_DONVI_TINH);
        assertThat(testTaisan.getGiatriConlai()).isEqualTo(UPDATED_GIATRI_CONLAI);
        assertThat(testTaisan.getNguon()).isEqualTo(UPDATED_NGUON);
        assertThat(testTaisan.getVitri()).isEqualTo(UPDATED_VITRI);
        assertThat(testTaisan.getDonviQuanly()).isEqualTo(UPDATED_DONVI_QUANLY);
        assertThat(testTaisan.getDonviSudung()).isEqualTo(UPDATED_DONVI_SUDUNG);
        assertThat(testTaisan.getSoluong()).isEqualTo(UPDATED_SOLUONG);
    }

    @Test
    public void updateNonExistingTaisan() throws Exception {
        int databaseSizeBeforeUpdate = taisanRepository.findAll().size();

        // Create the Taisan
        TaisanDTO taisanDTO = taisanMapper.toDto(taisan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaisanMockMvc.perform(put("/api/taisans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taisanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Taisan in the database
        List<Taisan> taisanList = taisanRepository.findAll();
        assertThat(taisanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteTaisan() throws Exception {
        // Initialize the database
        taisanRepository.save(taisan);

        int databaseSizeBeforeDelete = taisanRepository.findAll().size();

        // Delete the taisan
        restTaisanMockMvc.perform(delete("/api/taisans/{id}", taisan.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Taisan> taisanList = taisanRepository.findAll();
        assertThat(taisanList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
