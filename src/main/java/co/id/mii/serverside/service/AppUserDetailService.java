package co.id.mii.serverside.service;

import co.id.mii.serverside.model.AppUserDetail;
import co.id.mii.serverside.model.User;
import co.id.mii.serverside.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserDetailService implements UserDetailsService{

    private UserRepository UserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = UserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username or Password incorrect!"));
        return new AppUserDetail(user);
    }

}