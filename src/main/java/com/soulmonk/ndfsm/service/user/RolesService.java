package com.soulmonk.ndfsm.service.user;

import com.soulmonk.ndfsm.domain.user.Role;

import java.util.List;

public interface RolesService {
  public List<Role> findAll();

  public Role findById(Long id);

  public Role findByAuthority(String authority);

  public Role save(Role role);
}
