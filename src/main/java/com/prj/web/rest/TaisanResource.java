package com.prj.web.rest;

import com.prj.service.TaisanService;
import com.prj.web.rest.errors.BadRequestAlertException;
import com.prj.service.dto.TaisanDTO;

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
 * REST controller for managing {@link com.prj.domain.Taisan}.
 */
@RestController
@RequestMapping("/api")
public class TaisanResource {

    private final Logger log = LoggerFactory.getLogger(TaisanResource.class);

    private static final String ENTITY_NAME = "taisan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TaisanService taisanService;

    public TaisanResource(TaisanService taisanService) {
        this.taisanService = taisanService;
    }

    /**
     * {@code POST  /taisans} : Create a new taisan.
     *
     * @param taisanDTO the taisanDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new taisanDTO, or with status {@code 400 (Bad Request)} if the taisan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/taisans")
    public ResponseEntity<TaisanDTO> createTaisan(@Valid @RequestBody TaisanDTO taisanDTO) throws URISyntaxException {
        log.debug("REST request to save Taisan : {}", taisanDTO);
        if (taisanDTO.getId() != null) {
            throw new BadRequestAlertException("A new taisan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TaisanDTO result = taisanService.save(taisanDTO);
        return ResponseEntity.created(new URI("/api/taisans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /taisans} : Updates an existing taisan.
     *
     * @param taisanDTO the taisanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taisanDTO,
     * or with status {@code 400 (Bad Request)} if the taisanDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the taisanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/taisans")
    public ResponseEntity<TaisanDTO> updateTaisan(@Valid @RequestBody TaisanDTO taisanDTO) throws URISyntaxException {
        log.debug("REST request to update Taisan : {}", taisanDTO);
        if (taisanDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TaisanDTO result = taisanService.save(taisanDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, taisanDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /taisans} : get all the taisans.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of taisans in body.
     */
    @GetMapping("/taisans")
    public ResponseEntity<List<TaisanDTO>> getAllTaisans(Pageable pageable) {
        log.debug("REST request to get a page of Taisans");
        Page<TaisanDTO> page = taisanService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/taisans/filter")
    public ResponseEntity<List<TaisanDTO>> getAllTaisansByDonviSudung(@RequestParam String donviSudung) {
        // log.debug("REST request to get a page of Taisans");
        List<TaisanDTO> list = taisanService.getAllTaisansByDonviSudung(donviSudung);
        return ResponseEntity.ok().body(list);
    }

    /**
     * {@code GET  /taisans/:id} : get the "id" taisan.
     *
     * @param id the id of the taisanDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the taisanDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/taisans/{id}")
    public ResponseEntity<TaisanDTO> getTaisan(@PathVariable String id) {
        log.debug("REST request to get Taisan : {}", id);
        Optional<TaisanDTO> taisanDTO = taisanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(taisanDTO);
    }

    /**
     * {@code DELETE  /taisans/:id} : delete the "id" taisan.
     *
     * @param id the id of the taisanDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/taisans/{id}")
    public ResponseEntity<Void> deleteTaisan(@PathVariable String id) {
        log.debug("REST request to delete Taisan : {}", id);
        taisanService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
