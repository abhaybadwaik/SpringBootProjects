package com.example.Crud_Revision.service;

import com.example.Crud_Revision.entity.BookEntity;
import com.example.Crud_Revision.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

//    CREATE BOOKS
    public BookEntity addBook(BookEntity book){
        return bookRepository.save(book);
    }
//    ADD ALL BOOK
    public List<BookEntity> addAllBooks(List<BookEntity> book){
        return bookRepository.saveAll(book);
    }
//    GET_BOOK BY ID
    public BookEntity getBookById(int id){
        return bookRepository.findById(id).orElse(null);
    }
    public String deleteById(int id){
         bookRepository.deleteById(id);
         return "Your id No. " + id +" is deleted";
    }
}
