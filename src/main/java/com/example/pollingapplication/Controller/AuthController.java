package com.example.pollingapplication.Controller;

import java.net.URI;
import java.util.Collections;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.pollingapplication.Exceptions.AppException;
import com.example.pollingapplication.Payload.ApiResponse;
import com.example.pollingapplication.Payload.JwtAuthenticationResponse;
import com.example.pollingapplication.Payload.LoginRequest;
import com.example.pollingapplication.Payload.SignUpRequest;
import com.example.pollingapplication.models.Role;
import com.example.pollingapplication.models.RoleName;
import com.example.pollingapplication.models.User;
import com.example.pollingapplication.repositories.RoleRepository;
import com.example.pollingapplication.repositories.UserRepository;
import com.example.pollingapplication.security.JwtTokenProvider;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	  AuthenticationManager  authenticationManager; 
	
	@Autowired
	
	UserRepository userRepository;
	
	@Autowired
	
	RoleRepository roleRepository;
	
	@Autowired

	PasswordEncoder passwordEncoder;
	
	@Autowired

	JwtTokenProvider tokenProvider;
	
	
	//Login api 
	
	@PostMapping("/signin")
	
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest ){
		
		Authentication authentication = authenticationManager.authenticate(
				
				new UsernamePasswordAuthenticationToken(
						loginRequest.getUsernameOrEmail(),
						loginRequest.getPassword()
						
						)
				
				); 
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = tokenProvider.generateToken(authentication);
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
		
		
	}
	
	
// Registration api
	
	@PostMapping("/signup")
	
	// check if user already exists by email or password
	public ResponseEntity<?> RegisterUser(@Valid @RequestBody SignUpRequest signUpRequest){
		
	if(userRepository.existsByUsername(signUpRequest.getUsername())) {
		
		return new ResponseEntity(new ApiResponse(false,"username is already in use"),HttpStatus.BAD_REQUEST);
	}
	
	if(userRepository.existsByEmail(signUpRequest.getEmail())) {
		
		return new ResponseEntity(new ApiResponse(false,"email is already in use"),HttpStatus.BAD_REQUEST);
	}
	
	// create a user account
	
	
	User user = new User(signUpRequest.getName(),signUpRequest.getUsername(),signUpRequest.getEmail()
			,signUpRequest.getPassword(), null);
	
	// encode the password
	
	user.setPassword(passwordEncoder.encode(user.getPassword()));
	
   // set user roles

	Role userRole = roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(()-> new AppException("User role not set"));
	
	// assign a role to the registering user
	
	user.setRoles(Collections.singleton(userRole));
	
	// Save the registered user
	
	User results = userRepository.save(user);
	
	URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{username}")
			.buildAndExpand(results.getUsername()).toUri();	
	
	return ResponseEntity.created(location).body(new ApiResponse(true,"user registered successfully"));
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}
	
	
	
	
	
	
	
}
