package com.codegym.casestudym4.repository;

import com.codegym.casestudym4.model.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMessageRepository extends JpaRepository<Messages, Long> {
}
