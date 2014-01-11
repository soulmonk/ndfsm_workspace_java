package com.soulmonk.ndfsm.service.jpa;

import com.google.common.collect.Lists;
import com.soulmonk.ndfsm.domain.User;
import com.soulmonk.ndfsm.repository.UsersRepository;
import com.soulmonk.ndfsm.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("usersService")
@Repository
@Transactional
public class UsersImpl implements UsersService {
	
	@Autowired
	private UsersRepository usersRepository;

	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() {
		return Lists.newArrayList(usersRepository.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public User findById(Long id) {
		return usersRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public User save(User user) {
		return usersRepository.saveAndFlush(user);
	}

}
