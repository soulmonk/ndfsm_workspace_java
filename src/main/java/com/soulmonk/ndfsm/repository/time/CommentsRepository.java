package com.soulmonk.ndfsm.repository.time;

import com.soulmonk.ndfsm.domain.time.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments, Long> {

}
