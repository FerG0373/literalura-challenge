package com.alura.literalura_challenge.repository;

import com.alura.literalura_challenge.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}
