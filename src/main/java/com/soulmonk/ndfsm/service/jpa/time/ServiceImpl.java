package com.soulmonk.ndfsm.service.jpa.time;

import com.google.common.collect.Lists;
import com.soulmonk.ndfsm.domain.time.Service;
import com.soulmonk.ndfsm.domain.user.User;
import com.soulmonk.ndfsm.repository.time.ServiceRepository;
import com.soulmonk.ndfsm.security.UserDetailsAdapter;
import com.soulmonk.ndfsm.service.time.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Service("serviceService")
@Repository
@Transactional
public class ServiceImpl implements ServiceService {

  @Autowired
  private ServiceRepository serviceRepository;

  @Override
  @Transactional(readOnly = true)
  public List<Service> findAll() {
    return serviceRepository.findByUser(UserDetailsAdapter.getLogged().getUser());
  }

  @Override
  @Transactional(readOnly = true)
  public Service findById(Long id) {
    return serviceRepository.findByIdAndUser(id, UserDetailsAdapter.getLogged().getUser());
  }

  @Override
  @Transactional(readOnly = true)
  public Service save(Service service) {
    if (service.getId() == null) {
      service.setUser(UserDetailsAdapter.getLogged().getUser());
    } else {
      service.setUser(findById(service.getId()).getUser());
    }
    return serviceRepository.saveAndFlush(service);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<Service> findAllByPage(Pageable pageable) {
    return serviceRepository.findAll(pageable);
  }

  @Override
  public void delete(Long id) {
    serviceRepository.delete(id);
  }
}
