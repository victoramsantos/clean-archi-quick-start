package com.example.service;

import com.example.entities.BookLoan;
import com.example.repository.DataAccess;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookLoanServiceTest {

    @Mock private DataAccess<BookLoan> dataAccess;
    private BookLoanService service;

    @Test public void initialTest(){

    }
}
