# Use an official Maven runtime as a parent image
FROM maven:3.8.4-openjdk-11-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project's POM and source code
COPY pom.xml .
COPY src ./src

# Build the Maven project
RUN mvn clean install

# Expose the port your Java application is listening on
EXPOSE 4444

# Define the command to run your tests (replace with your actual test command)
CMD ["mvn", "test"]


