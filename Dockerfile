# 1. Java 17 JDK 이미지 사용
FROM eclipse-temurin:17-jdk-alpine

# 2. 작업 디렉토리 설정
WORKDIR /app/libro

# 3. 빌드된 JAR 파일 복사
COPY libro-backend-0.0.1-SNAPSHOT.jar app.jar

# 4. 애플리케이션 실행 명령어 (dev 프로파일 활성화)
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=local"]
