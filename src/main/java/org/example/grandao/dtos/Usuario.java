package org.example.grandao.dtos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 15)
    @NotNull(message = "El campo dni no puede ser nulo")
    @Column(name = "dni", nullable = false, length = 15)
    @Pattern(regexp = "^[0-9]{8}[A-Za-z]$", message = "El DNI debe ser válido")
    private String dni;

    @Size(max = 50)
    @Column(name = "nombre", length = 50)
    @Pattern(regexp =  "^[A-Z][a-zA-Z0-9 ]*$" , message = "El nombre solo debe tener valores alfanuméricos")
    private String nombre;

    @Size(max = 255)
    @Column(name = "email")
    @Pattern(regexp = "[A-Za-z0-9]{1,50}@(gmail.com|gmail.es)" , message = "El mail debe acabar en gmail.com o gmail.es")
    private String email;

    @Size(max = 12)
    @Column(name = "password", length = 12)
    @Pattern(regexp = "^[A-Za-z0-9]{4,12}$", message = "La contraseña debe ser alfanumérica y tener entre 4 y 12 caracteres")
    private String password;

    @Size(max = 255)
    @Column(name = "tipo")
    @Pattern(regexp = "^(Administrador|Normal)$", message = "El estado debe ser 'Administrador', 'Normal'")
    private String tipo;

    @Column(name = "penalizacionHasta")
    private LocalDate penalizacionHasta;

    @Size(max = 255)
    @Column(name = "apellido")
    private String apellido;

    @OneToMany(mappedBy = "usuario")
    private Set<Prestamo> prestamos = new LinkedHashSet<>();


}