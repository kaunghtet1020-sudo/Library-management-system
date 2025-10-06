package com.LibraryManagment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LibraryManagment.dots.BookDto;
import com.LibraryManagment.models.Book;
import com.LibraryManagment.services.BookService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
           @Autowired
    private final BookService bookService;

     @GetMapping("/getbookbyid/{id}")
    public ResponseEntity<Book>getBookbyid(@PathVariable Long id) {
        return  ResponseEntity.ok(bookService.getBookById(id));
    }

    @PostMapping("/addbook")

    public ResponseEntity<Book>addbooks(@RequestBody  BookDto bookDto) {
      Book book=new Book();
      book.setTitle(bookDto.getTitle());
      book.setAuthor(bookDto.getAuthor());
      book.setIsbn(bookDto.getIsbn());
      book.setQuantity(bookDto.getQuantity());
      book.setIsAvailable(bookDto.getIsAvailable());
      bookService.addBook(book);
      return ResponseEntity.ok(book);
    }

     @PutMapping("/updatebook/{id}")
    
    public ResponseEntity<Book>updatebook(@RequestBody  BookDto bookDto,@PathVariable Long id) {
      Book book=new Book();
      book.setTitle(bookDto.getTitle());
      book.setAuthor(bookDto.getAuthor());
      book.setIsbn(bookDto.getIsbn());
      book.setQuantity(bookDto.getQuantity());
      book.setIsAvailable(bookDto.getIsAvailable());
      return ResponseEntity.ok(bookService.updateBook(id, book));
    }
}
