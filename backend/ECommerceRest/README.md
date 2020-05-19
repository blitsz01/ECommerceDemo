# Build
mvn clean package && docker build -t com.org/ECommerceRest .

# RUN

docker rm -f ECommerceRest || true && docker run -d -p 8080:8080 -p 4848:4848 --name ECommerceRest com.org/ECommerceRest 