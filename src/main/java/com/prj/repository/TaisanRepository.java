package com.prj.repository;

import com.prj.domain.Taisan;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;



/**
 * Spring Data MongoDB repository for the Taisan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaisanRepository extends MongoRepository<Taisan, String> {
    List<Taisan> getAllTaisansByDonviSudung(String donviSudung);
}

