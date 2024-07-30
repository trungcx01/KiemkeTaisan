package com.prj.service;

import com.prj.service.dto.TaisanDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.prj.domain.Taisan}.
 */
public interface TaisanService {

    /**
     * Save a taisan.
     *
     * @param taisanDTO the entity to save.
     * @return the persisted entity.
     */
    TaisanDTO save(TaisanDTO taisanDTO);

    /**
     * Get all the taisans.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TaisanDTO> findAll(Pageable pageable);


    /**
     * Get the "id" taisan.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TaisanDTO> findOne(String id);

    /**
     * Delete the "id" taisan.
     *
     * @param id the id of the entity.
     */
    void delete(String id);

    List<TaisanDTO> getAllTaisansByDonviSudung(String donviSudung);
}
