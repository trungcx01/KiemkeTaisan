package com.prj.service.impl;

import com.prj.service.KiemkeTaisanService;
import com.prj.domain.BanghiKiemke;
import com.prj.domain.KiemkeTaisan;
import com.prj.domain.NhanvienKiemke;
import com.prj.domain.Taisan;
import com.prj.repository.BanghiKiemkeRepository;
import com.prj.repository.KiemkeTaisanRepository;
import com.prj.repository.NhanvienKiemkeRepository;
import com.prj.repository.TaisanRepository;
import com.prj.service.dto.BanghiKiemkeDTO;
import com.prj.service.dto.KiemkeTaisanDTO;
import com.prj.service.dto.NhanvienKiemkeDTO;
import com.prj.service.mapper.BanghiKiemkeMapper;
import com.prj.service.mapper.KiemkeTaisanMapper;
import com.prj.service.mapper.NhanvienKiemkeMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Service Implementation for managing {@link KiemkeTaisan}.
 */
@Service
public class KiemkeTaisanServiceImpl implements KiemkeTaisanService {

    private final Logger log = LoggerFactory.getLogger(KiemkeTaisanServiceImpl.class);

    private final KiemkeTaisanRepository kiemkeTaisanRepository;
    private final TaisanRepository taisanRepository;

    private final KiemkeTaisanMapper kiemkeTaisanMapper;

    private final NhanvienKiemkeRepository nhanvienKiemkeRepository;

    private final NhanvienKiemkeMapper nhanvienKiemkeMapper;

    private final BanghiKiemkeRepository banghiKiemkeRepository;

    private final BanghiKiemkeMapper banghiKiemkeMapper;

    public KiemkeTaisanServiceImpl(KiemkeTaisanRepository kiemkeTaisanRepository, KiemkeTaisanMapper kiemkeTaisanMapper,
            NhanvienKiemkeRepository nhanvienKiemkeRepository, NhanvienKiemkeMapper nhanvienKiemkeMapper,
            BanghiKiemkeRepository banghiKiemkeRepository, BanghiKiemkeMapper banghiKiemkeMapper,
            TaisanRepository taisanRepository) {
        this.kiemkeTaisanRepository = kiemkeTaisanRepository;
        this.taisanRepository = taisanRepository;
        this.kiemkeTaisanMapper = kiemkeTaisanMapper;
        this.nhanvienKiemkeRepository = nhanvienKiemkeRepository;
        this.nhanvienKiemkeMapper = nhanvienKiemkeMapper;
        this.banghiKiemkeRepository = banghiKiemkeRepository;
        this.banghiKiemkeMapper = banghiKiemkeMapper;
    }

    /**
     * Save a kiemkeTaisan.
     *
     * @param kiemkeTaisanDTO the entity to save.
     * @return the persisted entity.
     */

    @Override
    @Transactional
    public KiemkeTaisanDTO save(KiemkeTaisanDTO kiemkeTaisanDTO) {
        log.debug("Request to save KiemkeTaisan : {}", kiemkeTaisanDTO);
        if (kiemkeTaisanDTO.getId() != null) {
            nhanvienKiemkeRepository.deleteByKiemkeTaisanId(kiemkeTaisanDTO.getId());
            banghiKiemkeRepository.deleteByKiemkeTaisanId(kiemkeTaisanDTO.getId());
        }

        Set<NhanvienKiemke> set1 = new HashSet<>();
        Set<BanghiKiemke> set2 = new HashSet<>();
        for (NhanvienKiemkeDTO x : kiemkeTaisanDTO.getNhanvienKiemkes()) {
            NhanvienKiemke nhanvienKiemke = nhanvienKiemkeMapper.toEntity(x);
            nhanvienKiemke = nhanvienKiemkeRepository.save(nhanvienKiemke);
            set1.add(nhanvienKiemke);
        }

        for (BanghiKiemkeDTO x : kiemkeTaisanDTO.getBanghiKiemkes()) {
            BanghiKiemke banghiKiemke = banghiKiemkeMapper.toEntity(x);
            banghiKiemke = banghiKiemkeRepository.save(banghiKiemke);
            set2.add(banghiKiemke);
        }

        KiemkeTaisan kiemkeTaisan = kiemkeTaisanMapper.toEntity(kiemkeTaisanDTO);
        kiemkeTaisan.setBanghiKiemkes(set2);
        kiemkeTaisan.setNhanvienKiemkes(set1);
        kiemkeTaisan = kiemkeTaisanRepository.save(kiemkeTaisan);
        for (BanghiKiemke x : set2) {
            Taisan taisan = taisanRepository.findById(x.getTaisan().getId()).orElse(null);
            x.setSoluongBandau(taisan.getSoluong());
            x.setGiatriConlaiBandau(taisan.getGiatriConlai()); // cập nhật vào kiemkeTaisan, để đối chiếu
            // if (x.getHinhthucXuly() == 0 || x.getHinhthucXuly() == 1) {  // nếu là ghi tăng hoạc ghi giảm
            //     taisan.setGiatriConlai(x.getGiatriConlai());
            //     taisan.setSoluong(x.getSoluong());
            //     taisanRepository.save(taisan);
            // }
            // else{
            //     taisan.setGiatriConlai(x.getGiatriConlaiBandau());
            //     taisan.setSoluong(x.getSoluongBandau());
            //     taisanRepository.save(taisan);
            // }
            x.setKiemkeTaisan(kiemkeTaisan);
            banghiKiemkeRepository.save(x);
        }

        for (NhanvienKiemke x : set1) {
            x.setKiemkeTaisan(kiemkeTaisan);
            nhanvienKiemkeRepository.save(x);
        }
        return kiemkeTaisanMapper.toDto(kiemkeTaisan);
    }

    /**
     * Get all the kiemkeTaisans.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<KiemkeTaisanDTO> findAll(Pageable pageable) {
        log.debug("Request to get all KiemkeTaisans");
        return kiemkeTaisanRepository.findAll(pageable)
                .map(kiemkeTaisanMapper::toDto);
    }

    /**
     * Get one kiemkeTaisan by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<KiemkeTaisanDTO> findOne(String id) {
        log.debug("Request to get KiemkeTaisan : {}", id);
        return kiemkeTaisanRepository.findById(id)
                .map(kiemkeTaisanMapper::toDto);
    }

    /**
     * Delete the kiemkeTaisan by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete KiemkeTaisan : {}", id);
        KiemkeTaisan kiemkeTaisan = kiemkeTaisanRepository.findById(id).orElse(null);
        if (kiemkeTaisan != null && !kiemkeTaisan.getBanghiKiemkes().isEmpty()
                && !kiemkeTaisan.getNhanvienKiemkes().isEmpty()) {
            banghiKiemkeRepository.deleteAll(kiemkeTaisan.getBanghiKiemkes());
            nhanvienKiemkeRepository.deleteAll(kiemkeTaisan.getNhanvienKiemkes());
        }
        kiemkeTaisanRepository.deleteById(id);
    }
}
