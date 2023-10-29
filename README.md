# Taco Cloud

A sample project based on the examples in the book Spring in Action.

To set which profile(s) to use for application, set environment variable like below. We can have multiple profiles active at the same time.

```sh
export SPRING_PROFILES_ACTIVE=<profile>
```

## Development with H2 DB

The following are default configurations:

- GUI for H2 is available at path `/h2-console`
- DB URL (for in-memory) is `jdbc:h2:mem:tacocloud` (tacocloud because we changed the name in application file, otherwise by default it uses `test`)
- Is not neccessary to define `data.sql` and `schema.sql` explicitly as Spring Data JPA will create tables for us by default.
- After added Spring Security, CSRF needs to be enabled and access permitted and frame disabled (or strict same origin) for devs to access the GUI

## Docker Development

To add your user to the Docker group:

```sh
sudo usermod -aG docker $USER
```

## Developing with Cassandra

Run the following:

```sh
$ docker network create cassandra-net
$ docker run --name my-cassandra \
    --network cassandra-net \
    -p 9042:9042 \
    -d cassandra:latest
# Might need to wait a while for the container to be fully up and running
$ docker run -it --network cassandra-net --rm cassandra cqlsh my-cassandra
```

## Developing with Mongo

Run the following to spin up container for MongoDB:

```sh
docker run -p 27017:27017 -d mongo:latest
```

Skip using Flapdoodle (the embedded DB) as it is not working following instructions provided.

## Spring Security

Add log level `DEBUG` in `application.yml` file for debugging purpose.
The only port for development is 8080 since it will redirects to 8080 no matter what port you set.

Reading on:

### Cross-site Request Forgery (CSRF)

- https://owasp.org/www-community/attacks/csrf
- https://cheatsheetseries.owasp.org/cheatsheets/Cross-Site_Request_Forgery_Prevention_Cheat_Sheet.html

## Rest Endpoints

We can test the endpoints using `cURL`. Below are some examples:

```sh
curl "localhost:8080/data-api/tacos?size=5"
curl "localhost:8080/data-api/tacos?size=5&page=1"
# Post request
curl localhost:8080/ingredients \
    -H "Content-type: application/json" \
    -d '{"id":"FISH","name":"Stinky Fish", "type":"PROTEIN"}'
# Delete request with Basic authentication
curl localhost:8080/api/ingredients/1 -X DELETE  -u admin:password
# Post request with basic authentication
curl localhost:8080/ingredients \
    -H "Content-type: application/json" \
    -d '{"id":"FISH","name":"Stinky Fish", "type":"PROTEIN"}' \
    -u admin:password
```
