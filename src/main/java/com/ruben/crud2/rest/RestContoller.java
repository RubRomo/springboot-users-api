package com.ruben.crud2.rest;

import com.ruben.crud2.dto.IUserMapper;
import com.ruben.crud2.dto.UserDTO;
import com.ruben.crud2.model.AuthenticationReq;
import com.ruben.crud2.model.TokenInfo;
import com.ruben.crud2.model.User;
import com.ruben.crud2.repo.IUserRepository;
import com.ruben.crud2.service.JwtUtilService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class RestContoller {

    @Autowired
    UserDetailsService usuarioDetailsService;

    @Autowired
    private IUserRepository repo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtilService jwtUtilService;

    @Autowired
    PasswordEncoder encoder;

    private static final Logger logger = LoggerFactory.getLogger(RestContoller.class);

    @GetMapping
    public ResponseEntity getUsers(){
        List<User> response = repo.findAll();

        List<UserDTO> userDTOS = response.stream().map(
                user -> IUserMapper.mapper.userToUserDTO(user)).collect(Collectors.toList());

        var auth =  SecurityContextHolder.getContext().getAuthentication();

        logger.info("Datos del Usuario: {}", auth.getPrincipal());
        logger.info("Datos de los Permisos {}", auth.getAuthorities());
        logger.info("Esta autenticado {}", auth.isAuthenticated());

        return new ResponseEntity(userDTOS,HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity newUser(@RequestBody User u){
        u.setPassword(encoder.encode(u.getPassword()));
        User response = repo.save(u);
        UserDTO userDTO = IUserMapper.mapper.userToUserDTO(response);
        return new ResponseEntity(userDTO,HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updateUser(@RequestBody User u){

        User response = repo.save(u);
        UserDTO userDTO = IUserMapper.mapper.userToUserDTO(response);
        return new ResponseEntity(userDTO,HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable("id") Integer userID){
        repo.deleteById(userID);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationReq authenticationReq) {

        logger.info("Auth for user: {}", authenticationReq.getUsuario());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationReq.getUsuario(),
                        authenticationReq.getClave()));

        final UserDetails userDetails = usuarioDetailsService.loadUserByUsername(
                authenticationReq.getUsuario());

        final String jwt = jwtUtilService.generateToken(userDetails);
        
        return ResponseEntity.ok(new TokenInfo(jwt));
    }

}

