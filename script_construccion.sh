### Creacion de las imagenes

docker build --no-cache -t dockerfile_sf  -f DockerFile.SF .
docker build --no-cache -t dockerfile_rr1  -f DockerFile.RR1 .
docker build --no-cache -t dockerfile_rr2  -f DockerFile.RR2 .
docker build --no-cache -t dockerfile_rr3  -f DockerFile.RR3 .

### Creacion de la red

docker network create log_service_network
docker run -d --network=log_service_network -p 27017:27017 --name db mongo
docker run -d --network=log_service_network -p 36002:37000 --name dockerfile_rr1 dockerfile_rr1
docker run -d --network=log_service_network -p 36003:38000 --name dockerfile_rr2 dockerfile_rr2
docker run -d --network=log_service_network -p 36004:39000 --name dockerfile_rr3 dockerfile_rr3
docker run -d --network=log_service_network -p 36001:35000 --name dockerfile_sf dockerfile_sf
