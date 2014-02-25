package com.soulmonk.ndfsm.repository.user;

import com.soulmonk.ndfsm.domain.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
  public Role findByAuthority(String authority);
}
