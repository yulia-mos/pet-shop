package edu.kpi.iasa.mmsa.petshop.controller;

import edu.kpi.iasa.mmsa.petshop.configuration.security.JwtProcessor;
import edu.kpi.iasa.mmsa.petshop.dto.JwtRequestDto;
import edu.kpi.iasa.mmsa.petshop.dto.JwtResponseDto;
import edu.kpi.iasa.mmsa.petshop.service.AccountService;
import edu.kpi.iasa.mmsa.petshop.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;
@RequiredArgsConstructor
@Controller
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtProcessor jwtProcessor;
    private final UserDetailsService userDetailsService;
    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;


    @PostMapping(value="/signin")
    public ResponseEntity<JwtResponseDto> signIn(@RequestBody JwtRequestDto jwtRequestDto) {
        String username = jwtRequestDto.getUsername();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, jwtRequestDto.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String token = jwtProcessor.createJwt(username,
                (Collection<GrantedAuthority>) userDetails.getAuthorities());
        return ResponseEntity.ok(new JwtResponseDto(token));
    }
}
