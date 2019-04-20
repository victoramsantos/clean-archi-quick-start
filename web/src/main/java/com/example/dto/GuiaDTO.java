package com.example.dto;

import com.example.entities.Guia;
import com.example.exception.DtoParserException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuiaDTO {

    private Long id;
    private String name;

    public static Set<GuiaDTO> fromEntity(Set<Guia> guias) {
        return guias
                .stream()
                .map(GuiaDTO::fromEntity)
                .collect(toSet());
    }

    public static GuiaDTO fromEntity(Guia guia) {
        return builder()
                .id(guia.getId())
                .name(guia.getName())
                .build();
    }

    public Guia toEntity() {
        return Guia.builder()
                .id(id)
                .name(name)
                .build();
    }

    public Boolean sanitize() throws DtoParserException {
        if(name == null) throw new DtoParserException("Name can't be null");
        return true;
    }
}

