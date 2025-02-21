package org.example.grandao.dtos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;


@Data
@Getter
@Setter
@Entity
@Table(name = "libro")
public class Libro {
    @Id
    @Size(max = 20)
    @Column(name = "isbn", nullable = false, length = 20)
    private String isbn;

    @Size(max = 200)
    @NotNull(message = "El titulo no puede ser nulo")
    @Column(name = "titulo", nullable = false, length = 200)
    @Pattern(regexp =  "^[A-Z][a-zA-Z0-9 ]*$" , message = "La primera letra del título debe ser en mayusculas y solo caracteres alfanuméricos")
    private String titulo;

    @Size(max = 100)
    @NotNull(message = "El autor no puede ser nulo")
    @Column(name = "autor", nullable = false, length = 100)
    @Pattern(regexp = "^[A-Z][a-zA-Z0-9 ]*$" , message = "La primera letra del autor debe ser en mayusculas y solo caracteres alfanuméricos")
    private String autor;

    @OneToMany(mappedBy = "isbn")
    private Set<Ejemplar> ejemplars = new LinkedHashSet<>();

}