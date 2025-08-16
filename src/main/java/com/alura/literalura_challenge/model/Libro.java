package com.alura.literalura_challenge.model;

//import jakarta.persistence.*;
//
//
//@Entity
//@Table(name = "libros")
public class Libro {
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    //@Column(unique = true)
    private String titulo;

    // Relación ManyToOne: muchos libros pueden tener un solo autor.
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "autor_id")
    private Autor autor;

    private String idioma;
    private Integer numeroDeDescargas;

    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        this.idioma = datosLibro.idioma().isEmpty() ? "es" : datosLibro.idioma().get(0);
        this.numeroDeDescargas = datosLibro.numeroDeDescargas() != null ? datosLibro.numeroDeDescargas() : 0;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Integer numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    @Override
    public String toString() {
        return
                "titulo='" + titulo + '\'' +
                ", autor=" + (autor != null ? autor.getNombre() : "Desconocido") +
                ", idioma=" + idioma +
                ", numeroDeDescargas=" + numeroDeDescargas;
    }
}
