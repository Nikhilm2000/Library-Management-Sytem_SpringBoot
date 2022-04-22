package com.demo.librarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.librarymanagementsystem.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long>{

}
