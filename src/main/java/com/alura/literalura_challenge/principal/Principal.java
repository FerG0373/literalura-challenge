package com.alura.literalura_challenge.principal;

import com.alura.literalura_challenge.model.DatosLibro;
import com.alura.literalura_challenge.service.ConsumoAPI;
import com.alura.literalura_challenge.service.ConvierteDatos;

import java.util.Scanner;


public class Principal {

    private final Scanner teclado = new Scanner(System.in);
    private final ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();


    private DatosLibro getDatosLibro() {
        System.out.println("Escribe el nombre del libro que deseas buscar: ");
        var nombreLibro = teclado.nextLine();
        var urlConsulta = URL_BASE + "?search=" + nombreLibro.replace(" ", "%20");
        var json = consumoApi.obtenerDatos(urlConsulta);
        System.out.println(json);
        DatosLibro datos = conversor.obtenerDatos(json, DatosLibro.class);
        System.out.println(datos);

        return datos;
    }




    private void buscarLibroPorTitulo() {
        System.out.println("Escribe el nombre del libro que deseas buscar: ");
        var nombreLibro = teclado.nextLine();

        // Construye la URL de la consulta, reemplazando los espacios con "%20"
        var urlConsulta = URL_BASE + "?search=" + nombreLibro.replace(" ", "%20");

        // Llama al servicio de la API para obtener el JSON
        var json = consumoApi.obtenerDatos(urlConsulta);

        // Muestra la respuesta completa de la API en formato JSON
        System.out.println("Respuesta de la API:\n" + json);
    }


    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1- Buscar libro por titulo
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
                    getDatosLibro();
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
