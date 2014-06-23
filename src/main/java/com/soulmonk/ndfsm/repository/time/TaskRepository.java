package com.soulmonk.ndfsm.repository.time;

import com.soulmonk.ndfsm.domain.time.Task;
import com.soulmonk.ndfsm.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    public List<Task> findByUser(User user);
}
