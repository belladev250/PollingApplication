package com.example.pollingapplication.repositories;

import com.example.pollingapplication.Poll.Poll;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PollRepository extends JpaRepository<Poll,Long> {
    Optional<Poll> findById(Long pollId);
    Page<Poll> findByCreatedBy(Long userId, Pageable pageable);

    long countBycreatedBy(Long userId);
    List<Poll> findById(List<Long> pollIds);
    List<Poll> findById(List<Long>pollIds, Sort sort);
}
