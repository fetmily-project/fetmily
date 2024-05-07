package org.zerock.petmilyproject.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.petmilyproject.domain.Item;
import org.zerock.petmilyproject.dto.*;
import org.zerock.petmilyproject.repository.ShopRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {
    private final ShopRepository shopRepository;
    private final ModelMapper modelMapper;

    @Override
    public Long register(ItemDTO itemDTO) {
        return null;
    }

    @Override
    public ItemDTO readOne(Long bno) {
        return null;
    }

    @Override
    public void modify(ItemDTO itemDTO) {

    }

    @Override
    public void remove(Long bno) {

    }

    @Override
    public PageResponseDTO<ItemDTO> getListOfItem(PageRequestDTO pageRequestDTO) {
        Pageable pageable = PageRequest.of(pageRequestDTO.getPage() <=0? 0: pageRequestDTO.getPage() -1,
                pageRequestDTO.getSize(),
                Sort.by("itemId").ascending());

        Page<Item> result = shopRepository.listOfItem(pageable);

        List<ItemDTO> dtoList = result.getContent().stream().map(item -> modelMapper.map(item, ItemDTO.class))
                    .collect(Collectors.toList());

        return PageResponseDTO.<ItemDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }
}
