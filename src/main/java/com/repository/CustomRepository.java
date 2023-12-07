package com.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.entity.Custom;
@Repository
public interface CustomRepository extends PagingAndSortingRepository<Custom, Long> {
	 List<Custom> findAllByPhone(String phone, Pageable pageable);
	 List<Custom> findAllByName(String name, Pageable pageable);
	 List<Custom> findAllByAge(int age, Pageable pageable);

}
