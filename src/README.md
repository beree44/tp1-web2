# TP1 - API REST con arquitectura en capas

API desarrollada con Spring Boot que expone dos grupos de endpoints:

- **Productos** (`/api/productos`): consume la API pública de DummyJSON y expone su propio contrato JSON, de solo lectura.
- **Favoritos** (`/api/favoritos`): recurso propio con CRUD completo, guardado en memoria (sin persistencia real).

## Requisitos

- Java 25 (JDK)
- No hace falta instalar Maven: el proyecto incluye Maven Wrapper (mvnw / mvnw.cmd)
- Conexión a internet (para consumir la API externa de productos)

## Cómo levantar el proyecto

Desde la carpeta raíz del proyecto (donde está pom.xml):

Windows:
.\mvnw.cmd spring-boot:run

macOS/Linux:
./mvnw spring-boot:run

El servicio queda escuchando en http://localhost:8080.

## Documentación interactiva (Swagger)

Con el servicio corriendo, entrar a:

http://localhost:8080/swagger-ui.html

Ahí se pueden ver y probar todos los endpoints documentados.

## Endpoints principales

### Productos (solo lectura)

| Método | Endpoint | Descripción |
|---|---|---|
| GET | /api/productos | Lista todos los productos del catálogo externo |
| GET | /api/productos/{id} | Obtiene un producto puntual por su id |

### Favoritos (CRUD completo)

| Método | Endpoint | Código de éxito | Descripción |
|---|---|---|---|
| POST | /api/favoritos | 201 Created | Crea un nuevo favorito |
| GET | /api/favoritos | 200 OK | Lista todos los favoritos guardados |
| GET | /api/favoritos/{id} | 200 OK | Obtiene un favorito puntual |
| PUT | /api/favoritos/{id} | 200 OK | Actualiza un favorito existente |
| DELETE | /api/favoritos/{id} | 204 No Content | Elimina un favorito |

Ejemplo de body para crear/actualizar un favorito:

{
  "productoId": 1,
  "nota": "Me encanto este producto"
}

## Manejo de errores

La API devuelve errores con un formato uniforme:

{
  "status": 404,
  "mensaje": "No se encontro el favorito con id 999",
  "detalles": [],
  "timestamp": "2026-09-05T13:33:29"
}

Casos contemplados:
- 400 Bad Request: falla de validación en el body de favoritos (con el detalle de qué campo falló).
- 404 Not Found: se pidió un favorito con un id que no existe.
- 502 Bad Gateway: no se pudo consumir la API externa de productos.

## Notas

- Los favoritos se guardan en memoria: si se reinicia el servicio, se pierden.
- El TP2 va a incorporar persistencia real con una base de datos.