package com.soulmonk.ndfsm.service.time;

import com.soulmonk.ndfsm.domain.time.CommentStatus;

import java.util.List;

public interface CommentStatusService {
    public List<CommentStatus> findAll();

    public CommentStatus findById(Long id);

    public CommentStatus save(CommentStatus commentStatus);

    void delete(Long id);
}
