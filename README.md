# jogo-da-velha

./gradlew clean assemble
docker build . -t jogo-da-velha:v1
docker run -d -p 8080:8080 jogo-da-velha:v1