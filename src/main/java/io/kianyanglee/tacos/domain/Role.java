package io.kianyanglee.tacos.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
public class Role {
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private final String role;

    public enum RoleType {
        ROLE_USER, ROLE_ADMIN
    }
}
