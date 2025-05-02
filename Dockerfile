
FROM eclipse-temurin:21-jdk




RUN apt-get update && \
    apt-get install -y ant python3 python3-pip && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*


WORKDIR /app


COPY . .


CMD ["bash"]

