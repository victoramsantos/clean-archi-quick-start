package com.example;

import com.example.dto.BookDto;
import com.example.entities.Book;
import com.example.exception.DtoParserException;
import com.example.exception.DataValidationException;
import com.example.service.BookService;
import com.example.service.GenericService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/books")
public class BookController {
    private final GenericService<Book> service;
    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping
    public @ResponseBody
    ResponseEntity save(@RequestBody BookDto dto) {
        try{
            dto.sanitize();
            BookDto created = BookDto.fromEntity(service.save(dto.toEntity()));
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch(DataValidationException exception){
            return ResponseEntity.status(exception.getHttpStatus()).body(exception.getMessage());
        } catch (DtoParserException exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @PutMapping
    public @ResponseBody ResponseEntity update(@RequestBody BookDto dto) {
        try{
            dto.sanitize();
            Book result = service.update(dto.toEntity());
            return ResponseEntity.status(HttpStatus.OK).body(dto.fromEntity(result));
        } catch (DataValidationException exception){
            return ResponseEntity.status(exception.getHttpStatus()).body(exception.getMessage());
        } catch (DtoParserException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity delete(@PathVariable Long id) {
        try{
            service.delete(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (DataValidationException exception){
            return ResponseEntity.status(exception.getHttpStatus()).body(exception.getMessage());
        }
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity findById(@PathVariable Long id) {
        try{
            BookDto dto =  BookDto.fromEntity(service.findById(id));
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } catch (DataValidationException exception){
            return ResponseEntity.status(exception.getHttpStatus()).body(exception.getMessage());
        }
    }

    @GetMapping
    public @ResponseBody ResponseEntity<Set<BookDto>> list() {
        Set<BookDto> dtoSet = BookDto.fromEntity(service.list());
        return ResponseEntity.status(HttpStatus.OK).body(dtoSet);
    }
}
