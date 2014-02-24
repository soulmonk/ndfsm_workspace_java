package com.soulmonk.ndfsm.repository.time;

import com.soulmonk.ndfsm.domain.time.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasksRepository extends JpaRepository<Tasks, Long> {

}
