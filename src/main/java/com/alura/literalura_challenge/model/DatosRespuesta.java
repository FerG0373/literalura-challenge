package com.alura.literalura_challenge.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;


// Contiene la lista de libros, que es el campo "results" del JSON.
// La anotación @JsonIgnoreProperties(ignoreUnknown = true) nos permite ignorar cualquier campo del JSON que no esté definido en este record, evitando errores.
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosRespuesta(
        @JsonAlias("results") List<DatosLibro> resultados
) {}