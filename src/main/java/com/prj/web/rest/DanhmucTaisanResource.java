package com.prj.web.rest;

import com.prj.service.DanhmucTaisanService;
import com.prj.web.rest.errors.BadRequestAlertException;
import com.prj.service.dto.DanhmucTaisanDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.prj.domain.DanhmucTaisan}.
 */
@RestController
@RequestMapping("/api")
public class DanhmucTaisanResource {

    private final Logger log = LoggerFactory.getLogger(DanhmucTaisanResource.class);

    private static final String ENTITY_NAME = "danhmucTaisan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DanhmucTaisanService danhmucTaisanService;

    public DanhmucTaisanResource(DanhmucTaisanService danhmucTaisanService) {
        this.danhmucTaisanService = danhmucTaisanService;
    }

    /**
     * {@code POST  /danhmuc-taisans} : Create a new danhmucTaisan.
     *
     * @param danhmucTaisanDTO the danhmucTaisanDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new danhmucTaisanDTO, or with status {@code 400 (Bad Request)} if the danhmucTaisan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/danhmuc-taisans")
    public ResponseEntity<DanhmucTaisanDTO> createDanhmucTaisan(@Valid @RequestBody DanhmucTaisanDTO danhmucTaisanDTO) throws URISyntaxException {
        log.debug("REST request to save DanhmucTaisan : {}", danhmucTaisanDTO);
        if (danhmucTaisanDTO.getId() != null) {
            throw new BadRequestAlertException("A new danhmucTaisan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DanhmucTaisanDTO result = danhmucTaisanService.save(danhmucTaisanDTO);
        return ResponseEntity.created(new URI("/api/danhmuc-taisans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /danhmuc-taisans} : Updates an existing danhmucTaisan.
     *
     * @param danhmucTaisanDTO the danhmucTaisanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhmucTaisanDTO,
     * or with status {@code 400 (Bad Request)} if the danhmucTaisanDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the danhmucTaisanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/danhmuc-taisans")
    public ResponseEntity<DanhmucTaisanDTO> updateDanhmucTaisan(@Valid @RequestBody DanhmucTaisanDTO danhmucTaisanDTO) throws URISyntaxException {
        log.debug("REST request to update DanhmucTaisan : {}", danhmucTaisanDTO);
        if (danhmucTaisanDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DanhmucTaisanDTO result = danhmucTaisanService.save(danhmucTaisanDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhmucTaisanDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /danhmuc-taisans} : get all the danhmucTaisans.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of danhmucTaisans in body.
     */
    @GetMapping("/danhmuc-taisans")
    public ResponseEntity<List<DanhmucTaisanDTO>> getAllDanhmucTaisans(Pageable pageable) {
        log.debug("REST request to get a page of DanhmucTaisans");
        Page<DanhmucTaisanDTO> page = danhmucTaisanService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /danhmuc-taisans/:id} : get the "id" danhmucTaisan.
     *
     * @param id the id of the danhmucTaisanDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the danhmucTaisanDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/danhmuc-taisans/{id}")
    public ResponseEntity<DanhmucTaisanDTO> getDanhmucTaisan(@PathVariable String id) {
        log.debug("REST request to get DanhmucTaisan : {}", id);
        Optional<DanhmucTaisanDTO> danhmucTaisanDTO = danhmucTaisanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(danhmucTaisanDTO);
    }

    /**
     * {@code DELETE  /danhmuc-taisans/:id} : delete the "id" danhmucTaisan.
     *
     * @param id the id of the danhmucTaisanDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/danhmuc-taisans/{id}")
    public ResponseEntity<Void> deleteDanhmucTaisan(@PathVariable String id) {
        log.debug("REST request to delete DanhmucTaisan : {}", id);
        danhmucTaisanService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
