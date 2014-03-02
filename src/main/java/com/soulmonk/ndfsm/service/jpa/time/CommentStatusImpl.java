package com.soulmonk.ndfsm.service.jpa.time;

import com.google.common.collect.Lists;
import com.soulmonk.ndfsm.domain.time.CommentStatus;
import com.soulmonk.ndfsm.repository.time.CommentStatusRepository;
import com.soulmonk.ndfsm.security.UserDetailsAdapter;
import com.soulmonk.ndfsm.service.time.CommentStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("commentStatusService")
@Repository
@Transactional
public class CommentStatusImpl implements CommentStatusService {

  @Autowired
  private CommentStatusRepository commentStatusRepository;

  @Override
  @Transactional(readOnly = true)
  public List<CommentStatus> findAll() {
    return commentStatusRepository.findByUser(UserDetailsAdapter.getLogged().getUser());
  }

  @Override
  @Transactional(readOnly = true)
  public CommentStatus findById(Long id) {
    return commentStatusRepository.findOne(id);
  }

  @Override
  @Transactional(readOnly = true)
  public CommentStatus save(CommentStatus commentStatus) {
    if (commentStatus.getId() == null) {
      commentStatus.setUser(UserDetailsAdapter.getLogged().getUser());
    } else {
      commentStatus.setUser(findById(commentStatus.getId()).getUser());
    }
    return commentStatusRepository.saveAndFlush(commentStatus);
  }

  @Override
  public void delete(Long id) {
    commentStatusRepository.delete(id);
  }

}
