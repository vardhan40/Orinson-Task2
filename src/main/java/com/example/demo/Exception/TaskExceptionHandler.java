package com.example.demo.Exception;

public class TaskExceptionHandler extends RuntimeException{
	private static final long serialVersionID = 1L;

	public TaskExceptionHandler(String message) {
		super(message);
	}
}
