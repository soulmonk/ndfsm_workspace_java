package com.soulmonk.ndfsm.service.jpa.time;

import com.soulmonk.ndfsm.domain.time.Record;
import com.soulmonk.ndfsm.repository.time.RecordRepository;
import com.soulmonk.ndfsm.security.UserDetailsAdapter;
import com.soulmonk.ndfsm.service.time.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("recordService")
@Repository
@Transactional
public class RecordImpl implements RecordService {

  @Autowired
  private RecordRepository recordRepository;

  @Override
  @Transactional(readOnly = true)
  public List<Record> findAll() {
    return recordRepository.findByUser(UserDetailsAdapter.getLogged().getUser());
  }

  @Override
  @Transactional(readOnly = true)
  public Record findById(Long id) {
    return recordRepository.findOne(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Record save(Record record) {
    if (record.getId() == null) {
      record.setUser(UserDetailsAdapter.getLogged().getUser());
    } else {
      record.setUser(findById(record.getId()).getUser());
    }
    return recordRepository.saveAndFlush(record);
  }

  @Override
  public void delete(Long id) {
    recordRepository.delete(id);
  }

}
