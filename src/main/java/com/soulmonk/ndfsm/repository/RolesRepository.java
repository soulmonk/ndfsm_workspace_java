package com.soulmonk.ndfsm.repository;

import com.soulmonk.ndfsm.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Role, Long> {
  public Role findByAuthority(String authority);
}
