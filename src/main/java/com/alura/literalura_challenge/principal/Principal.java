package com.alura.literalura_challenge.principal;

import com.alura.literalura_challenge.model.DatosLibro;
import com.alura.literalura_challenge.model.DatosRespuesta;
import com.alura.literalura_challenge.service.ConsumoAPI;
import com.alura.literalura_challenge.service.ConvierteDatos;

import java.util.Optional;
import java.util.Scanner;


public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();


    private void buscarLibroPorTitulo() {
        System.out.println("Ingresar nombre del libro:");
        var tituloLibro = teclado.nextLine();
        var urlConsulta = URL_BASE + "?search=" + tituloLibro.replace(" ", "+");

        try {
            var json = consumoApi.obtenerDatos(urlConsulta);
            var datosBusqueda = conversor.obtenerDatos(json, DatosRespuesta.class);

            Optional<DatosLibro> libroBuscado = datosBusqueda.resultados().stream()
                    .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                    .findFirst();

            if(libroBuscado.isPresent()) {
                System.out.println("\n¡LIBRO ENCONTRADO!");
                mostrarLibroFormateado(libroBuscado.get());
            } else {
                System.out.println("Libro NO encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Error al buscar el libro: " + e.getMessage());
        }
    }

    private void mostrarLibroFormateado(DatosLibro libro) {
        System.out.println("--- LIBRO ---");
        System.out.println("Título: " + libro.titulo());

        // Formatear autores
        if (!libro.autor().isEmpty()) {
            System.out.println("Autor(es):");
            libro.autor().forEach(autor -> {
                System.out.println("  - " + autor.nombre() +
                        " (" + autor.fechaDeNacimiento() +
                        " - " + autor.fechaDeFallecimiento() + ")");
            });
        } else {
            System.out.println("Autor: Desconocido");
        }

        // Formatear idiomas
        System.out.println("Idioma(s): " + String.join(", ", libro.idioma()));

        // Formatear descargas
        System.out.println("Número de descargas: " + libro.numeroDeDescargas());
        System.out.println("---\n");
    }



    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1- Buscar libro por título
                    2- Listar libros registrados
                    3- Listar autores registrados
                    4- Listar autores vivos en un determinado año
                    5- Listar libros por idioma
                    0- Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

}
