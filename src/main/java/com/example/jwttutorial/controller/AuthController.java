package com.example.jwttutorial.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwttutorial.configuration.security.jwt.JwtUtils;
import com.example.jwttutorial.exception.TokenRefreshException;
import com.example.jwttutorial.model.RefreshToken;
import com.example.jwttutorial.model.Role;
import com.example.jwttutorial.model.User;
import com.example.jwttutorial.model.UsersRole;
import com.example.jwttutorial.payload.request.LoginRequest;
import com.example.jwttutorial.payload.request.SignupRequest;
import com.example.jwttutorial.payload.response.MessageResponse;
import com.example.jwttutorial.payload.response.UserInfoResponse;
import com.example.jwttutorial.repository.PrivilegeRepository;
import com.example.jwttutorial.repository.RoleRepository;
import com.example.jwttutorial.repository.UserRepository;
import com.example.jwttutorial.repository.UsersRoleRepository;
import com.example.jwttutorial.services.RefreshTokenService;
import com.example.jwttutorial.services.UserDetailsImpl;

@CrossOrigin(origins = "http://localhost:3070/", allowCredentials = "true")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UsersRoleRepository usersRoleRepository;

    @Autowired
    PrivilegeRepository privilegeRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    RefreshTokenService refreshTokenService;

    @PostMapping("/signin")

    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

        ResponseCookie jwtRefreshCookie = jwtUtils.generateRefreshJwtCookie(refreshToken.getToken());

        UserInfoResponse userInfoResponse = new UserInfoResponse(
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEnable(),
                userDetails.getAccount_non_lock(),
                roles);

        // add information of jwtCookie into userInfoResponse
        userInfoResponse.setJwtCookieValue(jwtCookie.getValue());

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .header(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString())
                .body(userInfoResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getEnable(),
                signUpRequest.getAccount_non_lock());

        Set<String> strRoles = signUpRequest.getRole();
        Set<UsersRole> usersRoles = new HashSet<>();

        if (strRoles == null) {
            Role role = roleRepository.findByName("USER")
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            UsersRole usersRole = new UsersRole(user, role);
            usersRoles.add(usersRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName("ADMIN")
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        UsersRole adminUsersRole = new UsersRole(user, adminRole);
                        usersRoles.add(adminUsersRole);
                        break;
                    case "author":
                        Role authorRole = roleRepository.findByName("AUTHOR")
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        UsersRole authorUsersRole = new UsersRole(user, authorRole);
                        usersRoles.add(authorUsersRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName("USER")
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        UsersRole userUsersRole = new UsersRole(user, userRole);
                        usersRoles.add(userUsersRole);
                }
            });
        }
        userRepository.save(user);
        usersRoleRepository.saveAll(usersRoles);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!principle.toString().equals("anonymousUser")) {
            Long userId = ((UserDetailsImpl) principle).getId();
            refreshTokenService.deleteByUserId(userId);
        }
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        ResponseCookie refreshCookie = jwtUtils.getCleanJwtRefreshCookie();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .header(HttpHeaders.SET_COOKIE, refreshCookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(HttpServletRequest request) {
        String refreshToken = jwtUtils.getJwtRefreshFromCookies(request);

        if ((refreshToken != null) && (!refreshToken.isEmpty())) {
            return refreshTokenService.findByToken(refreshToken)
                    .map(refreshTokenService::verifyExpiration)
                    .map(RefreshToken::getUser)
                    .map(user -> {
                        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(user);

                        return ResponseEntity.ok()
                                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                                .body(new MessageResponse("Token is refreshed successfully!"));
                    })
                    .orElseThrow(() -> new TokenRefreshException(refreshToken,
                            "Refresh token is not in database!"));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Refresh Token is empty!"));
    }
}