package com.soulmonk.ndfsm.repository.time;

import com.soulmonk.ndfsm.domain.time.Project;
import com.soulmonk.ndfsm.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

  public List<Project> findByServiceId(Long id);

  public List<Project> findByUser(User user);
}
