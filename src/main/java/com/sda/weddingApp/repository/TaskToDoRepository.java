package com.sda.weddingApp.repository;

import com.sda.weddingApp.model.TaskToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskToDoRepository extends JpaRepository<TaskToDo, Long> {
}
