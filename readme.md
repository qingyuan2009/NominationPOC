#   docker起zookeeper + kafka + postgres
       
    > docker-compose up -d

#   run 

    bootRunLocal   or
    bootRun (you need admin previlege)

#   import data
    POST  http://localhost:8080/api/upload
    use mock/data/data.json
    This project requires tenant info, so pls refer to postman script




