package com.soulmonk.ndfsm.service.jpa.user;

import com.google.common.collect.Lists;
import com.soulmonk.ndfsm.domain.user.User;
import com.soulmonk.ndfsm.domain.user.UserRole;
import com.soulmonk.ndfsm.repository.user.UserRepository;
import com.soulmonk.ndfsm.service.user.RoleService;
import com.soulmonk.ndfsm.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service("userService")
@Repository
@Transactional
@Secured("ROLE_ADMIN")
public class UserImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Inject
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return Lists.newArrayList(userRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User save(User user) {
        if (user.getId() == null) {
            if (user.getUserRoles().isEmpty()) {
                UserRole userRole = new UserRole();
                userRole.setRole(roleService.findByAuthority("ROLE_USER"));
                userRole.setUser(user);
                user.getUserRoles().add(userRole);
            }
            user.setPasswordChanged(true);
        }
        if (user.isPasswordChanged()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

}
