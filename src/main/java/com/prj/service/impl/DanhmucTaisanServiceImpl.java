package com.prj.service.impl;

import com.prj.service.DanhmucTaisanService;
import com.prj.domain.DanhmucTaisan;
import com.prj.repository.DanhmucTaisanRepository;
import com.prj.service.dto.DanhmucTaisanDTO;
import com.prj.service.mapper.DanhmucTaisanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DanhmucTaisan}.
 */
@Service
public class DanhmucTaisanServiceImpl implements DanhmucTaisanService {

    private final Logger log = LoggerFactory.getLogger(DanhmucTaisanServiceImpl.class);

    private final DanhmucTaisanRepository danhmucTaisanRepository;

    private final DanhmucTaisanMapper danhmucTaisanMapper;

    public DanhmucTaisanServiceImpl(DanhmucTaisanRepository danhmucTaisanRepository, DanhmucTaisanMapper danhmucTaisanMapper) {
        this.danhmucTaisanRepository = danhmucTaisanRepository;
        this.danhmucTaisanMapper = danhmucTaisanMapper;
    }

    /**
     * Save a danhmucTaisan.
     *
     * @param danhmucTaisanDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DanhmucTaisanDTO save(DanhmucTaisanDTO danhmucTaisanDTO) {
        log.debug("Request to save DanhmucTaisan : {}", danhmucTaisanDTO);
        DanhmucTaisan danhmucTaisan = danhmucTaisanMapper.toEntity(danhmucTaisanDTO);
        danhmucTaisan = danhmucTaisanRepository.save(danhmucTaisan);
        return danhmucTaisanMapper.toDto(danhmucTaisan);
    }

    /**
     * Get all the danhmucTaisans.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<DanhmucTaisanDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DanhmucTaisans");
        return danhmucTaisanRepository.findAll(pageable)
            .map(danhmucTaisanMapper::toDto);
    }


    /**
     * Get one danhmucTaisan by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<DanhmucTaisanDTO> findOne(String id) {
        log.debug("Request to get DanhmucTaisan : {}", id);
        return danhmucTaisanRepository.findById(id)
            .map(danhmucTaisanMapper::toDto);
    }

    /**
     * Delete the danhmucTaisan by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete DanhmucTaisan : {}", id);
        danhmucTaisanRepository.deleteById(id);
    }
}
