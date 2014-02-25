package com.soulmonk.ndfsm.service.user;

import com.soulmonk.ndfsm.domain.user.User;

import java.util.List;

public interface UserService {
  public List<User> findAll();

  public User findById(Long id);

  public User save(User user);

  public User findByLogin(String login);
}
