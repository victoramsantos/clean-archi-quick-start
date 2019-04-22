package com.example.controller;

import com.example.dto.BookDto;
import com.example.dto.UserDto;
import com.example.exception.DataValidationException;
import com.example.exception.DtoParserException;
import com.example.service.BookService;
import com.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public @ResponseBody
    ResponseEntity save(@RequestBody UserDto dto) {
        try{
            dto.sanitize();
            UserDto created = UserDto.fromEntity(service.save(dto.toEntity()));
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch(DataValidationException exception){
            return ResponseEntity.status(exception.getHttpStatus()).body(exception.getMessage());
        } catch (DtoParserException exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity findById(@PathVariable Long id) {
        try{
            UserDto dto =  UserDto.fromEntity(service.findById(id));
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } catch (DataValidationException exception){
            return ResponseEntity.status(exception.getHttpStatus()).body(exception.getMessage());
        }
    }

    @GetMapping
    public @ResponseBody ResponseEntity<Set<UserDto>> list() {
        Set<UserDto> dtoSet = UserDto.fromEntity(service.list());
        return ResponseEntity.status(HttpStatus.OK).body(dtoSet);
    }
}
