package com.prj.service.impl;

import com.prj.service.TaisanService;
import com.prj.domain.Taisan;
import com.prj.repository.TaisanRepository;
import com.prj.service.dto.TaisanDTO;
import com.prj.service.mapper.TaisanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Taisan}.
 */
@Service
public class TaisanServiceImpl implements TaisanService {

    private final Logger log = LoggerFactory.getLogger(TaisanServiceImpl.class);

    private final TaisanRepository taisanRepository;

    private final TaisanMapper taisanMapper;

    public TaisanServiceImpl(TaisanRepository taisanRepository, TaisanMapper taisanMapper) {
        this.taisanRepository = taisanRepository;
        this.taisanMapper = taisanMapper;
    }

    /**
     * Save a taisan.
     *
     * @param taisanDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TaisanDTO save(TaisanDTO taisanDTO) {
        log.debug("Request to save Taisan : {}", taisanDTO);
        Taisan taisan = taisanMapper.toEntity(taisanDTO);
        taisan = taisanRepository.save(taisan);
        return taisanMapper.toDto(taisan);
    }

    /**
     * Get all the taisans.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<TaisanDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Taisans");
        return taisanRepository.findAll(pageable)
            .map(taisanMapper::toDto);
    }


    /**
     * Get one taisan by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<TaisanDTO> findOne(String id) {
        log.debug("Request to get Taisan : {}", id);
        return taisanRepository.findById(id)
            .map(taisanMapper::toDto);
    }

    /**
     * Delete the taisan by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Taisan : {}", id);
        taisanRepository.deleteById(id);
    }

    @Override
    public List<TaisanDTO> getAllTaisansByDonviSudung(String donviSudung) {
        log.debug("Request to get All Taisans By DonviSudung : {}", donviSudung);
        if (donviSudung.equals("all")){
            return taisanRepository.findAll().stream().map(i -> taisanMapper.toDto(i)).collect(Collectors.toList());
        }
        return taisanRepository.getAllTaisansByDonviSudung(donviSudung).stream().map(i -> taisanMapper.toDto(i)).collect(Collectors.toList());
    }
}
