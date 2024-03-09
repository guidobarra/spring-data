# spring-elasticsearch

## Build

* create image docker, only app java

    ```
    make docker_build
    ```

## run app

* run stack elastic without credentials for run local
    ```
    make docker_deploy_elastic
    ```

* run stack elastic with credentials and app java 
    ```
    make docker_deploy
    ```

## swagger

[link](http://localhost:9292/elasticsearch/swagger-ui/index.html)

## kibana

[link](http://localhost:5601/login?next=%2F)