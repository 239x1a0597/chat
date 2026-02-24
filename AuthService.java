package com.example.chat.Service;

import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

public class AuthenticationService {
        private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final SecurityAutoConfiguration.authentication.AuthenticationManager authenticationManager;

    public AuthenticatonResponse register(User request) {
        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(user);
        String token = jwtUtils.generateToken(user);
        return new AuthenticationResponse(token, user.getName());
    }

public AuthenticationResponse login(User request) {
        authenticationManager.authenticate(
                new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        String token = jwtUtils.generateToken(user);
        return new AuthenticationResponse(token, user.getName());
    }


}
