package com.example.Crud_Revision.service;

import com.example.Crud_Revision.entity.BookEntity;
import com.example.Crud_Revision.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

//    CREATE
    public BookEntity addBook(BookEntity book){
        return bookRepository.save(book);
    }
//    ADDALL
    public List<BookEntity> addAllBooks(List<BookEntity> book){
        return bookRepository.saveAll(book);
    }
}
