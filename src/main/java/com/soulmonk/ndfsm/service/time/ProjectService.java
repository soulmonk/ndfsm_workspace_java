package com.soulmonk.ndfsm.service.time;

import com.soulmonk.ndfsm.domain.time.Project;

import java.util.List;

public interface ProjectService {
    public List<Project> findAll();

    public Project findById(Long id);

    public Project save(Project project);

    void delete(Long id);

    public List<Project> findByServiceId(Long id);
}
