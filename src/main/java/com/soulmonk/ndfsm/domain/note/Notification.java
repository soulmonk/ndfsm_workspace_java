package com.soulmonk.ndfsm.domain.note;

import com.soulmonk.ndfsm.domain.user.User;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Company: Valpio
 * User: SoulMonk
 * Date: 27.02.14
 * Time: 19:21
 */
@Entity
@Table(name = "note_notifications", schema = "", catalog = "ndfsm")
public class Notification {
  private Long id;
  private String content;
  private Timestamp dateAdd;
  private Timestamp dateModified;
  private byte status;
  private User user;

  @Id
  @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Basic
  @Column(name = "content", nullable = false, insertable = true, updatable = true, length = 65535, precision = 0)
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @Basic
  @Column(name = "date_add", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
  public Timestamp getDateAdd() {
    return dateAdd;
  }

  public void setDateAdd(Timestamp dateAdd) {
    this.dateAdd = dateAdd;
  }

  @Basic
  @Column(name = "date_modified", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
  public Timestamp getDateModified() {
    return dateModified;
  }

  public void setDateModified(Timestamp dateModified) {
    this.dateModified = dateModified;
  }

  @Basic
  @Column(name = "status", nullable = false, insertable = true, updatable = true, length = 3, precision = 0)
  public byte getStatus() {
    return status;
  }

  public void setStatus(byte status) {
    this.status = status;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Notification that = (Notification) o;

    if (status != that.status) return false;
    if (content != null ? !content.equals(that.content) : that.content != null) return false;
    if (dateAdd != null ? !dateAdd.equals(that.dateAdd) : that.dateAdd != null) return false;
    if (dateModified != null ? !dateModified.equals(that.dateModified) : that.dateModified != null) return false;
    if (id != null ? !id.equals(that.id) : that.id != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (content != null ? content.hashCode() : 0);
    result = 31 * result + (dateAdd != null ? dateAdd.hashCode() : 0);
    result = 31 * result + (dateModified != null ? dateModified.hashCode() : 0);
    result = 31 * result + (int) status;
    return result;
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
