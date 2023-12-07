package com.component;

import org.springframework.stereotype.Component;

import com.dto.CustomDTO;
import com.entity.Custom;

@Component
public class CustomConvert {
	
	
	public CustomDTO toDTO(Custom custom) {
		CustomDTO dto=new CustomDTO();
		
		dto.setId(custom.getId());
		dto.setName(custom.getName());
		dto.setPhone(custom.getPhone());
		dto.setAge(custom.getAge());
		
		return dto;
	}
	

}
