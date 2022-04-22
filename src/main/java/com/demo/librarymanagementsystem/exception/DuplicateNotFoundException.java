package com.demo.librarymanagementsystem.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class DuplicateNotFoundException extends DataIntegrityViolationException{

	public DuplicateNotFoundException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}
