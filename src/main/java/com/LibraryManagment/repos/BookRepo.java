package com.LibraryManagment.repos;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.LibraryManagment.models.Book;

@Repository
public interface BookRepo extends JpaRepository<Book,Long> {
 
}
