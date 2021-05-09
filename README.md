# kt-mn-bolt

Kotlin + Micronaut + Bolt

## Requirement

- OpenJDK 11
- direnv

### For IntelliJ IDEA Users
- plugins
  - EnvFile

## For Developer

```shell
$ cp .env.sample .env
$ direnv allow
```

## For Local Development

### Execute Application

- In the Shell
  - Execute `./gradlew :app:run`
- In the IntelliJ IDEA
  - Run `kt-mn-bolt:app [run]`

### ngrok

```shell
$ ngrok http 8080
```
