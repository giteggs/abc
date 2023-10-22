# Cloning the code
User `git clone` to clone into you local directory

# Perform the cleanup
`mvn clean`

# Spin up Mysql
Execute `.\d_mysql.bat` to bring up mysql server

# Run the spring boot app
Execute below command from project root folder to access the application using the localhost:8080
  - `docker-compose up -d --build`

# Logs
Execute below commands to see the logs
  - `docker logs ecommerce-spring-boot-app-1` to see spring boot app logs
  - `docker logs ecommerce-mysql-1` to see mysql logs

# Add User
- `URL`: localhost:8080/api/v1/user/signup
- `HTTP METHOD`: POST
- `Request Body`
  ```
  {
      "userName": "test",
      "password": "123",
      "role": "ADMIN",
      "enabled": 1
  }
  ```
  ![image](https://github.com/giteggs/abc/assets/47471292/d6c9c805-46b0-404e-b520-6d03ca42cba3)

# Get Token
- `URL`: localhost:8080/oauth/token?grant_type=password&username=test&password=123
- `HTTP METHOD`: POST
- `Authorization`: Basic Auth
     `Username: fooClientId`
     `Password: secret`
![image](https://github.com/giteggs/abc/assets/47471292/59d85cf6-0e43-4e0e-9767-b5a55f4da99e)

# Get Logistic Details
- `URL`: localhost:8080/api/v1/abc/logistics/info
- `HTTP METHOD`: POST
- `Authorization: Bearer Token` : use the token from Get Token method
- `Request Body`:
    ```
    {
    "originCountry":"Mal",
    "originState":"Kuala Lumpur",
    "originPostcode":"50000",
    "destinationCountry": "MY",
    "destinationState":"Kuala Lumpur",
    "destinationPostcode":"50470",
    "length":11,
    "width":11,
    "height":12,
    "selectedType":1,
    "parcelWeight":14
   }

    ```

    ![image](https://github.com/giteggs/abc/assets/47471292/0b538ef0-8a24-4c39-a307-8aa33b400fe0)

    ![image](https://github.com/giteggs/abc/assets/47471292/8062a35e-b917-42a7-955b-d6279c1eab26)
