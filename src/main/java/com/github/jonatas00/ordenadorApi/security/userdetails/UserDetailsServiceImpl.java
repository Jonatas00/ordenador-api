package com.github.jonatas00.ordenadorApi.security.userdetails;

import com.github.jonatas00.ordenadorApi.entities.UserModel;
import com.github.jonatas00.ordenadorApi.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  private UserRepository userRepository;

  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserModel user = userRepository.findByUsername(username);

    return new UserDetailsImpl(user);
  }
}
