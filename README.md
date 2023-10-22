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
# Get Token
- `URL`: localhost:8080/oauth/token?grant_type=password&username=test&password=123
- `HTTP METHOD`: POST
- `Authorization`: Basic Auth
     `Username: fooClientId`
     `Password: secret`

