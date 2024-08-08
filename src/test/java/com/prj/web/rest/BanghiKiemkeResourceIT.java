package com.prj.web.rest;

import com.prj.TaisanApp;
import com.prj.domain.BanghiKiemke;
import com.prj.domain.Taisan;
import com.prj.repository.BanghiKiemkeRepository;
import com.prj.service.BanghiKiemkeService;
import com.prj.service.dto.BanghiKiemkeDTO;
import com.prj.service.mapper.BanghiKiemkeMapper;
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
import java.util.List;

import static com.prj.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link BanghiKiemkeResource} REST controller.
 */
@SpringBootTest(classes = TaisanApp.class)
public class BanghiKiemkeResourceIT {

    private static final Integer DEFAULT_SOLUONG_BANDAU = 1;
    private static final Integer UPDATED_SOLUONG_BANDAU = 2;

    private static final BigDecimal DEFAULT_GIATRI_CONLAI_BANDAU = new BigDecimal(1);
    private static final BigDecimal UPDATED_GIATRI_CONLAI_BANDAU = new BigDecimal(2);

    private static final Integer DEFAULT_SOLUONG = 1;
    private static final Integer UPDATED_SOLUONG = 2;

    private static final BigDecimal DEFAULT_NGUYENGIA = new BigDecimal(1);
    private static final BigDecimal UPDATED_NGUYENGIA = new BigDecimal(2);

    private static final BigDecimal DEFAULT_GIATRI_CONLAI = new BigDecimal(1);
    private static final BigDecimal UPDATED_GIATRI_CONLAI = new BigDecimal(2);

    private static final String DEFAULT_GHICHU = "AAAAAAAAAA";
    private static final String UPDATED_GHICHU = "BBBBBBBBBB";

    private static final Integer DEFAULT_TINHTRANG_SUDUNG = 1;
    private static final Integer UPDATED_TINHTRANG_SUDUNG = 2;

    private static final Integer DEFAULT_HINHTHUC_XULY = 1;
    private static final Integer UPDATED_HINHTHUC_XULY = 2;

    @Autowired
    private BanghiKiemkeRepository banghiKiemkeRepository;

    @Autowired
    private BanghiKiemkeMapper banghiKiemkeMapper;

    @Autowired
    private BanghiKiemkeService banghiKiemkeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restBanghiKiemkeMockMvc;

