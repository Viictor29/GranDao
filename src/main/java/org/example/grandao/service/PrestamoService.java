package org.example.grandao.service;

import org.example.grandao.dtos.Ejemplar;
import org.example.grandao.dtos.Prestamo;
import org.example.grandao.dtos.Usuario;
import org.example.grandao.repository.EjemplarRepository;
import org.example.grandao.repository.PrestamoRepository;
import org.example.grandao.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrestamoService {

    private final UsuarioRepository usuarioRepository;
    private final EjemplarRepository ejemplarRepository;
    private PrestamoRepository prestamoRepository;

    @Autowired
    public PrestamoService(PrestamoRepository prestamoRepository, UsuarioRepository usuarioRepository, EjemplarRepository ejemplarRepository) {
        this.prestamoRepository = prestamoRepository;
        this.usuarioRepository = usuarioRepository;
        this.ejemplarRepository = ejemplarRepository;
    }

    public List<Prestamo> findAll() {
        return prestamoRepository.findAll();
    }

    public Prestamo getPrestamoById(int id) {
        return prestamoRepository.findById(id).orElseThrow(() ->
                new RuntimeException("No se encontro el prestamo con el id: " + id));
    }

    public Prestamo crearPrestamo(Prestamo cliente) {
        return prestamoRepository.save(cliente);
    }

    public Prestamo actualizarPrestamo(Integer id, Prestamo prestamo) {
        Prestamo prestamoActualizado = prestamoRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontro el prestamo con id: " + id));
        Usuario usuario = usuarioRepository.findById(prestamo.getUsuario().getId()).get();
        Ejemplar ejemplar = ejemplarRepository.findById(prestamo.getEjemplar().getId()).get();

        prestamoActualizado.setId(prestamo.getId());
        prestamoActualizado.setUsuario(usuario);
        prestamoActualizado.setEjemplar(ejemplar);
        prestamoActualizado.setFecha_inicio(prestamo.getFecha_inicio());
        prestamoActualizado.setFecha_devolucion(prestamo.getFecha_devolucion());

        return prestamoRepository.save(prestamoActualizado);
    }

    public void eliminarPrestamo(Integer id) {
        prestamoRepository.deleteById(id);
    }
}
