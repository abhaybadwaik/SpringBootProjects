package com.example.Crud_Revision.controller;

import com.example.Crud_Revision.entity.BookEntity;
import com.example.Crud_Revision.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    BookService bookService;

//    CREATE
    @PostMapping("/addBook")
    public BookEntity addBook(@RequestBody BookEntity book){
        return bookService.addBook(book);
    }
    @PostMapping("/addAllBooks")
    public List<BookEntity> addALl(@RequestBody List<BookEntity> book){
        return bookService.addAllBooks(book);
    }
    @GetMapping("/getBookByID/{id}")
    public BookEntity getBookById(@PathVariable int id){
        System.out.println(" in controller");
        return bookService.getBookById(id);
    }
    @DeleteMapping("/deleteByID")
    public String deleteByID(@RequestParam("id") int id){
        return bookService.deleteById(id);
    }

}
