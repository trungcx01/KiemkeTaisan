package com.prj.web.rest;

import com.prj.TaisanApp;
import com.prj.domain.KiemkeTaisan;
import com.prj.repository.KiemkeTaisanRepository;
import com.prj.service.KiemkeTaisanService;
import com.prj.service.dto.KiemkeTaisanDTO;
import com.prj.service.mapper.KiemkeTaisanMapper;
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
 * Integration tests for the {@link KiemkeTaisanResource} REST controller.
 */
@SpringBootTest(classes = TaisanApp.class)
public class KiemkeTaisanResourceIT {

    private static final String DEFAULT_SOPHIEU = "AAAAAAAAAA";
    private static final String UPDATED_SOPHIEU = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NGAY_LAPPHIEU = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_LAPPHIEU = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_NGAY_KIEMKE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_KIEMKE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DONVI_SUDUNG = "AAAAAAAAAA";
    private static final String UPDATED_DONVI_SUDUNG = "BBBBBBBBBB";

    private static final String DEFAULT_GHICHU = "AAAAAAAAAA";
    private static final String UPDATED_GHICHU = "BBBBBBBBBB";

    @Autowired
    private KiemkeTaisanRepository kiemkeTaisanRepository;

    @Autowired
    private KiemkeTaisanMapper kiemkeTaisanMapper;

    @Autowired
    private KiemkeTaisanService kiemkeTaisanService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restKiemkeTaisanMockMvc;

