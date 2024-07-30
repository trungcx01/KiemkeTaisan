package com.prj.web.rest;

import com.prj.service.BanghiKiemkeService;
import com.prj.web.rest.errors.BadRequestAlertException;
import com.prj.service.dto.BanghiKiemkeDTO;

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
 * REST controller for managing {@link com.prj.domain.BanghiKiemke}.
 */
@RestController
@RequestMapping("/api")
public class BanghiKiemkeResource {

    private final Logger log = LoggerFactory.getLogger(BanghiKiemkeResource.class);

    private static final String ENTITY_NAME = "banghiKiemke";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BanghiKiemkeService banghiKiemkeService;

    public BanghiKiemkeResource(BanghiKiemkeService banghiKiemkeService) {
        this.banghiKiemkeService = banghiKiemkeService;
    }

    /**
     * {@code POST  /banghi-kiemkes} : Create a new banghiKiemke.
     *
     * @param banghiKiemkeDTO the banghiKiemkeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new banghiKiemkeDTO, or with status {@code 400 (Bad Request)} if the banghiKiemke has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/banghi-kiemkes")
    public ResponseEntity<BanghiKiemkeDTO> createBanghiKiemke(@Valid @RequestBody BanghiKiemkeDTO banghiKiemkeDTO) throws URISyntaxException {
        log.debug("REST request to save BanghiKiemke : {}", banghiKiemkeDTO);
        if (banghiKiemkeDTO.getId() != null) {
            throw new BadRequestAlertException("A new banghiKiemke cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BanghiKiemkeDTO result = banghiKiemkeService.save(banghiKiemkeDTO);
        return ResponseEntity.created(new URI("/api/banghi-kiemkes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /banghi-kiemkes} : Updates an existing banghiKiemke.
     *
     * @param banghiKiemkeDTO the banghiKiemkeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated banghiKiemkeDTO,
     * or with status {@code 400 (Bad Request)} if the banghiKiemkeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the banghiKiemkeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/banghi-kiemkes")
    public ResponseEntity<BanghiKiemkeDTO> updateBanghiKiemke(@Valid @RequestBody BanghiKiemkeDTO banghiKiemkeDTO) throws URISyntaxException {
        log.debug("REST request to update BanghiKiemke : {}", banghiKiemkeDTO);
        if (banghiKiemkeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BanghiKiemkeDTO result = banghiKiemkeService.save(banghiKiemkeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, banghiKiemkeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /banghi-kiemkes} : get all the banghiKiemkes.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of banghiKiemkes in body.
     */
    @GetMapping("/banghi-kiemkes")
    public ResponseEntity<List<BanghiKiemkeDTO>> getAllBanghiKiemkes(Pageable pageable) {
        log.debug("REST request to get a page of BanghiKiemkes");
        Page<BanghiKiemkeDTO> page = banghiKiemkeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /banghi-kiemkes/:id} : get the "id" banghiKiemke.
     *
     * @param id the id of the banghiKiemkeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the banghiKiemkeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/banghi-kiemkes/{id}")
    public ResponseEntity<BanghiKiemkeDTO> getBanghiKiemke(@PathVariable String id) {
        log.debug("REST request to get BanghiKiemke : {}", id);
        Optional<BanghiKiemkeDTO> banghiKiemkeDTO = banghiKiemkeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(banghiKiemkeDTO);
    }

    /**
     * {@code DELETE  /banghi-kiemkes/:id} : delete the "id" banghiKiemke.
     *
     * @param id the id of the banghiKiemkeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/banghi-kiemkes/{id}")
    public ResponseEntity<Void> deleteBanghiKiemke(@PathVariable String id) {
        log.debug("REST request to delete BanghiKiemke : {}", id);
        banghiKiemkeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
