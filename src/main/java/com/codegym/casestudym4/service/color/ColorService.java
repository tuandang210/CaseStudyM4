package com.codegym.casestudym4.service.color;

import com.codegym.casestudym4.model.Color;
import com.codegym.casestudym4.repository.IColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ColorService implements IColorService {

    @Autowired
    private IColorRepository colorRepository;

    @Override
    public Iterable<Color> findAll() {
        return colorRepository.findAll();
    }

    @Override
    public Optional<Color> findById(Long id) {
        return colorRepository.findById(id);
    }

    @Override
    public Color save(Color color) {
        return colorRepository.save(color);
    }

    @Override
    public void delete(Long id) {
        colorRepository.deleteById(id);
    }
}
