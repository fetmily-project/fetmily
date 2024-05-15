package org.zerock.petmilyproject.service;

import org.zerock.petmilyproject.dto.MemoDTO;

public interface CalendarService {

    Long register(MemoDTO memoDTO);
    MemoDTO readOne(Long bno);
    void modify(MemoDTO memoDTO);
    void remove(Long bno);
}