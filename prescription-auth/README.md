## Prescription auth microservice
This microservice manages registering new users, authenticating them and authorizing them for the other microservices.
Prescription auth runs on port 8301

### Rest API Docs

1) POST /users/register 
- body ClientDTO object

{

  "fullname": "",
  
  "email": "",
  
  "password": "",
  
  "age": ""
  
}

- response boolean for successful/failed operation

2) POST /users/authenticate
- body UserDTO object

{

  "username": "", -> the email of the client
  
  "password": ""
  
}

- response AuthenticatedUserDTO object

{
  "username": "",
  
  "token": ""
  
}

3) POST /users/authorize -> should not be needed in UI
- body AuthenticatedUserDTO object

{

  "username": "",
  
  "token": ""
  
}

- response AuthResponse object

{

  "username": "",
  
  "authorized": boolean
  
}

4) POST /users
- body EmailDTO object

{

  "email": ""
  
}

- response Client object

{

  "id": integer,
  
  "email": "",
  
  "fullName": "",
  
  "age": integer
  
}
