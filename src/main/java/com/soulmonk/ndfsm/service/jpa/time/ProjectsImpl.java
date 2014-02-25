package com.soulmonk.ndfsm.service.jpa.time;

import com.google.common.collect.Lists;
import com.soulmonk.ndfsm.domain.time.Project;
import com.soulmonk.ndfsm.service.time.ProjectsService;
import com.soulmonk.ndfsm.repository.time.ProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("projectsService")
@Repository
@Transactional
public class ProjectsImpl implements ProjectsService {

  @Autowired
  private ProjectsRepository projectsRepository;

  @Override
  @Transactional(readOnly = true)
  public List<Project> findAll() {
    return Lists.newArrayList(projectsRepository.findAll());
  }

  @Override
  @Transactional(readOnly = true)
  public Project findById(Long id) {
    return projectsRepository.findOne(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Project save(Project project) {
    return projectsRepository.saveAndFlush(project);
  }

}
