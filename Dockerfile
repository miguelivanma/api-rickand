# Use an OpenJDK base image for Java 11
FROM openjdk:11

# Define the working directory in the container
WORKDIR /app

# Copy your app's JAR file to the image
COPY target/api-1.0.1.jar app.jar

# Exposes the port where your Spring Boot app runs
EXPOSE 8080

# Command to run your app when you start the container
CMD ["java", "-jar", "app.jar"]