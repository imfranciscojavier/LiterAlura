package com.skoold.literalura.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skoold.literalura.modelo.Libro;
import com.skoold.literalura.repositorio.LibroRepositorio;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GutendexServicio {

    @Autowired
    private LibroRepositorio libroRepositorio;

    public void agregarLibro(String titulo, String autor, String idioma, int anio) {
        Libro libro = new Libro(titulo, autor, idioma, anio);
        libroRepositorio.save(libro);
        System.out.println("Libro agregado con éxito: " + titulo);
    }

    public List<Libro> obtenerLibros() {
        return libroRepositorio.findAll();
    }

    public List<Libro> buscarLibroPorTitulo(String titulo) {
        return libroRepositorio.findAll().stream()
                .filter(libro -> libro.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<String> listarAutores() {
        return libroRepositorio.findAll().stream()
                .map(Libro::getAutor)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<String> listarAutoresPorPeriodo(int inicio, int fin) {
        return libroRepositorio.findAll().stream()
                .filter(libro -> libro.getAnioPublicacion() >= inicio && libro.getAnioPublicacion() <= fin)
                .map(Libro::getAutor)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Libro> listarLibrosPorIdioma(String idioma) {
        return libroRepositorio.findAll().stream()
                .filter(libro -> libro.getIdioma().equalsIgnoreCase(idioma))
                .collect(Collectors.toList());
    }
}
