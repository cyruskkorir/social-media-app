package com.cyrus.chat;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            User usr = user.get();
            return new UserDetails() {

                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    Set<GrantedAuthority> authorities = usr.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority(role.getName()))
                    .collect(Collectors.toSet());
                    return authorities;
                }

                @Override
                public String getPassword() {
                    return usr.getPassword();
                }

                @Override
                public String getUsername() {
                    return usr.getEmail();
                }
                
            };
        }
        throw new UsernameNotFoundException("No user named "+email);
    }
    
}
