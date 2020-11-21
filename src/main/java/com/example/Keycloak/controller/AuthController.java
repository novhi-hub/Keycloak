package com.example.Keycloak.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.security.RolesAllowed;

import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.Configuration;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Keycloak.reqres.req.UserRequestBody;


@RequestMapping(value = "/auth")
@RestController
public class AuthController {
	
	@Value("${keycloak.credentials.secret}")
	private String clientSecret;
	
	@Value("${keycloak.resource}")
	private String clientId;
	
	@Value("${keycloak.realm}")
	private String realm;
	
	private String authServerUrl = "http://localhost:8080/auth";
	

	@GetMapping(value = "/anonymous")
    public ResponseEntity<String> getAnonymous() {
        return ResponseEntity.ok("Hello Anonymous");
    }

    
    @PostMapping(path = "/signin")
    public ResponseEntity<?> signin(@RequestBody UserRequestBody userDTO) {

        Map<String, Object> clientCredentials = new HashMap<>();
        clientCredentials.put("secret", clientSecret);
        clientCredentials.put("grant_type", "password");

        Configuration configuration =
                new Configuration(authServerUrl, realm, clientId, clientCredentials, null);
        AuthzClient authzClient = AuthzClient.create(configuration);
        


        AccessTokenResponse response =
                authzClient.obtainAccessToken(userDTO.getEmail(), userDTO.getPassword());

        return ResponseEntity.ok(response);
    }
	
}
