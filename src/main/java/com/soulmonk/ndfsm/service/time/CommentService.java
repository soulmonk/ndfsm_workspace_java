package com.soulmonk.ndfsm.service.time;

import com.soulmonk.ndfsm.domain.time.ProjectComment;

import java.util.List;

public interface CommentService {
  public List<ProjectComment> findAll();

  public ProjectComment findById(Long id);

  public ProjectComment save(ProjectComment comment);

  public void delete(Long id);
}
