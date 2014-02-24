package com.soulmonk.ndfsm.domain.time;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * Company: Valpio
 * User: soulmonk
 * Date: 19.08.13
 * Time: 15:43
 */
@Entity
@Table(name = "time_comments")
public class Comments implements Serializable {
  private Long id;
  private String value;
  private Timestamp from;
  private Timestamp to;
  private Time timePlus = Time.valueOf("00:00:00");
  private Tasks tasks;
  private CommentStatuses commentStatuses;

  @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(name = "value", nullable = false, insertable = true, updatable = true, length = 65535, precision = 0)
  @Basic
  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Column(name = "`from`", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
  @Basic
  public Timestamp getFrom() {
    return from;
  }

  public void setFrom(Timestamp from) {
    this.from = from;
  }

  @Column(name = "`to`", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
  @Basic
  public Timestamp getTo() {
    return to;
  }

  public void setTo(Timestamp to) {
    this.to = to;
  }

  @Column(name = "time_plus", columnDefinition = "TIME default '00:00:00'", nullable = false, insertable = true, updatable = true, length = 8, precision = 0)
  @Basic
  public Time getTimePlus() {
    return timePlus;
  }

  public void setTimePlus(Time timePlus) {
    this.timePlus = timePlus;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Comments comments = (Comments) o;

    if (from != null ? !from.equals(comments.from) : comments.from != null) return false;
    if (id != null ? !id.equals(comments.id) : comments.id != null) return false;
    if (timePlus != null ? !timePlus.equals(comments.timePlus) : comments.timePlus != null) return false;
    if (to != null ? !to.equals(comments.to) : comments.to != null) return false;
    if (value != null ? !value.equals(comments.value) : comments.value != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (value != null ? value.hashCode() : 0);
    result = 31 * result + (from != null ? from.hashCode() : 0);
    result = 31 * result + (to != null ? to.hashCode() : 0);
    result = 31 * result + (timePlus != null ? timePlus.hashCode() : 0);
    return result;
  }

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "task_id", referencedColumnName = "id", nullable = false)
  public Tasks getTasks() {
    return tasks;
  }

  public void setTasks(Tasks tasks) {
    this.tasks = tasks;
  }

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
  public CommentStatuses getCommentStatuses() {
    return commentStatuses;
  }

  public void setCommentStatuses(CommentStatuses commentStatuses) {
    this.commentStatuses = commentStatuses;
  }
}
