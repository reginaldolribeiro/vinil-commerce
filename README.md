# vinil-commerce

Back-end for selling vinyl records.

Use a Spotify API for an initial load of discs by musical genre (Rock, Pop, MPB and Classic).

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
    
  3. (GET) Search sale by ID: 
   - http://localhost:8080/api/sale/1

  4. (POST) Create a new sale: 
   - http://localhost:8080/api/sale
    
    Request body (JSON example)
    
    {
      "customer_id": 1,
      "products": [1,2,3]
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
  7. [Spotify Web API Java](https://github.com/thelinmichael/spotify-web-api-java)
