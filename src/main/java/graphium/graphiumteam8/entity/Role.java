package graphium.graphiumteam8.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Role {
    ADMIN(), USER(), ORGANISATION();

    private final String[] authorities; // TODO: add authorities via constructor if needed...

    Role(String... authorities) {
        this.authorities = authorities;
    }

    public List<GrantedAuthority> getGrantedAuthorities() {
        final List<String> authorities = Arrays
                .stream(this.authorities)
                .collect(Collectors.toList());
        authorities.add("ROLE_" + name());

        return authorities
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
