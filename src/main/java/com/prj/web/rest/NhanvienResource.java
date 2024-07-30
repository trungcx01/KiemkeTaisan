package com.prj.web.rest;

import com.prj.service.NhanvienService;
import com.prj.web.rest.errors.BadRequestAlertException;
import com.prj.service.dto.NhanvienDTO;

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
 * REST controller for managing {@link com.prj.domain.Nhanvien}.
 */
@RestController
@RequestMapping("/api")
public class NhanvienResource {

    private final Logger log = LoggerFactory.getLogger(NhanvienResource.class);

    private static final String ENTITY_NAME = "nhanvien";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NhanvienService nhanvienService;

    public NhanvienResource(NhanvienService nhanvienService) {
        this.nhanvienService = nhanvienService;
    }

    /**
     * {@code POST  /nhanviens} : Create a new nhanvien.
     *
     * @param nhanvienDTO the nhanvienDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new nhanvienDTO, or with status {@code 400 (Bad Request)} if the nhanvien has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/nhanviens")
    public ResponseEntity<NhanvienDTO> createNhanvien(@Valid @RequestBody NhanvienDTO nhanvienDTO) throws URISyntaxException {
        log.debug("REST request to save Nhanvien : {}", nhanvienDTO);
        if (nhanvienDTO.getId() != null) {
            throw new BadRequestAlertException("A new nhanvien cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NhanvienDTO result = nhanvienService.save(nhanvienDTO);
        return ResponseEntity.created(new URI("/api/nhanviens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /nhanviens} : Updates an existing nhanvien.
     *
     * @param nhanvienDTO the nhanvienDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nhanvienDTO,
     * or with status {@code 400 (Bad Request)} if the nhanvienDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the nhanvienDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/nhanviens")
    public ResponseEntity<NhanvienDTO> updateNhanvien(@Valid @RequestBody NhanvienDTO nhanvienDTO) throws URISyntaxException {
        log.debug("REST request to update Nhanvien : {}", nhanvienDTO);
        if (nhanvienDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NhanvienDTO result = nhanvienService.save(nhanvienDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, nhanvienDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /nhanviens} : get all the nhanviens.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of nhanviens in body.
     */
    @GetMapping("/nhanviens")
    public ResponseEntity<List<NhanvienDTO>> getAllNhanviens(Pageable pageable) {
        log.debug("REST request to get a page of Nhanviens");
        Page<NhanvienDTO> page = nhanvienService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /nhanviens/:id} : get the "id" nhanvien.
     *
     * @param id the id of the nhanvienDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the nhanvienDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/nhanviens/{id}")
    public ResponseEntity<NhanvienDTO> getNhanvien(@PathVariable String id) {
        log.debug("REST request to get Nhanvien : {}", id);
        Optional<NhanvienDTO> nhanvienDTO = nhanvienService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nhanvienDTO);
    }

    /**
     * {@code DELETE  /nhanviens/:id} : delete the "id" nhanvien.
     *
     * @param id the id of the nhanvienDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/nhanviens/{id}")
    public ResponseEntity<Void> deleteNhanvien(@PathVariable String id) {
        log.debug("REST request to delete Nhanvien : {}", id);
        nhanvienService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
