package com.codegym.casestudym4.service.Image;

import com.codegym.casestudym4.model.Image;
import com.codegym.casestudym4.repository.IImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;


@Service
public class ImageService implements IImageService{

    @Autowired
    private IImageRepository imageRepository;

    @Override
    public Iterable<Image> findAll() {
        return imageRepository.findAll();
    }

    @Override
    public Optional<Image> findById(Long id) {
        return imageRepository.findById(id);
    }

    @Override
    public Image save(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public void delete(Long id) {
        imageRepository.deleteById(id);
    }

    @Override
    public List<BigInteger> findImageIdOfProductByID(Long productId) {
        return imageRepository.findImageIdOfProductByID(productId);
    }
}
