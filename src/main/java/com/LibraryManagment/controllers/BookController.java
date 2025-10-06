package com.LibraryManagment.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.LibraryManagment.models.Book;
import com.LibraryManagment.services.BookService;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {
    @Autowired
    private final BookService bookService;

    @GetMapping("/getallbook")
    public ResponseEntity<List<Book>>getAllbooks() {
        return ResponseEntity.ok(bookService.allbook());
    }

   


    


    

}
