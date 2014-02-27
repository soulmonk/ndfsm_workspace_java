package com.soulmonk.ndfsm.service.note;

import com.soulmonk.ndfsm.domain.note.PostComment;

import java.util.List;

/**
 * Company: Valpio
 * User: SoulMonk
 * Date: 27.02.14
 * Time: 21:29
 */
public interface PostCommentService {
  public List<PostComment> findAll();

  public PostComment findById(Long id);

  public PostComment save(PostComment postComment);

  void delete(Long id);
}
