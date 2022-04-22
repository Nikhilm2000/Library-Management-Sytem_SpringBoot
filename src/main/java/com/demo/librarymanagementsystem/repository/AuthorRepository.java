package com.demo.librarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.librarymanagementsystem.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
	

}
