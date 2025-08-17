package com.alura.literalura_challenge.principal;

import com.alura.literalura_challenge.model.Autor;
import com.alura.literalura_challenge.model.DatosLibro;
import com.alura.literalura_challenge.model.DatosRespuesta;
import com.alura.literalura_challenge.model.Libro;
import com.alura.literalura_challenge.repository.AutorRepository;
import com.alura.literalura_challenge.repository.LibroRepository;
import com.alura.literalura_challenge.service.ConsumoAPI;
import com.alura.literalura_challenge.service.ConvierteDatos;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository libroRepositorio;
    private AutorRepository autorRepositorio;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepositorio = libroRepository;
        this.autorRepositorio = autorRepository;
    }


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

                Autor autor = new Autor(libroBuscado.get().autor().get(0));
                Libro libro = new Libro(libroBuscado.get());
                libro.setAutor(autor);
                libroRepositorio.save(libro);

                mostrarLibroFormateado(libro);
            } else {
                System.out.println("Libro NO encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Error al buscar el libro: " + e.getMessage());
        }
    }

    private void mostrarLibroFormateado(Libro libro) {
        System.out.println("--- LIBRO ---");
        System.out.println("Título: " + libro.getTitulo());

        // Formatear autores
        if (libro.getAutor() != null) {
            System.out.println("Autor(es): " + libro.getAutor().getNombre() +
                    " (" + libro.getAutor().getFechaDeNacimiento() +
                    " - " + libro.getAutor().getFechaDeFallecimiento() + ")");
        } else {
            System.out.println("Autor: Desconocido");
        }
        // Formatear idiomas
        System.out.println("Idioma(s): " + libro.getIdioma());
        // Formatear descargas
        System.out.println("Número de descargas: " + libro.getNumeroDeDescargas());
        System.out.println("---\n");
    }

    private void listarLibrosRegistrados() {
        List<Libro> libros = libroRepositorio.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            System.out.println("--- LIBROS REGISTRADOS ---");
            libros.forEach(this::mostrarLibroFormateado);
        }
    }

    private void mostrarAutorFormateado(Autor autor) {
        System.out.println("--- AUTOR ---");
        System.out.println("Nombre: " + autor.getNombre());
        System.out.println("Fecha de nacimiento: " + autor.getFechaDeNacimiento());
        System.out.println("Fecha de fallecimiento: " + autor.getFechaDeFallecimiento());
        System.out.println("Libros: " + autor.getLibros().stream()
                .map(Libro::getTitulo).collect(Collectors.joining(", ")));
        System.out.println("---\n");
    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = autorRepositorio.findAll();
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados.");
        } else {
            System.out.println("--- AUTORES REGISTRADOS ---");
            autores.forEach(this::mostrarAutorFormateado);
        }
    }

    private void mostrarCantidadLibrosPorIdioma() {
        System.out.println("Ingrese el idioma (ejemplo: 'en' para inglés, 'es' para español):");
        String idioma = teclado.nextLine();

        Long cantidad = libroRepositorio.countByIdioma(idioma);

        if (cantidad > 0) {
            System.out.println("Cantidad de libros en idioma '" + idioma + "': " + cantidad);
        } else {
            System.out.println("No hay libros registrados en idioma '" + idioma + "'.");
        }
    }

    private void listarAutoresVivosEnAnio() {
        System.out.println("Ingrese un año para buscar autores vivos:");
        Integer anio = null;
        try {
            anio = Integer.parseInt(teclado.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Año inválido. Debe ingresar un número.");
            return;
        }

        // Autores con fecha de fallecimiento posterior o igual al año
        List<Autor> autoresConFallecimiento = autorRepositorio
                .findByFechaDeNacimientoLessThanEqualAndFechaDeFallecimientoGreaterThanEqual(anio, anio);

        // Autores que aún no tienen fallecimiento registrado
        List<Autor> autoresSinFallecimiento = autorRepositorio
                .findByFechaDeNacimientoLessThanEqualAndFechaDeFallecimientoIsNull(anio);

        // Unimos las dos listas
        autoresConFallecimiento.addAll(autoresSinFallecimiento);

        if (autoresConFallecimiento.isEmpty()) {
            System.out.println("No se encontraron autores vivos en el año " + anio);
        } else {
            System.out.println("--- AUTORES VIVOS EN " + anio + " ---");
            autoresConFallecimiento.forEach(this::mostrarAutorFormateado);
        }
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
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosEnAnio();
                    break;
                case 5:
                    mostrarCantidadLibrosPorIdioma();
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
