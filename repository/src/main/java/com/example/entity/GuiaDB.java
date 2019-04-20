package com.example.entity;

import com.example.entities.Guia;
import lombok.*;

import javax.persistence.*;

@Builder
@EqualsAndHashCode(of="id")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class GuiaDB {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "guia_generator")
    @SequenceGenerator(name="guia_generator", sequenceName = "guia_seq")
    @Getter
    @Setter
    private Long id;

    @Getter @Setter private String name;

    public Guia toEntity() {
        return Guia.builder()
                .id(id)
                .name(name)
                .build();
    }

    public static GuiaDB fromEntity(Guia guia) {
        return builder()
                .id(guia.getId())
                .name(guia.getName())
                .build();
    }
}
