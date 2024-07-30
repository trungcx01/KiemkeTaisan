package com.prj.repository;

import com.prj.domain.DonviCungcap;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the DonviCungcap entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DonviCungcapRepository extends MongoRepository<DonviCungcap, String> {

}
