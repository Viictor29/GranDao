package org.example.grandao.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.grandao.dtos.Libro;
import org.example.grandao.service.LibroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libro")
@CrossOrigin
@Slf4j
@RequiredArgsConstructor
public class LibroController {

    LibroService libroService;

    @GetMapping
    public ResponseEntity<List<Libro>> getLibros() {
        try {
            return ResponseEntity.ok(libroService.findAll());
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> getLibroByIsbn(@PathVariable String isbn) {
        try {
            return ResponseEntity.ok(libroService.getLibroByIsbn(isbn));
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/libro")
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro) {
        try {
            return ResponseEntity.ok(libroService.crearLibro(libro));
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/libro")
    public ResponseEntity<Libro> modificarLibro(@RequestBody Libro libro, @PathVariable String isbn) {
        try {
            return ResponseEntity.ok(libroService.actualizarLibro(isbn,libro));
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/libro")
    public ResponseEntity<String> eliminarLibro(@PathVariable String isbn) {
        try {
            libroService.eliminarLibro(isbn);
            return ResponseEntity.ok("Libro eliminado");
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
