package com.soulmonk.ndfsm.service.jpa.time;

import com.google.common.collect.Lists;
import com.soulmonk.ndfsm.domain.time.Comment;
import com.soulmonk.ndfsm.repository.time.CommentRepository;
import com.soulmonk.ndfsm.service.time.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("commentService")
@Repository
@Transactional
public class CommentImpl implements CommentService {

  @Autowired
  private CommentRepository commentRepository;

  @Override
  @Transactional(readOnly = true)
  public List<Comment> findAll() {
    return Lists.newArrayList(commentRepository.findAll());
  }

  @Override
  @Transactional(readOnly = true)
  public Comment findById(Long id) {
    return commentRepository.findOne(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Comment save(Comment comment) {
    return commentRepository.saveAndFlush(comment);
  }

}