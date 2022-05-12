# My Crypto App - Registration Service
Java project with Spring, Maven, MongoDB Atlas, JUnit, Lombok, Docker, Swagger and more

## Requirements
1. Maven: https://maven.apache.org/download.cgi
2. Java 11: https://www.oracle.com/co/java/technologies/javase/jdk11-archive-downloads.html
3. Docker: https://docs.docker.com/desktop/windows/install/
4. Set environment variables:

```
In your environment variable path, add this

%JAVA_HOME%\bin
and also add your path of Maven folder like this

C:\Program Files (x86)\apache-maven-3.8.4\bin
Then create user variable named

JAVA_HOME 
and set as path

C:\Program Files\Java\jdk-11.0.14\ 
(wihout bin)

Last step: important Restart IntelliJ or another program you are using.
```

### Application Deployment
To generate a docker image you can use the Dockerfile configured in the project as follows:

1. Go to the folder containing Dockerfile \Jaxon-Munoz-Training\
2. Then run the following commands according to your operating system:

```
#Generating Jar 
mvn clean install

#LINUX
#Generating the image to deploy 
sudo docker build -t mycryptoapp:1.0 .
#Running the image
sudo docker run -d -p 8080:8080 -t mycryptoapp:1.0

#WINDOWS
#Generating the image to deploy
docker build -t mycryptoapp:1.0 .
#Running the image
docker run -d -p 8080:8080 -t mycryptoapp:1.0

```

Do you need more details?
Here some tutorials:
1. https://mkyong.com/docker/docker-spring-boot-examples/
2. https://www.baeldung.com/dockerizing-spring-boot-application


### Testing the application

You can see all the services exposed in the [swagger documentation](http://localhost:8080/swagger-ui.html#/user-resource)

![image](https://user-images.githubusercontent.com/16148737/164075480-2d4f72c3-91be-4340-b4eb-c49f3775a389.png)

You can use [postman](https://www.postman.com/downloads/) and try a get request like this [local service](http://localhost:8080/user/registration/getUserByEmail/jaxonma@gmail.com) 

![image](https://user-images.githubusercontent.com/16148737/164063970-fe256b3b-ec96-47a3-9b4c-ff9cf276c374.png)


### Do you want to try it over AWS?

Here some tutorials:
1. https://blog.clairvoyantsoft.com/deploy-and-run-docker-images-on-aws-ecs-85a17a073281
2. https://medium.com/@chandupriya93/deploying-docker-containers-with-aws-ec2-instance-265038bba674

Here you can use my instance: [MyCryptoApp](http://ec2-34-227-13-165.compute-1.amazonaws.com:8080/user/registration/getUserByEmail/jaxonma@gmail.com)

¡Regards!



