package com.volia.example.userservice.security;

import com.volia.example.userservice.model.User;
import com.volia.example.userservice.repository.UserRepository; // or your base User repo if exists
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
return null;
        // Map your User entity to Spring Security UserDetails object
        return org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
                .password(user.getPassword()) // encoded password!
                .authorities("ROLE_USER") // adjust roles if you have
                .build();
    }
}

