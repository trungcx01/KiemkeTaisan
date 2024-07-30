package com.prj.web.rest;

import com.prj.service.DonviCungcapService;
import com.prj.web.rest.errors.BadRequestAlertException;
import com.prj.service.dto.DonviCungcapDTO;

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
 * REST controller for managing {@link com.prj.domain.DonviCungcap}.
 */
@RestController
@RequestMapping("/api")
public class DonviCungcapResource {

    private final Logger log = LoggerFactory.getLogger(DonviCungcapResource.class);

    private static final String ENTITY_NAME = "donviCungcap";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DonviCungcapService donviCungcapService;

    public DonviCungcapResource(DonviCungcapService donviCungcapService) {
        this.donviCungcapService = donviCungcapService;
    }

    /**
     * {@code POST  /donvi-cungcaps} : Create a new donviCungcap.
     *
     * @param donviCungcapDTO the donviCungcapDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new donviCungcapDTO, or with status {@code 400 (Bad Request)} if the donviCungcap has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/donvi-cungcaps")
    public ResponseEntity<DonviCungcapDTO> createDonviCungcap(@Valid @RequestBody DonviCungcapDTO donviCungcapDTO) throws URISyntaxException {
        log.debug("REST request to save DonviCungcap : {}", donviCungcapDTO);
        if (donviCungcapDTO.getId() != null) {
            throw new BadRequestAlertException("A new donviCungcap cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DonviCungcapDTO result = donviCungcapService.save(donviCungcapDTO);
        return ResponseEntity.created(new URI("/api/donvi-cungcaps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /donvi-cungcaps} : Updates an existing donviCungcap.
     *
     * @param donviCungcapDTO the donviCungcapDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated donviCungcapDTO,
     * or with status {@code 400 (Bad Request)} if the donviCungcapDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the donviCungcapDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/donvi-cungcaps")
    public ResponseEntity<DonviCungcapDTO> updateDonviCungcap(@Valid @RequestBody DonviCungcapDTO donviCungcapDTO) throws URISyntaxException {
        log.debug("REST request to update DonviCungcap : {}", donviCungcapDTO);
        if (donviCungcapDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DonviCungcapDTO result = donviCungcapService.save(donviCungcapDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, donviCungcapDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /donvi-cungcaps} : get all the donviCungcaps.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of donviCungcaps in body.
     */
    @GetMapping("/donvi-cungcaps")
    public ResponseEntity<List<DonviCungcapDTO>> getAllDonviCungcaps(Pageable pageable) {
        log.debug("REST request to get a page of DonviCungcaps");
        Page<DonviCungcapDTO> page = donviCungcapService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /donvi-cungcaps/:id} : get the "id" donviCungcap.
     *
     * @param id the id of the donviCungcapDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the donviCungcapDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/donvi-cungcaps/{id}")
    public ResponseEntity<DonviCungcapDTO> getDonviCungcap(@PathVariable String id) {
        log.debug("REST request to get DonviCungcap : {}", id);
        Optional<DonviCungcapDTO> donviCungcapDTO = donviCungcapService.findOne(id);
        return ResponseUtil.wrapOrNotFound(donviCungcapDTO);
    }

    /**
     * {@code DELETE  /donvi-cungcaps/:id} : delete the "id" donviCungcap.
     *
     * @param id the id of the donviCungcapDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/donvi-cungcaps/{id}")
    public ResponseEntity<Void> deleteDonviCungcap(@PathVariable String id) {
        log.debug("REST request to delete DonviCungcap : {}", id);
        donviCungcapService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
