
# Movies MicroService
*Author* : *Sibendra Pratap Singh ; spsingh23021991@gmail.com*

### Project Directory
#### movie-ms-apis 
This is a spring boot based maven project. It contains below APIs:

1. ***GET /movies***  - This API fetches all the movies from repository. By default this API returns first 10 records.
 
**Parameters:**

| Attribute | Param Type |M/O | Data Type | Remarks |
|--|--|--|--|--|
|offset|query|O|Integer|Page number. Default value: 0
| limit|query|O|Integer|Page Limit. Default value: 10


**Response Body:**
| Attribute | M/O | Data Type | Remarks |
|--|--|--|--|
| totalCount | M | Long | Total count of available records
| moreRecords | M | Boolean | true if more records are available
| nextOffset | O | Integer | Next offset to used while calling API
| movies | O | List | Movie object

**Movie:**
| Attribute | M/O | Data Type | Remarks |
|--|--|--|--|
| id | M | string | Unique Movie Id
| name | M | string | Movie Name
| releaseYear | M | Integer | Movie release year
| genre | M | string | Genre of movie
| director | M | string | Director's Name
| rating | O | Double | Movie's rating
| length | O | Integer| Movie's duration in minutes 

Sample:
`{"totalCount":4,"nextOffset":null,"moreRecords":false,"movies":[{"id":1,"name":"Hulk","releaseYear":2010,"genre":"Action","director":"Morris","rating":7.9,"length":125},{"id":2,"name":"Alvin","releaseYear":2015,"genre":"Kids","director":"Chris","rating":5.9,"length":90},{"id":3,"name":"Horror-Drama","releaseYear":2009,"genre":"Hrror","director":"Romy","rating":8.9,"length":130},{"id":4,"name":"ACID","releaseYear":2004,"genre":"Drama","director":"Jacob","rating":9.9,"length":160}]}`

2. ***POST /movies/add*** - This API adds a new movie into repository.

**Request Body -** 
| Attribute | M/O | Data Type | Remarks |
|--|--|--|--|
| name | M | string | Movie Name
| releaseYear | M | Integer | Movie release year
| genre | M | string | Genre of movie
| director | M | string | Director's Name
| rating | O | Double | Movie's rating
| length | O | Integer| Movie's duration in minutes 

Sample:
`{"name":"ACID","releaseYear":2004,"genre":"Drama","director":"Jacob","rating":null,"length":160}`

**Response Body:**

| Attribute | M/O | Data Type | Remarks |
|--|--|--|--|
| id | M | string | Unique Movie Id
| name | M | string | Movie Name
| releaseYear | M | Integer | Movie release year
| genre | M | string | Genre of movie
| director | M | string | Director's Name
| rating | O | Double | Movie's rating
| length | O | Integer| Movie's duration in minutes 


Sample: 
`{"id":2,"name":"ACID","releaseYear":2004,"genre":"Drama","director":"Jacob","rating":null,"length":160}`

3. ***DELETE /movies/{movieId} -*** This API deletes the movie with movie Id from repository permanently.
**Parameters:**

| Attribute | Param Type |M/O | Data Type | Remarks |
|--|--|--|--|--|
|movieId|path|M|Long|Unique movie Id

**Response Body:**
Response Status: 204
Response Body: NO_CONTENT

4. ***GET /movies/{movieId} -*** This API fetches the movie details with movie Id.
 
**Parameters:**
| Attribute | Param Type |M/O | Data Type | Remarks |
|--|--|--|--|--|
|movieId|path|M|Long|Unique movie Id

**Response Body:**

| Attribute | M/O | Data Type | Remarks |
|--|--|--|--|
| id | M | string | Unique Movie Id
| name | M | string | Movie Name
| releaseYear | M | Integer | Movie release year
| genre | M | string | Genre of movie
| director | M | string | Director's Name
| rating | O | Double | Movie's rating
| length | O | Integer| Movie's duration in minutes 

**Build Application:**
go to /movie-ms-apis
run ***mvn clean install***

**Start Application**
1. Go to /movie-ms-apis 
2. Run ***mvnw spring-boot:run*** (windows machine)
3. Access APIs on http://localhost:8080/ via REST Client e.g. Postman


### Dependencies/Prerequisites required to run the application:
1.  Java 1.8+ should be installed.
2.  Maven must be installed.
3. Corporate proxy should be taken care of

### Run Application using Docker Image 
1. *docker pull sibendra30/movie-ms-apis:1.0*
2. *docker container run --publish 8080:8080 --name MovieMS-1 sibendra30/movie-ms-apis:1.0*

## Documentation:
*http://localhost:8080/swagger-ui/index.html*
