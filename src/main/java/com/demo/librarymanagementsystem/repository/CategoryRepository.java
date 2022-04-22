package com.demo.librarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.demo.librarymanagementsystem.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
