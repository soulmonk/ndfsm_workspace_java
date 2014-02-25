package com.soulmonk.ndfsm.service.time;

import com.soulmonk.ndfsm.domain.time.Services;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ServiceService {
  public List<Services> findAll();

  public Services findById(Long id);

  public Services save(Services service);

  public Page<Services> findAllByPage(Pageable pageable);
}
