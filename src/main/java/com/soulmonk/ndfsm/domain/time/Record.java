package com.soulmonk.ndfsm.domain.time;

import com.soulmonk.ndfsm.domain.user.User;

import javax.persistence.*;
import java.io.Serializable;
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

  @Column(name = "id", nullable = false, insertable = true, updatable = true)
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private Long id;

  @Column(name = "date_from", nullable = false, insertable = true, updatable = true)
  private Timestamp dateFrom;

  @Column(name = "date_to", nullable = false, insertable = true, updatable = true)
  private Timestamp dateTo;

  @Column(name = "comment", nullable = false, insertable = true, updatable = true)
  private String comment;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
  private User user;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Record record = (Record) o;

    if (id != null ? !id.equals(record.id) : record.id != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    return result;
  }
}
