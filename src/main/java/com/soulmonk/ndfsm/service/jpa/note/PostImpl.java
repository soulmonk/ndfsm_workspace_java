package com.soulmonk.ndfsm.service.jpa.note;

import com.google.common.collect.Lists;
import com.soulmonk.ndfsm.domain.note.Post;
import com.soulmonk.ndfsm.repository.note.PostRepository;
import com.soulmonk.ndfsm.service.note.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: SoulMonk
 * Date: 27.02.14
 * Time: 21:32
 */
@Service("postService")
@Repository
@Transactional
public class PostImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Post> findAll() {
        return Lists.newArrayList(postRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Post findById(Long id) {
        return postRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Post save(Post post) {
        return postRepository.saveAndFlush(post);
    }

    @Override
    @Transactional(readOnly = true)
    public void delete(Long id) {
        postRepository.delete(id);
    }
}
