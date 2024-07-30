package com.prj.repository;

import com.prj.domain.BanghiKiemke;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the BanghiKiemke entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BanghiKiemkeRepository extends MongoRepository<BanghiKiemke, String> {
    void deleteByKiemkeTaisanId(String taisanId);
}
