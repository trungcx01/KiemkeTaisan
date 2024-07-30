package com.prj.service.impl;

import com.prj.service.BanghiKiemkeService;
import com.prj.domain.BanghiKiemke;
import com.prj.repository.BanghiKiemkeRepository;
import com.prj.service.dto.BanghiKiemkeDTO;
import com.prj.service.mapper.BanghiKiemkeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link BanghiKiemke}.
 */
@Service
public class BanghiKiemkeServiceImpl implements BanghiKiemkeService {

    private final Logger log = LoggerFactory.getLogger(BanghiKiemkeServiceImpl.class);

    private final BanghiKiemkeRepository banghiKiemkeRepository;

    private final BanghiKiemkeMapper banghiKiemkeMapper;

    public BanghiKiemkeServiceImpl(BanghiKiemkeRepository banghiKiemkeRepository, BanghiKiemkeMapper banghiKiemkeMapper) {
        this.banghiKiemkeRepository = banghiKiemkeRepository;
        this.banghiKiemkeMapper = banghiKiemkeMapper;
    }

    /**
     * Save a banghiKiemke.
     *
     * @param banghiKiemkeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public BanghiKiemkeDTO save(BanghiKiemkeDTO banghiKiemkeDTO) {
        log.debug("Request to save BanghiKiemke : {}", banghiKiemkeDTO);
        BanghiKiemke banghiKiemke = banghiKiemkeMapper.toEntity(banghiKiemkeDTO);
        banghiKiemke = banghiKiemkeRepository.save(banghiKiemke);
        return banghiKiemkeMapper.toDto(banghiKiemke);
    }

    /**
     * Get all the banghiKiemkes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<BanghiKiemkeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BanghiKiemkes");
        return banghiKiemkeRepository.findAll(pageable)
            .map(banghiKiemkeMapper::toDto);
    }


    /**
     * Get one banghiKiemke by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<BanghiKiemkeDTO> findOne(String id) {
        log.debug("Request to get BanghiKiemke : {}", id);
        return banghiKiemkeRepository.findById(id)
            .map(banghiKiemkeMapper::toDto);
    }

    /**
     * Delete the banghiKiemke by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete BanghiKiemke : {}", id);
        banghiKiemkeRepository.deleteById(id);
    }
}
