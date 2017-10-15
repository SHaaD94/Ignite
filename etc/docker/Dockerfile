FROM frolvlad/alpine-oraclejdk8:slim

ADD ignite-*.jar app.jar

RUN apk update && \
    apk add libstdc++ && \
    rm -rf /var/cache/apk/* && \
    sh -c 'touch /app.jar'

ENV JAVA_OPTS="-Xmx3800m -Djava.security.egd=file:/dev/./urandom"

ENTRYPOINT exec java $JAVA_OPTS -jar /app.jar