FROM maven:latest

LABEL authors="yangyang"

WORKDIR /app

COPY pom.xml /app/

COPY . /app/

RUN mvn  package

CMD ["java", "-jar", "target/cartLocal.jar"]

