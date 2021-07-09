package com.codegym.casestudym4.service.Image;

import com.codegym.casestudym4.model.Image;
import com.codegym.casestudym4.service.IGeneralService;

import java.math.BigInteger;
import java.util.List;

public interface IImageService extends IGeneralService<Image> {
    List<BigInteger> findImageIdOfProductByID(Long productId);
}
