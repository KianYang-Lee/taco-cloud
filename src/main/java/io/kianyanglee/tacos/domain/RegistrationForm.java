package io.kianyanglee.tacos.domain;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

import io.kianyanglee.tacos.domain.Role.RoleType;
import lombok.Data;

@Data
public class RegistrationForm {

    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;

    public User toUser(PasswordEncoder passwordEncoder) {
        Role role = new Role(RoleType.ROLE_USER.toString());
        List<Role> roles = Arrays.asList(role);
        return new User(username, password, fullname, street, city, state, zip, phone, roles);
    }

}
