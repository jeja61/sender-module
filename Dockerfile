# Базовый образ с OpenJDK 21
FROM openjdk:21-jdk-slim

# Рабочая директория
WORKDIR /app

# 1. Копируем только pom.xml (для кэширования зависимостей)
COPY pom.xml .

# 2. Скачиваем все зависимости (кешируем этот слой)
RUN mvn dependency:go-offline -B

# 3. Копируем исходный код
COPY src ./src

# 4. Собираем приложение
RUN mvn package -DskipTests

# 5. Запускаем приложение
ENTRYPOINT ["java", "-jar", "target/sender-module-0.0.1-SNAPSHOT.jar"]