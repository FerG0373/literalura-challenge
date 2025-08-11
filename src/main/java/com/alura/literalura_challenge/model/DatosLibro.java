package com.alura.literalura_challenge.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

// El record DatosLibro representa los datos de un libro individual.
// Con @JsonAlias, le decimos a Jackson que el campo "title" del JSON se mapea
// a nuestro campo "titulo", "authors" a "autores", etc.
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DatosAutor> autores,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("download_count") Integer numeroDeDescargas
) {}