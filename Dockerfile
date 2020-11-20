FROM openjdk:11-alpine
VOLUME /tmp
ARG EXTRACTED_DIR=./target/extracted
ARG APP_DIR=/app
WORKDIR $APP_DIR
COPY $EXTRACTED_DIR/BOOT-INF/lib ./BOOT-INF/lib
COPY $EXTRACTED_DIR/org ./org
COPY $EXTRACTED_DIR/META-INF ./META-INF
COPY $EXTRACTED_DIR/BOOT-INF/classes ./BOOT-INF/classes
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
