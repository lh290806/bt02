# ==========================================
# 1. Build Stage: Biên dịch ứng dụng bằng Maven
# ==========================================
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copy pom.xml và mã nguồn
COPY pom.xml .
COPY src ./src
COPY web ./web

# Đóng gói thành file WAR (tạo ra target/ROOT.war)
RUN mvn clean package -DskipTests

# ==========================================
# 2. Run Stage: Khởi chạy trên Apache Tomcat 10.1 (Jakarta EE 10 / Java 21)
# ==========================================
FROM tomcat:10.1-jdk21-temurin

# Xóa các ứng dụng mặc định của Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy file ROOT.war vào thư mục webapps để ứng dụng chạy tại root context ("/")
COPY --from=build /app/target/ROOT.war /usr/local/tomcat/webapps/ROOT.war

# Mặc định cổng 8080
EXPOSE 8080

# Cập nhật cổng động theo biến môi trường $PORT của Render và khởi động Tomcat
CMD sed -i "s/port=\"8080\"/port=\"${PORT:-8080}\"/g" /usr/local/tomcat/conf/server.xml && catalina.sh run
