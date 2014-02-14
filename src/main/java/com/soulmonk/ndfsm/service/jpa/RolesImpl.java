package com.soulmonk.ndfsm.service.jpa;

import com.google.common.collect.Lists;
import com.soulmonk.ndfsm.domain.Role;
import com.soulmonk.ndfsm.repository.RolesRepository;
import com.soulmonk.ndfsm.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("rolesService")
@Repository
@Transactional
public class RolesImpl implements RolesService {

  @Autowired
  private RolesRepository rolesRepository;

  @Override
  @Transactional(readOnly = true)
  public List<Role> findAll() {
    return Lists.newArrayList(rolesRepository.findAll());
  }

  @Override
  @Transactional(readOnly = true)
  public Role findById(Long id) {
    return rolesRepository.findOne(id);
  }

  @Override
  public Role findByAuthority(String authority) {
    return rolesRepository.findByAuthority(authority);
  }

  @Override
  @Transactional(readOnly = true)
  public Role save(Role role) {
    return rolesRepository.saveAndFlush(role);
  }

}