    private KiemkeTaisan kiemkeTaisan;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final KiemkeTaisanResource kiemkeTaisanResource = new KiemkeTaisanResource(kiemkeTaisanService);
        this.restKiemkeTaisanMockMvc = MockMvcBuilders.standaloneSetup(kiemkeTaisanResource)
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
    public static KiemkeTaisan createEntity() {
        KiemkeTaisan kiemkeTaisan = new KiemkeTaisan()
            .sophieu(DEFAULT_SOPHIEU)
            .ngayLapphieu(DEFAULT_NGAY_LAPPHIEU)
            .ngayKiemke(DEFAULT_NGAY_KIEMKE)
            .donviSudung(DEFAULT_DONVI_SUDUNG)
            .ghichu(DEFAULT_GHICHU);
        return kiemkeTaisan;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KiemkeTaisan createUpdatedEntity() {
        KiemkeTaisan kiemkeTaisan = new KiemkeTaisan()
            .sophieu(UPDATED_SOPHIEU)
            .ngayLapphieu(UPDATED_NGAY_LAPPHIEU)
            .ngayKiemke(UPDATED_NGAY_KIEMKE)
            .donviSudung(UPDATED_DONVI_SUDUNG)
            .ghichu(UPDATED_GHICHU);
        return kiemkeTaisan;
    }

    @BeforeEach
    public void initTest() {
        kiemkeTaisanRepository.deleteAll();
        kiemkeTaisan = createEntity();
    }

    @Test
    public void createKiemkeTaisan() throws Exception {
        int databaseSizeBeforeCreate = kiemkeTaisanRepository.findAll().size();

        // Create the KiemkeTaisan
        KiemkeTaisanDTO kiemkeTaisanDTO = kiemkeTaisanMapper.toDto(kiemkeTaisan);
        restKiemkeTaisanMockMvc.perform(post("/api/kiemke-taisans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kiemkeTaisanDTO)))
            .andExpect(status().isCreated());

        // Validate the KiemkeTaisan in the database
        List<KiemkeTaisan> kiemkeTaisanList = kiemkeTaisanRepository.findAll();
        assertThat(kiemkeTaisanList).hasSize(databaseSizeBeforeCreate + 1);
        KiemkeTaisan testKiemkeTaisan = kiemkeTaisanList.get(kiemkeTaisanList.size() - 1);
        assertThat(testKiemkeTaisan.getSophieu()).isEqualTo(DEFAULT_SOPHIEU);
        assertThat(testKiemkeTaisan.getNgayLapphieu()).isEqualTo(DEFAULT_NGAY_LAPPHIEU);
        assertThat(testKiemkeTaisan.getNgayKiemke()).isEqualTo(DEFAULT_NGAY_KIEMKE);
        assertThat(testKiemkeTaisan.getDonviSudung()).isEqualTo(DEFAULT_DONVI_SUDUNG);
        assertThat(testKiemkeTaisan.getGhichu()).isEqualTo(DEFAULT_GHICHU);
    }

    @Test
    public void createKiemkeTaisanWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = kiemkeTaisanRepository.findAll().size();

        // Create the KiemkeTaisan with an existing ID
        kiemkeTaisan.setId("existing_id");
        KiemkeTaisanDTO kiemkeTaisanDTO = kiemkeTaisanMapper.toDto(kiemkeTaisan);

        // An entity with an existing ID cannot be created, so this API call must fail
        restKiemkeTaisanMockMvc.perform(post("/api/kiemke-taisans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kiemkeTaisanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the KiemkeTaisan in the database
        List<KiemkeTaisan> kiemkeTaisanList = kiemkeTaisanRepository.findAll();
        assertThat(kiemkeTaisanList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkSophieuIsRequired() throws Exception {
        int databaseSizeBeforeTest = kiemkeTaisanRepository.findAll().size();
        // set the field null
        kiemkeTaisan.setSophieu(null);

        // Create the KiemkeTaisan, which fails.
        KiemkeTaisanDTO kiemkeTaisanDTO = kiemkeTaisanMapper.toDto(kiemkeTaisan);

        restKiemkeTaisanMockMvc.perform(post("/api/kiemke-taisans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kiemkeTaisanDTO)))
            .andExpect(status().isBadRequest());

        List<KiemkeTaisan> kiemkeTaisanList = kiemkeTaisanRepository.findAll();
        assertThat(kiemkeTaisanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkNgayLapphieuIsRequired() throws Exception {
        int databaseSizeBeforeTest = kiemkeTaisanRepository.findAll().size();
        // set the field null
        kiemkeTaisan.setNgayLapphieu(null);

        // Create the KiemkeTaisan, which fails.
        KiemkeTaisanDTO kiemkeTaisanDTO = kiemkeTaisanMapper.toDto(kiemkeTaisan);

        restKiemkeTaisanMockMvc.perform(post("/api/kiemke-taisans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kiemkeTaisanDTO)))
            .andExpect(status().isBadRequest());

        List<KiemkeTaisan> kiemkeTaisanList = kiemkeTaisanRepository.findAll();
        assertThat(kiemkeTaisanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkNgayKiemkeIsRequired() throws Exception {
        int databaseSizeBeforeTest = kiemkeTaisanRepository.findAll().size();
        // set the field null
        kiemkeTaisan.setNgayKiemke(null);

        // Create the KiemkeTaisan, which fails.
        KiemkeTaisanDTO kiemkeTaisanDTO = kiemkeTaisanMapper.toDto(kiemkeTaisan);

        restKiemkeTaisanMockMvc.perform(post("/api/kiemke-taisans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kiemkeTaisanDTO)))
            .andExpect(status().isBadRequest());

        List<KiemkeTaisan> kiemkeTaisanList = kiemkeTaisanRepository.findAll();
        assertThat(kiemkeTaisanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkDonviSudungIsRequired() throws Exception {
        int databaseSizeBeforeTest = kiemkeTaisanRepository.findAll().size();
        // set the field null
        kiemkeTaisan.setDonviSudung(null);

        // Create the KiemkeTaisan, which fails.
        KiemkeTaisanDTO kiemkeTaisanDTO = kiemkeTaisanMapper.toDto(kiemkeTaisan);

        restKiemkeTaisanMockMvc.perform(post("/api/kiemke-taisans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kiemkeTaisanDTO)))
            .andExpect(status().isBadRequest());

        List<KiemkeTaisan> kiemkeTaisanList = kiemkeTaisanRepository.findAll();
        assertThat(kiemkeTaisanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllKiemkeTaisans() throws Exception {
        // Initialize the database
        kiemkeTaisanRepository.save(kiemkeTaisan);

        // Get all the kiemkeTaisanList
        restKiemkeTaisanMockMvc.perform(get("/api/kiemke-taisans?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(kiemkeTaisan.getId())))
            .andExpect(jsonPath("$.[*].sophieu").value(hasItem(DEFAULT_SOPHIEU)))
            .andExpect(jsonPath("$.[*].ngayLapphieu").value(hasItem(DEFAULT_NGAY_LAPPHIEU.toString())))
            .andExpect(jsonPath("$.[*].ngayKiemke").value(hasItem(DEFAULT_NGAY_KIEMKE.toString())))
            .andExpect(jsonPath("$.[*].donviSudung").value(hasItem(DEFAULT_DONVI_SUDUNG)))
            .andExpect(jsonPath("$.[*].ghichu").value(hasItem(DEFAULT_GHICHU)));
    }
    
    @Test
    public void getKiemkeTaisan() throws Exception {
        // Initialize the database
        kiemkeTaisanRepository.save(kiemkeTaisan);

        // Get the kiemkeTaisan
        restKiemkeTaisanMockMvc.perform(get("/api/kiemke-taisans/{id}", kiemkeTaisan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(kiemkeTaisan.getId()))
            .andExpect(jsonPath("$.sophieu").value(DEFAULT_SOPHIEU))
            .andExpect(jsonPath("$.ngayLapphieu").value(DEFAULT_NGAY_LAPPHIEU.toString()))
            .andExpect(jsonPath("$.ngayKiemke").value(DEFAULT_NGAY_KIEMKE.toString()))
            .andExpect(jsonPath("$.donviSudung").value(DEFAULT_DONVI_SUDUNG))
            .andExpect(jsonPath("$.ghichu").value(DEFAULT_GHICHU));
    }

    @Test
    public void getNonExistingKiemkeTaisan() throws Exception {
        // Get the kiemkeTaisan
        restKiemkeTaisanMockMvc.perform(get("/api/kiemke-taisans/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateKiemkeTaisan() throws Exception {
        // Initialize the database
        kiemkeTaisanRepository.save(kiemkeTaisan);

        int databaseSizeBeforeUpdate = kiemkeTaisanRepository.findAll().size();

        // Update the kiemkeTaisan
        KiemkeTaisan updatedKiemkeTaisan = kiemkeTaisanRepository.findById(kiemkeTaisan.getId()).get();
        updatedKiemkeTaisan
            .sophieu(UPDATED_SOPHIEU)
            .ngayLapphieu(UPDATED_NGAY_LAPPHIEU)
            .ngayKiemke(UPDATED_NGAY_KIEMKE)
            .donviSudung(UPDATED_DONVI_SUDUNG)
            .ghichu(UPDATED_GHICHU);
        KiemkeTaisanDTO kiemkeTaisanDTO = kiemkeTaisanMapper.toDto(updatedKiemkeTaisan);

        restKiemkeTaisanMockMvc.perform(put("/api/kiemke-taisans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kiemkeTaisanDTO)))
            .andExpect(status().isOk());

        // Validate the KiemkeTaisan in the database
        List<KiemkeTaisan> kiemkeTaisanList = kiemkeTaisanRepository.findAll();
        assertThat(kiemkeTaisanList).hasSize(databaseSizeBeforeUpdate);
        KiemkeTaisan testKiemkeTaisan = kiemkeTaisanList.get(kiemkeTaisanList.size() - 1);
        assertThat(testKiemkeTaisan.getSophieu()).isEqualTo(UPDATED_SOPHIEU);
        assertThat(testKiemkeTaisan.getNgayLapphieu()).isEqualTo(UPDATED_NGAY_LAPPHIEU);
        assertThat(testKiemkeTaisan.getNgayKiemke()).isEqualTo(UPDATED_NGAY_KIEMKE);
        assertThat(testKiemkeTaisan.getDonviSudung()).isEqualTo(UPDATED_DONVI_SUDUNG);
        assertThat(testKiemkeTaisan.getGhichu()).isEqualTo(UPDATED_GHICHU);
    }

    @Test
    public void updateNonExistingKiemkeTaisan() throws Exception {
        int databaseSizeBeforeUpdate = kiemkeTaisanRepository.findAll().size();

        // Create the KiemkeTaisan
        KiemkeTaisanDTO kiemkeTaisanDTO = kiemkeTaisanMapper.toDto(kiemkeTaisan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKiemkeTaisanMockMvc.perform(put("/api/kiemke-taisans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kiemkeTaisanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the KiemkeTaisan in the database
        List<KiemkeTaisan> kiemkeTaisanList = kiemkeTaisanRepository.findAll();
        assertThat(kiemkeTaisanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteKiemkeTaisan() throws Exception {
        // Initialize the database
        kiemkeTaisanRepository.save(kiemkeTaisan);

        int databaseSizeBeforeDelete = kiemkeTaisanRepository.findAll().size();

        // Delete the kiemkeTaisan
        restKiemkeTaisanMockMvc.perform(delete("/api/kiemke-taisans/{id}", kiemkeTaisan.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<KiemkeTaisan> kiemkeTaisanList = kiemkeTaisanRepository.findAll();
        assertThat(kiemkeTaisanList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
