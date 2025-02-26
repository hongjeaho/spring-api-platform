# service platform Project

## Docker install

### Mysql

```
docker-compose up -d
```

### MySQL 접속 정보

* host : localhost
* port : 3306
* username : root
* password : root
* database : store

## Data Migration (flyway)

최소 실행시 전체 데이터베이스 초기화 및 jooq 스키마 생성 필요

```
./gradlew flywayClean generateJooq
```

이후 부터 generateJooq 실행 하여 DB 스키마만 및 jooq 최신화.

```
./gradlew generateJooq
```