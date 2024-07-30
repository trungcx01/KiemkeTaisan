package com.prj.web.rest;

import com.prj.service.KiemkeTaisanService;
import com.prj.web.rest.errors.BadRequestAlertException;
import com.prj.service.dto.KiemkeTaisanDTO;

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
 * REST controller for managing {@link com.prj.domain.KiemkeTaisan}.
 */
@RestController
@RequestMapping("/api")
public class KiemkeTaisanResource {

    private final Logger log = LoggerFactory.getLogger(KiemkeTaisanResource.class);

    private static final String ENTITY_NAME = "kiemkeTaisan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final KiemkeTaisanService kiemkeTaisanService;

    public KiemkeTaisanResource(KiemkeTaisanService kiemkeTaisanService) {
        this.kiemkeTaisanService = kiemkeTaisanService;
    }

    /**
     * {@code POST  /kiemke-taisans} : Create a new kiemkeTaisan.
     *
     * @param kiemkeTaisanDTO the kiemkeTaisanDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new kiemkeTaisanDTO, or with status {@code 400 (Bad Request)} if the kiemkeTaisan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/kiemke-taisans")
    public ResponseEntity<KiemkeTaisanDTO> createKiemkeTaisan(@Valid @RequestBody KiemkeTaisanDTO kiemkeTaisanDTO) throws URISyntaxException {
        log.debug("REST request to save KiemkeTaisan : {}", kiemkeTaisanDTO);
        if (kiemkeTaisanDTO.getId() != null) {
            throw new BadRequestAlertException("A new kiemkeTaisan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        KiemkeTaisanDTO result = kiemkeTaisanService.save(kiemkeTaisanDTO);
        return ResponseEntity.created(new URI("/api/kiemke-taisans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /kiemke-taisans} : Updates an existing kiemkeTaisan.
     *
     * @param kiemkeTaisanDTO the kiemkeTaisanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated kiemkeTaisanDTO,
     * or with status {@code 400 (Bad Request)} if the kiemkeTaisanDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the kiemkeTaisanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/kiemke-taisans")
    public ResponseEntity<KiemkeTaisanDTO> updateKiemkeTaisan(@Valid @RequestBody KiemkeTaisanDTO kiemkeTaisanDTO) throws URISyntaxException {
        log.debug("REST request to update KiemkeTaisan : {}", kiemkeTaisanDTO);
        if (kiemkeTaisanDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        KiemkeTaisanDTO result = kiemkeTaisanService.save(kiemkeTaisanDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, kiemkeTaisanDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /kiemke-taisans} : get all the kiemkeTaisans.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of kiemkeTaisans in body.
     */
    @GetMapping("/kiemke-taisans")
    public ResponseEntity<List<KiemkeTaisanDTO>> getAllKiemkeTaisans(Pageable pageable) {
        log.debug("REST request to get a page of KiemkeTaisans");
        Page<KiemkeTaisanDTO> page = kiemkeTaisanService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /kiemke-taisans/:id} : get the "id" kiemkeTaisan.
     *
     * @param id the id of the kiemkeTaisanDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the kiemkeTaisanDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/kiemke-taisans/{id}")
    public ResponseEntity<KiemkeTaisanDTO> getKiemkeTaisan(@PathVariable String id) {
        log.debug("REST request to get KiemkeTaisan : {}", id);
        Optional<KiemkeTaisanDTO> kiemkeTaisanDTO = kiemkeTaisanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(kiemkeTaisanDTO);
    }

    /**
     * {@code DELETE  /kiemke-taisans/:id} : delete the "id" kiemkeTaisan.
     *
     * @param id the id of the kiemkeTaisanDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/kiemke-taisans/{id}")
    public ResponseEntity<Void> deleteKiemkeTaisan(@PathVariable String id) {
        log.debug("REST request to delete KiemkeTaisan : {}", id);
        kiemkeTaisanService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
