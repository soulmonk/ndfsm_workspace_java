package com.soulmonk.ndfsm.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Company: Valpio
 * User: soulmonk
 * Date: 19.08.13
 * Time: 15:43
 */
@Entity
@Table(name = "users")
public class User implements Serializable {
  private Long id;
  private String firstName;
  private String lastName;
  private String login;
  private String password;
  private String email;
  private Boolean enabled;
  private Timestamp dateCreate;
  private Timestamp dateModified;

  private List<Role> roles = new ArrayList<Role>();

  @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(name = "first_name", nullable = false, insertable = true, updatable = true, length = 127, precision = 0)
  @Basic
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @Column(name = "last_name", nullable = false, insertable = true, updatable = true, length = 127, precision = 0)
  @Basic
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Column(name = "last_name", insertable = false, updatable = false)
  public String getFullName() {
    return firstName + ' ' + lastName;
  }

  public void setFullName(String fullName) {
  }

  @Column(name = "login", nullable = false, insertable = true, updatable = true, length = 127, precision = 0)
  @Basic
  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  @Column(name = "password", nullable = false, insertable = true, updatable = true, length = 255, precision = 0)
  @Basic
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Column(name = "email", nullable = false, insertable = true, updatable = true, length = 255, precision = 0)
  @Basic
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Column(name = "enable", nullable = false, insertable = true, updatable = true, length = 1, precision = 0)
  @Basic
  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  @Column(name = "date_create", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
  @Basic
  public Timestamp getDateCreate() {
    return dateCreate;
  }

  public void setDateCreate(Timestamp dateCreate) {
    this.dateCreate = dateCreate;
  }

  @Column(name = "date_modified", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
  @Basic
  public Timestamp getDateModified() {
    return dateModified;
  }


  @Fetch(value = FetchMode.SUBSELECT)
  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "pk.role")
  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

  public void setDateModified(Timestamp dateModified) {
    this.dateModified = dateModified;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    User user = (User) o;

    if (email != null ? !email.equals(user.email) : user.email != null) return false;
    if (id != null ? !id.equals(user.id) : user.id != null) return false;
    if (login != null ? !login.equals(user.login) : user.login != null) return false;
    if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
    if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
    if (password != null ? !password.equals(user.password) : user.password != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
    result = 31 * result + (login != null ? login.hashCode() : 0);
    result = 31 * result + (password != null ? password.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "id = " + id + "<br/>fullName: " + getFullName();
  }
}
