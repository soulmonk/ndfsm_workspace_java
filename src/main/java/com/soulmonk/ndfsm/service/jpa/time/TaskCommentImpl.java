package com.soulmonk.ndfsm.service.jpa.time;

import com.soulmonk.ndfsm.domain.time.ProjectComment;
import com.soulmonk.ndfsm.repository.time.TaskCommentRepository;
import com.soulmonk.ndfsm.security.UserDetailsAdapter;
import com.soulmonk.ndfsm.service.time.TaskCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("taskCommentService")
@Repository
@Transactional
public class TaskCommentImpl implements TaskCommentService {

    @Autowired
    private TaskCommentRepository taskCommentRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProjectComment> findAll() {
        return taskCommentRepository.findByUser(UserDetailsAdapter.getLogged().getUser());
    }

    @Override
    @Transactional(readOnly = true)
    public ProjectComment findById(Long id) {
        return taskCommentRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public ProjectComment save(ProjectComment comment) {
        if (comment.getId() == null) {
            comment.setUser(UserDetailsAdapter.getLogged().getUser());
        } else {
            comment.setUser(findById(comment.getId()).getUser());
        }
        return taskCommentRepository.saveAndFlush(comment);
    }

    @Override
    public void delete(Long id) {
        taskCommentRepository.delete(id);
    }

}
