package com.example.SpringSecurity.service;

import com.example.SpringSecurity.dto.AuthRequest;
import com.example.SpringSecurity.dto.UserRequestDto;
import com.example.SpringSecurity.entity.Role;
import com.example.SpringSecurity.entity.User;
import com.example.SpringSecurity.repository.RoleRepo;
import com.example.SpringSecurity.repository.UserRepo;
import com.example.SpringSecurity.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.beans.Encoder;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserService customUserService;
    @Autowired
    private JwtUtil jwtUtil;


    public User register(UserRequestDto userRequestDto) {
        User user = new User();
        user.setUsername(userRequestDto.getUsername());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
//        user.setPassword(userRequestDto.getPassword());
        Set<String> roles = userRequestDto.getRoles();
        Set<Role> roleSet=roles.stream().map(role->roleRepo.findByName(role).orElseThrow(()->new UsernameNotFoundException("Role Not found")))
                        .collect(Collectors.toSet());
        user.setRoles(roleSet);
        userRepo.save(user);
        return user;
    }
    public String authenticate(AuthRequest authRequest)
    {
        System.out.println("in auth service");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword())
        );
        UserDetails userDetails = customUserService.loadUserByUsername(authRequest.getUsername());
        return jwtUtil.generateToken(userDetails);
    }

    public List<User> getAll(){
        return userRepo.findAll();
    }

}
