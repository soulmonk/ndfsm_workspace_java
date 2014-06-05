package com.soulmonk.ndfsm.repository.time;

import com.soulmonk.ndfsm.domain.time.Record;
import com.soulmonk.ndfsm.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {
  public List<Record> findByUser(User user);
}
