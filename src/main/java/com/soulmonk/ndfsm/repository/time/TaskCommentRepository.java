package com.soulmonk.ndfsm.repository.time;

import com.soulmonk.ndfsm.domain.time.ProjectComment;
import com.soulmonk.ndfsm.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskCommentRepository extends JpaRepository<ProjectComment, Long> {

    public List<ProjectComment> findByUser(User user);
}
