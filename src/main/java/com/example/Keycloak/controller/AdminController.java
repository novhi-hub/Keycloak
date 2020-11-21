package com.example.Keycloak.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/admin")
@RestController
public class AdminController {

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping(value = "/test")
    public ResponseEntity<String> getAdmin() {
        return ResponseEntity.ok("Hello Admin");
    }
	
    @RolesAllowed("ROLE_USER")
    @GetMapping(value = "/user")
    public ResponseEntity<String> getUser() {
        return ResponseEntity.ok("Hello User");
    }


    @RolesAllowed({ "ROLE_ADMIN", "ROLE_USER" })
    @GetMapping(value = "/all-user")
    public ResponseEntity<String> getAllUser() {
        return ResponseEntity.ok("Hello All User");
    }
}
