# spring-elasticsearch

## Build

* create image docker, only app java

``
docker compose --env-file ./docker/elasticsearch.env -f docker/docker-compose.yml --project-directory ./docker build --no-cache
``

## run app

* run stack elastic without credentials for run local
```
docker compose --env-file ./docker/elasticsearch.env  -f docker/elasticsearch.yml --project-directory ./docker up -d
```

* run stack elastic with credentials and app java 
```
docker compose --env-file ./docker/elasticsearch.env  -f docker/docker-compose.yml --project-directory ./docker up -d
```