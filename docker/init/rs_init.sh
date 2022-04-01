#!/bin/bash

echo "Starting replica set initialize"

sleep 400

until mongo --host mongo1 --eval "print(\"waited for connection\")"
do
    sleep 20
done

until mongo --host mongo2 --eval "print(\"waited for connection\")"
do
    sleep 20
done

until mongo --host mongo3 --eval "print(\"waited for connection\")"
do
    sleep 20
done

echo "Connection finished"
echo "Creating replica set"

MONGO1IP=$(getent hosts mongo1 | awk '{ print $1 }')
MONGO2IP=$(getent hosts mongo2 | awk '{ print $1 }')
MONGO3IP=$(getent hosts mongo3 | awk '{ print $1 }')

echo $MONGO1IP
echo $MONGO2IP
echo $MONGO3IP

mongo --host mongo1:27017 <<EOF
    var config = {
        "_id": "${MONGO_REPLICA_SET_NAME}",
        "members": [
                    {
                        "_id": 1,
                        "host": "${MONGO1IP}:27017",
                        "priority": 3
                    },
                    {
                        "_id": 2,
                        "host": "${MONGO2IP}:27017",
                        "priority": 2
                    },
                    {
                        "_id": 3,
                        "host": "${MONGO3IP}:27017",
                        "priority": 1
                    }
        ]
    };
    rs.initiate(config, { force: true });
    rs.slaveOk();
    rs.status();
EOF