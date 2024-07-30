package com.prj.repository;

import com.prj.domain.KiemkeTaisan;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the KiemkeTaisan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KiemkeTaisanRepository extends MongoRepository<KiemkeTaisan, String> {

}
