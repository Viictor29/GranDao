package org.example.grandao.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.grandao.dtos.Ejemplar;
import org.example.grandao.dtos.Libro;
import org.example.grandao.service.EjemplarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ejemplar")
@CrossOrigin
@Slf4j
@RequiredArgsConstructor
public class EjemplarController {

    EjemplarService ejemplarService;

    @GetMapping
    public ResponseEntity<List<Ejemplar>> getEjemplares() {
        try {
            return ResponseEntity.ok(ejemplarService.findAll());
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ejemplar> getEjemplarPorId(@PathVariable int id) {
        try {
            return ResponseEntity.ok(ejemplarService.getEjemplarById(id));
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/ejemplar")
    public ResponseEntity<Ejemplar> crearEjemplar(@RequestBody Ejemplar ejemplar) {
        try {
            return ResponseEntity.ok(ejemplarService.crearEjemplar(ejemplar));
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/ejemplar")
    public ResponseEntity<Ejemplar> modificarEjemplar(@RequestBody Ejemplar ejemplar, @PathVariable int id) {
        try {
            return ResponseEntity.ok(ejemplarService.actualizarEjemplar(id,ejemplar));
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/ejemplar")
    public ResponseEntity<String> eliminarEjemplar(@PathVariable int id) {
        try {
            ejemplarService.eliminarEjemplar(id);
            return ResponseEntity.ok("Ejemplar eliminado");
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
