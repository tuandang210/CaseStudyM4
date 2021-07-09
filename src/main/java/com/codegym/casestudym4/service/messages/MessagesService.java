package com.codegym.casestudym4.service.messages;

import com.codegym.casestudym4.model.Messages;
import com.codegym.casestudym4.repository.IMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessagesService implements IMessagesService {
    @Autowired
    private IMessageRepository messageRepository;

    @Override
    public Iterable<Messages> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public Optional<Messages> findById(Long id) {
        return messageRepository.findById(id);
    }

    @Override
    public Messages save(Messages messages) {
        return messageRepository.save(messages);
    }

    @Override
    public void delete(Long id) {
        messageRepository.deleteById(id);
    }

    @Override
    public Page<Messages> findAllByPageable(Pageable pageable) {
        return messageRepository.findAll(pageable);
    }
}
