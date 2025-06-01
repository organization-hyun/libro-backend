# 1단계: Gradle 빌드 (래퍼 사용)
FROM gradle:8.3.0-jdk17 AS build
WORKDIR /app

COPY . .

# gradle 래퍼가 있는 경우 아래처럼 실행
RUN ./gradlew bootJar --no-daemon

# 2단계: 실행용 이미지
FROM openjdk:17-jdk-slim
WORKDIR /app

# 환경 변수 추가: 프로덕션 환경 사용
ENV SPRING_PROFILES_ACTIVE=prod

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
