package com.soulmonk.ndfsm.service.jpa.time;

import com.google.common.collect.Lists;
import com.soulmonk.ndfsm.domain.time.Services;
import com.soulmonk.ndfsm.repository.time.ServiceRepository;
import com.soulmonk.ndfsm.service.time.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("serviceService")
@Repository
@Transactional
public class ServiceImpl implements ServiceService {

  @Autowired
  private ServiceRepository serviceRepository;

  @Override
  @Transactional(readOnly = true)
  public List<Services> findAll() {
    return Lists.newArrayList(serviceRepository.findAll());
  }

  @Override
  @Transactional(readOnly = true)
  public Services findById(Long id) {
    return serviceRepository.findOne(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Services save(Services service) {
    return serviceRepository.saveAndFlush(service);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<Services> findAllByPage(Pageable pageable) {
    return serviceRepository.findAll(pageable);
  }

}
