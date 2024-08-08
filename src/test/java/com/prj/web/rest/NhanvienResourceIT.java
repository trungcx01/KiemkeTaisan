package com.prj.web.rest;

import com.prj.TaisanApp;
import com.prj.domain.Nhanvien;
import com.prj.repository.NhanvienRepository;
import com.prj.service.NhanvienService;
import com.prj.service.dto.NhanvienDTO;
import com.prj.service.mapper.NhanvienMapper;
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


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.prj.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link NhanvienResource} REST controller.
 */
@SpringBootTest(classes = TaisanApp.class)
public class NhanvienResourceIT {

    private static final String DEFAULT_MA_NV = "AAAAAAAAAA";
    private static final String UPDATED_MA_NV = "BBBBBBBBBB";

    private static final String DEFAULT_TEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN = "BBBBBBBBBB";

    private static final String DEFAULT_CHUCVU = "AAAAAAAAAA";
    private static final String UPDATED_CHUCVU = "BBBBBBBBBB";

    private static final String DEFAULT_DIACHI = "AAAAAAAAAA";
    private static final String UPDATED_DIACHI = "BBBBBBBBBB";

    private static final Integer DEFAULT_GIOITINH = 1;
    private static final Integer UPDATED_GIOITINH = 2;

    private static final String DEFAULT_SDT = "AAAAAAAAAA";
    private static final String UPDATED_SDT = "BBBBBBBBBB";

    private static final String DEFAULT_PHONGBAN = "AAAAAAAAAA";
    private static final String UPDATED_PHONGBAN = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NGAY_THAMGIA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_THAMGIA = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private NhanvienRepository nhanvienRepository;

    @Autowired
    private NhanvienMapper nhanvienMapper;

    @Autowired
    private NhanvienService nhanvienService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restNhanvienMockMvc;

