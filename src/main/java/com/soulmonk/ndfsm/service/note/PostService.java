package com.soulmonk.ndfsm.service.note;

import com.soulmonk.ndfsm.domain.note.Post;

import java.util.List;

/**
 * Company: Valpio
 * User: SoulMonk
 * Date: 27.02.14
 * Time: 21:28
 */
public interface PostService {
    public List<Post> findAll();

    public Post findById(Long id);

    public Post save(Post post);

    void delete(Long id);
}
