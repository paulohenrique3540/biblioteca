# Etapa 1: Build do projeto usando Maven
FROM maven:3.8.6-eclipse-temurin-17 AS build

# Diretório de trabalho dentro do container
WORKDIR /app

# Copia o pom.xml e os arquivos de código para o container
COPY pom.xml .
COPY src ./src

# Faz o build do projeto (vai gerar o JAR)
RUN mvn clean package -DskipTests

# Etapa 2: Executar o JAR gerado em uma imagem mais leve
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copia o JAR gerado da etapa de build
COPY --from=build /app/target/*.jar app.jar

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]

# Porta padrão do Spring Boot
EXPOSE 8080
