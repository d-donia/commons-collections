 #<!--
 #  Licensed to the Apache Software Foundation (ASF) under one or more
 # contributor license agreements.  See the NOTICE file distributed with
 # this work for additional information regarding copyright ownership.
 # The ASF licenses this file to You under the Apache License, Version 2.0
 # (the "License"); you may not use this file except in compliance with
 # the License.  You may obtain a copy of the License at

 #      http://www.apache.org/licenses/LICENSE-2.0

  # Unless required by applicable law or agreed to in writing, software
 #  distributed under the License is distributed on an "AS IS" BASIS,
 #  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 #  See the License for the specific language governing permissions and
 #  limitations under the License.
 # -->
FROM maven:3.8.3-openjdk-17-slim

WORKDIR /app

# Copy the pom.xml files for the two projects into the Docker build context
COPY pom.xml ./commons/
COPY src/main/initial/pom.xml ./spring-boot/

# Copy the source code for both projects into the Docker build context
COPY src ./commons/src/
COPY src/main/initial/src ./spring-boot/src/

# Install the dependencies for both projects
RUN mvn -f ./commons/pom.xml clean package
RUN mvn -f ./spring-boot/pom.xml clean package



# Expose the port that the Spring Boot app is listening on
EXPOSE 8080

# Start the Spring Boot app
CMD ["java", "-jar", "./spring-boot/target/spring-boot.jar"]