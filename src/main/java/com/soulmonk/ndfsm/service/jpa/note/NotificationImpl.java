package com.soulmonk.ndfsm.service.jpa.note;

import com.google.common.collect.Lists;
import com.soulmonk.ndfsm.domain.note.Notification;
import com.soulmonk.ndfsm.repository.note.NotificationRepository;
import com.soulmonk.ndfsm.service.note.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Company: PolecatSoft
 * User: SoulMonk
 * Date: 27.02.14
 * Time: 21:30
 */
@Service("notificationService")
@Repository
@Transactional
public class NotificationImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Notification> findAll() {
        return Lists.newArrayList(notificationRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Notification findById(Long id) {
        return notificationRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Notification save(Notification notification) {
        return notificationRepository.saveAndFlush(notification);
    }

    @Override
    @Transactional(readOnly = true)
    public void delete(Long id) {
        notificationRepository.delete(id);
    }
}
