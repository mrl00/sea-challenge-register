![gh_actions](https://github.com/mrl00/sea-challenge-register/actions/workflows/docker-build-push.yaml/badge.svg) \
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/github%20actions-%232671E5.svg?style=for-the-badge&logo=githubactions&logoColor=white)

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
