package com.demo.librarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.demo.librarymanagementsystem.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	

}
