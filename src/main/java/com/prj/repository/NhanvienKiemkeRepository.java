package com.prj.repository;

import com.prj.domain.NhanvienKiemke;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the NhanvienKiemke entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NhanvienKiemkeRepository extends MongoRepository<NhanvienKiemke, String> {
    void deleteByKiemkeTaisanId(String kiemkeTaisanId);
}
