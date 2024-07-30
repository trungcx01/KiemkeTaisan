package com.prj.web.rest;

import com.prj.TaisanApp;
import com.prj.domain.NhanvienKiemke;
import com.prj.domain.Nhanvien;
import com.prj.repository.NhanvienKiemkeRepository;
import com.prj.service.NhanvienKiemkeService;
import com.prj.service.dto.NhanvienKiemkeDTO;
import com.prj.service.mapper.NhanvienKiemkeMapper;
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
 * Integration tests for the {@link NhanvienKiemkeResource} REST controller.
 */
@SpringBootTest(classes = TaisanApp.class)
public class NhanvienKiemkeResourceIT {

    private static final String DEFAULT_DAIDIEN = "AAAAAAAAAA";
    private static final String UPDATED_DAIDIEN = "BBBBBBBBBB";

    private static final Integer DEFAULT_VAITRO = 1;
    private static final Integer UPDATED_VAITRO = 2;

    @Autowired
    private NhanvienKiemkeRepository nhanvienKiemkeRepository;

    @Autowired
    private NhanvienKiemkeMapper nhanvienKiemkeMapper;

    @Autowired
    private NhanvienKiemkeService nhanvienKiemkeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restNhanvienKiemkeMockMvc;

    private NhanvienKiemke nhanvienKiemke;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NhanvienKiemkeResource nhanvienKiemkeResource = new NhanvienKiemkeResource(nhanvienKiemkeService);
        this.restNhanvienKiemkeMockMvc = MockMvcBuilders.standaloneSetup(nhanvienKiemkeResource)
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
    public static NhanvienKiemke createEntity() {
        NhanvienKiemke nhanvienKiemke = new NhanvienKiemke()
            .daidien(DEFAULT_DAIDIEN)
            .vaitro(DEFAULT_VAITRO);
        // Add required entity
        Nhanvien nhanvien;
        nhanvien = NhanvienResourceIT.createEntity();
        nhanvien.setId("fixed-id-for-tests");
        nhanvienKiemke.setNhanvien(nhanvien);
        return nhanvienKiemke;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NhanvienKiemke createUpdatedEntity() {
        NhanvienKiemke nhanvienKiemke = new NhanvienKiemke()
            .daidien(UPDATED_DAIDIEN)
            .vaitro(UPDATED_VAITRO);
        // Add required entity
        Nhanvien nhanvien;
        nhanvien = NhanvienResourceIT.createUpdatedEntity();
        nhanvien.setId("fixed-id-for-tests");
        nhanvienKiemke.setNhanvien(nhanvien);
        return nhanvienKiemke;
    }

    @BeforeEach
    public void initTest() {
        nhanvienKiemkeRepository.deleteAll();
        nhanvienKiemke = createEntity();
    }

    @Test
    public void createNhanvienKiemke() throws Exception {
        int databaseSizeBeforeCreate = nhanvienKiemkeRepository.findAll().size();

        // Create the NhanvienKiemke
        NhanvienKiemkeDTO nhanvienKiemkeDTO = nhanvienKiemkeMapper.toDto(nhanvienKiemke);
        restNhanvienKiemkeMockMvc.perform(post("/api/nhanvien-kiemkes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhanvienKiemkeDTO)))
            .andExpect(status().isCreated());

        // Validate the NhanvienKiemke in the database
        List<NhanvienKiemke> nhanvienKiemkeList = nhanvienKiemkeRepository.findAll();
        assertThat(nhanvienKiemkeList).hasSize(databaseSizeBeforeCreate + 1);
        NhanvienKiemke testNhanvienKiemke = nhanvienKiemkeList.get(nhanvienKiemkeList.size() - 1);
        assertThat(testNhanvienKiemke.getDaidien()).isEqualTo(DEFAULT_DAIDIEN);
        assertThat(testNhanvienKiemke.getVaitro()).isEqualTo(DEFAULT_VAITRO);
    }

    @Test
    public void createNhanvienKiemkeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nhanvienKiemkeRepository.findAll().size();

        // Create the NhanvienKiemke with an existing ID
        nhanvienKiemke.setId("existing_id");
        NhanvienKiemkeDTO nhanvienKiemkeDTO = nhanvienKiemkeMapper.toDto(nhanvienKiemke);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNhanvienKiemkeMockMvc.perform(post("/api/nhanvien-kiemkes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhanvienKiemkeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NhanvienKiemke in the database
        List<NhanvienKiemke> nhanvienKiemkeList = nhanvienKiemkeRepository.findAll();
        assertThat(nhanvienKiemkeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkDaidienIsRequired() throws Exception {
        int databaseSizeBeforeTest = nhanvienKiemkeRepository.findAll().size();
        // set the field null
        nhanvienKiemke.setDaidien(null);

        // Create the NhanvienKiemke, which fails.
        NhanvienKiemkeDTO nhanvienKiemkeDTO = nhanvienKiemkeMapper.toDto(nhanvienKiemke);

        restNhanvienKiemkeMockMvc.perform(post("/api/nhanvien-kiemkes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhanvienKiemkeDTO)))
            .andExpect(status().isBadRequest());

        List<NhanvienKiemke> nhanvienKiemkeList = nhanvienKiemkeRepository.findAll();
        assertThat(nhanvienKiemkeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkVaitroIsRequired() throws Exception {
        int databaseSizeBeforeTest = nhanvienKiemkeRepository.findAll().size();
        // set the field null
        nhanvienKiemke.setVaitro(null);

        // Create the NhanvienKiemke, which fails.
        NhanvienKiemkeDTO nhanvienKiemkeDTO = nhanvienKiemkeMapper.toDto(nhanvienKiemke);

        restNhanvienKiemkeMockMvc.perform(post("/api/nhanvien-kiemkes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhanvienKiemkeDTO)))
            .andExpect(status().isBadRequest());

        List<NhanvienKiemke> nhanvienKiemkeList = nhanvienKiemkeRepository.findAll();
        assertThat(nhanvienKiemkeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllNhanvienKiemkes() throws Exception {
        // Initialize the database
        nhanvienKiemkeRepository.save(nhanvienKiemke);

        // Get all the nhanvienKiemkeList
        restNhanvienKiemkeMockMvc.perform(get("/api/nhanvien-kiemkes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nhanvienKiemke.getId())))
            .andExpect(jsonPath("$.[*].daidien").value(hasItem(DEFAULT_DAIDIEN)))
            .andExpect(jsonPath("$.[*].vaitro").value(hasItem(DEFAULT_VAITRO)));
    }
    
    @Test
    public void getNhanvienKiemke() throws Exception {
        // Initialize the database
        nhanvienKiemkeRepository.save(nhanvienKiemke);

        // Get the nhanvienKiemke
        restNhanvienKiemkeMockMvc.perform(get("/api/nhanvien-kiemkes/{id}", nhanvienKiemke.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(nhanvienKiemke.getId()))
            .andExpect(jsonPath("$.daidien").value(DEFAULT_DAIDIEN))
            .andExpect(jsonPath("$.vaitro").value(DEFAULT_VAITRO));
    }

    @Test
    public void getNonExistingNhanvienKiemke() throws Exception {
        // Get the nhanvienKiemke
        restNhanvienKiemkeMockMvc.perform(get("/api/nhanvien-kiemkes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateNhanvienKiemke() throws Exception {
        // Initialize the database
        nhanvienKiemkeRepository.save(nhanvienKiemke);

        int databaseSizeBeforeUpdate = nhanvienKiemkeRepository.findAll().size();

        // Update the nhanvienKiemke
        NhanvienKiemke updatedNhanvienKiemke = nhanvienKiemkeRepository.findById(nhanvienKiemke.getId()).get();
        updatedNhanvienKiemke
            .daidien(UPDATED_DAIDIEN)
            .vaitro(UPDATED_VAITRO);
        NhanvienKiemkeDTO nhanvienKiemkeDTO = nhanvienKiemkeMapper.toDto(updatedNhanvienKiemke);

        restNhanvienKiemkeMockMvc.perform(put("/api/nhanvien-kiemkes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhanvienKiemkeDTO)))
            .andExpect(status().isOk());

        // Validate the NhanvienKiemke in the database
        List<NhanvienKiemke> nhanvienKiemkeList = nhanvienKiemkeRepository.findAll();
        assertThat(nhanvienKiemkeList).hasSize(databaseSizeBeforeUpdate);
        NhanvienKiemke testNhanvienKiemke = nhanvienKiemkeList.get(nhanvienKiemkeList.size() - 1);
        assertThat(testNhanvienKiemke.getDaidien()).isEqualTo(UPDATED_DAIDIEN);
        assertThat(testNhanvienKiemke.getVaitro()).isEqualTo(UPDATED_VAITRO);
    }

    @Test
    public void updateNonExistingNhanvienKiemke() throws Exception {
        int databaseSizeBeforeUpdate = nhanvienKiemkeRepository.findAll().size();

        // Create the NhanvienKiemke
        NhanvienKiemkeDTO nhanvienKiemkeDTO = nhanvienKiemkeMapper.toDto(nhanvienKiemke);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNhanvienKiemkeMockMvc.perform(put("/api/nhanvien-kiemkes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhanvienKiemkeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NhanvienKiemke in the database
        List<NhanvienKiemke> nhanvienKiemkeList = nhanvienKiemkeRepository.findAll();
        assertThat(nhanvienKiemkeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteNhanvienKiemke() throws Exception {
        // Initialize the database
        nhanvienKiemkeRepository.save(nhanvienKiemke);

        int databaseSizeBeforeDelete = nhanvienKiemkeRepository.findAll().size();

        // Delete the nhanvienKiemke
        restNhanvienKiemkeMockMvc.perform(delete("/api/nhanvien-kiemkes/{id}", nhanvienKiemke.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NhanvienKiemke> nhanvienKiemkeList = nhanvienKiemkeRepository.findAll();
        assertThat(nhanvienKiemkeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
