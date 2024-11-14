# Estructura del Proyecto `ttps-spring`

Este documento describe la estructura de archivos y carpetas del proyecto Maven `ttps-spring`, importado para desarrollo con Spring. A continuación se explica el propósito de cada directorio y archivo en el proyecto.

## Estructura de Directorios del Proyecto

### Nivel Raíz

- **`pom.xml`**  
  Archivo POM (Project Object Model) de Maven. Define las dependencias del proyecto, configuración de compilación, plugins y otras configuraciones necesarias para construir y ejecutar el proyecto.

- **`README.md`**  
  Archivo de documentación que proporciona una visión general, instrucciones de configuración e información adicional relevante sobre el proyecto.

- **`ttps-spring.iml`**  
  Archivo de proyecto específico de IntelliJ IDEA, usado por el IDE para almacenar configuraciones específicas del proyecto.

---

### `/Java Resources`

Contiene el código principal en Java, las configuraciones y las bibliotecas necesarias para el proyecto.

#### `/src/main/java`

- **`/ttps/spring/config`**  
  Contiene archivos de configuración para la aplicación Spring.
  - **`PersistenceConfig.java`**: Clase de configuración para la persistencia, incluyendo configuración de conexiones a la base de datos y JPA.

- **`/ttps/spring/model`**  
  Contiene los modelos de entidad que representan las estructuras de datos y los mapeos a tablas de la base de datos.

- **`/ttps/spring/test`**  
  Contiene archivos y clases de prueba para el proyecto, permitiendo la validación de la funcionalidad de manera aislada.

#### `/src`

Archivos fuente y recursos adicionales, normalmente para configuraciones o recursos externos al código principal.

#### `Libraries`

Almacena las bibliotecas externas referenciadas por el proyecto, administradas a través de dependencias de Maven.

---

### `/target`

El directorio `target` es generado por Maven durante el proceso de construcción y contiene clases compiladas, recursos y otros archivos generados.

- **`/m2e-wtp`**  
  Archivos de integración entre Maven y Eclipse Web Tools Platform (WTP), ayudando en la configuración del proyecto para su despliegue web.

  - **`/web-resources`**  
    Contiene los recursos de la aplicación web generados por Maven.

    - **`META-INF`**
      - **`/maven/ttps-spring/ttps-spring`**  
        Contiene archivos de configuración específicos de Maven para el proyecto.
        - **`pom.xml`**: Una copia del archivo `pom.xml` a nivel raíz utilizada para metadatos de Maven.
        - **`pom.properties`**: Archivo de propiedades con metadatos del proyecto para Maven.

- **`/test-classes`**  
  Clases de prueba compiladas generadas por Maven durante el proceso de construcción.

---

### `/WebContext`

Directorio raíz para configuraciones y recursos de la aplicación web.

- **`META-INF`**  
  Directorio para el manifiesto y otros archivos de metadatos.
  
  - **`MANIFEST.MF`**: Contiene información de metadatos sobre el proyecto, normalmente relacionada con la ejecución y versionado.

- **`WEB-INF`**  
  Contiene recursos y configuraciones del lado del servidor, inaccesibles directamente para los usuarios.

  - **`web.xml`**  
    Descriptor de despliegue para el proyecto. Define parámetros de contexto, filtros, listeners, mapeos de servlets, y páginas de error.
    
    - **Parámetros de Contexto**: Configuraciones accesibles a los servlets y filtros en toda la aplicación.
    - **Páginas de Error**: Configuraciones para mostrar páginas de error personalizadas.
    - **Mapeos de Filtros**: Define qué solicitudes pasarán a través de filtros específicos.
    - **Filtros**: Filtros para preprocesar o postprocesar solicitudes.
    - **Listeners**: Componentes que escuchan eventos del ciclo de vida, como el inicio de la aplicación.
    - **Referencias**: Referencias a recursos externos o recursos JNDI.
    - **Mapeos de Servlets**: Define patrones de URL para servlets.
    - **Servlets**: Componentes que manejan solicitudes HTTP específicas.
    - **Páginas de Bienvenida**: Página de inicio mostrada cuando se accede a la raíz de la aplicación.

