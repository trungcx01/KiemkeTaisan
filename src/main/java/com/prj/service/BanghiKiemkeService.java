package com.prj.service;

import com.prj.service.dto.BanghiKiemkeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.prj.domain.BanghiKiemke}.
 */
public interface BanghiKiemkeService {

    /**
     * Save a banghiKiemke.
     *
     * @param banghiKiemkeDTO the entity to save.
     * @return the persisted entity.
     */
    BanghiKiemkeDTO save(BanghiKiemkeDTO banghiKiemkeDTO);

    /**
     * Get all the banghiKiemkes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BanghiKiemkeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" banghiKiemke.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BanghiKiemkeDTO> findOne(String id);

    /**
     * Delete the "id" banghiKiemke.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
