#!/bin/bash

docker-compose -f mongo.yml --env-file .env up -d


#docker-compose -f mongo.yml --env-file down