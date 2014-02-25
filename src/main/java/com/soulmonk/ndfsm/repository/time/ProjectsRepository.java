package com.soulmonk.ndfsm.repository.time;

import com.soulmonk.ndfsm.domain.time.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectsRepository extends JpaRepository<Project, Long> {

}
