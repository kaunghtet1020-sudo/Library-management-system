package com.LibraryManagment.impls;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LibraryManagment.models.Book;
import com.LibraryManagment.models.IssueRecord;
import com.LibraryManagment.models.User;
import com.LibraryManagment.repos.BookRepo;
import com.LibraryManagment.repos.IssuerecordRepo;
import com.LibraryManagment.repos.UserRepo;
import com.LibraryManagment.services.IssuerecordService;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class IssueRecordImpl implements IssuerecordService {
    @Autowired
    private final BookRepo bookRepo;
    @Autowired
    private final UserRepo userRepo;
    @Autowired
    private final IssuerecordRepo issuerecordRepo;

    @Override
    public IssueRecord issueRecord(Long bookId) {
        Book book = bookRepo.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.getQuantity() <= 0 || !book.getIsAvailable()) {
            throw new RuntimeException("Book is not available");

        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepo.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        IssueRecord issueRecord = new IssueRecord();
        issueRecord.setIssueDate(null);
        issueRecord.setDueDate(null);
        issueRecord.setIsReturned(false);
        issueRecord.setUser(user);
        issueRecord.setBook(book);
        book.setQuantity(book.getQuantity() - 1);
        if (book.getQuantity() == 0) {
            book.setIsAvailable(false);

        }
        bookRepo.save(book);
        return issuerecordRepo.save(issueRecord);
    }

    @Override
    public IssueRecord returnTheBook(Long issueRecordId) {
        IssueRecord issueRecord = issuerecordRepo.findById(issueRecordId)
                .orElseThrow(() -> new RuntimeException("Issue record not found"));
        if (issueRecord.getIsReturned()) {
            throw new RuntimeException("Book already returned");
        }
        Book book = issueRecord.getBook();
        book.setQuantity(book.getQuantity() + 1);
        book.setIsAvailable(true);
        bookRepo.save(book);
        issueRecord.setReturnDate(LocalDate.now());
        issueRecord.setIsReturned(true);
        return issuerecordRepo.save(issueRecord);
    }

}
