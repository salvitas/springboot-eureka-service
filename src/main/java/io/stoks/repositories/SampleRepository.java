package io.stoks.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SampleRepository extends PagingAndSortingRepository<SampleDTO, String> {
    Page<SampleDTO> findByAlias(String alias, Pageable pageable);
    Page<SampleDTO> findAll(Pageable pageable);
}
