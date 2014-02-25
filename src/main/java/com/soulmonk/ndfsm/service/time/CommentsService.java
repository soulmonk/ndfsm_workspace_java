package com.soulmonk.ndfsm.service.time;

import com.soulmonk.ndfsm.domain.time.Comment;

import java.util.List;

public interface CommentsService {
  public List<Comment> findAll();

  public Comment findById(Long id);

  public Comment save(Comment comment);
}
