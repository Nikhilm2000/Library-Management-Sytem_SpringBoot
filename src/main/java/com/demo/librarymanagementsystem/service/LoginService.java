package com.demo.librarymanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.librarymanagementsystem.entity.Login;
import com.demo.librarymanagementsystem.repository.LoginRepository;

@Service
public class LoginService {
	@Autowired

	LoginRepository repository;
	
	public void save(Login login) {
		repository.save(login);
	}

	public Login login(String username, String password) {
		Login user = repository.findByUsernameAndPassword(username, password);
		return user;
	}

}
