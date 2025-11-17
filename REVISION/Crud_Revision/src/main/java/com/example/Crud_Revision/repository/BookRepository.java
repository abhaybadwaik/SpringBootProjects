package com.example.Crud_Revision.repository;

import com.example.Crud_Revision.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity,Integer> {
}
