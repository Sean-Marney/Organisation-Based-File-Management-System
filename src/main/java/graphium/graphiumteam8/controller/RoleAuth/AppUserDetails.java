package graphium.graphiumteam8.controller.RoleAuth;

import org.apache.catalina.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class AppUserDetails implements UserDetails {
    private String username;
    private String password;
    private List<GrantedAuthority> authorities;
    private Boolean enabled;

    public AppUserDetails(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.enabled = user.getEnabled();
        this.authorities = Arrays.asList(new SimpleGrantedAuthority(user.getRole()));
//        this.authorities = user.getRoles();
    }

    @Override
    public String getPassword() {
        return password;
//        return "pass";
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Set<Role> roles=authorities;
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//
//        for(Role role:roles){
//            authorities.add(new SimpleGrantedAuthority(role.getName()));
//        }
//
//        return authorities;
//    }

}
