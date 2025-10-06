package com.LibraryManagment.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LibraryManagment.models.Book;
import com.LibraryManagment.repos.BookRepo;
import com.LibraryManagment.services.BookService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class BookImpl implements BookService {
    @Autowired
    private final BookRepo bookRepo;

    @Override
    public List<Book> allbook() {
         return  bookRepo.findAll();
    }

    @Override
    public Book getBookById(Long id) {
         return bookRepo.findById(id).orElseThrow(()->new RuntimeException("Book not found"));
    }

    @Override
    public void addBook(Book book) {
         bookRepo.save(book);
    }

    @Override
    public Book updateBook(Long id, Book book) {
         Book dBook=getBookById(id);
         if(dBook!=null){
            dBook.setTitle(book.getTitle());
            dBook.setAuthor(book.getAuthor());
            dBook.setIsbn(book.getIsbn());
            dBook.setIsAvailable(book.getIsAvailable());
            dBook.setQuantity(book.getQuantity());
          return  bookRepo.save(dBook);
         }
         return null;
    }

    @Override
    public void deleteBook(Long id) {
        Book book=getBookById(id);
        if (book!=null) {
            bookRepo.deleteById(id);
        }
    }

}
