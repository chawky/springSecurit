package controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springSecProject.modals.ERole;
import com.springSecProject.modals.Role;
import com.springSecProject.modals.User;

import jwt.JwtUtils;
import reporsitories.RoleRepo;
import reporsitories.UserRepo;
import requestPayload.LoginRequest;
import requestPayload.SignUpRequest;
import responsePayload.JwtResponse;
import responsePayload.MessageResponse;
import security.securityServices.UserDetailsImpl;

@CrossOrigin(origins = "*")
@RestController
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserRepo userRepo;
	@Autowired
	RoleRepo roleRepo;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> autheticateUser(@RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPw()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(loginRequest.getUsername());
		UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = new ArrayList<String>();
		if(Objects.nonNull(userDetailsImpl.getAuthorities()) && userDetailsImpl.getAuthorities().isEmpty()) {
			roles=	userDetailsImpl.getAuthorities().stream().map(Item -> Item.getAuthority())
				.collect(Collectors.toList());
		}
		return ResponseEntity.ok(new JwtResponse(jwt, userDetailsImpl.getId(), userDetailsImpl.getUsername(),
				userDetailsImpl.getEmail(), roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {
		if (userRepo.existsByUserName(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("user Taken"));

		}
		if (userRepo.existsByEmail(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("user Taken"));

		}
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPw()));
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
		if (!Objects.nonNull(strRoles)) {
			Role userRole = roleRepo.findByRoleName(ERole.USER_ROLE)
					.orElseThrow(() -> new RuntimeException("role not found"));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepo.findByRoleName(ERole.ADMIN_ROLE)
							.orElseThrow(() -> new RuntimeException("role not found"));
					roles.add(adminRole);
					break;
				case "mod":
					Role modRole = roleRepo.findByRoleName(ERole.MODERATOR_ROLE)
							.orElseThrow(() -> new RuntimeException("role not found"));
					roles.add(modRole);
					break;

				default:
					Role userRole = roleRepo.findByRoleName(ERole.USER_ROLE)
							.orElseThrow(() -> new RuntimeException("role not found"));
					roles.add(userRole);
					break;
				}
			});
		}
		user.setRole(roles);
		userRepo.save(user);
		return ResponseEntity.ok(new MessageResponse("user added " + user.toString()));

	}

}
