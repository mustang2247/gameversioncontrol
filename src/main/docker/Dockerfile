FROM java:8
EXPOSE 9990

RUN mkdir -p /usr/upload/

VOLUME /usr/upload/

ADD run.sh /run.sh

RUN chmod +x /run.sh

ADD zsl-*.jar /zsl.jar

ENTRYPOINT sh /run.sh