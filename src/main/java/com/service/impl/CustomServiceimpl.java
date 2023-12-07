package com.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.component.CustomConvert;
import com.dto.CustomDTO;
import com.entity.Custom;
import com.repository.CustomRepository;
import com.service.ICustomService;
@Service

public class CustomServiceimpl implements ICustomService {

	
	
	@Autowired
	private CustomRepository customRepository;
	
	@Autowired
	private CustomConvert customConvert;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<CustomDTO> findAll(Pageable pageable) {
		List<CustomDTO> customs=new ArrayList<CustomDTO>();
	
		List<Custom> entities=customRepository.findAll(pageable).getContent();
		
		for(Custom item:entities) {
			CustomDTO dtos=customConvert.toDTO(item);
			customs.add(dtos);
			
			
		}
		return customs;
	}

	@Override
	public int totalItem() {
	
		return (int) customRepository.count();
	}

	@Override
	public List<CustomDTO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CustomDTO> findAllByPhone(String phone, Pageable pageable) {
		List<CustomDTO> customs=new ArrayList<CustomDTO>();
		List<Custom> entities=customRepository.findAllByPhone(phone, pageable);
		
		for(Custom item:entities) {
			CustomDTO dtos=customConvert.toDTO(item);
			customs.add(dtos);
			
			
		}
		return customs;   
		
	}

	@Override
	public List<CustomDTO> findAllByName(String name, Pageable pageable) {
		List<CustomDTO> customs=new ArrayList<CustomDTO>();
		List<Custom> entities=customRepository.findAllByName(name, pageable);
		
		for(Custom item:entities) {
			CustomDTO dtos=customConvert.toDTO(item);
			customs.add(dtos);
			
			
		}
		return customs;   
		
	}

	@Override
	public List<CustomDTO> findAllByAge(String age, Pageable pageable) {
		int ageconvert=Integer.parseInt(age);
		List<CustomDTO> customs=new ArrayList<CustomDTO>();
		List<Custom> entities=customRepository.findAllByAge(ageconvert, pageable);
		
		for(Custom item:entities) {
			CustomDTO dtos=customConvert.toDTO(item);
			customs.add(dtos);
			
			
		}
		return customs;   
		
		
	}


	

}
