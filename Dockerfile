 FROM amazoncorretto:17
WORKDIR /usr/src/app
COPY ./build/libs/api.linkmart.jar .
EXPOSE 8080
CMD java -jar api.linkmart.jar \
    --spring.config.location=file:./config/env.properties