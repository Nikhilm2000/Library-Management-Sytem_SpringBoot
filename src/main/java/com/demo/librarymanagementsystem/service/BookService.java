package com.demo.librarymanagementsystem.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.demo.librarymanagementsystem.entity.Book;

public interface BookService {

	public List<Book> findAllBooks();
	
	public List<Book> searchBooks(String keyword);

	public Book findBookById(Long id);

	public void createBook(Book book);

	public void updateBook(Book book);

	public void deleteBook(Long id);
	
	Page<Book> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}
