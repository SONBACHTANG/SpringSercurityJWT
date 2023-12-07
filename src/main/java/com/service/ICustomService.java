package com.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.dto.CustomDTO;


public interface ICustomService {
	
	List<CustomDTO> findAll(Pageable pageable);
	int totalItem();
	List<CustomDTO> findAll();
	List<CustomDTO> findAllByPhone(String phone, Pageable pageable);
	List<CustomDTO> findAllByName(String name, Pageable pageable);
	List<CustomDTO> findAllByAge(String age, Pageable pageable);
}
