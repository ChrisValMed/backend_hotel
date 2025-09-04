package com.myhotel.template.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myhotel.template.services.GuestService;

@RestController
@RequestMapping("/api/v1/notifications")
public class EmailGuestController {
	
	private final GuestService service;

    public EmailGuestController(GuestService service) {
        this.service = service;
    }

    
    @GetMapping("/guest/{guestIds}")
    public ResponseEntity<?> getNotificationGuest(@PathVariable List<Long> guestIds) {
        return ResponseEntity.ok(service.getGuest(guestIds));
    }
}
