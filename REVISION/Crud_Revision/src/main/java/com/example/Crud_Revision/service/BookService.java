package com.example.Crud_Revision.service;

import com.example.Crud_Revision.entity.BookEntity;
import com.example.Crud_Revision.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.security.PublicKey;
import java.util.List;
import java.util.Optional;

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
    public BookEntity updateById(BookEntity book,int id){
        Optional<BookEntity> existingBook = bookRepository.findById(id);
        if (existingBook.isPresent()){
            BookEntity bookEntity = existingBook.get();
            System.out.println(bookEntity);
            bookEntity.setTitle(book.getTitle());
            bookEntity.setAuthor(book.getAuthor());
            bookEntity.setPrice(book.getPrice());

            BookEntity save = bookRepository.save(book);
            System.out.println(save);
            return save;
        }
        return null;
    }
}
