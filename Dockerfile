FROM openjdk:19-alpine AS DEPS
RUN apk add maven && rm -rf /var/cache/apk/*
WORKDIR /opt/app

# Copy every module
COPY customer/pom.xml customer/pom.xml

COPY pom.xml .
RUN mvn -B -e -C org.apache.maven.plugins:maven-dependency-plugin:3.1.2:go-offline

# if you have modules that depends each other, you may use -DexcludeArtifactIds as follows
# RUN mvn -B -e -C org.apache.maven.plugins:maven-dependency-plugin:3.1.2:go-offline -DexcludeArtifactIds=module1

# Copy the dependencies from the DEPS stage with the advantage of using docker layer caches. If something goes wrong
# from this line on, all dependencies from DEPS were already downloaded and stored in docker's layers.
FROM openjdk:19-alpine AS BUILDER
RUN apk add maven && rm -rf /var/cache/apk/*

WORKDIR /opt/app

COPY --from=deps /root/.m2 /root/.m2
COPY --from=deps /opt/app/ /opt/app
COPY customer/src /opt/app/customer/src

# Use -o (--offline) if you didn't need to exclude artifacts. if you have excluded artifacts, then remove -o flag
RUN mvn -B -e clean package -DskipTests=true

# At this point, BUILDER stage should have your .jar or whatever in some path
FROM openjdk:19-alpine
WORKDIR /opt/app
COPY --from=builder /opt/app/ app.jar
EXPOSE 8080
CMD [ "java", "-jar", "/opt/app/app.jar" ]