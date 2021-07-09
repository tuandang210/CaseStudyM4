package com.codegym.casestudym4.service.comment;

import com.codegym.casestudym4.model.Comment;
import com.codegym.casestudym4.model.Product;
import com.codegym.casestudym4.model.User;
import com.codegym.casestudym4.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICommentService extends IGeneralService<Comment> {
    Page<Comment> findAllPageable(Pageable pageable);

    Page<Comment> findAllByUser(Pageable pageable, User user);

    Iterable<Comment> findAllByProduct(Product product);

}
