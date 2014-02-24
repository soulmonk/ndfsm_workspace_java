package com.soulmonk.ndfsm.service.time;

import com.soulmonk.ndfsm.domain.time.Comments;

import java.util.List;

public interface CommentsService {
  public List<Comments> findAll();

  public Comments findById(Long id);

  public Comments save(Comments comment);
}
