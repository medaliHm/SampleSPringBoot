package com.sample.credentials.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sample.credentials.dao.entity.UserGroup;
import com.sample.credentials.dao.entity.UserGroupId;

@Repository

public interface UserGroupRepository extends JpaRepository<UserGroup, UserGroupId> {

	@Query("Select g from UserGroup  g where g.dateFin is null")
	public List<UserGroup> recupActifUsers();

	@Query("Select g from UserGroup  g where g.dateFin is null and g.user.name= :idCnp")
	public List<UserGroup> recupActifUserByUsename(@Param("idCnp") String idCnp);

}
