package com.soulmonk.ndfsm.repository.time;

import com.soulmonk.ndfsm.domain.time.ProjectComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<ProjectComment, Long> {

}
