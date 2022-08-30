package com.TemizlikSirketi.proje.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.TemizlikSirketi.proje.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

	 @Query(value = "SELECT * FROM users u where u.name = ? and u.password = ?;", nativeQuery = true)
	    public UserModel findByUsername(String name, Long password);

	    @Query(value = "SELECT * FROM users u where u.is_admin = ?;", nativeQuery = true)
	    public List<UserModel> getUsernameByisAdmin(boolean is_admin);
 
	    
		
}
