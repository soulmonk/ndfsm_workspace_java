package com.soulmonk.ndfsm.service.time;

import com.soulmonk.ndfsm.domain.time.Task;
import com.soulmonk.ndfsm.domain.user.User;

import java.util.List;

public interface TaskService {
  public List<Task> findAll();

  public Task findById(Long id);

  public Task save(Task task);

  public void delete(Long id);
}
