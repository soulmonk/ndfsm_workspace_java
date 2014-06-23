package com.soulmonk.ndfsm.repository.note;

import com.soulmonk.ndfsm.domain.note.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Company: Valpio
 * User: SoulMonk
 * Date: 27.02.14
 * Time: 21:22
 */
public interface PostRepository extends JpaRepository<Post, Long> {
}
