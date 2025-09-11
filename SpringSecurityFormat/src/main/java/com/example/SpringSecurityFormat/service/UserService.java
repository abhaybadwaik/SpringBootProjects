package com.example.SpringSecurityFormat.service;

import com.example.SpringSecurityFormat.dto.AuthRequest;
import com.example.SpringSecurityFormat.dto.UserRequestDto;
import com.example.SpringSecurityFormat.entity.Role;
import com.example.SpringSecurityFormat.entity.User;
import com.example.SpringSecurityFormat.repository.RoleRepo;
import com.example.SpringSecurityFormat.repository.UserRepo;
import com.example.SpringSecurityFormat.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserService customUserService;

    public User register(UserRequestDto userRequestDto)
    {
        User user=new User();
        user.setUsername(userRequestDto.getUsername());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));

        Set<String> roles = userRequestDto.getRoles();
        Set<Role> roleSet = roles.stream().map(role -> roleRepo.findByName(role).
                        orElseThrow(() -> new UsernameNotFoundException("Role not found")))
                .collect(Collectors.toSet());
        user.setRoles(roleSet);
        userRepo.save(user);
        return user;
    }

    public String authenticate(AuthRequest authRequest)
    {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword())
        );
        UserDetails userDetails = customUserService.loadUserByUsername(authRequest.getUsername());
        return jwtUtil.generateToken(userDetails);

    }

    public List<User> getAll()
    {
        return userRepo.findAll();
    }
}
