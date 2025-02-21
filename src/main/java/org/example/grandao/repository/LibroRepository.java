package org.example.grandao.repository;

import org.example.grandao.dtos.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, String> {
}
