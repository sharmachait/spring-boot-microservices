FROM eclipse-temurin:23-jdk-alpine AS build
WORKDIR /app
COPY . .
# Define the build argument with no default value (to force it to be provided)
ARG PROFILE
# Fail the build if PROFILE arg is not provided
# RUN if [ -z "$PROFILE" ]; then echo "ERROR: PROFILE build argument is required" && exit 1; fi
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:23-jre-alpine
WORKDIR /app
ENV profile=$PROFILE
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]