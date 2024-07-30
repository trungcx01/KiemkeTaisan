package com.prj.service;

import com.prj.service.dto.NhanvienDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.prj.domain.Nhanvien}.
 */
public interface NhanvienService {

    /**
     * Save a nhanvien.
     *
     * @param nhanvienDTO the entity to save.
     * @return the persisted entity.
     */
    NhanvienDTO save(NhanvienDTO nhanvienDTO);

    /**
     * Get all the nhanviens.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<NhanvienDTO> findAll(Pageable pageable);


    /**
     * Get the "id" nhanvien.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NhanvienDTO> findOne(String id);

    /**
     * Delete the "id" nhanvien.
     *
     * @param id the id of the entity.
     */
    void delete(String id);

    
}
