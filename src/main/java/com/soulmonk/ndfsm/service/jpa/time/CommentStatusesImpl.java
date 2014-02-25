package com.soulmonk.ndfsm.service.jpa.time;

import com.google.common.collect.Lists;
import com.soulmonk.ndfsm.domain.time.CommentStatus;
import com.soulmonk.ndfsm.repository.time.CommentStatusesRepository;
import com.soulmonk.ndfsm.service.time.CommentStatusesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("commentStatusesService")
@Repository
@Transactional
public class CommentStatusesImpl implements CommentStatusesService {

  @Autowired
  private CommentStatusesRepository commentStatusRepository;

  @Override
  @Transactional(readOnly = true)
  public List<CommentStatus> findAll() {
    return Lists.newArrayList(commentStatusRepository.findAll());
  }

  @Override
  @Transactional(readOnly = true)
  public CommentStatus findById(Long id) {
    return commentStatusRepository.findOne(id);
  }

  @Override
  @Transactional(readOnly = true)
  public CommentStatus save(CommentStatus commentStatus) {
    return commentStatusRepository.saveAndFlush(commentStatus);
  }

  @Override
  public void delete(Long id) {
    commentStatusRepository.delete(id);
  }

}
