package com.soulmonk.ndfsm.repository.time;

import com.soulmonk.ndfsm.domain.time.ProjectComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskCommentRepository extends JpaRepository<ProjectComment, Long> {

}
