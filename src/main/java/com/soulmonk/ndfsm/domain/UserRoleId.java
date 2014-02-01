package com.soulmonk.ndfsm.domain;

import javax.persistence.Embeddable;
import javax.persistence.ManyToMany;
import java.io.Serializable;

/**
 * Company: Valpio
 * User: soulmonk
 * Date: 01.02.14
 * Time: 12:38
 */
@Embeddable
public class UserRoleId implements Serializable {

  private User user;
  private Role role;

  @ManyToMany
  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @ManyToMany
  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserRoleId userRole = (UserRoleId) o;

    if (user != null ? !user.equals(userRole.user) : userRole.user != null) return false;
    if (role != null ? !role.equals(userRole.role) : userRole.role != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = (user != null ? user.hashCode() : 0);
    result = 31 * result + (role != null ? role.hashCode() : 0);
    return result;
  }
}
