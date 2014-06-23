package com.soulmonk.ndfsm.service.note;

import com.soulmonk.ndfsm.domain.note.Notification;

import java.util.List;

/**
 * Company: Valpio
 * User: SoulMonk
 * Date: 27.02.14
 * Time: 21:28
 */
public interface NotificationService {
    public List<Notification> findAll();

    public Notification findById(Long id);

    public Notification save(Notification notification);

    void delete(Long id);
}
