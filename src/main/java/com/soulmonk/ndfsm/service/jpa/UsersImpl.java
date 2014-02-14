package com.soulmonk.ndfsm.service.jpa;

import com.google.common.collect.Lists;
import com.soulmonk.ndfsm.domain.User;
import com.soulmonk.ndfsm.domain.UserRole;
import com.soulmonk.ndfsm.repository.UsersRepository;
import com.soulmonk.ndfsm.service.RolesService;
import com.soulmonk.ndfsm.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service("usersService")
@Repository
@Transactional
public class UsersImpl implements UsersService {

  @Autowired
  private UsersRepository usersRepository;

  @Autowired
  private RolesService rolesService;

  @Inject
  private PasswordEncoder passwordEncoder;

  @Override
  @Transactional(readOnly = true)
  public List<User> findAll() {
    return Lists.newArrayList(usersRepository.findAll());
  }

  @Override
  @Transactional(readOnly = true)
  public User findById(Long id) {
    return usersRepository.findOne(id);
  }

  @Override
  @Transactional(readOnly = true)
  public User save(User user) {
    if (user.getId() == null) {
      if (user.getUserRoles().isEmpty()) {
        UserRole userRole = new UserRole();
        userRole.setRole(rolesService.findByAuthority("ROLE_USER"));
        userRole.setUser(user);
        user.getUserRoles().add(userRole);
      }
      user.setPassword(passwordEncoder.encode(user.getPassword()));
    }
    return usersRepository.saveAndFlush(user);
  }

  @Override
  public User findByLogin(String login) {
    return usersRepository.findByLogin(login);
  }

}
