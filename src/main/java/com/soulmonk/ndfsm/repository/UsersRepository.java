package com.soulmonk.ndfsm.repository;

import com.soulmonk.ndfsm.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Long> {
  public User findByLogin(String login);
}
