package com.prj.service.impl;

import com.prj.service.NhanvienService;
import com.prj.domain.Nhanvien;
import com.prj.repository.NhanvienRepository;
import com.prj.service.dto.NhanvienDTO;
import com.prj.service.mapper.NhanvienMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Nhanvien}.
 */
@Service
public class NhanvienServiceImpl implements NhanvienService {

    private final Logger log = LoggerFactory.getLogger(NhanvienServiceImpl.class);

    private final NhanvienRepository nhanvienRepository;

    private final NhanvienMapper nhanvienMapper;

    public NhanvienServiceImpl(NhanvienRepository nhanvienRepository, NhanvienMapper nhanvienMapper) {
        this.nhanvienRepository = nhanvienRepository;
        this.nhanvienMapper = nhanvienMapper;
    }

    /**
     * Save a nhanvien.
     *
     * @param nhanvienDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public NhanvienDTO save(NhanvienDTO nhanvienDTO) {
        log.debug("Request to save Nhanvien : {}", nhanvienDTO);
        Nhanvien nhanvien = nhanvienMapper.toEntity(nhanvienDTO);
        nhanvien = nhanvienRepository.save(nhanvien);
        return nhanvienMapper.toDto(nhanvien);
    }

    /**
     * Get all the nhanviens.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<NhanvienDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Nhanviens");
        return nhanvienRepository.findAll(pageable)
            .map(nhanvienMapper::toDto);
    }


    /**
     * Get one nhanvien by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<NhanvienDTO> findOne(String id) {
        log.debug("Request to get Nhanvien : {}", id);
        return nhanvienRepository.findById(id)
            .map(nhanvienMapper::toDto);
    }

    /**
     * Delete the nhanvien by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Nhanvien : {}", id);
        nhanvienRepository.deleteById(id);
    }
}
