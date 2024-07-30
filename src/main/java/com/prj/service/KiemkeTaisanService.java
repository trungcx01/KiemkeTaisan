package com.prj.service;

import com.prj.service.dto.KiemkeTaisanDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.prj.domain.KiemkeTaisan}.
 */
public interface KiemkeTaisanService {

    /**
     * Save a kiemkeTaisan.
     *
     * @param kiemkeTaisanDTO the entity to save.
     * @return the persisted entity.
     */
    KiemkeTaisanDTO save(KiemkeTaisanDTO kiemkeTaisanDTO);

    /**
     * Get all the kiemkeTaisans.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<KiemkeTaisanDTO> findAll(Pageable pageable);


    /**
     * Get the "id" kiemkeTaisan.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<KiemkeTaisanDTO> findOne(String id);

    /**
     * Delete the "id" kiemkeTaisan.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
