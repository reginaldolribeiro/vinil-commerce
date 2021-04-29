# vinil-commerce

Back-end for selling vinyl records.

#### Cashback

| Genre   | Sunday  | Monday  | Tuesday | Wednesday | Thursday | Friday | Saturday |
|---------|---------|---------|---------|-----------|----------|--------|----------|
| POP     | 25%     | 7%      | 6%      | 2%        | 10%      | 15%    | 20%      |
| MPB     | 30%     | 5%      | 10%     | 15%       | 20%      | 25%    | 30%      |
| CLASSIC | 35%     | 3%      | 5%      | 8%        | 13%      | 18%    | 25%      |
| ROCK    | 40%     | 10%     | 15%     | 15%       | 15%      | 20%    | 40%      |

  #### Endpoints REST
  1. (GET) Search discs by genre or all of them
   - by genre: http://localhost:8080/api/album?genre=rock
   - all discs: http://localhost:8080/api/album

  2. (GET) Search disc by ID: 
   - http://localhost:8080/api/album/200
    
  3. (GET) Search all sales or by ID: 
   - http://localhost:8080/api/sale
   - http://localhost:8080/api/sale/1     

  4. (POST) Create a new sale: 
   - http://localhost:8080/api/sale
    
    Request body
    
    {
      "customer_id": 1,
      "products": [1,2,3]
    }
    
  5. (GET) Search all customers or customer by ID
   - http://localhost:8080/api/customer
   - http://localhost:8080/api/customer/1

  6. (POST) Create customer
   - http://localhost:8080/api/customer

  7. (PUT) Update customer
   - http://localhost:8080/api/customer/1
       
    Request body
    
    {
      "name": "Beltrano da Silveira",
      "email": "beltranosilveira@gmail.com",
      "birth_date": "1990-09-23"
    }
   
   
  #### Setup
  1. Start docker: docker-compose -f docker/docker-compose.yaml up -d
  2. Run project: ./gradlew bootRun
  3. Access http://localhost:8080/api/
  
  #### Technologies
  1. Kotlin
  2. Spring Boot
  3. Unit tests
  4. Gradle
  5. Postgres
  6. [Flyway](https://flywaydb.org/)
