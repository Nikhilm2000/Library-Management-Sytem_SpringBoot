package com.demo.librarymanagementsystem.entity;

import java.sql.Date;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Student{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	String name;
	Date dob;
	Long phno;
	String address;
	Date doj;
	String rentbookname;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<Book> books = new HashSet<Book>();
	public Student() {
		super();
	}
	public Long getId(){
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Long getPhno() {
		return phno;
	}
	public void setPhno(Long phno) {
		this.phno = phno;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getDoj() {
		return doj;
	}
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	public String getRentbookname() {
		return rentbookname;
	}
	public void setRentbookname(String rentbookname) {
		this.rentbookname = rentbookname;
	}
	public Set<Book> getBooks() {
		return books;
	}
	public void setBooks(Set<Book> books) {
		this.books = books;
	}
	public Student(Long id, String name, Date dob, Long phno, String address, Date doj, String rentbookname,
			Set<Book> books) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.phno = phno;
		this.address = address;
		this.doj = doj;
		this.rentbookname = rentbookname;
		this.books = books;
	}
	
}
