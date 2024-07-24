# Utiliza la imagen base de OpenJDK 22
FROM openjdk:22

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el JAR construido de tu aplicación al contenedor
COPY build/libs/demo-0.0.1-SNAPSHOT.jar /app/app.jar

# Expone el puerto que utiliza tu aplicación (ajústalo según tu configuración)
EXPOSE 8080

# Comando para ejecutar la aplicación dentro del contenedor
CMD ["java", "-jar", "app.jar"]
