package com.soulmonk.ndfsm.service.jpa.user;

import com.google.common.collect.Lists;
import com.soulmonk.ndfsm.domain.user.Role;
import com.soulmonk.ndfsm.repository.user.RoleRepository;
import com.soulmonk.ndfsm.service.user.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("roleService")
@Repository
@Transactional
public class RoleImpl implements RoleService {

  @Autowired
  private RoleRepository roleRepository;

  @Override
  @Transactional(readOnly = true)
  public List<Role> findAll() {
    return Lists.newArrayList(roleRepository.findAll());
  }

  @Override
  @Transactional(readOnly = true)
  public Role findById(Long id) {
    return roleRepository.findOne(id);
  }

  @Override
  public Role findByAuthority(String authority) {
    return roleRepository.findByAuthority(authority);
  }

  @Override
  @Transactional(readOnly = true)
  public Role save(Role role) {
    return roleRepository.saveAndFlush(role);
  }

}
