package com.soulmonk.ndfsm.service.time;

import com.soulmonk.ndfsm.domain.time.CommentStatuses;

import java.util.List;

public interface CommentStatusesService {
	public List<CommentStatuses> findAll();
	public CommentStatuses findById(Long id);
	public CommentStatuses save(CommentStatuses commentStatus);

  void delete(Long id);
}
