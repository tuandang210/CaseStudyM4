package com.codegym.casestudym4.service.messages;

import com.codegym.casestudym4.model.Messages;
import com.codegym.casestudym4.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMessagesService extends IGeneralService<Messages> {
    Page<Messages> findAllByPageable (Pageable pageable);
}
