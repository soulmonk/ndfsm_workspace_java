package com.soulmonk.ndfsm.repository.note;

import com.soulmonk.ndfsm.domain.note.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Company: Valpio
 * User: SoulMonk
 * Date: 27.02.14
 * Time: 21:25
 */
public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
}
