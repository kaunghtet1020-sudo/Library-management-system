package com.LibraryManagment.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.LibraryManagment.models.IssueRecord;

@Repository
public interface IssuerecordRepo extends JpaRepository<IssueRecord,Long> {

}
