package com.controll;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dto.OutputSort;
import com.service.ICustomService;

@CrossOrigin
@RestController
public class CustomController {
	
	@Autowired 
	private ICustomService customService;
	
	
	@GetMapping(value = "/customs")
	public OutputSort showNew(@RequestParam(value = "page", required = false) Integer page,
							 @RequestParam(value = "limit", required = false) Integer limit,@RequestParam("name") Optional<String> name,
							 @RequestParam("phone") Optional<String> phone,@RequestParam("age") Optional<String> age,@RequestParam("sort") String sort) {
		OutputSort result = new OutputSort();
		if (page != null && limit != null) {
			result.setPage(page);
			Sort sortable = null;
			if(sort.equals("ASC")) {
				sortable = Sort.by("age").ascending();}
			Pageable pageable =  PageRequest.of(page - 1, limit,sortable);
			System.out.println(phone);
			 if(name.isPresent()){
					result.setListResult(customService.findAllByName(name.get(), pageable));
					System.out.println(result);
			 }
			  if(phone.isPresent()){
					result.setListResult(customService.findAllByPhone(phone.get(), pageable));
			 }
			  if(age.isPresent()) {
				 result.setListResult(customService.findAllByAge(age.get(), pageable));
			 }
			 if(age.isEmpty() && name.isEmpty() && phone.isEmpty()) {
				
				 result.setListResult(customService.findAll(pageable));
			 }
			
			result.setTotalPage((int) Math.ceil((double) (customService.totalItem()) / limit));
		} else {
			result.setListResult(customService.findAll());
		}
		return result;
	}
	

}
