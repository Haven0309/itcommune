FROM java:8
#解决容器无字体问题，拷贝宋体到容器中
COPY simsun.ttc /usr/share/fonts/simsun.ttc

ADD target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]