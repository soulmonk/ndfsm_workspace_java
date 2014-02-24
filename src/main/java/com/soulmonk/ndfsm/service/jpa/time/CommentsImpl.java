package com.soulmonk.ndfsm.service.jpa.time;

import com.google.common.collect.Lists;
import com.soulmonk.ndfsm.domain.time.Comments;
import com.soulmonk.ndfsm.repository.time.CommentsRepository;
import com.soulmonk.ndfsm.service.time.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("commentsService")
@Repository
@Transactional
public class CommentsImpl implements CommentsService {

  @Autowired
  private CommentsRepository commentsRepository;

  @Override
  @Transactional(readOnly = true)
  public List<Comments> findAll() {
    return Lists.newArrayList(commentsRepository.findAll());
  }

  @Override
  @Transactional(readOnly = true)
  public Comments findById(Long id) {
    return commentsRepository.findOne(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Comments save(Comments comment) {
    return commentsRepository.saveAndFlush(comment);
  }

}
