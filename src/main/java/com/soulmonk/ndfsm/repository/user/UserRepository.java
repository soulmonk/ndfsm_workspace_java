package com.soulmonk.ndfsm.repository.user;

import com.soulmonk.ndfsm.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  public User findByLogin(String login);
}
