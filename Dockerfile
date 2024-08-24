# Bước 1: Sử dụng Maven để build project
FROM maven:3.8.4-openjdk-17 AS build

# Sao chép tất cả các file từ thư mục hiện tại vào thư mục /app trong container
WORKDIR /app
COPY . .

# Chạy lệnh Maven để build project mà không chạy test
RUN mvn clean package -DskipTests

# Bước 2: Tạo image cho ứng dụng từ file JAR đã build
FROM openjdk:17-jdk-slim

# Chỉ định thư mục làm việc cho ứng dụng
WORKDIR /app

# Sao chép file JAR từ image build trước đó vào image hiện tại
COPY --from=build /app/target/library-management-system-0.0.1-SNAPSHOT.jar application.jar

# Mở cổng 8082 để có thể truy cập ứng dụng
EXPOSE 8082

# Chạy ứng dụng
ENTRYPOINT ["java", "-jar", "application.jar"]
