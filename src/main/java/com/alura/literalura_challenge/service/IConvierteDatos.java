package com.alura.literalura_challenge.service;

// Esta interfaz define el contrato para el servicio de conversión de datos.
public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}