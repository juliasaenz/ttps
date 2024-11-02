## Guía de uso
1. Configurar la base de datos
	a. Crear la base de datos con nombre _ttps_ en el sistema de gestión (por ej: MySQL Workbench)
	b. Configurar la conexión al puerto 3306
	c. Crear usuario "ttps" contraseña "4qwvQMFK" con permisos para acceder y manipular la base de datos
2. Subir las tablas
	a. Correr el archivo en `/src/test/java/TestMain.java` 
		La consola debería imprimir "Entidades subidas a la base de datos"
3. Correr los tests
	a. Ir a la carpeta `/src/test/java/quecomemos/jpa` y correr los test