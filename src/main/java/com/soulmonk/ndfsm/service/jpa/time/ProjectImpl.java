package com.soulmonk.ndfsm.service.jpa.time;

import com.google.common.collect.Lists;
import com.soulmonk.ndfsm.domain.time.Project;
import com.soulmonk.ndfsm.repository.time.ProjectRepository;
import com.soulmonk.ndfsm.security.UserDetailsAdapter;
import com.soulmonk.ndfsm.service.time.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("projectService")
@Repository
@Transactional
public class ProjectImpl implements ProjectService {

  @Autowired
  private ProjectRepository projectRepository;

  @Override
  @Transactional(readOnly = true)
  public List<Project> findAll() {
    return projectRepository.findByUser(UserDetailsAdapter.getLogged().getUser());
  }

  @Override
  @Transactional(readOnly = true)
  public Project findById(Long id) {
    return projectRepository.findOne(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Project save(Project project) {
    if (project.getId() == null) {
      project.setUser(UserDetailsAdapter.getLogged().getUser());
    } else {
      project.setUser(findById(project.getId()).getUser());
    }
    return projectRepository.saveAndFlush(project);
  }

  @Override
  public void delete(Long id) {
    projectRepository.delete(id);
  }

  @Override
  public List<Project> findByServiceId(Long id) {
    return projectRepository.findByServiceId(id);
  }

}
