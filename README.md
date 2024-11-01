# Cozzani Tp Java BBVA

# Proyecto Integrador de Gestión de Pólizas de Seguros

Este proyecto es un sistema de gestión de pólizas de seguros que permite a los usuarios administrar pólizas, clientes, tipos de seguros y siniestros. La aplicación proporciona una API RESTful desarrollada en Java, utilizando el framework Spring Boot y otras tecnologías modernas para asegurar un desarrollo robusto y escalable.

## Características del Proyecto

- **Gestión de Pólizas**: CRUD de pólizas de seguros, asociando clientes y tipos de seguros.
- **Autenticación y Autorización**: Implementada con Spring Security y JWT para proteger las rutas de la API.
- **Validación de Datos**: Validación de las entradas de usuario con anotaciones de Jakarta Validation.
- **Pruebas Unitarias y de Integración**: Pruebas con JUnit y Mockito para garantizar la calidad del código.
- **Manejo de Errores Personalizado**: Respuestas claras y estructuradas para errores en la API.

## Tecnologías y Frameworks Utilizados

### Backend
- **Java**: Lenguaje de programación principal.
- **Spring Boot**: Framework para el desarrollo de la aplicación.
- **Spring Data JPA**: Manejo de persistencia y consultas a la base de datos.
- **Spring Security**: Seguridad de la aplicación con autenticación y autorización.
- **JWT (JSON Web Tokens)**: Implementación de autenticación basada en tokens.
- **Hibernate**: Implementación de JPA para el mapeo objeto-relacional.
- **Jakarta Validation (JSR 380)**: Validación de datos de entrada.
- **Maven**: Herramienta de gestión de dependencias y construcción del proyecto.
- **Lombok**: Reducción de código boilerplate mediante anotaciones.
- **JUnit 5**: Framework para la ejecución de pruebas unitarias.
- **Mockito**: Framework de mocking para pruebas unitarias.

### Base de Datos
- **SQL (PostgreSQL o MySQL)**: Base de datos relacional para almacenamiento de datos.
- **H2 Database**: Base de datos en memoria para pruebas y desarrollo.

### Herramientas de Desarrollo
- **Postman**: Herramienta para pruebas de endpoints de la API.
- **IntelliJ IDEA / Eclipse**: Entorno de desarrollo integrado (IDE).
- **Git**: Control de versiones.
- **GitHub / GitLab**: Repositorio de código fuente.

### Estructura del Proyecto
El proyecto sigue una arquitectura de capas bien definida:
- **Controller**: Maneja las solicitudes HTTP y coordina la lógica de negocio.
- **Service**: Contiene la lógica de negocio de la aplicación.
- **Repository**: Capa de acceso a datos, gestionada por Spring Data JPA.
- **DTOs (Data Transfer Objects)**: Estructuras de datos para transferir información entre capas.
- **Entidades (Entities)**: Representación de las tablas de la base de datos.

### Endpoints Principales
- **Clientes**
  - `GET /clientes`
  - `POST /clientes`
  - `PUT /clientes/{id}`
  - `DELETE /clientes/{id}`

- **Pólizas**
  - `GET /polizas`
  - `POST /polizas`
  - `PUT /polizas/{id}`
  - `DELETE /polizas/{id}`

- **Siniestros**
  - `GET /siniestros`
  - `POST /siniestros`
  - `PUT /siniestros/{id}`
  - `DELETE /siniestros/{id}`

- **Tipos de Seguros**
  - `GET /tipos-seguros`
  - `POST /tipos-seguros`
  - `PUT /tipos-seguros/{id}`
  - `DELETE /tipos-seguros/{id}`

## Instalación y Ejecución

1. Clona el repositorio:
   ```bash
   git clone https://github.com/hcozzani/Cozzani_Tp_Java_Bbva.git
