package com.sample.credentials.dao.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sample.credentials.dao.entity.RftCodeModule;

@Repository
public interface RftCodeModuleRepository extends JpaRepository<RftCodeModule, Long> {

    @Query(value = "select r from RftCodeModule r where r.name = ?1")
    RftCodeModule findRftCdModuleByLibModule(String module);

}
