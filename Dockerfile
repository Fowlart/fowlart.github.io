FROM adoptopenjdk/openjdk8:alpine-slim

# Copy the jar to the production image from the builder stage.
COPY target/app-1.0.war /app-1.0.war

# Run the web service on container startup.
CMD ["java", "-jar", "/app-1.0.war"]
