# geb-ol
Keep the scores for competitions with your friends.

# How to set up development environment
Java version 11
> brew tap AdoptOpenJDK/openjdk
> 
> brew cask install adoptopenjdk11

Maven version 3.6.3
> brew install maven

# Testing framework
This project uses Junit4.
Run tests with
> mvn test

# Running a local server

Fire up a server with
>  mvn spring-boot:run

If the build succeeds, the page can be accessed at http://localhost:8080.
