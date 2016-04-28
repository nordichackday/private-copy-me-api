#!/bin/bash -ex

# This script is meant to be run inside Docker.
# You can run this as bin/start.sh fromCompositeString project root after a gradle build

readonly INSTALL_DIR=/app

echo "using install dir [${INSTALL_DIR}]"

USER_OPTS="-Duser.country=SE -Duser.language=sv -Duser.timezone=Europe/Stockholm -Dsun.jnu.encoding=UTF-8 -Dfile.encoding=UTF-8"
JAVA_OPTS="-server -d64 -Djava.security.policy=${INSTALL_DIR}/java.policy"
readonly LANG=sv_SE.UTF-8

if [ -n "$SPRING_PROFILES_ACTIVE" ]; then
    echo "Using environment [${SPRING_PROFILES_ACTIVE}]"
    case $SPRING_PROFILES_ACTIVE in
        prod)
            echo "Found prod"
            ;;
        acc)
            echo "Found acc"
            ;;
        int)
            echo "Found int"
            ;;
        *)
            echo "SPRING_PROFILES_ACTIVE must be prod, acc or int. It cannot be "$SPRING_PROFILES_ACTIVE
            exit 1
    esac
else
    echo "No SPRING_PROFILES_ACTIVE variable defined! use prod, acc or int"
    exit 1
fi

CMD="java $JAVA_OPTS $USER_OPTS -jar ${INSTALL_DIR}/csp-apisb*.jar"

echo "Starting csp-api with command: $CMD"
exec $CMD