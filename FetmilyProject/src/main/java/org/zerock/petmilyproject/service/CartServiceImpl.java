package org.zerock.petmilyproject.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.petmilyproject.domain.Cart;
import org.zerock.petmilyproject.domain.Item;
import org.zerock.petmilyproject.dto.CartDTO;
import org.zerock.petmilyproject.dto.ItemDTO;
import org.zerock.petmilyproject.repository.CartRepository;

import java.util.List;
import java.util.stream.Collectors;
import org.zerock.petmilyproject.repository.ItemRepository;

@Service
@RequiredArgsConstructor
@Log4j2
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;

    private final ItemRepository itemRepository;

//    @Override
//    public Long register(CartDTO cartDTO) {
//        Cart cart = modelMapper.map(cartDTO, Cart.class);
//        cartRepository.save(cart);
//
//        return cart.getCartId();
//    }

    @Override
    public CartDTO readOne(Long cartId) {

        return null;
    }

    @Override
    public void modify(CartDTO cartDTO) {
        Cart cart = cartRepository.findById(cartDTO.getCartId())
            .orElseThrow();
        cart.changeCnt(cartDTO.getCnt());
        cartRepository.save(cart);
    }

    @Override
    public void remove(Long cartId) {
        cartRepository.deleteById(cartId);
    }

//    @Override
//    public List<CartDTO> readList(Long memberId) {
//        List<Cart> cartList = cartRepository.findAllByMemberId(memberId);
//        List<CartDTO> cartDTOList = cartList.stream()
//            .map(cartItem -> modelMapper.map(cartItem, CartDTO.class)).collect(Collectors.toList());
//
//        return cartDTOList;
//    }
    @Override
    public int totalPrice(Long memberId){
        List<Cart> cartList = cartRepository.findAllByMemberId1(memberId);
        int totalPrice = 0;
        for(Cart cart : cartList){
            totalPrice += cart.getCartPrice();
        }
        return totalPrice;
    }

    @Override
    public void orderOK(Long cartId) {
            Cart cart = cartRepository.findById(cartId).orElseThrow();
            cart.changeStatus();
            cartRepository.save(cart);

    }


    @Override
    public Long register(CartDTO cartDTO) {
        Cart cart = modelMapper.map(cartDTO, Cart.class);
        Cart isExist = cartRepository.findByItemId(cartDTO.getItemId());

        log.info(isExist);
        if(isExist == null){
            cartRepository.save(cart);
        }else{
            isExist.changeCnt(cartDTO.getCnt());
            cartRepository.save(isExist);
        }

        return cart.getCartId();
    }

    @Override
    public List<ItemDTO> readList(Long memberId) {
        List<Item> cartList = cartRepository.findAllByMemberId(memberId);
        List<ItemDTO> cartDTOList = cartList.stream()
            .map(cartItem -> modelMapper.map(cartItem, ItemDTO.class)).collect(Collectors.toList());
        log.info(cartDTOList);
        log.info(cartList);
        return cartDTOList;
    }

    @Override
    public List<CartDTO> readList1(Long memberId) {
        List<Cart> cartList = cartRepository.findAllByMemberId1(memberId);
        List<CartDTO> cartDTOList = cartList.stream()
            .map(cartItem -> modelMapper.map(cartItem, CartDTO.class)).collect(Collectors.toList());
        log.info(cartDTOList);
        log.info(cartList);
        return cartDTOList;
    }


}
