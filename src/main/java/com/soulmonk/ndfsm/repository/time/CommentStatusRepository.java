package com.soulmonk.ndfsm.repository.time;

import com.soulmonk.ndfsm.domain.time.CommentStatus;
import com.soulmonk.ndfsm.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentStatusRepository extends JpaRepository<CommentStatus, Long> {

    public List<CommentStatus> findByUser(User user);
}
