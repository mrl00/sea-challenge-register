# Run project
## docker compose
Save **.env** and **docker-compose** in the same folder and run **_docker-compose up_**

#### .env:
```dotenv
API_SERVER_PORT=9000

TEST_DB_DRIVER=org.h2.Driver
TEST_DBASE_URL=jdbc:h2:mem:register
TEST_DBASE_USERNAME=sa
TEST_DBASE_PASSWORD=sa

DBASE_NAME=register
DBASE_HOSTNAME=postgres-register
DBASE_PORT=5432
DBASE_DRIVER=org.postgresql.Driver
DBASE_URL=jdbc:postgresql://${DBASE_HOSTNAME}:${DBASE_PORT}/${DBASE_NAME}
DBASE_USERNAME=postgres
DBASE_PASSWORD=qwert

JWT_SECRET=asdasd
```

#### docker-compose file:
```yaml
version: '3.1'

services:
  sea-challenge-register:
    image: m2ldocker/sea-register:latest
    restart: unless-stopped
    depends_on:
      postgres-register:
        condition: service_healthy
    environment:
      API_SERVER_PORT: ${API_SERVER_PORT}
      DBASE_DRIVER: ${DBASE_DRIVER}
      DBASE_URL: ${DBASE_URL}
      DBASE_USERNAME: ${DBASE_USERNAME}
      DBASE_PASSWORD: ${DBASE_PASSWORD}
    command: "printenv"
    ports:
      - ${API_SERVER_PORT}:8080
    networks:
        - sea_net
    env_file:
      - .env
  
  postgres-register:
    image: postgres
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U postgres"]
      interval: 2s
      timeout: 2s
      retries: 2
    environment:
      POSTGRES_DB: ${DBASE_NAME}
      POSTGRES_PASSWORD: ${DBASE_PASSWORD}
      PGPORT: ${DBASE_PORT}
    networks:
        - sea_net
networks:
  sea_net:
    driver: bridge
```

## Exemplos

### login:
uri: /auth/login \
metodo: POST \
body: 
```json
{
    "userName": "admin",
    "password": "123qwe!@#"
}
```

### registro de usuario:
uri: /auth/register \
metodo: POST \
body: 
```json
{
    "userName": "admin",
    "password": "123qwe!@#",
    "role": "ADMIN"
}
```

### cadastro de client
uri: /register/v1/client
metodo: POST
body: 
```json
{
    "name": "joao ",
    "cpf": "440.882.910-26",
    "emails": [
        "user@mail1.com"
    ],
    "address": {
        "cep": "11.222-333",
        "publicPlace": "logradouro",
        "neighborhood": "bairro",
        "city": "cidade",
        "uf": "DF",
        "complement": "complement"
    },
    "phones": [
        {
            "phoneType": "CeLLPHONE",
            "phone": "(61) 98888-7777"
        },
        {
            "phoneType": "COMMERCIAL",
            "phone": "(61) 8888-7777"
        }
    ]
}
```
### consulta de cliente:
uri: /register/v1/client/{id} \
metodo: GET 

### consulta de cep:
uri: /register/v1/cep/{cep} \
metodo: GET 


## Não concluído:
* test unitarios com web security 
* melhoria no tratamento de erros
* documentacao da api

## Referencias

https://www.baeldung.com/jpa-size-length-column-differences \
https://www.freecodecamp.org/news/unit-testing-services-endpoints-and-repositories-in-spring-boot-4b7d9dc2b772/ \
https://www.baeldung.com/spring-security-registration-password-encoding-bcrypt \
https://pt.stackoverflow.com/questions/185058/o-que-%C3%A9-salt-quando-se-trata-de-criptografia-de-senhas \
https://www.baeldung.com/spring-mvc-custom-validator \
https://www.baeldung.com/spring-validate-list-controller \
https://medium.com/@shreeyaruias/using-enums-in-spring-boot-88d3115fa7ee \
https://www.baeldung.com/spring-validation-message-interpolation \
https://www.baeldung.com/mapstruct \
https://www.baeldung.com/mapstruct-ignore-unmapped-properties \
https://www.baeldung.com/java-mapstruct-mapping-collections \
https://www.baeldung.com/mapstruct-custom-mapper \
https://www.baeldung.com/reactive-streams-step-verifier-test-publisher \
https://www.baeldung.com/spring-validate-list-controller \
https://stackoverflow.com/questions/3236880/assert-equals-between-2-lists-in-junit \
https://stackoverflow.com/questions/3236880/assert-equals-between-2-lists-in-junit \
https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/validation/ObjectError.html \
https://salithachathuranga94.medium.com/validation-and-exception-handling-in-spring-boot-51597b580ffd \
https://www.baeldung.com/spring-boot-h2-database \
https://www.baeldung.com/database-migrations-with-flyway \
https://stackoverflow.com/questions/75940222/cannot-see-h2-database-tables-and-data-via-dbeaver-or-intellij-idea \
https://www.baeldung.com/spring-security-integration-tests \
https://stackoverflow.com/questions/31169720/disable-security-for-unit-tests-with-spring-boot \

https://www.youtube.com/watch?v=5w-YCcOjPD0 \
https://www.youtube.com/watch?v=HMrffmy9DIQ \
https://www.youtube.com/watch?v=ngbKmhXDP4A&t=1166s \
