package com.LibraryManagment.services;

import org.springframework.stereotype.Service;

import com.LibraryManagment.models.IssueRecord;

@Service
public interface IssuerecordService {
    public IssueRecord issueRecord(Long bookId);

    public IssueRecord returnTheBook(Long issueRecordId);

}
