package com.alura.literalura_challenge.principal;

import com.alura.literalura_challenge.service.ConsumoAPI;
import java.util.Scanner;


public class Principal {
    // Instancia el servicio para consumir la API
    private final ConsumoAPI consumoAPI = new ConsumoAPI();
    // Define la URL base de la API de Gutendex
    private final String URL_BASE = "https://gutendex.com/books/";
    // Objeto para leer la entrada del usuario
    private final Scanner teclado = new Scanner(System.in);

    // Mostrar el menú.
    public void mostrarMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1- Buscar libro por titulo:
                    2- Listar libros registrados:
                    3- Listar autores registrados:
                    4- Listar autores vivos en un determinado año:
                    5- Listar libros por idioma:
                    0 - Salir
                    """;
            System.out.println(menu);
            // Lee la opción del usuario y consume la línea restante
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

    // Método para buscar un libro por título
    private void buscarLibroPorTitulo() {
        System.out.println("Escribe el nombre del libro que deseas buscar: ");
        var nombreLibro = teclado.nextLine();

        // Construye la URL de la consulta, reemplazando los espacios con "%20"
        var urlConsulta = URL_BASE + "?search=" + nombreLibro.replace(" ", "%20");

        // Llama al servicio de la API para obtener el JSON
        var json = consumoAPI.obtenerDatos(urlConsulta);

        // Muestra la respuesta completa de la API en formato JSON
        System.out.println("Respuesta de la API:\n" + json);
    }
}
