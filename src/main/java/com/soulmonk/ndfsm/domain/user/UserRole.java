package com.soulmonk.ndfsm.domain.user;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: soulmonk
 * Date: 01.02.14
 * Time: 12:27
 */
@Entity
@Table(name = "user_role")
@AssociationOverrides({
    @AssociationOverride(name = "pk.user",
        joinColumns = @JoinColumn(name = "user_id")),
    @AssociationOverride(name = "pk.role",
        joinColumns = @JoinColumn(name = "role_id"))})
public class UserRole implements Serializable {
    private static final long serialVersionUID = 1L;

    private UserRoleId pk = new UserRoleId();

    @EmbeddedId
    public UserRoleId getPk() {
        return pk;
    }

    public void setPk(UserRoleId pk) {
        this.pk = pk;
    }

    @Transient
    public User getUser() {
        return getPk().getUser();
    }

    public void setUser(User user) {
        getPk().setUser(user);
    }

    @Transient
    public Role getRole() {
        return getPk().getRole();
    }

    public void setRole(Role role) {
        getPk().setRole(role);
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        UserRole that = (UserRole) o;

        if (getPk() != null ? !getPk().equals(that.getPk())
            : that.getPk() != null)
            return false;

        return true;
    }

    public int hashCode() {
        return (getPk() != null ? getPk().hashCode() : 0);
    }
}
