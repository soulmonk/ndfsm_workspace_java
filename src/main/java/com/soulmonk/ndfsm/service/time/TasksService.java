package com.soulmonk.ndfsm.service.time;

import com.soulmonk.ndfsm.domain.time.Tasks;

import java.util.List;

public interface TasksService {
  public List<Tasks> findAll();

  public Tasks findById(Long id);

  public Tasks save(Tasks task);

  public void delete(Long id);
}
