# Cinema service

Java 21 / Spring Boot. Контракт и инструкции: [README проекта](../README.md), [api.yaml](../api.yaml).

Из корня репозитория:

```shell
mvn -f service/pom.xml clean verify
mvn -f service/pom.xml spring-boot:run
```

`api/` и `dto/` генерируются. `web/`, `security/`, `application/` поддерживаются отдельно. Бизнес-операции пока возвращают `501 Not Implemented`.
