package com.prj.web.rest;

import com.prj.TaisanApp;
import com.prj.domain.DonviCungcap;
import com.prj.repository.DonviCungcapRepository;
import com.prj.service.DonviCungcapService;
import com.prj.service.dto.DonviCungcapDTO;
import com.prj.service.mapper.DonviCungcapMapper;
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


import java.util.List;

import static com.prj.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link DonviCungcapResource} REST controller.
 */
@SpringBootTest(classes = TaisanApp.class)
public class DonviCungcapResourceIT {

    private static final String DEFAULT_MA_DVCC = "AAAAAAAAAA";
    private static final String UPDATED_MA_DVCC = "BBBBBBBBBB";

    private static final String DEFAULT_TEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN = "BBBBBBBBBB";

    private static final String DEFAULT_DIACHI = "AAAAAAAAAA";
    private static final String UPDATED_DIACHI = "BBBBBBBBBB";

    private static final String DEFAULT_SO_DIEN_THOAI = "AAAAAAAAAA";
    private static final String UPDATED_SO_DIEN_THOAI = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    @Autowired
    private DonviCungcapRepository donviCungcapRepository;

    @Autowired
    private DonviCungcapMapper donviCungcapMapper;

    @Autowired
    private DonviCungcapService donviCungcapService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restDonviCungcapMockMvc;

