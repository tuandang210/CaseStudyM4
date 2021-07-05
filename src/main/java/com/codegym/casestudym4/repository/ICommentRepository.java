package com.codegym.casestudym4.repository;

import com.codegym.casestudym4.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findAllByAuthor(Pageable pageable, String author);
}
