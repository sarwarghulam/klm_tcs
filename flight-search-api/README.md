# REST API

The REST API for a flight domain model app is described below.

## Get 

###URL and  Request object json for getting the details of all flights

`GET localhost:8080/api/v1/flights`

###URL and  Request object json for getting the details of one flight 

`GET localhost:8080/api/v1/flights/{flightNumber}`


###URL and  Request object json for getting the details of all the flight with origin and destination 

`GET localhost:8080/api/v1/flights/{origin}/{destination}`


## Post

###URL and  Request body for creating a flight details 

`POST localhost:8080/api/v1/flights/save`

##Request Object 

{
    "flightNumber":"10",
    "destination":"mumbai",
    "origin":"delhi",
    "duration":607
}


## Put

###URL and  Request body for updating a flight details 

`PUT localhost:8080/api/v1/flights/update/{flightNumber}`

##Request Object 

{
    "flightNumber":"10",
    "destination":"mumbai",
    "origin":"delhi",
    "duration":607
}  


## Patch

###URL and  Request body for patching a flight details 

`PATCH localhost:8080/api/v1/flights/patch/{flightNumber}`

##Request Object 

{
    "flightNumber":"10",
    "destination":"mumbai",
    "origin":"delhi",
    "duration":607
}  


## delete

###URL and  Request body for deleting a flight details 

`DELETE localhost:8080/api/v1/flights/delete/{flightNumber}`


###URL and  Request body for deleting entire flight details

`DELETE localhost:8080/api/v1/flights/delete/`
