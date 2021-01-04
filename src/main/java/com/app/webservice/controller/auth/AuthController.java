package com.app.webservice.controller.auth;

import com.app.webservice.config.JwtTokenUtil;
import com.app.webservice.model.master.ERole;
import com.app.webservice.model.master.Role;
import com.app.webservice.model.master.User;
import com.app.webservice.model.payload.request.LoginRequest;
import com.app.webservice.model.payload.request.RegisterRequest;
import com.app.webservice.model.payload.response.JwtResponse;
import com.app.webservice.service.master.RoleService;
import com.app.webservice.service.master.UserService;
import com.app.webservice.service.security.UserDetailsImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@Api(value = "Authentication", description = "API login and register")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @ApiOperation(value = "login user", response = JwtResponse.class)
    @PostMapping("/signin")
    public ResponseEntity<?> authenticationUser(
    		@ApiParam(value = "username and password", required = true)
    		@RequestBody(required = true) LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenUtil.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(role -> role.getAuthority()).collect(Collectors.toList());

        return ResponseEntity.ok(
                new JwtResponse( token,
                        userDetails.getUserId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        roles)
        );
    }
    
    @ApiOperation(value = "register user", response = String.class)
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody(required = true) RegisterRequest registerRequest) {
        if(userService.isUserExist(registerRequest.getUsername())) {
            return ResponseEntity.badRequest().body("Fail : Username already exist.");
        }

        if(userService.isEmailExist(registerRequest.getEmail())) {
            return ResponseEntity.badRequest().body("Fail : E-Mail already in use.");
        }

        User user = new User();
        user.setUserName(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        Set<String> paramRole = registerRequest.getRoles();
        Set<Role> role = new HashSet<>();

		/*
		 * if(paramRole == null) { Role userRole =
		 * roleService.findByName(ERole.ROLE_USER) .orElseThrow(() -> new
		 * RuntimeException("Error : Role is not found")); role.add(userRole); } else {
		 * paramRole.forEach(x -> { switch (x) { case "ADMIN" : Role adminRole =
		 * roleService.findByName(ERole.ROLE_ADMIN) .orElseThrow(() -> new
		 * RuntimeException("Error : Role is not found")); role.add(adminRole); break;
		 * default: Role userRole = roleService.findByName(ERole.ROLE_USER)
		 * .orElseThrow(() -> new RuntimeException("Error : Role is not found"));
		 * role.add(userRole); break; } }); }
		 */

        userService.save(user);
        return ResponseEntity.ok("User already registered.");
    }
}
