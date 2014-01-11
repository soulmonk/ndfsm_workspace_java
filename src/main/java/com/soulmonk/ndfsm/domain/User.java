package com.soulmonk.ndfsm.domain;

import javax.persistence.*;

/**
 * Company: Valpio
 * User: soulmonk
 * Date: 19.08.13
 * Time: 15:43
 */
@Entity
@Table(name = "users")
public class User {
  private Long id;
  private String name;
  private String login;
  private String password;
  private String email;

  @javax.persistence.Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @javax.persistence.Column(name = "name", nullable = false, insertable = true, updatable = true, length = 127, precision = 0)
  @Basic
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @javax.persistence.Column(name = "login", nullable = false, insertable = true, updatable = true, length = 127, precision = 0)
  @Basic
  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  @javax.persistence.Column(name = "password", nullable = false, insertable = true, updatable = true, length = 255, precision = 0)
  @Basic
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @javax.persistence.Column(name = "email", nullable = false, insertable = true, updatable = true, length = 255, precision = 0)
  @Basic
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    User user = (User) o;

    if (email != null ? !email.equals(user.email) : user.email != null) return false;
    if (id != null ? !id.equals(user.id) : user.id != null) return false;
    if (login != null ? !login.equals(user.login) : user.login != null) return false;
    if (name != null ? !name.equals(user.name) : user.name != null) return false;
    if (password != null ? !password.equals(user.password) : user.password != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (login != null ? login.hashCode() : 0);
    result = 31 * result + (password != null ? password.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "id = " + id + "<br/>name: " + name;
  }
}
