package com.LibraryManagment.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.LibraryManagment.models.Book;

@Service
public interface BookService {
    List<Book> allbook();

    Book getBookById(Long id);

    void addBook(Book book);

    Book updateBook(Long id, Book book);

    void deleteBook(Long id);

}
