package com.demo.librarymanagementsystem.service.impl;

import java.util.List;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.demo.librarymanagementsystem.entity.Author;
import com.demo.librarymanagementsystem.entity.Student;
import com.demo.librarymanagementsystem.exception.NotFoundException;
import com.demo.librarymanagementsystem.repository.AuthorRepository;
import com.demo.librarymanagementsystem.repository.StudentRepository;
import com.demo.librarymanagementsystem.service.AuthorService;
import com.demo.librarymanagementsystem.service.StudentService;


@Service
public class StudentServiceImpl implements StudentService {

	private final StudentRepository studentRepository;

	public StudentServiceImpl(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<Student> findAllStudents() {
		return studentRepository.findAll();
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public Student findStudentById(Long id) {
		return studentRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Student not found with ID %d", id)));
	}

	@Override
	public void createStudent(Student student) {
		try {
			studentRepository.save(student);
		}catch(DataIntegrityViolationException e) {
			e.getMessage();
		}
	}

	@Override
	public void updateStudent(Student student) {
		
		studentRepository.save(student);
	}

	@Override
	public void deleteStudent(Long id) {
		final Student student = studentRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Author not found with ID %d", id)));

		studentRepository.deleteById(student.getId());
	}
	
//	@Override
//	public Page<Author> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
//		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
//			Sort.by(sortField).descending();
//		
//		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
//		return this.authorRepository.findAll(pageable);
//	}

}
