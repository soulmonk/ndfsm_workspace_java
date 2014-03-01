package com.soulmonk.ndfsm.service.jpa.user;

import com.google.common.collect.Lists;
import com.soulmonk.ndfsm.domain.user.User;
import com.soulmonk.ndfsm.domain.user.UserRole;
import com.soulmonk.ndfsm.repository.user.UserRepository;
import com.soulmonk.ndfsm.service.user.RoleService;
import com.soulmonk.ndfsm.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service("userService")
@Repository
@Transactional
public class UserImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleService roleService;

  @Inject
  private PasswordEncoder passwordEncoder;

  @Override
  @Transactional(readOnly = true)
  public List<User> findAll() {
    return Lists.newArrayList(userRepository.findAll());
  }

  @Override
  @Transactional(readOnly = true)
  public User findById(Long id) {
    return userRepository.findOne(id);
  }

  @Override
  @Transactional(readOnly = true)
  public User save(User user) {
    Logger logger = LoggerFactory.getLogger(getClass());
    if (user.getId() == null) {
      if (user.getUserRoles().isEmpty()) {
        UserRole userRole = new UserRole();
        userRole.setRole(roleService.findByAuthority("ROLE_USER"));
        userRole.setUser(user);
        user.getUserRoles().add(userRole);
      }
      logger.debug("Password Before: " + user.getPassword());
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      logger.debug("Password After: " + user.getPassword());
    }
    return userRepository.saveAndFlush(user);
  }

  @Override
  public User findByLogin(String login) {
    return userRepository.findByLogin(login);
  }

}
