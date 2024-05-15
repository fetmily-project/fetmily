package org.zerock.petmilyproject.service;

import org.zerock.petmilyproject.dto.EventDTO;
import org.zerock.petmilyproject.dto.MemoDTO;

import java.util.List;

public interface MemoService {
    void addMemo(MemoDTO memoDTO);
    void updateMemo(Long memoId, MemoDTO memoDTO);
    void deleteMemo(Long memoId);
    MemoDTO getMemo(Long memoId);
    List<MemoDTO> getAllMemo();

}