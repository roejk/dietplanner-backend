package com.rojek.dietplanner.service;

import com.rojek.dietplanner.dto.UserDTO;
import com.rojek.dietplanner.entity.User;
import com.rojek.dietplanner.helper.MapHelper;
import com.rojek.dietplanner.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final MapHelper mapHelper;

    public List<UserDTO> getUsers() {
        List<User> users = userRepository.findAll();
        return mapHelper.mapUsersToDTO(users);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new
                UsernameNotFoundException("User " + username + " not found"));
    }
}
