package com.soulmonk.ndfsm.service.jpa.time;

import com.google.common.collect.Lists;
import com.soulmonk.ndfsm.domain.time.Tasks;
import com.soulmonk.ndfsm.repository.time.TasksRepository;
import com.soulmonk.ndfsm.service.time.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("tasksService")
@Repository
@Transactional
public class TasksImpl implements TasksService {
	
	@Autowired
	private TasksRepository tasksRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Tasks> findAll() {
		return Lists.newArrayList(tasksRepository.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public Tasks findById(Long id) {
		return tasksRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Tasks save(Tasks task) {
		return tasksRepository.saveAndFlush(task);
	}

  @Override
  public void delete(Long id) {
    tasksRepository.delete(id);
  }

}
