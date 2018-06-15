FROM java:8-alpine
MAINTAINER Your Name <you@example.com>

ADD target/uberjar/kiranomi.jar /kiranomi/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/kiranomi/app.jar"]
