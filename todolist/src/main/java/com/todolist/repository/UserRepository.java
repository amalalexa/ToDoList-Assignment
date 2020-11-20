package com.todolist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.todolist.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>, ParentRepository {
	
	@Query(value="SELECT u.user_id FROM user u", nativeQuery=true)
	List<String> findAllIds();
	
	@Query(value="SELECT count(1) FROM user u where u.user_id = :userId", nativeQuery=true)
	int checkUserExsists(@Param("userId") String userId);
	
	@Query(value="SELECT * from user u where u.user_name = :username", nativeQuery=true)
	User getUserName(@Param("username") String userName);

}
