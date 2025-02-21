package org.example.grandao.service;

import org.example.grandao.dtos.Ejemplar;
import org.example.grandao.dtos.Prestamo;
import org.example.grandao.dtos.Usuario;
import org.example.grandao.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }


    public Usuario getUsuarioById(int id) {
        return usuarioRepository.findById(id).orElseThrow(() ->
                new RuntimeException("No se encontro el Usuario con el id: " + id));
    }

    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }


    public Usuario actualizarUsuario(Integer id, Usuario usuario) {
        Usuario usuarioActualizado = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontro el Usuario con id: " + id));

        usuarioActualizado.setDni(usuario.getDni());
        usuarioActualizado.setNombre(usuario.getNombre());
        usuarioActualizado.setApellido(usuario.getApellido());
        usuarioActualizado.setEmail(usuario.getEmail());
        usuarioActualizado.setPassword(usuario.getPassword());
        usuarioActualizado.setTipo(usuario.getTipo());
        usuarioActualizado.setPenalizacionHasta(usuario.getPenalizacionHasta());

        return usuarioRepository.save(usuarioActualizado);
    }


    public void eliminarUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }
}
