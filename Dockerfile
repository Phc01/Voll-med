# D:/Java/Alura e ONE/api/Dockerfile

# Usa a imagem completa do JDK 17, que é ótima para desenvolvimento e build.
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

# --- Otimização de Cache do Docker ---
# 1. Copia apenas os arquivos de definição de dependências primeiro.
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# 2. Baixa as dependências. Esta camada só será reconstruída se o pom.xml mudar.
RUN ./mvnw dependency:go-offline

# 3. Agora, copia o código-fonte da aplicação.
COPY src ./src

# Expõe a porta em que a aplicação Spring Boot roda.
EXPOSE 8080

# Comando para compilar e executar a aplicação em um único passo.
# O Spring Boot Plugin permite rodar diretamente sem um 'package' prévio,
# o que é ideal para desenvolvimento.
CMD ["./mvnw", "spring-boot:run"]