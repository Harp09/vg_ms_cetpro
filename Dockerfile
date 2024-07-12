# Usar una imagen base de Java
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo pom.xml y el wrapper de Maven
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# Asegurarse de que mvnw tenga permisos de ejecución
RUN chmod +x mvnw

# Descargar las dependencias del proyecto sin ejecutar los tests
RUN ./mvnw dependency:go-offline

# Copiar el código fuente del proyecto
COPY src ./src

# Copiar el archivo application.yml
COPY src/main/resources/application.yml src/main/resources/application.yml

# Compilar el proyecto y construir el archivo JAR
RUN ./mvnw package -Profile

# Exponer el puerto en el que la aplicación se ejecutará
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "target/tu-archivo.jar"]