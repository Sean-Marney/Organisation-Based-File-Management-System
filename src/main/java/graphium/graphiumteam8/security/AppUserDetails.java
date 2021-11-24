//package graphium.graphiumteam8.security;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//
//public class AppUserDetails implements UserDetails {
//    private String username;
//    private String password;
//    private List<GrantedAuthority> authorities;
//    private Boolean enabled;
//
//    public AppUserDetails(AppUser user) {
//        this.username = user.getUsername();
//        this.password = user.getPassword();
//        this.enabled = user.getEnabled();
//        this.authorities = Arrays.asList(new SimpleGrantedAuthority(user.getRole()));
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return enabled;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return enabled;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return enabled;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return enabled;
//    }
//
//}
