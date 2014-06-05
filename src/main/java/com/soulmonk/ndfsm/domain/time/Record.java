package com.soulmonk.ndfsm.domain.time;

import com.soulmonk.ndfsm.domain.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * Company: Valpio
 * User: soulmonk
 * Date: 03.06.14
 * Time: 14:33
 */
@Entity
@Table(name = "time_records")
public class Record implements Serializable {

  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private Long id;

  @Column(name = "task", nullable = false)
  private String task;

  @Column(name = "date_from", nullable = false)
  private Timestamp dateFrom;

  @Column(name = "date_to", nullable = false)
  private Timestamp dateTo;

  @Column(name = "break_time", nullable = false)
  private Time breakTime;

  @Column(name = "dirty_time", nullable = false)
  private Time dirtyTime;

  @Column(name = "comment", nullable = false)
  private String comment;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
  private CommentStatus commentStatus;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Timestamp getDateFrom() {
    return dateFrom;
  }

  public void setDateFrom(Timestamp dateFrom) {
    this.dateFrom = dateFrom;
  }

  public Timestamp getDateTo() {
    return dateTo;
  }

  public void setDateTo(Timestamp dateTo) {
    this.dateTo = dateTo;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public CommentStatus getCommentStatus() {
    return commentStatus;
  }

  public void setCommentStatus(CommentStatus commentStatus) {
    this.commentStatus = commentStatus;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Record record = (Record) o;

    if (id != null ? !id.equals(record.id) : record.id != null) return false;
    if (task != null ? !task.equals(record.task) : record.task != null) return false;
    if (comment != null ? !comment.equals(record.comment) : record.comment != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result += task != null ? task.hashCode() : 0;
    result += comment != null ? comment.hashCode() : 0;
    return result;
  }
}
