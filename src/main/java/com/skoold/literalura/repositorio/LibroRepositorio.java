package com.skoold.literalura.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.skoold.literalura.modelo.Libro;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, Long> {
}
