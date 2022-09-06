
#   How to run 

##  run on docker
    
    1.  kafka.properties -> apply kafka.properties.docker
    2.  postgres.properties -> apply postgres.properites.docker
    3.  > docker-compose up -d
    bootRunLocal   or
    bootRun (you need admin previlege)

##  run on local 
    
    1.  start your zookeeper on local, e.g.: 1-zookeeper-server.cmd (batch folder)
    2.  start your kafka on local, e.g.: 2-kafka-server.cmd (batch folder)
    3.  kafka.properties -> apply kafka.properties.local
    4.  postgres.properties -> apply postgres.properites.local
    bootRunLocal   or
    bootRun (you need admin previlege)    

#   import data
    POST  http://localhost:8082/api/upload (not using 8080, because local zookeeper will occupy 8080 )
    use mock/data/data.json
    This project requires tenant info, so pls refer to postman script






