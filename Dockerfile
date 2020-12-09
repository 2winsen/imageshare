FROM maven:3.6.1-jdk-7

COPY . /opt/app
WORKDIR /opt/app
RUN mvn clean install
EXPOSE 8080
CMD ["mvn", "jetty:run"]