package com.prj.repository;

import com.prj.domain.Nhanvien;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the Nhanvien entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NhanvienRepository extends MongoRepository<Nhanvien, String> {

}
