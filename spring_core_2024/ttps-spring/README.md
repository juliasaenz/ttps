# Estructura del Proyecto

```plaintext
ttps-spring/
├── pom.xml                 # Archivo de configuración de Maven, especifica dependencias, plugins, etc.
├── target/                 # Archivos compilados, JAR/WAR, etc. (generado tras la compilación)
├── src/
│   ├── main/
│   │   ├── java/           # Archivos fuente de Java (controladores, servicios, modelos, etc. de Spring)
│   │   │   └── ttps/
│   │   │       └── spring/
│   │   │           ├── config/    # Clases de configuración (como PersistenceConfig, AppConfig)
│   │   │           ├── model/     # Clases de modelo (entidades JPA, POJOs)
│   │   │           └── test/      # Clases de prueba (pruebas unitarias, pruebas de Spring)
│   │   └── webContent/     # Carpeta de contenido web (recursos como JSP, archivos estáticos)
│   │       ├── META-INF/
│   │       │   └── MANIFEST.MF  # Metadatos sobre la aplicación web (puede generarse automáticamente)
│   │       └── WEB-INF/
│   │           └── web.xml      # Archivo de configuración principal para el servlet de contexto
```