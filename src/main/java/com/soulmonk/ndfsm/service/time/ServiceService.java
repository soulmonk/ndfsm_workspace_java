package com.soulmonk.ndfsm.service.time;

import com.soulmonk.ndfsm.domain.time.Service;
import com.soulmonk.ndfsm.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ServiceService {
  public List<Service> findAll();

  public Service findById(Long id);

  public Service save(Service service);

  public Page<Service> findAllByPage(Pageable pageable);

  void delete(Long id);
}
