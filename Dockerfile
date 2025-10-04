# Use slim JDK image
FROM eclipse-temurin:17-jre-jammy

# Create app user
RUN useradd -ms /bin/bash appuser
WORKDIR /app

# Copy jar
COPY build/libs/example-service-1.0.0.jar /app/app.jar

# Non-root run
USER appuser

# Expose port used by app
EXPOSE 8080

# Run application
ENTRYPOINT ["java","-jar","/app/app.jar"]
