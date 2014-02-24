package com.soulmonk.ndfsm.service.jpa.time;

import com.google.common.collect.Lists;
import com.soulmonk.ndfsm.domain.time.Services;
import com.soulmonk.ndfsm.repository.time.ServicesRepository;
import com.soulmonk.ndfsm.service.time.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("servicesService")
@Repository
@Transactional
public class ServicesImpl implements ServicesService {

  @Autowired
  private ServicesRepository servicesRepository;

  @Override
  @Transactional(readOnly = true)
  public List<Services> findAll() {
    return Lists.newArrayList(servicesRepository.findAll());
  }

  @Override
  @Transactional(readOnly = true)
  public Services findById(Long id) {
    return servicesRepository.findOne(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Services save(Services service) {
    return servicesRepository.saveAndFlush(service);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<Services> findAllByPage(Pageable pageable) {
    return servicesRepository.findAll(pageable);
  }

}
