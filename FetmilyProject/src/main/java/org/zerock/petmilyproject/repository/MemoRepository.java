package org.zerock.petmilyproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.petmilyproject.domain.Memo;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    // 여기에 필요한 추가적인 메소드가 있으면 추가할 수 있습니다.
}