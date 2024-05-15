package org.zerock.petmilyproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.petmilyproject.domain.Event;
import org.zerock.petmilyproject.domain.Memo;
import org.zerock.petmilyproject.dto.EventDTO;
import org.zerock.petmilyproject.dto.MemoDTO;
import org.zerock.petmilyproject.repository.EventRepository;
import org.zerock.petmilyproject.repository.MemoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class MemoServiceImpl implements MemoService {

    private final MemoRepository memoRepository;

    @Autowired
    public MemoServiceImpl(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    @Override
    public void addMemo(MemoDTO memoDTO) {
        // DTO로부터 엔티티 생성
        Memo memo = Memo.builder()
                .content(memoDTO.getContent())
                .memoDate(LocalDate.from(memoDTO.getMemoDate()))
                .build();


        System.out.println(memo);
        // 엔티티 저장
        memoRepository.save(memo);
    }

    @Override
    public void updateMemo(Long memoId, MemoDTO memoDTO) {
        // 이벤트 ID로 기존 이벤트 조회
        Optional<Memo> optionalMemo = memoRepository.findById(memoId);

        if (optionalMemo.isPresent()) {
            Memo memo = optionalMemo.get();

            // 업데이트할 필드 설정
            memo.setContent(memoDTO.getContent());
            memo.setMemoDate(LocalDate.from(memoDTO.getMemoDate()));

            // 엔티티 업데이트
            memoRepository.save(memo);
            // 변경 사항은 트랜잭션 커밋 시점에 자동으로 반영됨
        } else {
            // 해당 이벤트가 존재하지 않는 경우 처리
            throw new IllegalArgumentException("Event with ID " + memoId + " not found");
        }
    }



    @Override
    public void deleteMemo(Long memoId) {
        // 이벤트 ID로 이벤트 삭제
        memoRepository.deleteById(memoId);
    }

    @Override
    public MemoDTO getMemo(Long memoId) {
        Optional<Memo> optionalMemo = memoRepository.findById(memoId);

        if (optionalMemo.isPresent()) {
            Memo memo = optionalMemo.get();

            // 엔티티를 DTO로 변환하여 반환
            return MemoDTO.builder()
                    .content(memo.getContent())
                    .memoDate(memo.getMemoDate().atStartOfDay())
                    .build();
        } else {
            // 해당 이벤트가 존재하지 않는 경우 처리
            throw new IllegalArgumentException("Event with ID " + memoId + " not found");
        }


    }

    @Override
    public List<MemoDTO> getAllMemo() {
        List<Memo> memos = memoRepository.findAll();
        return memos.stream()
                .map(memo -> MemoDTO.builder()
                        .content(memo.getContent())
                        .memoDate(memo.getMemoDate().atStartOfDay())
                        .build())
                .collect(Collectors.toList());
    }
}