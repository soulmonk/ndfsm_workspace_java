package com.soulmonk.ndfsm.domain.time;

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
@javax.persistence.Table(name = "time_comment_statuses")
@Entity
public class CommentStatuses implements Serializable {
  private Long id;
  private String name;
  private String description;
  private String color;
  private List<Comments> comments = new ArrayList<Comments>();

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

  @Column(name = "description", nullable = false, insertable = true, updatable = true, length = 65535, precision = 0)
  @Basic
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Column(name = "color", nullable = false, insertable = true, updatable = true, length = 6, precision = 0)
  @Basic
  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    CommentStatuses that = (CommentStatuses) o;

    if (color != null ? !color.equals(that.color) : that.color != null) return false;
    if (description != null ? !description.equals(that.description) : that.description != null) return false;
    if (id != null ? !id.equals(that.id) : that.id != null) return false;
    if (name != null ? !name.equals(that.name) : that.name != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (description != null ? description.hashCode() : 0);
    result = 31 * result + (color != null ? color.hashCode() : 0);
    return result;
  }

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "commentStatuses")
  public List<Comments> getComments() {
    return comments;
  }

  public void setComments(List<Comments> comments) {
    this.comments = comments;
  }

  @Transient
  public String getColorHtml() {
    return "#" + color;
  }
}
