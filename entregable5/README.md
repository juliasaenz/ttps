# Proyecto Qué Comemos

Este proyecto es una aplicación de gestión de cartas, menús y comidas.

## Integrantes
- Manuel Rubiano
- Julia Saenz

## Requisitos previos
- [Java 11 o superior](https://adoptopenjdk.net/)
- [Maven](https://maven.apache.org/)
- [MySQL](https://www.mysql.com/)
- [Eclipse](https://www.eclipse.org/), o cualquier IDE compatible con Java

## Configuración de la base de datos

1. **Base de datos MySQL**:
   - Crear una base de datos llamada `buffet` en MySQL (si no existe).
   - Datos de la conexión
   		- **Usuario**: `ttps`
   		- **Contraseña**: `4qwvQMFK`
   		- **URL**: `jdbc:mysql://localhost:3306/buffet`
   		- **Puerto**: `3306` (por defecto de MySQL)

2. **Estructura de la base de datos**:
   - Ejecutar los siguientes comandos para generar el esquema de la base de datos:
     - `mvn clean install`
     - `mvn compile`
     - `mvn exec:java`

## Ejecución de la aplicación

### 1. Ejecutar la aplicación desde Eclipse

1. Abrir Eclipse y cargar el proyecto.
2. Navegar a `src/java/ttps/spring/test/BootTest.java`.
3. Hacer clic derecho sobre `BootTest.java` y seleccionar **Ejecutar como > Aplicación Java**.
4. Esto iniciará la aplicación y, si todo está configurado correctamente, debería conectarse a la base de datos y permitir testear los endpoints en Postman.

### 2. Importar la colección de Postman

1. Descargar la colección de Postman (archivo `Que Comemos API.postman_collection.json`).
2. Importar archivo JSON a Postman
3. Correr colección. Deberían pasar todos los tests.

Nota: Si por alguna razón es necesario resetear los datos de la base de datos, el archivo `import.sql` tiene la configuración inicial en SQL
