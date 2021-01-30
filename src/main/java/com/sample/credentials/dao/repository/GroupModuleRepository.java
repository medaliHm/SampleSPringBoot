package com.sample.credentials.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sample.credentials.dao.entity.GroupeModule;
import com.sample.credentials.dao.entity.GroupeModuleId;

@Repository
public interface GroupModuleRepository extends JpaRepository<GroupeModule, GroupeModuleId> {
	@Query("Select g from GroupeModule  g where g.dateFin is null")
	public List<GroupeModule> recupOuvertGroupeModule();

	@Query("select case when count(g)> 0 then true else false end from GroupeModule  g where g.dateFin is null and g.id.libelleGroup=?1")
	public boolean getValideGroupe(String libelle);

	@Query("Select g from GroupeModule  g where g.dateFin is null and g.id.libelleGroup=:libelleGroup")
	public List<GroupeModule> getGroupeModuleByLibGroupe(@Param("libelleGroup") String libelleGroup);

}
