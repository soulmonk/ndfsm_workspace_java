package com.soulmonk.ndfsm.repository.time;

import com.soulmonk.ndfsm.domain.time.CommentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentStatusesRepository extends JpaRepository<CommentStatus, Long> {

}
