package com.example.controller;

import com.example.dto.BookDto;
import com.example.dto.BookLoanDto;
import com.example.dto.GuiaDto;
import com.example.entities.BookLoan;
import com.example.exception.DataValidationException;
import com.example.exception.DtoParserException;
import com.example.service.BookLoanService;
import com.example.service.BookService;
import com.example.service.GuiaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/booksloan")
public class BookLoanController {

    private final BookLoanService service;
    private final BookService bookService;
    private final GuiaService guiaService;

    public BookLoanController(BookLoanService service, BookService bookService, GuiaService guiaService) {
        this.service = service;
        this.bookService = bookService;
        this.guiaService = guiaService;
    }

    @PostMapping
    public @ResponseBody ResponseEntity save(@RequestBody BookLoanDto dto) {
        try{
            dto.sanitize();

            GuiaDto guiaDto = GuiaDto.fromEntity(guiaService.findById(dto.getGuiaId()));
            dto.setGuia(guiaDto);

            BookDto bookDto = BookDto.fromEntity(bookService.findById(dto.getBookId()));
            dto.setBook(bookDto);

            BookLoan created = service.save(dto.toEntity());
            BookLoanDto callback = BookLoanDto.fromEntity(created);

            return ResponseEntity.status(HttpStatus.CREATED).body(callback);
        } catch(DataValidationException exception){
            return ResponseEntity.status(exception.getHttpStatus()).body(exception.getMessage());
        } catch (DtoParserException exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }


    @GetMapping
    public @ResponseBody ResponseEntity<Set<BookLoanDto>> list() {
        Set<BookLoanDto> dtoSet = BookLoanDto.fromEntity(service.list());
        return ResponseEntity.status(HttpStatus.OK).body(dtoSet);
    }
}
