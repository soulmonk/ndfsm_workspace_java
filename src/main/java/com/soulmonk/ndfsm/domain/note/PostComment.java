package com.soulmonk.ndfsm.domain.note;

import com.soulmonk.ndfsm.domain.user.User;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * User: SoulMonk
 * Date: 27.02.14
 * Time: 19:21
 */
@Entity
@Table(name = "note_comments", schema = "", catalog = "ndfsm")
public class PostComment {
    private Long id;
    private String content;
    private Timestamp dateAdd;
    private Timestamp dateModified;
    private Post post;
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
    @Column(name = "content", nullable = false, insertable = true, updatable = true, length = 2147483647, precision = 0)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostComment comment = (PostComment) o;

        if (content != null ? !content.equals(comment.content) : comment.content != null) return false;
        if (dateAdd != null ? !dateAdd.equals(comment.dateAdd) : comment.dateAdd != null) return false;
        if (dateModified != null ? !dateModified.equals(comment.dateModified) : comment.dateModified != null)
            return false;
        if (id != null ? !id.equals(comment.id) : comment.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (dateAdd != null ? dateAdd.hashCode() : 0);
        result = 31 * result + (dateModified != null ? dateModified.hashCode() : 0);
        return result;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id", referencedColumnName = "id", nullable = false)
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
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
