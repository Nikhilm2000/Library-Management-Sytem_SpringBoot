package com.demo.librarymanagementsystem.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.librarymanagementsystem.entity.Book;
import com.demo.librarymanagementsystem.service.AuthorService;
import com.demo.librarymanagementsystem.service.BookService;
import com.demo.librarymanagementsystem.service.CategoryService;
import com.demo.librarymanagementsystem.service.PublisherService;

@Controller
public class BookController {

	private final BookService bookService;
	private final AuthorService authorService;
	private final CategoryService categoryService;
	private final PublisherService publisherService;

	@Autowired
	public BookController(BookService bookService, AuthorService authorService, CategoryService categoryService,
			PublisherService publisherService) {
		this.bookService = bookService;
		this.authorService = authorService;
		this.categoryService = categoryService;
		this.publisherService = publisherService;
	}

	@RequestMapping("/books")
	public String findAllBooks(Model model) {
		final List<Book> books = bookService.findAllBooks();

		model.addAttribute("books", books);
		findPaginated(1, "isbn", "asc", model);	
		return "list-books";
	}
	@RequestMapping("/searchBook")
	public String searchBook(@Param("keyword") String keyword, Model model) {
		final List<Book> books = bookService.searchBooks(keyword);

		model.addAttribute("books", books);
		model.addAttribute("keyword", keyword);
		return "book-search";
	}
	
	@RequestMapping("/bookCategory")
	public String searchCategory(Model model) {
		final List<Book> books = bookService.findAllBooks();

		model.addAttribute("books", books);
		return "book-category";
	}
	

	@RequestMapping("/book/{id}")
	public String findBookById(@PathVariable("id") Long id, Model model) {
		final Book book = bookService.findBookById(id);

		model.addAttribute("book", book);
		return "list-book";
	}

	
	@GetMapping("/add")
	public String showCreateForm(Book book, Model model) {
		model.addAttribute("categories", categoryService.findAllCategories());
		model.addAttribute("authors", authorService.findAllAuthors());
		model.addAttribute("publishers", publisherService.findAllPublishers());
		return "add-book";
	}

	@RequestMapping("/add-book")
	public String createBook(Book book, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-book";
		}

		bookService.createBook(book);
		model.addAttribute("book", bookService.findAllBooks());
		return "redirect:/books";
	}

	@GetMapping("/update/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		final Book book = bookService.findBookById(id);

		model.addAttribute("book", book);
		return "update-book";
	}

	@RequestMapping("/update-book/{id}")
	public String updateBook(@PathVariable("id") Long id, Book book, BindingResult result, Model model) {
		if (result.hasErrors()) {
			book.setId(id);
			return "update-book";
		}

		bookService.updateBook(book);
		model.addAttribute("book", bookService.findAllBooks());
		return "redirect:/books";
	}

	@RequestMapping("/remove-book/{id}")
	public String deleteBook(@PathVariable("id") Long id, Model model) {
		bookService.deleteBook(id);

		model.addAttribute("book", bookService.findAllBooks());
		return "redirect:/books";
	}
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Book> page = bookService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Book> listBooks = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listBooks", listBooks);
		return "list-books";
	}


}
