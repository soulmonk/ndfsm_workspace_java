package com.soulmonk.ndfsm.service.jpa.note;

import com.google.common.collect.Lists;
import com.soulmonk.ndfsm.domain.note.PostComment;
import com.soulmonk.ndfsm.repository.note.PostCommentRepository;
import com.soulmonk.ndfsm.service.note.PostCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: SoulMonk
 * Date: 27.02.14
 * Time: 21:34
 */
@Service("postCommentService")
@Repository
@Transactional
public class PostCommentImpl implements PostCommentService {

    @Autowired
    private PostCommentRepository postCommentRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PostComment> findAll() {
        return Lists.newArrayList(postCommentRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public PostComment findById(Long id) {
        return postCommentRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public PostComment save(PostComment postComment) {
        return postCommentRepository.saveAndFlush(postComment);
    }

    @Override
    @Transactional(readOnly = true)
    public void delete(Long id) {
        postCommentRepository.delete(id);
    }
}
