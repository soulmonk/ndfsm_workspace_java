package com.soulmonk.ndfsm.service.time;

import com.soulmonk.ndfsm.domain.time.Projects;

import java.util.List;

public interface ProjectsService {
  public List<Projects> findAll();

  public Projects findById(Long id);

  public Projects save(Projects project);
}
