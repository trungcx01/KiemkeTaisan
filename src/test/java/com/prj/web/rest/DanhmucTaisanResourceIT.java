package com.prj.web.rest;

import com.prj.TaisanApp;
import com.prj.domain.DanhmucTaisan;
import com.prj.repository.DanhmucTaisanRepository;
import com.prj.service.DanhmucTaisanService;
import com.prj.service.dto.DanhmucTaisanDTO;
import com.prj.service.mapper.DanhmucTaisanMapper;
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
 * Integration tests for the {@link DanhmucTaisanResource} REST controller.
 */
@SpringBootTest(classes = TaisanApp.class)
public class DanhmucTaisanResourceIT {

    private static final String DEFAULT_MA_DMTS = "AAAAAAAAAA";
    private static final String UPDATED_MA_DMTS = "BBBBBBBBBB";

    private static final String DEFAULT_TEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN = "BBBBBBBBBB";

    private static final String DEFAULT_MOTA = "AAAAAAAAAA";
    private static final String UPDATED_MOTA = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NGAYTAO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAYTAO = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private DanhmucTaisanRepository danhmucTaisanRepository;

    @Autowired
    private DanhmucTaisanMapper danhmucTaisanMapper;

    @Autowired
    private DanhmucTaisanService danhmucTaisanService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restDanhmucTaisanMockMvc;

