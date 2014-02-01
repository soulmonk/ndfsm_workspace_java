package com.soulmonk.ndfsm.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Company: Valpio
 * User: soulmonk
 * Date: 01.02.14
 * Time: 12:13
 */
@Entity
@Table(name = "roles")
public class Role implements Serializable {

  private Long id;
  private String name;
  private String authority;

  private List<User> users = new ArrayList<User>();

  @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 127, precision = 0)
  @Basic
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(name = "authority", nullable = false, insertable = true, updatable = true, length = 127, precision = 0)
  @Basic
  public String getAuthority() {
    return authority;
  }

  public void setAuthority(String authority) {
    this.authority = authority;
  }

  @Fetch(value = FetchMode.SUBSELECT)
  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "pk.user")
  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Role role = (Role) o;

    if (id != null ? !id.equals(role.id) : role.id != null) return false;
    if (name != null ? !name.equals(role.name) : role.name != null) return false;
    if (authority != null ? !authority.equals(role.authority) : role.authority != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (authority != null ? authority.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "id = " + id + "<br/>name: " + name;
  }
}
