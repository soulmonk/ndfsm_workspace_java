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
@Table(name = "time_services")
public class Services implements Serializable {
  private Long id;
  private String name;
  private String url;
  private List<Project> projects = new ArrayList<Project>();

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
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @javax.persistence.Column(name = "url", nullable = false, insertable = true, updatable = true, length = 255, precision = 0)
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Services services = (Services) o;

    if (id != null ? !id.equals(services.id) : services.id != null) return false;
    if (name != null ? !name.equals(services.name) : services.name != null) return false;
    if (url != null ? !url.equals(services.url) : services.url != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (url != null ? url.hashCode() : 0);
    return result;
  }

  @Fetch(value = FetchMode.SUBSELECT)
  @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, mappedBy = "service")
  public List<Project> getProjects() {
    return projects;
  }

  public void setProjects(List<Project> projects) {
    this.projects = projects;
  }
}
