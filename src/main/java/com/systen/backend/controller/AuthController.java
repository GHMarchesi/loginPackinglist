package com.systen.backend.controller;

import com.systen.backend.model.Usuario;
import com.systen.backend.security.JwtRequest;
import com.systen.backend.security.JwtResponse;
import com.systen.backend.security.JwtTokenUtil;
import com.systen.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        logger.debug("Attempting to authenticate user: {}", authenticationRequest.getLogin());

        authenticate(authenticationRequest.getLogin(), authenticationRequest.getSenha());

        final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getLogin());
        final String token = jwtTokenUtil.generateToken(userDetails);

        Usuario usuario = userService.findByLogin(authenticationRequest.getLogin());
        logger.debug("User authenticated successfully: {}", usuario.getLogin());

        return ResponseEntity.ok(new JwtResponse(token, usuario.getId(), usuario.getLogin(), usuario.getNome()));
    }

    private void authenticate(String login, String senha) throws Exception {
        try {
            Usuario usuario = userService.findByLogin(login);
            if (usuario == null || !passwordEncoder.matches(senha, usuario.getSenha())) {
                logger.error("Invalid login or password for user: {}", login);
                throw new UsernameNotFoundException("Invalid login or password");
            }
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, senha));
        } catch (UsernameNotFoundException e) {
            logger.error("User not found: {}", login, e);
            throw new Exception("USER_NOT_FOUND", e);
        }
    }
}