    private DanhmucTaisan danhmucTaisan;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DanhmucTaisanResource danhmucTaisanResource = new DanhmucTaisanResource(danhmucTaisanService);
        this.restDanhmucTaisanMockMvc = MockMvcBuilders.standaloneSetup(danhmucTaisanResource)
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
    public static DanhmucTaisan createEntity() {
        DanhmucTaisan danhmucTaisan = new DanhmucTaisan()
            .maDMTS(DEFAULT_MA_DMTS)
            .ten(DEFAULT_TEN)
            .mota(DEFAULT_MOTA)
            .ngaytao(DEFAULT_NGAYTAO);
        return danhmucTaisan;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhmucTaisan createUpdatedEntity() {
        DanhmucTaisan danhmucTaisan = new DanhmucTaisan()
            .maDMTS(UPDATED_MA_DMTS)
            .ten(UPDATED_TEN)
            .mota(UPDATED_MOTA)
            .ngaytao(UPDATED_NGAYTAO);
        return danhmucTaisan;
    }

    @BeforeEach
    public void initTest() {
        danhmucTaisanRepository.deleteAll();
        danhmucTaisan = createEntity();
    }

    @Test
    public void createDanhmucTaisan() throws Exception {
        int databaseSizeBeforeCreate = danhmucTaisanRepository.findAll().size();

        // Create the DanhmucTaisan
        DanhmucTaisanDTO danhmucTaisanDTO = danhmucTaisanMapper.toDto(danhmucTaisan);
        restDanhmucTaisanMockMvc.perform(post("/api/danhmuc-taisans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danhmucTaisanDTO)))
            .andExpect(status().isCreated());

        // Validate the DanhmucTaisan in the database
        List<DanhmucTaisan> danhmucTaisanList = danhmucTaisanRepository.findAll();
        assertThat(danhmucTaisanList).hasSize(databaseSizeBeforeCreate + 1);
        DanhmucTaisan testDanhmucTaisan = danhmucTaisanList.get(danhmucTaisanList.size() - 1);
        assertThat(testDanhmucTaisan.getMaDMTS()).isEqualTo(DEFAULT_MA_DMTS);
        assertThat(testDanhmucTaisan.getTen()).isEqualTo(DEFAULT_TEN);
        assertThat(testDanhmucTaisan.getMota()).isEqualTo(DEFAULT_MOTA);
        assertThat(testDanhmucTaisan.getNgaytao()).isEqualTo(DEFAULT_NGAYTAO);
    }

    @Test
    public void createDanhmucTaisanWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = danhmucTaisanRepository.findAll().size();

        // Create the DanhmucTaisan with an existing ID
        danhmucTaisan.setId("existing_id");
        DanhmucTaisanDTO danhmucTaisanDTO = danhmucTaisanMapper.toDto(danhmucTaisan);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDanhmucTaisanMockMvc.perform(post("/api/danhmuc-taisans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danhmucTaisanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DanhmucTaisan in the database
        List<DanhmucTaisan> danhmucTaisanList = danhmucTaisanRepository.findAll();
        assertThat(danhmucTaisanList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkMaDMTSIsRequired() throws Exception {
        int databaseSizeBeforeTest = danhmucTaisanRepository.findAll().size();
        // set the field null
        danhmucTaisan.setMaDMTS(null);

        // Create the DanhmucTaisan, which fails.
        DanhmucTaisanDTO danhmucTaisanDTO = danhmucTaisanMapper.toDto(danhmucTaisan);

        restDanhmucTaisanMockMvc.perform(post("/api/danhmuc-taisans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danhmucTaisanDTO)))
            .andExpect(status().isBadRequest());

        List<DanhmucTaisan> danhmucTaisanList = danhmucTaisanRepository.findAll();
        assertThat(danhmucTaisanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenIsRequired() throws Exception {
        int databaseSizeBeforeTest = danhmucTaisanRepository.findAll().size();
        // set the field null
        danhmucTaisan.setTen(null);

        // Create the DanhmucTaisan, which fails.
        DanhmucTaisanDTO danhmucTaisanDTO = danhmucTaisanMapper.toDto(danhmucTaisan);

        restDanhmucTaisanMockMvc.perform(post("/api/danhmuc-taisans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danhmucTaisanDTO)))
            .andExpect(status().isBadRequest());

        List<DanhmucTaisan> danhmucTaisanList = danhmucTaisanRepository.findAll();
        assertThat(danhmucTaisanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllDanhmucTaisans() throws Exception {
        // Initialize the database
        danhmucTaisanRepository.save(danhmucTaisan);

        // Get all the danhmucTaisanList
        restDanhmucTaisanMockMvc.perform(get("/api/danhmuc-taisans?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(danhmucTaisan.getId())))
            .andExpect(jsonPath("$.[*].maDMTS").value(hasItem(DEFAULT_MA_DMTS)))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].mota").value(hasItem(DEFAULT_MOTA)))
            .andExpect(jsonPath("$.[*].ngaytao").value(hasItem(DEFAULT_NGAYTAO.toString())));
    }
    
    @Test
    public void getDanhmucTaisan() throws Exception {
        // Initialize the database
        danhmucTaisanRepository.save(danhmucTaisan);

        // Get the danhmucTaisan
        restDanhmucTaisanMockMvc.perform(get("/api/danhmuc-taisans/{id}", danhmucTaisan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(danhmucTaisan.getId()))
            .andExpect(jsonPath("$.maDMTS").value(DEFAULT_MA_DMTS))
            .andExpect(jsonPath("$.ten").value(DEFAULT_TEN))
            .andExpect(jsonPath("$.mota").value(DEFAULT_MOTA))
            .andExpect(jsonPath("$.ngaytao").value(DEFAULT_NGAYTAO.toString()));
    }

    @Test
    public void getNonExistingDanhmucTaisan() throws Exception {
        // Get the danhmucTaisan
        restDanhmucTaisanMockMvc.perform(get("/api/danhmuc-taisans/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateDanhmucTaisan() throws Exception {
        // Initialize the database
        danhmucTaisanRepository.save(danhmucTaisan);

        int databaseSizeBeforeUpdate = danhmucTaisanRepository.findAll().size();

        // Update the danhmucTaisan
        DanhmucTaisan updatedDanhmucTaisan = danhmucTaisanRepository.findById(danhmucTaisan.getId()).get();
        updatedDanhmucTaisan
            .maDMTS(UPDATED_MA_DMTS)
            .ten(UPDATED_TEN)
            .mota(UPDATED_MOTA)
            .ngaytao(UPDATED_NGAYTAO);
        DanhmucTaisanDTO danhmucTaisanDTO = danhmucTaisanMapper.toDto(updatedDanhmucTaisan);

        restDanhmucTaisanMockMvc.perform(put("/api/danhmuc-taisans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danhmucTaisanDTO)))
            .andExpect(status().isOk());

        // Validate the DanhmucTaisan in the database
        List<DanhmucTaisan> danhmucTaisanList = danhmucTaisanRepository.findAll();
        assertThat(danhmucTaisanList).hasSize(databaseSizeBeforeUpdate);
        DanhmucTaisan testDanhmucTaisan = danhmucTaisanList.get(danhmucTaisanList.size() - 1);
        assertThat(testDanhmucTaisan.getMaDMTS()).isEqualTo(UPDATED_MA_DMTS);
        assertThat(testDanhmucTaisan.getTen()).isEqualTo(UPDATED_TEN);
        assertThat(testDanhmucTaisan.getMota()).isEqualTo(UPDATED_MOTA);
        assertThat(testDanhmucTaisan.getNgaytao()).isEqualTo(UPDATED_NGAYTAO);
    }

    @Test
    public void updateNonExistingDanhmucTaisan() throws Exception {
        int databaseSizeBeforeUpdate = danhmucTaisanRepository.findAll().size();

        // Create the DanhmucTaisan
        DanhmucTaisanDTO danhmucTaisanDTO = danhmucTaisanMapper.toDto(danhmucTaisan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhmucTaisanMockMvc.perform(put("/api/danhmuc-taisans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danhmucTaisanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DanhmucTaisan in the database
        List<DanhmucTaisan> danhmucTaisanList = danhmucTaisanRepository.findAll();
        assertThat(danhmucTaisanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteDanhmucTaisan() throws Exception {
        // Initialize the database
        danhmucTaisanRepository.save(danhmucTaisan);

        int databaseSizeBeforeDelete = danhmucTaisanRepository.findAll().size();

        // Delete the danhmucTaisan
        restDanhmucTaisanMockMvc.perform(delete("/api/danhmuc-taisans/{id}", danhmucTaisan.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DanhmucTaisan> danhmucTaisanList = danhmucTaisanRepository.findAll();
        assertThat(danhmucTaisanList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
