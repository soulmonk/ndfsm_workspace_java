package com.soulmonk.ndfsm.domain.time;

import com.soulmonk.ndfsm.domain.user.User;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Company: PolecatSoft
 * User: soulmonk
 * Date: 19.08.13
 * Time: 15:43
 */
@Entity
@Table(name = "time_tasks")
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private int sum;
    private String extId;
    private List<ProjectComment> comments = new ArrayList<ProjectComment>();
    private Project project;
    private User user;

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

        Task task = (Task) o;

        if (sum != task.sum) return false;
        if (extId != null ? !extId.equals(task.extId) : task.extId != null) return false;
        if (id != null ? !id.equals(task.id) : task.id != null) return false;
        if (name != null ? !name.equals(task.name) : task.name != null) return false;

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
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, mappedBy = "task")
    public List<ProjectComment> getComments() {
        return comments;
    }

    public void setComments(List<ProjectComment> comments) {
        this.comments = comments;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false)
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
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
