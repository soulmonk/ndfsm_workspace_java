package com.soulmonk.ndfsm.repository.time;

import com.soulmonk.ndfsm.domain.time.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}