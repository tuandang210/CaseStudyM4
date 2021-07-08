package com.codegym.casestudym4.repository;

import com.codegym.casestudym4.model.Comment;
import com.codegym.casestudym4.model.Product;
import com.codegym.casestudym4.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findAllByUser(Pageable pageable, User user);

    Iterable<Comment> findAllByProduct(Product product);
}
