package org.example.grandao.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.grandao.dtos.Prestamo;
import org.example.grandao.service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prestamo")
@CrossOrigin
@Slf4j
@RequiredArgsConstructor

public class PrestamoController {

    private PrestamoService prestamoService;

    @GetMapping
    public ResponseEntity<List<Prestamo>> getPrestamos() {
        try {
            List<Prestamo> listaPrestamos = prestamoService.findAll();
            return ResponseEntity.ok(listaPrestamos);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> getPrestamoById(@PathVariable Integer id) {
        try {
            Prestamo prestamo = prestamoService.getPrestamoById(id);
            return ResponseEntity.ok(prestamo);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Prestamo> crearPrestamo(@Valid @RequestBody Prestamo prestamo) {
        try {
            Prestamo prestamoPersistido = prestamoService.crearPrestamo(prestamo);
            return ResponseEntity.status(HttpStatus.CREATED).body(prestamoPersistido);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prestamo> actualizarPrestamo(@PathVariable Integer id, @Valid @RequestBody Prestamo prestamo) {
        try {
            Prestamo prestamoPersistido = prestamoService.actualizarPrestamo(id, prestamo);
            return ResponseEntity.ok(prestamoPersistido);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Prestamo> eliminarPrestamo(@PathVariable Integer id) {
        try {
            prestamoService.eliminarPrestamo(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
