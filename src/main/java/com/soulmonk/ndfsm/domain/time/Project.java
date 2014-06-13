package com.soulmonk.ndfsm.domain.time;

import com.soulmonk.ndfsm.domain.user.User;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Company: Valpio
 * User: soulmonk
 * Date: 19.08.13
 * Time: 15:43
 */
@Entity
@Table(name = "time_projects")
public class Project implements Serializable {
  private static final long serialVersionUID = 1L;
  private Long id;
  private String name;
  private Service service;
  private List<Task> tasks = new ArrayList<Task>();
  private User user;

  @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 255, precision = 0)
  @Basic
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Project project = (Project) o;

    if (id != null ? !id.equals(project.id) : project.id != null) return false;
    if (name != null ? !name.equals(project.name) : project.name != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    return result;
  }

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "service_id", insertable = true, updatable = true, referencedColumnName = "id")
  public Service getService() {
    return service;
  }

  public void setService(Service service) {
    this.service = service;
  }

  @Fetch(value = FetchMode.SUBSELECT)
  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "project")
  public List<Task> getTasks() {
    return tasks;
  }

  public void setTasks(List<Task> tasks) {
    this.tasks = tasks;
  }

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
