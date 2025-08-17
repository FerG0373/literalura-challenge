package com.alura.literalura_challenge.repository;

import com.alura.literalura_challenge.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    // Autores con nacimiento <= año y fallecimiento >= año (seguían vivos en ese año)
    List<Autor> findByFechaDeNacimientoLessThanEqualAndFechaDeFallecimientoGreaterThanEqual(
            Integer nacimiento, Integer fallecimiento);

    // Autores con nacimiento <= año y sin fallecimiento registrado (aún vivos)
    List<Autor> findByFechaDeNacimientoLessThanEqualAndFechaDeFallecimientoIsNull(Integer nacimiento);
}
