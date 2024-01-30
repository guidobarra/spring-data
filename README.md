# spring-data
to use data spring with different DataSource


# build

``docker-compose -f docker/docker-compose.yml --project-directory ./docker build --no-cache
``

# run

``
docker-compose -f docker/docker-compose.yml --project-directory ./docker up -d
``

# redis

1) into one instance of cluster redis

``
docker exec -it redis_1 bash
``
2) create cluster with replica 1

`` 
redis-cli --cluster create redis_1:6379 redis_2:6379 redis_3:6379 redis_4:6379 redis_5:6379 redis_6:6379 --cluster-replicas 1
``


