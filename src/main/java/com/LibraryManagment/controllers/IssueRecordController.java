package com.LibraryManagment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.LibraryManagment.models.IssueRecord;
import com.LibraryManagment.services.IssuerecordService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;





@RestController
@RequestMapping("/issuerecords")
@AllArgsConstructor
public class IssueRecordController {
      @Autowired 
      private final IssuerecordService issuerecordService;

      @PostMapping("/issuethebook/{bookId}")
      public ResponseEntity<IssueRecord>issueThebook(@PathVariable Long bookId) {
         
          
          return ResponseEntity.ok(issuerecordService.issueRecord(bookId));
      }

      @PostMapping("path")
      public ResponseEntity<IssueRecord>returnthebook(@PathVariable Long issueRecordId) {
          return ResponseEntity.ok(issuerecordService.returnTheBook(issueRecordId));
      }
      
      
}