    private BanghiKiemke banghiKiemke;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BanghiKiemkeResource banghiKiemkeResource = new BanghiKiemkeResource(banghiKiemkeService);
        this.restBanghiKiemkeMockMvc = MockMvcBuilders.standaloneSetup(banghiKiemkeResource)
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
    public static BanghiKiemke createEntity() {
        BanghiKiemke banghiKiemke = new BanghiKiemke()
            .soluongBandau(DEFAULT_SOLUONG_BANDAU)
            .giatriConlaiBandau(DEFAULT_GIATRI_CONLAI_BANDAU)
            .soluong(DEFAULT_SOLUONG)
            .nguyengia(DEFAULT_NGUYENGIA)
            .giatriConlai(DEFAULT_GIATRI_CONLAI)
            .ghichu(DEFAULT_GHICHU)
            .tinhtrangSudung(DEFAULT_TINHTRANG_SUDUNG)
            .hinhthucXuly(DEFAULT_HINHTHUC_XULY);
        // Add required entity
        Taisan taisan;
        taisan = TaisanResourceIT.createEntity();
        taisan.setId("fixed-id-for-tests");
        banghiKiemke.setTaisan(taisan);
        return banghiKiemke;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BanghiKiemke createUpdatedEntity() {
        BanghiKiemke banghiKiemke = new BanghiKiemke()
            .soluongBandau(UPDATED_SOLUONG_BANDAU)
            .giatriConlaiBandau(UPDATED_GIATRI_CONLAI_BANDAU)
            .soluong(UPDATED_SOLUONG)
            .nguyengia(UPDATED_NGUYENGIA)
            .giatriConlai(UPDATED_GIATRI_CONLAI)
            .ghichu(UPDATED_GHICHU)
            .tinhtrangSudung(UPDATED_TINHTRANG_SUDUNG)
            .hinhthucXuly(UPDATED_HINHTHUC_XULY);
        // Add required entity
        Taisan taisan;
        taisan = TaisanResourceIT.createUpdatedEntity();
        taisan.setId("fixed-id-for-tests");
        banghiKiemke.setTaisan(taisan);
        return banghiKiemke;
    }

    @BeforeEach
    public void initTest() {
        banghiKiemkeRepository.deleteAll();
        banghiKiemke = createEntity();
    }

    @Test
    public void createBanghiKiemke() throws Exception {
        int databaseSizeBeforeCreate = banghiKiemkeRepository.findAll().size();

        // Create the BanghiKiemke
        BanghiKiemkeDTO banghiKiemkeDTO = banghiKiemkeMapper.toDto(banghiKiemke);
        restBanghiKiemkeMockMvc.perform(post("/api/banghi-kiemkes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(banghiKiemkeDTO)))
            .andExpect(status().isCreated());

        // Validate the BanghiKiemke in the database
        List<BanghiKiemke> banghiKiemkeList = banghiKiemkeRepository.findAll();
        assertThat(banghiKiemkeList).hasSize(databaseSizeBeforeCreate + 1);
        BanghiKiemke testBanghiKiemke = banghiKiemkeList.get(banghiKiemkeList.size() - 1);
        assertThat(testBanghiKiemke.getSoluongBandau()).isEqualTo(DEFAULT_SOLUONG_BANDAU);
        assertThat(testBanghiKiemke.getGiatriConlaiBandau()).isEqualTo(DEFAULT_GIATRI_CONLAI_BANDAU);
        assertThat(testBanghiKiemke.getSoluong()).isEqualTo(DEFAULT_SOLUONG);
        assertThat(testBanghiKiemke.getNguyengia()).isEqualTo(DEFAULT_NGUYENGIA);
        assertThat(testBanghiKiemke.getGiatriConlai()).isEqualTo(DEFAULT_GIATRI_CONLAI);
        assertThat(testBanghiKiemke.getGhichu()).isEqualTo(DEFAULT_GHICHU);
        assertThat(testBanghiKiemke.getTinhtrangSudung()).isEqualTo(DEFAULT_TINHTRANG_SUDUNG);
        assertThat(testBanghiKiemke.getHinhthucXuly()).isEqualTo(DEFAULT_HINHTHUC_XULY);
    }

    @Test
    public void createBanghiKiemkeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = banghiKiemkeRepository.findAll().size();

        // Create the BanghiKiemke with an existing ID
        banghiKiemke.setId("existing_id");
        BanghiKiemkeDTO banghiKiemkeDTO = banghiKiemkeMapper.toDto(banghiKiemke);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBanghiKiemkeMockMvc.perform(post("/api/banghi-kiemkes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(banghiKiemkeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BanghiKiemke in the database
        List<BanghiKiemke> banghiKiemkeList = banghiKiemkeRepository.findAll();
        assertThat(banghiKiemkeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkSoluongBandauIsRequired() throws Exception {
        int databaseSizeBeforeTest = banghiKiemkeRepository.findAll().size();
        // set the field null
        banghiKiemke.setSoluongBandau(null);

        // Create the BanghiKiemke, which fails.
        BanghiKiemkeDTO banghiKiemkeDTO = banghiKiemkeMapper.toDto(banghiKiemke);

        restBanghiKiemkeMockMvc.perform(post("/api/banghi-kiemkes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(banghiKiemkeDTO)))
            .andExpect(status().isBadRequest());

        List<BanghiKiemke> banghiKiemkeList = banghiKiemkeRepository.findAll();
        assertThat(banghiKiemkeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkGiatriConlaiBandauIsRequired() throws Exception {
        int databaseSizeBeforeTest = banghiKiemkeRepository.findAll().size();
        // set the field null
        banghiKiemke.setGiatriConlaiBandau(null);

        // Create the BanghiKiemke, which fails.
        BanghiKiemkeDTO banghiKiemkeDTO = banghiKiemkeMapper.toDto(banghiKiemke);

        restBanghiKiemkeMockMvc.perform(post("/api/banghi-kiemkes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(banghiKiemkeDTO)))
            .andExpect(status().isBadRequest());

        List<BanghiKiemke> banghiKiemkeList = banghiKiemkeRepository.findAll();
        assertThat(banghiKiemkeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkSoluongIsRequired() throws Exception {
        int databaseSizeBeforeTest = banghiKiemkeRepository.findAll().size();
        // set the field null
        banghiKiemke.setSoluong(null);

        // Create the BanghiKiemke, which fails.
        BanghiKiemkeDTO banghiKiemkeDTO = banghiKiemkeMapper.toDto(banghiKiemke);

        restBanghiKiemkeMockMvc.perform(post("/api/banghi-kiemkes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(banghiKiemkeDTO)))
            .andExpect(status().isBadRequest());

        List<BanghiKiemke> banghiKiemkeList = banghiKiemkeRepository.findAll();
        assertThat(banghiKiemkeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkNguyengiaIsRequired() throws Exception {
        int databaseSizeBeforeTest = banghiKiemkeRepository.findAll().size();
        // set the field null
        banghiKiemke.setNguyengia(null);

        // Create the BanghiKiemke, which fails.
        BanghiKiemkeDTO banghiKiemkeDTO = banghiKiemkeMapper.toDto(banghiKiemke);

        restBanghiKiemkeMockMvc.perform(post("/api/banghi-kiemkes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(banghiKiemkeDTO)))
            .andExpect(status().isBadRequest());

        List<BanghiKiemke> banghiKiemkeList = banghiKiemkeRepository.findAll();
        assertThat(banghiKiemkeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkGiatriConlaiIsRequired() throws Exception {
        int databaseSizeBeforeTest = banghiKiemkeRepository.findAll().size();
        // set the field null
        banghiKiemke.setGiatriConlai(null);

        // Create the BanghiKiemke, which fails.
        BanghiKiemkeDTO banghiKiemkeDTO = banghiKiemkeMapper.toDto(banghiKiemke);

        restBanghiKiemkeMockMvc.perform(post("/api/banghi-kiemkes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(banghiKiemkeDTO)))
            .andExpect(status().isBadRequest());

        List<BanghiKiemke> banghiKiemkeList = banghiKiemkeRepository.findAll();
        assertThat(banghiKiemkeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllBanghiKiemkes() throws Exception {
        // Initialize the database
        banghiKiemkeRepository.save(banghiKiemke);

        // Get all the banghiKiemkeList
        restBanghiKiemkeMockMvc.perform(get("/api/banghi-kiemkes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(banghiKiemke.getId())))
            .andExpect(jsonPath("$.[*].soluongBandau").value(hasItem(DEFAULT_SOLUONG_BANDAU)))
            .andExpect(jsonPath("$.[*].giatriConlaiBandau").value(hasItem(DEFAULT_GIATRI_CONLAI_BANDAU.intValue())))
            .andExpect(jsonPath("$.[*].soluong").value(hasItem(DEFAULT_SOLUONG)))
            .andExpect(jsonPath("$.[*].nguyengia").value(hasItem(DEFAULT_NGUYENGIA.intValue())))
            .andExpect(jsonPath("$.[*].giatriConlai").value(hasItem(DEFAULT_GIATRI_CONLAI.intValue())))
            .andExpect(jsonPath("$.[*].ghichu").value(hasItem(DEFAULT_GHICHU)))
            .andExpect(jsonPath("$.[*].tinhtrangSudung").value(hasItem(DEFAULT_TINHTRANG_SUDUNG)))
            .andExpect(jsonPath("$.[*].hinhthucXuly").value(hasItem(DEFAULT_HINHTHUC_XULY)));
    }
    
    @Test
    public void getBanghiKiemke() throws Exception {
        // Initialize the database
        banghiKiemkeRepository.save(banghiKiemke);

        // Get the banghiKiemke
        restBanghiKiemkeMockMvc.perform(get("/api/banghi-kiemkes/{id}", banghiKiemke.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(banghiKiemke.getId()))
            .andExpect(jsonPath("$.soluongBandau").value(DEFAULT_SOLUONG_BANDAU))
            .andExpect(jsonPath("$.giatriConlaiBandau").value(DEFAULT_GIATRI_CONLAI_BANDAU.intValue()))
            .andExpect(jsonPath("$.soluong").value(DEFAULT_SOLUONG))
            .andExpect(jsonPath("$.nguyengia").value(DEFAULT_NGUYENGIA.intValue()))
            .andExpect(jsonPath("$.giatriConlai").value(DEFAULT_GIATRI_CONLAI.intValue()))
            .andExpect(jsonPath("$.ghichu").value(DEFAULT_GHICHU))
            .andExpect(jsonPath("$.tinhtrangSudung").value(DEFAULT_TINHTRANG_SUDUNG))
            .andExpect(jsonPath("$.hinhthucXuly").value(DEFAULT_HINHTHUC_XULY));
    }

    @Test
    public void getNonExistingBanghiKiemke() throws Exception {
        // Get the banghiKiemke
        restBanghiKiemkeMockMvc.perform(get("/api/banghi-kiemkes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateBanghiKiemke() throws Exception {
        // Initialize the database
        banghiKiemkeRepository.save(banghiKiemke);

        int databaseSizeBeforeUpdate = banghiKiemkeRepository.findAll().size();

        // Update the banghiKiemke
        BanghiKiemke updatedBanghiKiemke = banghiKiemkeRepository.findById(banghiKiemke.getId()).get();
        updatedBanghiKiemke
            .soluongBandau(UPDATED_SOLUONG_BANDAU)
            .giatriConlaiBandau(UPDATED_GIATRI_CONLAI_BANDAU)
            .soluong(UPDATED_SOLUONG)
            .nguyengia(UPDATED_NGUYENGIA)
            .giatriConlai(UPDATED_GIATRI_CONLAI)
            .ghichu(UPDATED_GHICHU)
            .tinhtrangSudung(UPDATED_TINHTRANG_SUDUNG)
            .hinhthucXuly(UPDATED_HINHTHUC_XULY);
        BanghiKiemkeDTO banghiKiemkeDTO = banghiKiemkeMapper.toDto(updatedBanghiKiemke);

        restBanghiKiemkeMockMvc.perform(put("/api/banghi-kiemkes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(banghiKiemkeDTO)))
            .andExpect(status().isOk());

        // Validate the BanghiKiemke in the database
        List<BanghiKiemke> banghiKiemkeList = banghiKiemkeRepository.findAll();
        assertThat(banghiKiemkeList).hasSize(databaseSizeBeforeUpdate);
        BanghiKiemke testBanghiKiemke = banghiKiemkeList.get(banghiKiemkeList.size() - 1);
        assertThat(testBanghiKiemke.getSoluongBandau()).isEqualTo(UPDATED_SOLUONG_BANDAU);
        assertThat(testBanghiKiemke.getGiatriConlaiBandau()).isEqualTo(UPDATED_GIATRI_CONLAI_BANDAU);
        assertThat(testBanghiKiemke.getSoluong()).isEqualTo(UPDATED_SOLUONG);
        assertThat(testBanghiKiemke.getNguyengia()).isEqualTo(UPDATED_NGUYENGIA);
        assertThat(testBanghiKiemke.getGiatriConlai()).isEqualTo(UPDATED_GIATRI_CONLAI);
        assertThat(testBanghiKiemke.getGhichu()).isEqualTo(UPDATED_GHICHU);
        assertThat(testBanghiKiemke.getTinhtrangSudung()).isEqualTo(UPDATED_TINHTRANG_SUDUNG);
        assertThat(testBanghiKiemke.getHinhthucXuly()).isEqualTo(UPDATED_HINHTHUC_XULY);
    }

    @Test
    public void updateNonExistingBanghiKiemke() throws Exception {
        int databaseSizeBeforeUpdate = banghiKiemkeRepository.findAll().size();

        // Create the BanghiKiemke
        BanghiKiemkeDTO banghiKiemkeDTO = banghiKiemkeMapper.toDto(banghiKiemke);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBanghiKiemkeMockMvc.perform(put("/api/banghi-kiemkes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(banghiKiemkeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BanghiKiemke in the database
        List<BanghiKiemke> banghiKiemkeList = banghiKiemkeRepository.findAll();
        assertThat(banghiKiemkeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteBanghiKiemke() throws Exception {
        // Initialize the database
        banghiKiemkeRepository.save(banghiKiemke);

        int databaseSizeBeforeDelete = banghiKiemkeRepository.findAll().size();

        // Delete the banghiKiemke
        restBanghiKiemkeMockMvc.perform(delete("/api/banghi-kiemkes/{id}", banghiKiemke.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BanghiKiemke> banghiKiemkeList = banghiKiemkeRepository.findAll();
        assertThat(banghiKiemkeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
