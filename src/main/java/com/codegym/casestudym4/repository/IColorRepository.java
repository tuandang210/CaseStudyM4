package com.codegym.casestudym4.repository;

import com.codegym.casestudym4.model.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IColorRepository extends JpaRepository<Color,Long> {
}