    private DonviCungcap donviCungcap;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DonviCungcapResource donviCungcapResource = new DonviCungcapResource(donviCungcapService);
        this.restDonviCungcapMockMvc = MockMvcBuilders.standaloneSetup(donviCungcapResource)
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
    public static DonviCungcap createEntity() {
        DonviCungcap donviCungcap = new DonviCungcap()
            .maDVCC(DEFAULT_MA_DVCC)
            .ten(DEFAULT_TEN)
            .diachi(DEFAULT_DIACHI)
            .soDienThoai(DEFAULT_SO_DIEN_THOAI)
            .email(DEFAULT_EMAIL);
        return donviCungcap;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DonviCungcap createUpdatedEntity() {
        DonviCungcap donviCungcap = new DonviCungcap()
            .maDVCC(UPDATED_MA_DVCC)
            .ten(UPDATED_TEN)
            .diachi(UPDATED_DIACHI)
            .soDienThoai(UPDATED_SO_DIEN_THOAI)
            .email(UPDATED_EMAIL);
        return donviCungcap;
    }

    @BeforeEach
    public void initTest() {
        donviCungcapRepository.deleteAll();
        donviCungcap = createEntity();
    }

    @Test
    public void createDonviCungcap() throws Exception {
        int databaseSizeBeforeCreate = donviCungcapRepository.findAll().size();

        // Create the DonviCungcap
        DonviCungcapDTO donviCungcapDTO = donviCungcapMapper.toDto(donviCungcap);
        restDonviCungcapMockMvc.perform(post("/api/donvi-cungcaps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(donviCungcapDTO)))
            .andExpect(status().isCreated());

        // Validate the DonviCungcap in the database
        List<DonviCungcap> donviCungcapList = donviCungcapRepository.findAll();
        assertThat(donviCungcapList).hasSize(databaseSizeBeforeCreate + 1);
        DonviCungcap testDonviCungcap = donviCungcapList.get(donviCungcapList.size() - 1);
        assertThat(testDonviCungcap.getMaDVCC()).isEqualTo(DEFAULT_MA_DVCC);
        assertThat(testDonviCungcap.getTen()).isEqualTo(DEFAULT_TEN);
        assertThat(testDonviCungcap.getDiachi()).isEqualTo(DEFAULT_DIACHI);
        assertThat(testDonviCungcap.getSoDienThoai()).isEqualTo(DEFAULT_SO_DIEN_THOAI);
        assertThat(testDonviCungcap.getEmail()).isEqualTo(DEFAULT_EMAIL);
    }

    @Test
    public void createDonviCungcapWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = donviCungcapRepository.findAll().size();

        // Create the DonviCungcap with an existing ID
        donviCungcap.setId("existing_id");
        DonviCungcapDTO donviCungcapDTO = donviCungcapMapper.toDto(donviCungcap);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDonviCungcapMockMvc.perform(post("/api/donvi-cungcaps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(donviCungcapDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DonviCungcap in the database
        List<DonviCungcap> donviCungcapList = donviCungcapRepository.findAll();
        assertThat(donviCungcapList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkMaDVCCIsRequired() throws Exception {
        int databaseSizeBeforeTest = donviCungcapRepository.findAll().size();
        // set the field null
        donviCungcap.setMaDVCC(null);

        // Create the DonviCungcap, which fails.
        DonviCungcapDTO donviCungcapDTO = donviCungcapMapper.toDto(donviCungcap);

        restDonviCungcapMockMvc.perform(post("/api/donvi-cungcaps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(donviCungcapDTO)))
            .andExpect(status().isBadRequest());

        List<DonviCungcap> donviCungcapList = donviCungcapRepository.findAll();
        assertThat(donviCungcapList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenIsRequired() throws Exception {
        int databaseSizeBeforeTest = donviCungcapRepository.findAll().size();
        // set the field null
        donviCungcap.setTen(null);

        // Create the DonviCungcap, which fails.
        DonviCungcapDTO donviCungcapDTO = donviCungcapMapper.toDto(donviCungcap);

        restDonviCungcapMockMvc.perform(post("/api/donvi-cungcaps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(donviCungcapDTO)))
            .andExpect(status().isBadRequest());

        List<DonviCungcap> donviCungcapList = donviCungcapRepository.findAll();
        assertThat(donviCungcapList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllDonviCungcaps() throws Exception {
        // Initialize the database
        donviCungcapRepository.save(donviCungcap);

        // Get all the donviCungcapList
        restDonviCungcapMockMvc.perform(get("/api/donvi-cungcaps?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(donviCungcap.getId())))
            .andExpect(jsonPath("$.[*].maDVCC").value(hasItem(DEFAULT_MA_DVCC)))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].diachi").value(hasItem(DEFAULT_DIACHI)))
            .andExpect(jsonPath("$.[*].soDienThoai").value(hasItem(DEFAULT_SO_DIEN_THOAI)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)));
    }
    
    @Test
    public void getDonviCungcap() throws Exception {
        // Initialize the database
        donviCungcapRepository.save(donviCungcap);

        // Get the donviCungcap
        restDonviCungcapMockMvc.perform(get("/api/donvi-cungcaps/{id}", donviCungcap.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(donviCungcap.getId()))
            .andExpect(jsonPath("$.maDVCC").value(DEFAULT_MA_DVCC))
            .andExpect(jsonPath("$.ten").value(DEFAULT_TEN))
            .andExpect(jsonPath("$.diachi").value(DEFAULT_DIACHI))
            .andExpect(jsonPath("$.soDienThoai").value(DEFAULT_SO_DIEN_THOAI))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL));
    }

    @Test
    public void getNonExistingDonviCungcap() throws Exception {
        // Get the donviCungcap
        restDonviCungcapMockMvc.perform(get("/api/donvi-cungcaps/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateDonviCungcap() throws Exception {
        // Initialize the database
        donviCungcapRepository.save(donviCungcap);

        int databaseSizeBeforeUpdate = donviCungcapRepository.findAll().size();

        // Update the donviCungcap
        DonviCungcap updatedDonviCungcap = donviCungcapRepository.findById(donviCungcap.getId()).get();
        updatedDonviCungcap
            .maDVCC(UPDATED_MA_DVCC)
            .ten(UPDATED_TEN)
            .diachi(UPDATED_DIACHI)
            .soDienThoai(UPDATED_SO_DIEN_THOAI)
            .email(UPDATED_EMAIL);
        DonviCungcapDTO donviCungcapDTO = donviCungcapMapper.toDto(updatedDonviCungcap);

        restDonviCungcapMockMvc.perform(put("/api/donvi-cungcaps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(donviCungcapDTO)))
            .andExpect(status().isOk());

        // Validate the DonviCungcap in the database
        List<DonviCungcap> donviCungcapList = donviCungcapRepository.findAll();
        assertThat(donviCungcapList).hasSize(databaseSizeBeforeUpdate);
        DonviCungcap testDonviCungcap = donviCungcapList.get(donviCungcapList.size() - 1);
        assertThat(testDonviCungcap.getMaDVCC()).isEqualTo(UPDATED_MA_DVCC);
        assertThat(testDonviCungcap.getTen()).isEqualTo(UPDATED_TEN);
        assertThat(testDonviCungcap.getDiachi()).isEqualTo(UPDATED_DIACHI);
        assertThat(testDonviCungcap.getSoDienThoai()).isEqualTo(UPDATED_SO_DIEN_THOAI);
        assertThat(testDonviCungcap.getEmail()).isEqualTo(UPDATED_EMAIL);
    }

    @Test
    public void updateNonExistingDonviCungcap() throws Exception {
        int databaseSizeBeforeUpdate = donviCungcapRepository.findAll().size();

        // Create the DonviCungcap
        DonviCungcapDTO donviCungcapDTO = donviCungcapMapper.toDto(donviCungcap);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDonviCungcapMockMvc.perform(put("/api/donvi-cungcaps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(donviCungcapDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DonviCungcap in the database
        List<DonviCungcap> donviCungcapList = donviCungcapRepository.findAll();
        assertThat(donviCungcapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteDonviCungcap() throws Exception {
        // Initialize the database
        donviCungcapRepository.save(donviCungcap);

        int databaseSizeBeforeDelete = donviCungcapRepository.findAll().size();

        // Delete the donviCungcap
        restDonviCungcapMockMvc.perform(delete("/api/donvi-cungcaps/{id}", donviCungcap.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DonviCungcap> donviCungcapList = donviCungcapRepository.findAll();
        assertThat(donviCungcapList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
