package com.prj.service.impl;

import com.prj.service.DonviCungcapService;
import com.prj.domain.DonviCungcap;
import com.prj.repository.DonviCungcapRepository;
import com.prj.service.dto.DonviCungcapDTO;
import com.prj.service.mapper.DonviCungcapMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DonviCungcap}.
 */
@Service
public class DonviCungcapServiceImpl implements DonviCungcapService {

    private final Logger log = LoggerFactory.getLogger(DonviCungcapServiceImpl.class);

    private final DonviCungcapRepository donviCungcapRepository;

    private final DonviCungcapMapper donviCungcapMapper;

    public DonviCungcapServiceImpl(DonviCungcapRepository donviCungcapRepository, DonviCungcapMapper donviCungcapMapper) {
        this.donviCungcapRepository = donviCungcapRepository;
        this.donviCungcapMapper = donviCungcapMapper;
    }

    /**
     * Save a donviCungcap.
     *
     * @param donviCungcapDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DonviCungcapDTO save(DonviCungcapDTO donviCungcapDTO) {
        log.debug("Request to save DonviCungcap : {}", donviCungcapDTO);
        DonviCungcap donviCungcap = donviCungcapMapper.toEntity(donviCungcapDTO);
        donviCungcap = donviCungcapRepository.save(donviCungcap);
        return donviCungcapMapper.toDto(donviCungcap);
    }

    /**
     * Get all the donviCungcaps.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<DonviCungcapDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DonviCungcaps");
        return donviCungcapRepository.findAll(pageable)
            .map(donviCungcapMapper::toDto);
    }


    /**
     * Get one donviCungcap by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<DonviCungcapDTO> findOne(String id) {
        log.debug("Request to get DonviCungcap : {}", id);
        return donviCungcapRepository.findById(id)
            .map(donviCungcapMapper::toDto);
    }

    /**
     * Delete the donviCungcap by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete DonviCungcap : {}", id);
        donviCungcapRepository.deleteById(id);
    }
}
