package com.myhotel.template.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myhotel.template.entities.GuestResponse;
import com.myhotel.template.repositories.GuestRepository;

@Service
public class GuestService {
	
	@Autowired
	GuestRepository repository;
	
	public List<GuestResponse> getGuest(List<Long> ids){
		
		
		 List<GuestResponse> resp = repository.findAllById(ids);
		
		 
		return resp;
		
	}

}
