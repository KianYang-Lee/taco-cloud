# Taco Cloud

A sample project based on the examples in the book Spring in Action.

## Development with H2 DB

The following are default configurations:

- GUI for H2 is available at path `/h2-console`
- DB URL (for in-memory) is `jdbc:h2:mem:tacocloud` (tacocloud because we changed the name in application file, otherwise by default it uses `test`)
- Is not neccessary to define `data.sql` and `schema.sql` explicitly as Spring Data JPA will create tables for us by default.