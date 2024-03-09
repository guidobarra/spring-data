
SHELL=/bin/bash

docker_build:
	docker compose --env-file ./docker/elasticsearch.env -f docker/docker-compose.yml --project-directory ./docker build --no-cache

docker_deploy_elastic: # make nebula_deploy NBL_ENV=test NBL_TAG=_OPTIONAL_PARAM_
	docker compose --env-file ./docker/elasticsearch.env  -f docker/elasticsearch.yml --project-directory ./docker up -d

docker_deploy:
	docker compose --env-file ./docker/elasticsearch.env  -f docker/docker-compose.yml --project-directory ./docker up -d

docker_stop_elastic:
	docker compose --env-file ./docker/elasticsearch.env  -f docker/elasticsearch.yml --project-directory ./docker down

docker_stop:
	docker compose --env-file ./docker/elasticsearch.env  -f docker/docker-compose.yml --project-directory ./docker down

docker_remove_all_volume:
	docker volume rm $(docker volume ls -q)

docker_run:
	docker-compose up --build -V --force-recreate

docker_run_detach:
	docker-compose up --build -V --force-recreate -d && docker-compose logs -f