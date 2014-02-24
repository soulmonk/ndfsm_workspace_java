package com.soulmonk.ndfsm.domain.time;

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
public class Projects implements Serializable {
  private Long id;
  private String name;
  private Services service;
  private List<Tasks> tasks = new ArrayList<Tasks>();

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

    Projects projects = (Projects) o;

    if (id != null ? !id.equals(projects.id) : projects.id != null) return false;
    if (name != null ? !name.equals(projects.name) : projects.name != null) return false;

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
  public Services getService() {
    return service;
  }

  public void setService(Services service) {
    this.service = service;
  }

  @Fetch(value = FetchMode.SUBSELECT)
  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "projects")
  public List<Tasks> getTasks() {
    return tasks;
  }

  public void setTasks(List<Tasks> tasks) {
    this.tasks = tasks;
  }
}
