### stocks-api
    - get list of stock
    - create a stock
    - get one stock from the list
    - update the price of a single stock
    - delete a single stock

### documentation
http://localhost:7098/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config

### How to Run the service in local

1. install docker desktop if not installed previously
2. go to terminal and type :
       docker compose up
3. verify cassandra instance in docker desktop on 9042
4. open a new terminal window and type
       docker ps
5. copy container id 
6. type : 
       docker exec -it <<paste container id>> bash
7. you will see that you are under root .
8. type cqlsh
9. type :
   CREATE KEYSPACE mykeyspace WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 3};
10. type
    use mykeyspace
11. Create table for stock

    CREATE TABLE IF NOT EXISTS Stock (
    ID BIGINT,
    NAME TEXT,
    CURRENT_PRICE DECIMAL,
    LAST_UPDATE TIMESTAMP,
    PRIMARY KEY (ID)
    ) WITH comment='Important stock data';


13. validate select * From stock;
    expected output - 0 records

14.Let's start the spring boot appliction

    mvn clean install ; mvn spring-boot:run

### Access services using a web browser
When the application is running, you can use a web browser to access the REST service. 

15. use open-api url to validate api
    http://localhost:7098/stocks-api.html
    
### Thank You !!

Improvement could be done 
     - security 
     - Caching 
     - Data Validation 
     
