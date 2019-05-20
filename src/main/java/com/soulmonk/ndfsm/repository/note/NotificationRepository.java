package com.soulmonk.ndfsm.repository.note;

import com.soulmonk.ndfsm.domain.note.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: SoulMonk
 * Date: 27.02.14
 * Time: 21:24
 */
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
