package com.prj.web.rest;

import com.prj.service.NhanvienKiemkeService;
import com.prj.web.rest.errors.BadRequestAlertException;
import com.prj.service.dto.NhanvienKiemkeDTO;

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
 * REST controller for managing {@link com.prj.domain.NhanvienKiemke}.
 */
@RestController
@RequestMapping("/api")
public class NhanvienKiemkeResource {

    private final Logger log = LoggerFactory.getLogger(NhanvienKiemkeResource.class);

    private static final String ENTITY_NAME = "nhanvienKiemke";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NhanvienKiemkeService nhanvienKiemkeService;

    public NhanvienKiemkeResource(NhanvienKiemkeService nhanvienKiemkeService) {
        this.nhanvienKiemkeService = nhanvienKiemkeService;
    }

    /**
     * {@code POST  /nhanvien-kiemkes} : Create a new nhanvienKiemke.
     *
     * @param nhanvienKiemkeDTO the nhanvienKiemkeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new nhanvienKiemkeDTO, or with status {@code 400 (Bad Request)} if the nhanvienKiemke has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/nhanvien-kiemkes")
    public ResponseEntity<NhanvienKiemkeDTO> createNhanvienKiemke(@Valid @RequestBody NhanvienKiemkeDTO nhanvienKiemkeDTO) throws URISyntaxException {
        log.debug("REST request to save NhanvienKiemke : {}", nhanvienKiemkeDTO);
        if (nhanvienKiemkeDTO.getId() != null) {
            throw new BadRequestAlertException("A new nhanvienKiemke cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NhanvienKiemkeDTO result = nhanvienKiemkeService.save(nhanvienKiemkeDTO);
        return ResponseEntity.created(new URI("/api/nhanvien-kiemkes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /nhanvien-kiemkes} : Updates an existing nhanvienKiemke.
     *
     * @param nhanvienKiemkeDTO the nhanvienKiemkeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nhanvienKiemkeDTO,
     * or with status {@code 400 (Bad Request)} if the nhanvienKiemkeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the nhanvienKiemkeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/nhanvien-kiemkes")
    public ResponseEntity<NhanvienKiemkeDTO> updateNhanvienKiemke(@Valid @RequestBody NhanvienKiemkeDTO nhanvienKiemkeDTO) throws URISyntaxException {
        log.debug("REST request to update NhanvienKiemke : {}", nhanvienKiemkeDTO);
        if (nhanvienKiemkeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NhanvienKiemkeDTO result = nhanvienKiemkeService.save(nhanvienKiemkeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, nhanvienKiemkeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /nhanvien-kiemkes} : get all the nhanvienKiemkes.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of nhanvienKiemkes in body.
     */
    @GetMapping("/nhanvien-kiemkes")
    public ResponseEntity<List<NhanvienKiemkeDTO>> getAllNhanvienKiemkes(Pageable pageable) {
        log.debug("REST request to get a page of NhanvienKiemkes");
        Page<NhanvienKiemkeDTO> page = nhanvienKiemkeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /nhanvien-kiemkes/:id} : get the "id" nhanvienKiemke.
     *
     * @param id the id of the nhanvienKiemkeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the nhanvienKiemkeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/nhanvien-kiemkes/{id}")
    public ResponseEntity<NhanvienKiemkeDTO> getNhanvienKiemke(@PathVariable String id) {
        log.debug("REST request to get NhanvienKiemke : {}", id);
        Optional<NhanvienKiemkeDTO> nhanvienKiemkeDTO = nhanvienKiemkeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nhanvienKiemkeDTO);
    }

    /**
     * {@code DELETE  /nhanvien-kiemkes/:id} : delete the "id" nhanvienKiemke.
     *
     * @param id the id of the nhanvienKiemkeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/nhanvien-kiemkes/{id}")
    public ResponseEntity<Void> deleteNhanvienKiemke(@PathVariable String id) {
        log.debug("REST request to delete NhanvienKiemke : {}", id);
        nhanvienKiemkeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
