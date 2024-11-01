package eu.kekx.long_game.service.security;

import eu.kekx.long_game.persistence.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository.findById(Long.valueOf(email))
                .orElseThrow(() -> new UsernameNotFoundException("User with id [" + email + "] not found"));

        return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
