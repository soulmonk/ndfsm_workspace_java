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
@Table(name = "time_tasks")
public class Tasks implements Serializable {
  private Long id;
  private String name;
  private int sum;
  private String extId;
  private List<Comments> comments = new ArrayList<Comments>();
  private Projects projects;

  @javax.persistence.Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @javax.persistence.Column(name = "name", nullable = false, insertable = true, updatable = true, length = 255, precision = 0)
  @Basic
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @javax.persistence.Column(name = "sum", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
  @Basic
  public int getSum() {
    return sum;
  }

  public void setSum(int sum) {
    this.sum = sum;
  }

  @javax.persistence.Column(name = "ext_id", nullable = false, insertable = true, updatable = true, length = 32, precision = 0)
  @Basic
  public String getExtId() {
    return extId;
  }

  public void setExtId(String extId) {
    this.extId = extId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Tasks tasks = (Tasks) o;

    if (sum != tasks.sum) return false;
    if (extId != null ? !extId.equals(tasks.extId) : tasks.extId != null) return false;
    if (id != null ? !id.equals(tasks.id) : tasks.id != null) return false;
    if (name != null ? !name.equals(tasks.name) : tasks.name != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + sum;
    result = 31 * result + (extId != null ? extId.hashCode() : 0);
    return result;
  }

  @Fetch(value = FetchMode.SUBSELECT)
  @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, mappedBy = "tasks")
  public List<Comments> getComments() {
    return comments;
  }

  public void setComments(List<Comments> comments) {
    this.comments = comments;
  }

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false)
  public Projects getProjects() {
    return projects;
  }

  public void setProjects(Projects projects) {
    this.projects = projects;
  }
}
