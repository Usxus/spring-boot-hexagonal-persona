# Proyecto Spring Boot - Gestión de Personas

## Información del Proyecto

Este proyecto es una aplicación desarrollada en Java y Spring Boot aplicando una Arquitectura Hexagonal. Permite realizar operaciones CRUD (GET, POST, PUT, DELETE) sobre la entidad `PersonaEntity` conectada a una base de datos PostgreSQL.

### Características Principales:
- **Arquitectura Hexagonal:** Separación limpia de la lógica de dominio, servicios de aplicación e infraestructura (Controladores REST y Persistencia).
- **Documentación Swagger / OpenAPI:** Documentación e interfaz interactiva para pruebas de la API.
- **Mapeo de Datos:** Uso de **MapStruct** para la conversión entre DTOs, modelos de dominio y la entidad de persistencia.
- **Reducción de Código:** Uso de **Lombok** para generar getters, setters, constructores y patrones builder.
- **Persistencia JPA:** Conexión y gestión de datos con **Spring Data JPA** hacia PostgreSQL.
- **Operaciones HTTP:**
  - **GET (Consulta):** Obtener persona por ID o listar todas las personas.
  - **POST (Registrar):** Alta de personas en la base de datos PostgreSQL.
  - **PUT (Modificar):** Actualización de los datos de una persona existente.
  - **DELETE (Eliminar):** Eliminación de una persona por su ID.

### Documentación Swagger UI:
- **Interfaz Swagger UI:** `http://localhost:8080/swagger-ui/index.html`
- **Especificación OpenAPI (JSON):** `http://localhost:8080/v3/api-docs`

### Endpoints de la API REST (Rutas Completas):
- **POST** `http://localhost:8080/api/personas` ➔ Registrar / Alta de persona
- **GET** `http://localhost:8080/api/personas` ➔ Consultar todas las personas
- **GET** `http://localhost:8080/api/personas/{id}` ➔ Consultar persona por ID
- **PUT** `http://localhost:8080/api/personas/{id}` ➔ Modificar persona por ID
- **DELETE** `http://localhost:8080/api/personas/{id}` ➔ Eliminar persona por ID

### Ejemplos de JSON Body para Peticiones:

#### 1. POST (Registrar Persona)
- **URL:** `POST http://localhost:8080/api/personas`
- **Body (JSON):**
```json
{
  "nombre": "Carlos",
  "apellido": "González",
  "edad": 28
}
```

#### 2. PUT (Modificar Persona)
- **URL:** `PUT http://localhost:8080/api/personas/1`
- **Body (JSON):**
```json
{
  "nombre": "Carlos Alberto",
  "apellido": "González Pérez",
  "edad": 29
}
```

#### 3. DELETE (Eliminar Persona)
- **URL:** `DELETE http://localhost:8080/api/personas/1`
- *(No requiere JSON Body - Retorna estado `204 No Content`)*

### Entidad Principal (`PersonaEntity`):
Representa la tabla `persona` en la base de datos de PostgreSQL con los siguientes campos:
- `id` (Long, Autoincrementable)
- `nombre` (String)
- `apellido` (String)
- `edad` (Integer)
