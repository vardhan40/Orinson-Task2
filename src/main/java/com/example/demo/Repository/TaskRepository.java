package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Task;

public interface TaskRepository extends JpaRepository<Task,Integer>{

}
