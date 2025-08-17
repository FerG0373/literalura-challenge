# 📚 Literalura Challenge

Proyecto desarrollado como parte del programa **ONE (Oracle Next Education)**.  
El objetivo es construir una aplicación de consola en **Java con Spring Boot** que interactúe con la API de [Gutendex](https://gutendex.com/) para la búsqueda y gestión de libros y autores, persistiendo los datos en una base de datos **PostgreSQL**.

---

## 🚀 Tecnologías utilizadas
- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven

---

## ⚙️ Configuración del proyecto
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/usuario/literalura-challenge.git

2. Configurar la base de datos PostgreSQL:
CREATE DATABASE literalura;

3. Revisar el archivo application.properties y ajustar credenciales según tu entorno:
spring.datasource.url=jdbc:postgresql://localhost/literalura
spring.datasource.username=postgres
spring.datasource.password=admin
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update

4. Ejecutar el proyecto


📖 Funcionalidades implementadas:
Menú principal.
Al iniciar la aplicación se despliega un menú con las siguientes opciones:

1) Buscar libro por título:
  - Consulta la API de Gutendex.
  - Persiste el libro y su autor en la base de datos.

2) Listar libros registrados:
  - Muestra todos los libros guardados en la base de datos.

3) Listar autores registrados:
  - Muestra todos los autores guardados, junto con sus libros asociados.

4) Listar autores vivos en un determinado año:
  - El usuario ingresa un año, y la aplicación busca autores que estaban vivos en ese período.
  - Implementado con Derived Queries de Spring Data JPA.

5) Listar libros por idioma:
  - El usuario ingresa un idioma (ejemplo: es o en).
  - Se muestra la cantidad de libros almacenados en ese idioma.
  - Implementado con Derived Queries (countByIdioma).

6) Salir


🗄️ Modelo de datos
Entidad Libro:
  - id
  - titulo
  - numeroDeDescargas
  - autor (relación muchos-a-uno con Autor)

Entidad Autor:
  - id
  - nombre
  - fechaDeNacimiento
  - fechaDeFallecimiento
  - libros (relación uno-a-muchos con Libro)


📊 Ejemplo de uso
<img width="416" height="148" alt="{5527F832-4CD2-496B-AAD1-2FEF4A1B6CC0}" src="https://github.com/user-attachments/assets/c49841ab-302d-4690-a856-26b6a30d7ecb" />


📌 Aprendizajes clave:
  - Consumo de APIs REST con Java
  - Manejo de JSON usando clases DTO
  - Persistencia de datos con Spring Data JPA y PostgreSQL
  - Uso de Derived Queries (countBy..., findBy...) para consultas personalizadas


👨‍💻 Autor

Fernando González
Estudiante de la Tecnicatura en Análisis de Sistemas
Participante del programa ONE - Oracle Next Education


