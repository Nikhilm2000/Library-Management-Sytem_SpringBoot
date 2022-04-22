package com.demo.librarymanagementsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "publisher_contact")

//insert into publisher_contact values(6,'bookdiya@gmail.com',3546578779,6);
public class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	@Column
    private String email;
	@Column
    private Long phone;
	@Column
	private Long pub_id;
	public Contact(Long id, String email, Long phone, Long pub_id) {
		super();
		Id = id;
		this.email = email;
		this.phone = phone;
		this.pub_id = pub_id;
	}
	public Contact() {
		
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	public Long getPub_id() {
		return pub_id;
	}
	public void setPub_id(Long pub_id) {
		this.pub_id = pub_id;
	}
	
}
