package com.app.webservice.repo.master;

import com.app.webservice.model.master.ERole;
import com.app.webservice.model.master.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
	
	@Query("select r from Role r "
			+ "where r.roleName = ?1")
    Optional<Role> findByName(ERole name);
}
