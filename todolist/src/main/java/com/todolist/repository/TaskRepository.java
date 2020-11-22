package com.todolist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.todolist.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, String>, ParentRepository{

	@Query(value="SELECT t.task_id FROM task t", nativeQuery=true)
	List<String> findAllIds();
	
	@Query(value="SELECT * from task t where t.task_id = :taskId", nativeQuery=true)
	Task findTaskById(@Param("taskId") int taskId);
	
	@Query(value="SELECT * from task t where t.user_id = :userId", nativeQuery=true)
	List<Task> findAllTaskByUserId(@Param("userId") int userId);
	
}
