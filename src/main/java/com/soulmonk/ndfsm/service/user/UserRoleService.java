package com.soulmonk.ndfsm.service.user;

import com.soulmonk.ndfsm.domain.user.UserRole;
import com.soulmonk.ndfsm.domain.user.UserRoleId;

import java.util.List;

/**
 * Company: PolecatSoft
 * User: SoulMonk
 * Date: 04.03.14
 * Time: 21:41
 */
public interface UserRoleService {
    public List<UserRole> findAll();

    public UserRole findById(UserRoleId pk);

  /*public List<UserRole> findByUser(User user);

  public List<UserRole> findByRole(Role role);*/

    public UserRole save(UserRole userRole);

    public void delete(UserRole userRole);
}
