package com.prj.service;

import com.prj.service.dto.DonviCungcapDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.prj.domain.DonviCungcap}.
 */
public interface DonviCungcapService {

    /**
     * Save a donviCungcap.
     *
     * @param donviCungcapDTO the entity to save.
     * @return the persisted entity.
     */
    DonviCungcapDTO save(DonviCungcapDTO donviCungcapDTO);

    /**
     * Get all the donviCungcaps.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DonviCungcapDTO> findAll(Pageable pageable);


    /**
     * Get the "id" donviCungcap.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DonviCungcapDTO> findOne(String id);

    /**
     * Delete the "id" donviCungcap.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
