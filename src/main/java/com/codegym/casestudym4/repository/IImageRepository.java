package com.codegym.casestudym4.repository;

import com.codegym.casestudym4.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;


@Repository
@Transactional
public interface IImageRepository extends JpaRepository<Image,Long> {
    @Query(value="select image_set_id from products_image_set  where product_id=?1 ",nativeQuery = true)
    List<BigInteger> findImageIdOfProductByID(Long product_id);
}
