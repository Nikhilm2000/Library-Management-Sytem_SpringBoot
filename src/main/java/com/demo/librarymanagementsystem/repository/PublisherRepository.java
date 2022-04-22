package com.demo.librarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.librarymanagementsystem.entity.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

}
