package co.id.mii.serverside.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class AppUserDetail implements UserDetails {

    private User user;

    public AppUserDetail(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        user.getRoles()
//                .stream().map(role -> new SimpleGrantedAuthority("ROLE_"+role.getName().toUpperCase()))
//                .collect(Collectors.toList());
        Collection<GrantedAuthority> authorityies = new ArrayList<>();
        user.getRoles()
                .forEach(role -> {
                    authorityies.add(new SimpleGrantedAuthority("ROLE_"+role.getName().toUpperCase()));
                    // ROLE_USER, ADMIN
                });
        return authorityies;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !user.getIsAccountLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getIsEnabled();
    }

}
