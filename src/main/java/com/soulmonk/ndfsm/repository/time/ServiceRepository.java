package com.soulmonk.ndfsm.repository.time;

import com.soulmonk.ndfsm.domain.time.Service;
import com.soulmonk.ndfsm.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Long> {

  public List<Service> findByUser(User user);

  public Service findByIdAndUser(Long id, User user);
}
