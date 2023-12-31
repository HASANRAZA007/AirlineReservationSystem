package com.ars.airlinereservationsystem.controller;

import com.ars.airlinereservationsystem.dto.SignInDTO;
import com.ars.airlinereservationsystem.dto.SignUpDTO;
import com.ars.airlinereservationsystem.dto.UserDTO;
import com.ars.airlinereservationsystem.exception.CustomControllerException;
import com.ars.airlinereservationsystem.security.auth.AuthenticationResponse;
import com.ars.airlinereservationsystem.security.auth.AuthenticationService;
import com.ars.airlinereservationsystem.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admins")
public class AdminController {
    private final UserServiceImpl adminService;
    private final AuthenticationService authenticationService;

    @Autowired
    AdminController(UserServiceImpl adminService,AuthenticationService authenticationService){
        this.adminService=adminService;
        this.authenticationService=authenticationService;
    }

    @PostMapping
    public ResponseEntity<AuthenticationResponse> register(@RequestBody SignUpDTO request){
        try {
            return ResponseEntity.ok(authenticationService.register(request));
        }
        catch (CustomControllerException e){
            throw  new CustomControllerException(403,"Forbidden Request");
        }
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody SignInDTO request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> userDTOList=adminService.getAllAdminUsers();
        return new ResponseEntity<List<UserDTO>>(userDTOList,HttpStatus.FOUND);
    }
    @DeleteMapping("/{email}{password}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable String email, @PathVariable String password){
        UserDTO userDTO=adminService.deleteUser(email,password);
        return new ResponseEntity<UserDTO>(userDTO,HttpStatus.ACCEPTED);
    }
    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO){
        UserDTO userDTO1=adminService.updateUser(userDTO);
        return new ResponseEntity<UserDTO>(userDTO1,HttpStatus.ACCEPTED);
    }
    @GetMapping("/{email}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String email){
        UserDTO userDTO=adminService.getUser(email);
        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.FOUND);
    }
}
