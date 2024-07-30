package com.prj.service;

import com.prj.service.dto.NhanvienKiemkeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.prj.domain.NhanvienKiemke}.
 */
public interface NhanvienKiemkeService {

    /**
     * Save a nhanvienKiemke.
     *
     * @param nhanvienKiemkeDTO the entity to save.
     * @return the persisted entity.
     */
    NhanvienKiemkeDTO save(NhanvienKiemkeDTO nhanvienKiemkeDTO);

    /**
     * Get all the nhanvienKiemkes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<NhanvienKiemkeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" nhanvienKiemke.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NhanvienKiemkeDTO> findOne(String id);

    /**
     * Delete the "id" nhanvienKiemke.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
