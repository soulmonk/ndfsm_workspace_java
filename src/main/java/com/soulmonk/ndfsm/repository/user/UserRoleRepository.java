package com.soulmonk.ndfsm.repository.user;

import com.soulmonk.ndfsm.domain.user.Role;
import com.soulmonk.ndfsm.domain.user.User;
import com.soulmonk.ndfsm.domain.user.UserRole;
import com.soulmonk.ndfsm.domain.user.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Company: Valpio
 * User: SoulMonk
 * Date: 04.03.14
 * Time: 21:40
 */
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
/*  public List<UserRole> findByUser(User user);

  public List<UserRole> findByRole(Role role);*/
}
