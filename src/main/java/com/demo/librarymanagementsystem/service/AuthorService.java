package com.demo.librarymanagementsystem.service;

import java.util.List;


import org.springframework.data.domain.Page;

import com.demo.librarymanagementsystem.entity.Author;
import com.demo.librarymanagementsystem.entity.Student;


public interface AuthorService {

	public List<Author> findAllAuthors();

	public Author findAuthorById(Long id);

	public void createAuthor(Author author);

	public void updateAuthor(Author author);

	public void deleteAuthor(Long id);

	//Page<Author> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

	

}
