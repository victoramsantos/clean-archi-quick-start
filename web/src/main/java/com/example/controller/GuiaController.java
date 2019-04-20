package com.example.controller;

import com.example.dto.GuiaDto;
import com.example.dto.GenericListDTO;
import com.example.entities.Guia;
import com.example.exception.DtoParserException;
import com.example.exception.DataValidationException;
import com.example.service.GuiaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/guias")
public class GuiaController {

    private final GuiaService guiaService;

    public GuiaController(GuiaService guiaService) {
        this.guiaService = guiaService;
    }

    @PostMapping
    public @ResponseBody ResponseEntity save(@RequestBody GuiaDto dto) {
        try{
            dto.sanitize();
            GuiaDto created = GuiaDto.fromEntity(guiaService.save(dto.toEntity()));
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch(DataValidationException exception){
            return ResponseEntity.status(exception.getHttpStatus()).body(exception.getMessage());
        } catch(DtoParserException exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @PutMapping
    public @ResponseBody ResponseEntity update(@RequestBody GuiaDto guiaDTO) {
        try{
            Guia result = guiaService.update(guiaDTO.toEntity());
            return ResponseEntity.status(HttpStatus.OK).body(guiaDTO.fromEntity(result));
        }catch (DataValidationException exception){
            return ResponseEntity.status(exception.getHttpStatus()).body(exception.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity delete(@PathVariable Long id) {
        try{
            guiaService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (DataValidationException exception){
            return ResponseEntity.status(exception.getHttpStatus()).body(exception.getMessage());
        }
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity findById(@PathVariable Long id) {
        try{
            GuiaDto guiaDTO =  GuiaDto.fromEntity(guiaService.findById(id));
            return ResponseEntity.status(HttpStatus.OK).body(guiaDTO);
        }catch (DataValidationException exception){
            return ResponseEntity.status(exception.getHttpStatus()).body(exception.getMessage());
        }
    }

    @GetMapping
    public @ResponseBody ResponseEntity<GenericListDTO<GuiaDto>> list() {
        Set<Guia> set =  guiaService.list();

        GenericListDTO<GuiaDto> response = new GenericListDTO(GuiaDto.fromEntity(set));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
