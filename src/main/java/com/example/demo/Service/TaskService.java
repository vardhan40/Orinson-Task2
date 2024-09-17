package com.example.demo.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Task;
import com.example.demo.Repository.TaskRepository;

@Service
public class TaskService {
	@Autowired
	TaskRepository taskRepo;
	
	public Task saveOrUpdate(Task task) {
		return taskRepo.save(task);
	}
	
	public List<Task> getAllTasks() {
		return taskRepo.findAll();
	}
	public Optional<Task> getTaskById(int id) {
		return taskRepo.findById(id);
	}	
	public void deleteTaskById(int id) {
		taskRepo.findById(id);
		taskRepo.deleteById(id);
	}
}
