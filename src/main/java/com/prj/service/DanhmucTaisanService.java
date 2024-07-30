package com.prj.service;

import com.prj.service.dto.DanhmucTaisanDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.prj.domain.DanhmucTaisan}.
 */
public interface DanhmucTaisanService {

    /**
     * Save a danhmucTaisan.
     *
     * @param danhmucTaisanDTO the entity to save.
     * @return the persisted entity.
     */
    DanhmucTaisanDTO save(DanhmucTaisanDTO danhmucTaisanDTO);

    /**
     * Get all the danhmucTaisans.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DanhmucTaisanDTO> findAll(Pageable pageable);


    /**
     * Get the "id" danhmucTaisan.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DanhmucTaisanDTO> findOne(String id);

    /**
     * Delete the "id" danhmucTaisan.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
