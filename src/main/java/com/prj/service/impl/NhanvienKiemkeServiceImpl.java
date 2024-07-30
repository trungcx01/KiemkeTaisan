package com.prj.service.impl;

import com.prj.service.NhanvienKiemkeService;
import com.prj.domain.NhanvienKiemke;
import com.prj.repository.NhanvienKiemkeRepository;
import com.prj.service.dto.NhanvienKiemkeDTO;
import com.prj.service.mapper.NhanvienKiemkeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link NhanvienKiemke}.
 */
@Service
public class NhanvienKiemkeServiceImpl implements NhanvienKiemkeService {

    private final Logger log = LoggerFactory.getLogger(NhanvienKiemkeServiceImpl.class);

    private final NhanvienKiemkeRepository nhanvienKiemkeRepository;

    private final NhanvienKiemkeMapper nhanvienKiemkeMapper;

    public NhanvienKiemkeServiceImpl(NhanvienKiemkeRepository nhanvienKiemkeRepository, NhanvienKiemkeMapper nhanvienKiemkeMapper) {
        this.nhanvienKiemkeRepository = nhanvienKiemkeRepository;
        this.nhanvienKiemkeMapper = nhanvienKiemkeMapper;
    }

    /**
     * Save a nhanvienKiemke.
     *
     * @param nhanvienKiemkeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public NhanvienKiemkeDTO save(NhanvienKiemkeDTO nhanvienKiemkeDTO) {
        log.debug("Request to save NhanvienKiemke : {}", nhanvienKiemkeDTO);
        NhanvienKiemke nhanvienKiemke = nhanvienKiemkeMapper.toEntity(nhanvienKiemkeDTO);
        nhanvienKiemke = nhanvienKiemkeRepository.save(nhanvienKiemke);
        return nhanvienKiemkeMapper.toDto(nhanvienKiemke);
    }

    /**
     * Get all the nhanvienKiemkes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<NhanvienKiemkeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NhanvienKiemkes");
        return nhanvienKiemkeRepository.findAll(pageable)
            .map(nhanvienKiemkeMapper::toDto);
    }


    /**
     * Get one nhanvienKiemke by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<NhanvienKiemkeDTO> findOne(String id) {
        log.debug("Request to get NhanvienKiemke : {}", id);
        return nhanvienKiemkeRepository.findById(id)
            .map(nhanvienKiemkeMapper::toDto);
    }

    /**
     * Delete the nhanvienKiemke by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete NhanvienKiemke : {}", id);
        nhanvienKiemkeRepository.deleteById(id);
    }
}
