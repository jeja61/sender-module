# Используем официальный образ OpenJDK 21
FROM eclipse-temurin:21-jdk

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем jar-файл в контейнер
COPY target/sender-module-1.0-SNAPSHOT.jar app.jar

# Открываем порт
EXPOSE 9090

# Указываем переменные окружения для запуска (будут переданы при запуске)
# Примечание: не используем ARG для переменных окружения, передаем их через docker run
CMD ["java", "-jar", "app.jar"]
