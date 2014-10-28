package com.soulmonk.ndfsm.service.jpa.user;

import com.soulmonk.ndfsm.domain.user.UserRole;
import com.soulmonk.ndfsm.domain.user.UserRoleId;
import com.soulmonk.ndfsm.repository.user.UserRoleRepository;
import com.soulmonk.ndfsm.service.user.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Company: Valpio
 * User: SoulMonk
 * Date: 04.03.14
 * Time: 21:46
 */
@Service("userRoleService")
@Repository
@Transactional
@Secured("ROLE_ADMIN")
public class UserRoleImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public List<UserRole> findAll() {
        return userRoleRepository.findAll();
    }

    @Override
    public UserRole findById(UserRoleId pk) {
        return userRoleRepository.findOne(pk);
    }
/*
  @Override
  public List<UserRole> findByUser(User user) {
    return userRoleRepository.findByUser(user);
  }

  @Override
  public List<UserRole> findByRole(Role role) {
    return userRoleRepository.findByRole(role);
  }
*/

    @Override
    public UserRole save(UserRole userRole) {
        return userRoleRepository.saveAndFlush(userRole);
    }

    @Override
    public void delete(UserRole userRole) {
        userRoleRepository.delete(userRole);
    }
}
