package org.example.grandao.service;

import org.example.grandao.dtos.Libro;
import org.example.grandao.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    private LibroRepository libroRepository;

    @Autowired
    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public List<Libro> findAll() {
        return libroRepository.findAll();
    }

    public Libro getLibroByIsbn(String Isbn) {
        return libroRepository.findById(Isbn).orElseThrow(() -> new RuntimeException("No existe el libro con ese isbn"));
    }

    public Libro crearLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    public Libro actualizarLibro(String isbn, Libro libro) {
        Libro updated = libroRepository.findById(isbn).orElseThrow(() -> new RuntimeException("No existe el libro con ese isbn"));
        updated.setTitulo(libro.getTitulo());
        updated.setAutor(libro.getAutor());
        return libroRepository.save(updated);
    }

    public void eliminarLibro(String isbn) {
        libroRepository.deleteById(isbn);
    }

}
