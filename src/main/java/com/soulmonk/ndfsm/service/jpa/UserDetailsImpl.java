package com.soulmonk.ndfsm.service.jpa;

import com.soulmonk.ndfsm.domain.User;
import com.soulmonk.ndfsm.domain.UserDetailsAdapter;
import com.soulmonk.ndfsm.repository.UsersRepository;
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
  private UsersRepository usersRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = usersRepository.findByLogin(username);

    if (user == null) {
      throw new UsernameNotFoundException("No such user: " + username);
    } else if (user.getRoles().isEmpty()) {
      throw new UsernameNotFoundException("User " + username + " has no authorities");
    }
    UserDetailsAdapter userDetails = new UserDetailsAdapter(user);
    userDetails.setPassword(user.getPassword());
    return userDetails;
  }
}
