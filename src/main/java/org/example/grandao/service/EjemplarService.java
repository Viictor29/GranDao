package org.example.grandao.service;

import org.example.grandao.dtos.Ejemplar;
import org.example.grandao.repository.EjemplarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EjemplarService {

    private final EjemplarRepository ejemplarRepository;

    @Autowired
    public EjemplarService(EjemplarRepository ejemplarRepository) {
        this.ejemplarRepository = ejemplarRepository;
    }

    public List<Ejemplar> findAll() {
        return ejemplarRepository.findAll();
    }

    public Ejemplar getEjemplarById(int id) {
        return ejemplarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el ejemplar con ese ID"));
    }

    public Ejemplar crearEjemplar(Ejemplar ejemplar) {
        return ejemplarRepository.save(ejemplar);
    }

    public Ejemplar actualizarEjemplar(int id, Ejemplar ejemplar) {
        Ejemplar updated = ejemplarRepository.findById(id).orElseThrow(() -> new RuntimeException("No existe el ejemplar con ese ID"));
        updated.setEstado(ejemplar.getEstado());
        return ejemplarRepository.save(updated);
    }

    public void eliminarEjemplar(int id) {
        ejemplarRepository.deleteById(id);
    }
}

