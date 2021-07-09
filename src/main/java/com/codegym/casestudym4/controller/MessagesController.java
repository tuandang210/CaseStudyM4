package com.codegym.casestudym4.controller;

import com.codegym.casestudym4.model.Messages;
import com.codegym.casestudym4.service.messages.IMessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/messages")
public class MessagesController {

    @Autowired
    private IMessagesService messagesService;

    @GetMapping
    public ResponseEntity<Page<Messages>> showAllMessage(@PageableDefault Pageable pageable){
        Page<Messages> messages = messagesService.findAllByPageable(pageable);
        if (messages.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Messages> createMessage(@RequestBody Messages messages){
        return new ResponseEntity<>(messagesService.save(messages), HttpStatus.CREATED);
    }
}
