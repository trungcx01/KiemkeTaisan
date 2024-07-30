package com.prj.repository;

import com.prj.domain.DanhmucTaisan;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the DanhmucTaisan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DanhmucTaisanRepository extends MongoRepository<DanhmucTaisan, String> {

}