    private Nhanvien nhanvien;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NhanvienResource nhanvienResource = new NhanvienResource(nhanvienService);
        this.restNhanvienMockMvc = MockMvcBuilders.standaloneSetup(nhanvienResource)
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
    public static Nhanvien createEntity() {
        Nhanvien nhanvien = new Nhanvien()
            .maNV(DEFAULT_MA_NV)
            .ten(DEFAULT_TEN)
            .chucvu(DEFAULT_CHUCVU)
            .diachi(DEFAULT_DIACHI)
            .gioitinh(DEFAULT_GIOITINH)
            .sdt(DEFAULT_SDT)
            .phongban(DEFAULT_PHONGBAN)
            .email(DEFAULT_EMAIL)
            .ngayThamgia(DEFAULT_NGAY_THAMGIA);
        return nhanvien;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Nhanvien createUpdatedEntity() {
        Nhanvien nhanvien = new Nhanvien()
            .maNV(UPDATED_MA_NV)
            .ten(UPDATED_TEN)
            .chucvu(UPDATED_CHUCVU)
            .diachi(UPDATED_DIACHI)
            .gioitinh(UPDATED_GIOITINH)
            .sdt(UPDATED_SDT)
            .phongban(UPDATED_PHONGBAN)
            .email(UPDATED_EMAIL)
            .ngayThamgia(UPDATED_NGAY_THAMGIA);
        return nhanvien;
    }

    @BeforeEach
    public void initTest() {
        nhanvienRepository.deleteAll();
        nhanvien = createEntity();
    }

    @Test
    public void createNhanvien() throws Exception {
        int databaseSizeBeforeCreate = nhanvienRepository.findAll().size();

        // Create the Nhanvien
        NhanvienDTO nhanvienDTO = nhanvienMapper.toDto(nhanvien);
        restNhanvienMockMvc.perform(post("/api/nhanviens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhanvienDTO)))
            .andExpect(status().isCreated());

        // Validate the Nhanvien in the database
        List<Nhanvien> nhanvienList = nhanvienRepository.findAll();
        assertThat(nhanvienList).hasSize(databaseSizeBeforeCreate + 1);
        Nhanvien testNhanvien = nhanvienList.get(nhanvienList.size() - 1);
        assertThat(testNhanvien.getMaNV()).isEqualTo(DEFAULT_MA_NV);
        assertThat(testNhanvien.getTen()).isEqualTo(DEFAULT_TEN);
        assertThat(testNhanvien.getChucvu()).isEqualTo(DEFAULT_CHUCVU);
        assertThat(testNhanvien.getDiachi()).isEqualTo(DEFAULT_DIACHI);
        assertThat(testNhanvien.getGioitinh()).isEqualTo(DEFAULT_GIOITINH);
        assertThat(testNhanvien.getSdt()).isEqualTo(DEFAULT_SDT);
        assertThat(testNhanvien.getPhongban()).isEqualTo(DEFAULT_PHONGBAN);
        assertThat(testNhanvien.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testNhanvien.getNgayThamgia()).isEqualTo(DEFAULT_NGAY_THAMGIA);
    }

    @Test
    public void createNhanvienWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nhanvienRepository.findAll().size();

        // Create the Nhanvien with an existing ID
        nhanvien.setId("existing_id");
        NhanvienDTO nhanvienDTO = nhanvienMapper.toDto(nhanvien);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNhanvienMockMvc.perform(post("/api/nhanviens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhanvienDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Nhanvien in the database
        List<Nhanvien> nhanvienList = nhanvienRepository.findAll();
        assertThat(nhanvienList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkMaNVIsRequired() throws Exception {
        int databaseSizeBeforeTest = nhanvienRepository.findAll().size();
        // set the field null
        nhanvien.setMaNV(null);

        // Create the Nhanvien, which fails.
        NhanvienDTO nhanvienDTO = nhanvienMapper.toDto(nhanvien);

        restNhanvienMockMvc.perform(post("/api/nhanviens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhanvienDTO)))
            .andExpect(status().isBadRequest());

        List<Nhanvien> nhanvienList = nhanvienRepository.findAll();
        assertThat(nhanvienList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenIsRequired() throws Exception {
        int databaseSizeBeforeTest = nhanvienRepository.findAll().size();
        // set the field null
        nhanvien.setTen(null);

        // Create the Nhanvien, which fails.
        NhanvienDTO nhanvienDTO = nhanvienMapper.toDto(nhanvien);

        restNhanvienMockMvc.perform(post("/api/nhanviens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhanvienDTO)))
            .andExpect(status().isBadRequest());

        List<Nhanvien> nhanvienList = nhanvienRepository.findAll();
        assertThat(nhanvienList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkChucvuIsRequired() throws Exception {
        int databaseSizeBeforeTest = nhanvienRepository.findAll().size();
        // set the field null
        nhanvien.setChucvu(null);

        // Create the Nhanvien, which fails.
        NhanvienDTO nhanvienDTO = nhanvienMapper.toDto(nhanvien);

        restNhanvienMockMvc.perform(post("/api/nhanviens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhanvienDTO)))
            .andExpect(status().isBadRequest());

        List<Nhanvien> nhanvienList = nhanvienRepository.findAll();
        assertThat(nhanvienList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkGioitinhIsRequired() throws Exception {
        int databaseSizeBeforeTest = nhanvienRepository.findAll().size();
        // set the field null
        nhanvien.setGioitinh(null);

        // Create the Nhanvien, which fails.
        NhanvienDTO nhanvienDTO = nhanvienMapper.toDto(nhanvien);

        restNhanvienMockMvc.perform(post("/api/nhanviens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhanvienDTO)))
            .andExpect(status().isBadRequest());

        List<Nhanvien> nhanvienList = nhanvienRepository.findAll();
        assertThat(nhanvienList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkSdtIsRequired() throws Exception {
        int databaseSizeBeforeTest = nhanvienRepository.findAll().size();
        // set the field null
        nhanvien.setSdt(null);

        // Create the Nhanvien, which fails.
        NhanvienDTO nhanvienDTO = nhanvienMapper.toDto(nhanvien);

        restNhanvienMockMvc.perform(post("/api/nhanviens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhanvienDTO)))
            .andExpect(status().isBadRequest());

        List<Nhanvien> nhanvienList = nhanvienRepository.findAll();
        assertThat(nhanvienList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkPhongbanIsRequired() throws Exception {
        int databaseSizeBeforeTest = nhanvienRepository.findAll().size();
        // set the field null
        nhanvien.setPhongban(null);

        // Create the Nhanvien, which fails.
        NhanvienDTO nhanvienDTO = nhanvienMapper.toDto(nhanvien);

        restNhanvienMockMvc.perform(post("/api/nhanviens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhanvienDTO)))
            .andExpect(status().isBadRequest());

        List<Nhanvien> nhanvienList = nhanvienRepository.findAll();
        assertThat(nhanvienList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = nhanvienRepository.findAll().size();
        // set the field null
        nhanvien.setEmail(null);

        // Create the Nhanvien, which fails.
        NhanvienDTO nhanvienDTO = nhanvienMapper.toDto(nhanvien);

        restNhanvienMockMvc.perform(post("/api/nhanviens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhanvienDTO)))
            .andExpect(status().isBadRequest());

        List<Nhanvien> nhanvienList = nhanvienRepository.findAll();
        assertThat(nhanvienList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkNgayThamgiaIsRequired() throws Exception {
        int databaseSizeBeforeTest = nhanvienRepository.findAll().size();
        // set the field null
        nhanvien.setNgayThamgia(null);

        // Create the Nhanvien, which fails.
        NhanvienDTO nhanvienDTO = nhanvienMapper.toDto(nhanvien);

        restNhanvienMockMvc.perform(post("/api/nhanviens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhanvienDTO)))
            .andExpect(status().isBadRequest());

        List<Nhanvien> nhanvienList = nhanvienRepository.findAll();
        assertThat(nhanvienList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllNhanviens() throws Exception {
        // Initialize the database
        nhanvienRepository.save(nhanvien);

        // Get all the nhanvienList
        restNhanvienMockMvc.perform(get("/api/nhanviens?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nhanvien.getId())))
            .andExpect(jsonPath("$.[*].maNV").value(hasItem(DEFAULT_MA_NV)))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].chucvu").value(hasItem(DEFAULT_CHUCVU)))
            .andExpect(jsonPath("$.[*].diachi").value(hasItem(DEFAULT_DIACHI)))
            .andExpect(jsonPath("$.[*].gioitinh").value(hasItem(DEFAULT_GIOITINH)))
            .andExpect(jsonPath("$.[*].sdt").value(hasItem(DEFAULT_SDT)))
            .andExpect(jsonPath("$.[*].phongban").value(hasItem(DEFAULT_PHONGBAN)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].ngayThamgia").value(hasItem(DEFAULT_NGAY_THAMGIA.toString())));
    }
    
    @Test
    public void getNhanvien() throws Exception {
        // Initialize the database
        nhanvienRepository.save(nhanvien);

        // Get the nhanvien
        restNhanvienMockMvc.perform(get("/api/nhanviens/{id}", nhanvien.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(nhanvien.getId()))
            .andExpect(jsonPath("$.maNV").value(DEFAULT_MA_NV))
            .andExpect(jsonPath("$.ten").value(DEFAULT_TEN))
            .andExpect(jsonPath("$.chucvu").value(DEFAULT_CHUCVU))
            .andExpect(jsonPath("$.diachi").value(DEFAULT_DIACHI))
            .andExpect(jsonPath("$.gioitinh").value(DEFAULT_GIOITINH))
            .andExpect(jsonPath("$.sdt").value(DEFAULT_SDT))
            .andExpect(jsonPath("$.phongban").value(DEFAULT_PHONGBAN))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.ngayThamgia").value(DEFAULT_NGAY_THAMGIA.toString()));
    }

    @Test
    public void getNonExistingNhanvien() throws Exception {
        // Get the nhanvien
        restNhanvienMockMvc.perform(get("/api/nhanviens/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateNhanvien() throws Exception {
        // Initialize the database
        nhanvienRepository.save(nhanvien);

        int databaseSizeBeforeUpdate = nhanvienRepository.findAll().size();

        // Update the nhanvien
        Nhanvien updatedNhanvien = nhanvienRepository.findById(nhanvien.getId()).get();
        updatedNhanvien
            .maNV(UPDATED_MA_NV)
            .ten(UPDATED_TEN)
            .chucvu(UPDATED_CHUCVU)
            .diachi(UPDATED_DIACHI)
            .gioitinh(UPDATED_GIOITINH)
            .sdt(UPDATED_SDT)
            .phongban(UPDATED_PHONGBAN)
            .email(UPDATED_EMAIL)
            .ngayThamgia(UPDATED_NGAY_THAMGIA);
        NhanvienDTO nhanvienDTO = nhanvienMapper.toDto(updatedNhanvien);

        restNhanvienMockMvc.perform(put("/api/nhanviens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhanvienDTO)))
            .andExpect(status().isOk());

        // Validate the Nhanvien in the database
        List<Nhanvien> nhanvienList = nhanvienRepository.findAll();
        assertThat(nhanvienList).hasSize(databaseSizeBeforeUpdate);
        Nhanvien testNhanvien = nhanvienList.get(nhanvienList.size() - 1);
        assertThat(testNhanvien.getMaNV()).isEqualTo(UPDATED_MA_NV);
        assertThat(testNhanvien.getTen()).isEqualTo(UPDATED_TEN);
        assertThat(testNhanvien.getChucvu()).isEqualTo(UPDATED_CHUCVU);
        assertThat(testNhanvien.getDiachi()).isEqualTo(UPDATED_DIACHI);
        assertThat(testNhanvien.getGioitinh()).isEqualTo(UPDATED_GIOITINH);
        assertThat(testNhanvien.getSdt()).isEqualTo(UPDATED_SDT);
        assertThat(testNhanvien.getPhongban()).isEqualTo(UPDATED_PHONGBAN);
        assertThat(testNhanvien.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testNhanvien.getNgayThamgia()).isEqualTo(UPDATED_NGAY_THAMGIA);
    }

    @Test
    public void updateNonExistingNhanvien() throws Exception {
        int databaseSizeBeforeUpdate = nhanvienRepository.findAll().size();

        // Create the Nhanvien
        NhanvienDTO nhanvienDTO = nhanvienMapper.toDto(nhanvien);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNhanvienMockMvc.perform(put("/api/nhanviens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhanvienDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Nhanvien in the database
        List<Nhanvien> nhanvienList = nhanvienRepository.findAll();
        assertThat(nhanvienList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteNhanvien() throws Exception {
        // Initialize the database
        nhanvienRepository.save(nhanvien);

        int databaseSizeBeforeDelete = nhanvienRepository.findAll().size();

        // Delete the nhanvien
        restNhanvienMockMvc.perform(delete("/api/nhanviens/{id}", nhanvien.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Nhanvien> nhanvienList = nhanvienRepository.findAll();
        assertThat(nhanvienList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
