# Taco Cloud

A sample project based on the examples in the book Spring in Action.

## Development with H2 DB

The following are default configurations:

- GUI for H2 is available at path `/h2-console`
- DB URL (for in-memory) is `jdbc:h2:mem:tacocloud` (tacocloud because we changed the name in application file, otherwise by default it uses `test`)
- Is not neccessary to define `data.sql` and `schema.sql` explicitly as Spring Data JPA will create tables for us by default.

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