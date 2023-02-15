package com.webtutsplus.ecommerce.service;

import com.webtutsplus.ecommerce.model.WishList;
import com.webtutsplus.ecommerce.repository.WishListRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class WishListService {
    final WishListRepository wishListRepository;

    public WishListService(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    public void createWishlist(WishList wishList) {
        wishListRepository.save(wishList);
    }

    public List<WishList> readWishList(Integer userId) {
        return wishListRepository.findAllByUserIdOrderByCreatedDateDesc(userId);
    }
}
