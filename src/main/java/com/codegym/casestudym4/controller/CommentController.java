package com.codegym.casestudym4.controller;

import com.codegym.casestudym4.model.Comment;
import com.codegym.casestudym4.model.Product;
import com.codegym.casestudym4.model.User;
import com.codegym.casestudym4.service.comment.ICommentService;
import com.codegym.casestudym4.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private ICommentService commentService;

    @Autowired
    private IProductService productService;

    @GetMapping("/list")
    public ModelAndView showList(@PageableDefault(size = 1000) Pageable pageable) {
        Page<Comment> comments = commentService.findAllPageable(pageable);
        if (comments.isEmpty()) {
            return new ModelAndView("/error-404");
        }
        ModelAndView modelAndView = new ModelAndView("/comment/list", "comments", comments);
        Page<Product> products = productService.findAll(pageable);
        if(products.isEmpty()){
            return new ModelAndView("/error-404");
        }
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        return new ResponseEntity<>(commentService.save(comment), HttpStatus.CREATED);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Iterable<Comment>> getComment(@PathVariable Long id){
        Iterable<Comment> comments = commentService.findAllByProduct(productService.findById(id).get());
        return new ResponseEntity<>(commentService.findAllByProduct(productService.findById(id).get()),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody Comment comment) {
        Optional<Comment> commentOptional = commentService.findById(id);
        if (!commentOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        comment.setId(commentOptional.get().getId());
        return new ResponseEntity<>(commentService.save(comment), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable Long id) {
        Optional<Comment> commentOptional = commentService.findById(id);
        if (!commentOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        commentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> findById(@PathVariable Long id) {
        try {
            Comment commentOptional = commentService.findById(id).get();
            commentOptional.setLikes(commentOptional.getLikes()+1);
            commentService.save(commentOptional);
            return new ResponseEntity<>(commentOptional, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @GetMapping
//    public ResponseEntity<?> search(@RequestParam(required = false) User user, @PageableDefault Pageable pageable){
//        Page<Comment> comments;
//        if (user == null) {
//            user = "";
//        }
//        if (user !=) {
//            comments = commentService.findAllByUser(pageable, user);
//        } else {
//            comments = commentService.findAllPageable(pageable);
//        }
//        return new ResponseEntity<>(comments, HttpStatus.OK);
//    }
}
