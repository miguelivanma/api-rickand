# Clone the Project
1. git clone https://github.com/miguelivanma/api-rickand
# Create jar
1. mvn clean install
# Create imagen Docker
1. mvn clean package
2. docker build -t api-1.0.1 .
# Start the container
1. docker run api-1.0.1