package com.soulmonk.ndfsm.security;

import com.soulmonk.ndfsm.domain.user.User;
import com.soulmonk.ndfsm.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Company: Valpio
 * User: soulmonk
 * Date: 01.02.14
 * Time: 11:09
 */
@Service("userDetailsService")
@Transactional(readOnly = true)
public class UserDetailsImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByLogin(username.toLowerCase());
    if (user == null) {
      throw new UsernameNotFoundException("No such user: " + username);
    } else if (user.getUserRoles().isEmpty()) {
      throw new UsernameNotFoundException("User " + username + " has no authorities");
    }
    return new UserDetailsAdapter(user);
  }
}
