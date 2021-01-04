package com.app.webservice.repo.master;

import com.app.webservice.model.master.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

	@Query("select u from User u " 
			+ "where u.userName = ?1")
	Optional<User> findByUsername(String userName);

	@Query(value = "select CASE when count(1) > 0 then true else false end "
			+ "from mst_user where user_name = :userName", nativeQuery = true)
	Boolean isExistByUsername(String userName);
	
	@Query(value = "select CASE when count(1) > 0 then true else false end "
			+ "from mst_user where email = :email", nativeQuery = true)
	Boolean isExistByEmail(String email);
}
