package com.example.demo.Controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Task;
import com.example.demo.Exception.ErrorResponse;
import com.example.demo.Exception.TaskExceptionHandler;
import com.example.demo.Service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/tasks")
public class TaskController {
	@Autowired
	TaskService taskService;

	@PostMapping()
	public Task addBook(@Valid @RequestBody Task task) {
		return taskService.saveOrUpdate(task);
	}

	@GetMapping
	public ResponseEntity<List<Task>> getAllTasks() {
		List<Task> tasks = taskService.getAllTasks();
		return ResponseEntity.ok(tasks);
	}

	@GetMapping("/{id}")
	public Task getTaskById(@PathVariable int id) {
		return taskService.getTaskById(id)
				.orElseThrow(() -> new TaskExceptionHandler("Task is not present with this ID"));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable int id, @RequestBody Task task) {
		Task updateBook = taskService.getTaskById(id)
				.orElseThrow(() -> new TaskExceptionHandler("Task is not present with this ID"));
		task.setId(id);
		Task addBook = taskService.saveOrUpdate(task);
		return ResponseEntity.status(HttpStatus.CREATED).body(addBook);
	}

	@DeleteMapping("/{id}")
	public void deletebook(@PathVariable int id) {
		taskService.getTaskById(id).orElseThrow(() -> new TaskExceptionHandler("Book is not present with this ID"));
		taskService.deleteTaskById(id);
	}

	@ExceptionHandler(TaskExceptionHandler.class)
	public ResponseEntity<Object> handlerCustomException(TaskExceptionHandler ex) {
		ErrorResponse response = new ErrorResponse();
		response.setMessage(ex.getMessage());
		response.setTimeStamp(LocalDateTime.now());
		response.setErrorCode(HttpStatus.NOT_FOUND);

		return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
	}
}
