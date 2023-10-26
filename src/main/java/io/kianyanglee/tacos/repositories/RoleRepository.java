package io.kianyanglee.tacos.repositories;

import org.springframework.data.repository.CrudRepository;

import io.kianyanglee.tacos.domain.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
    public Role findByRole(String role);
}
