package com.example.repository;

import com.example.entity.BookLoanDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookLoanRepository extends JpaRepository<BookLoanDB, Long> {
}
