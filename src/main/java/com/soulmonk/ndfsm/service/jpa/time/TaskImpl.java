package com.soulmonk.ndfsm.service.jpa.time;

import com.google.common.collect.Lists;
import com.soulmonk.ndfsm.domain.time.Task;
import com.soulmonk.ndfsm.repository.time.TaskRepository;
import com.soulmonk.ndfsm.service.time.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("taskService")
@Repository
@Transactional
public class TaskImpl implements TaskService {

  @Autowired
  private TaskRepository taskRepository;

  @Override
  @Transactional(readOnly = true)
  public List<Task> findAll() {
    return Lists.newArrayList(taskRepository.findAll());
  }

  @Override
  @Transactional(readOnly = true)
  public Task findById(Long id) {
    return taskRepository.findOne(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Task save(Task task) {
    return taskRepository.saveAndFlush(task);
  }

  @Override
  public void delete(Long id) {
    taskRepository.delete(id);
  }

}
